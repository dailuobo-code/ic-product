package com.mallcai.bs.service;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ResponseCodeEnum;
import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.soa.city.CityProductDto;
import com.dailuobo.api.domain.util.LoggerUtils;
import com.dailuobo.api.domain.vo.*;
import com.dailuobo.api.enums.HqProductExtraAttrAction;
import com.dailuobo.biz.exception.BusinessException;
import com.dailuobo.biz.manager.product.ProductCacheManager;
import com.dailuobo.biz.manager.product.ProductManager;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.enums.AuditStatus;
import com.dailuobo.ic.api.request.product.HqProductLocalizeRequest;
import com.dailuobo.ic.api.request.product.HqProductSyncExtraAttrRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.dailuobo.ic.api.util.CacheKeyGenerator;
import com.dailuobo.ic.service.inner.AfterCommitExecutor;
import com.google.common.collect.Lists;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.cache.mongo.utils.BeanMapUtils;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.bs.dao.*;
import com.mallcai.bs.domain.ProductExtraAttr;
import com.mallcai.domain.product.request.LocalizeProductRequest;
import com.mallcai.service.enums.ProductDeliveryModeEnum;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import com.mallcai.service.openapi.IProductService;
import com.mallcai.service.request.product.ProductExtraAttrPutRequest;
import com.mallcai.service.vo.attr.ProductDeliveryHomeAttr;
import com.mallcai.service.vo.ic.common.SOAProductExtraAttr;
import dailuobo.dao.mapper.ProductGoodsRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CityProductService {

    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private SpecDao specDao;

    @Autowired
    private UnSpecDao unSpecdao;

    @Autowired
    private ClassifyDao classifyDao;

    @Autowired
    private ProductCacheManager productCacheManager;

    @Autowired
    private HqProductService hqProductService;

    @Autowired
    private SOAHPService soaHPService;

    @Autowired
    private MultiBargainService multiBargainService;

    @Autowired
    private SOACityGlobalService soaCityGlobalService;

    @Autowired
    private SOAHPService soaHpService;

    @Autowired
    private HqProductDao hqProductDao;

    @Autowired
    private IProductService iProductService;

    /** 货品关联仓储服务 **/
    @Autowired
    private ProductGoodsRelMapper productGoodsRelMapper;

    private String approvalStatus = AuditStatus.APPROVAL.name();

    @Autowired
    private ProductExtraAttrDao productExtraAttrDao;

    @Autowired
    com.mallcai.bs.mapper.SOAHPMapper SOAHPMapper;

    @Autowired
    private MgrProductService mgrProductService;

    @Autowired
    private AfterCommitExecutor afterCommitExecutor;

    @Autowired
    private ProductManager productManager;

    @Transactional
    public List<CityProduct> selectHqAll(Map<String, Object> param) {
        return cityProductDao.selectHqAll(param);
    }

    @Transactional
    public void localize(Map<String, Object> param) throws BusinessException {
        List<Integer> hqProductIds = (List<Integer>) param.getOrDefault("hqProductIds", null);
        if (CollectionUtils.isNotEmpty(hqProductIds)) {
            List<HqProduct> products = hqProductDao.selectHqProductByIds(hqProductIds);
            for (HqProduct product : products) {
                if (!approvalStatus.equals(product.getAuditStatus())) {
                    throw new BusinessException(ResponseCodeEnum.FAIL, String.format("商品【%s】审批不通过，不可本市化", product.getHqProductName()));
                }
            }
        }
        cityProductDao.localize(param);
    }

    @Transactional
    public void localizeV2(HqProductLocalizeRequest localizeRequest) throws BusinessException {
        log.info("总部商品本地化请求参数,{}", JSON.toJSONString(localizeRequest));
        List<Integer> hqProductIds = localizeRequest.getHqProducts().stream()
                .map(HqProductLocalizeRequest.HqProductDTO::getHqProductId)
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(hqProductIds)) {
            List<HqProduct> products = hqProductDao.selectHqProductByIds(hqProductIds);
            for (HqProduct product : products) {
                if (!approvalStatus.equals(product.getAuditStatus())) {
                    throw new BusinessException(
                            ResponseCodeEnum.FAIL,
                            String.format("localizeV2 商品【%s】审批不通过，不可本市化", product.getHqProductName())
                    );
                }
            }
        }

        List<HqProductLocalize> hqProductLocalizes = cityProductDao.localizeNew(localizeRequest);

        List<Integer> productIds = hqProductLocalizes.stream().map(HqProductLocalize::getHqProductId).collect(Collectors.toList());

        LocalizeProductRequest request = new LocalizeProductRequest();
        request.setProductIds(productIds);
        request.setUserId(localizeRequest.getUserId());
        afterCommitExecutor.executeSync(() -> mgrProductService.localizeProduct(request));

        Map<String, HqProductLocalize> productLocalizeMap = hqProductLocalizes.stream().collect(Collectors.toMap(it -> {
            return it.getHqProductId() + "_" + it.getCityId();
        }, Function.identity()));

        // 总部发货时间商品属性赋值
        List<HqProductLocalizeRequest.HqProductDTO> hqProductDTOList =
                localizeRequest.getHqProducts().stream().
                        filter(hqProductDTO -> Objects.nonNull(hqProductDTO.getAttrDTO()) && hqProductDTO.getAttrDTO().getArrivalDay() != null)
                        .collect(Collectors.toList());


        hqProductDTOList.forEach(it -> {
            HqProductLocalize hqProductLocalize = productLocalizeMap.get(it.getHqProductId() + "_" + it.getCityId());
            ProductExtraAttrPutRequest<ProductDeliveryHomeAttr> attrPutRequest = new ProductExtraAttrPutRequest<>();
            attrPutRequest.setAttrType(ProductExtraAttrTypeEnum.DELIVERY_HOME);
            attrPutRequest.setUserId(hqProductLocalize.getUserId());
            attrPutRequest.setCityProductId(hqProductLocalize.getCityProductId());

            ProductDeliveryHomeAttr deliveryHomeAttr = new ProductDeliveryHomeAttr();
            deliveryHomeAttr.setArrivalDay(it.getAttrDTO().getArrivalDay());
            deliveryHomeAttr.setMerchantName("test");

            attrPutRequest.setAttr(deliveryHomeAttr);
            log.info("本地化赋值商品属性>>{}", JSON.toJSONString(attrPutRequest));
            productManager.putProductExtraAttr(attrPutRequest);
        });

    }

    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @Transactional
    public List<CityProduct> selectAll(Map<String, Object> param) {

        List<CityProduct> cityProductList = cityProductDao.selectAll(param);

        if (param.get("cityId") != null) {
            Integer cityId = (Integer) param.get("cityId");
            //多规格商品查询
            productManager.fillProductAttribute(cityId, cityProductList);
        }


        return cityProductList;
    }

    @Transactional
    public List<CityProduct> selectStcokProduct(Map<String, Object> param) {
        return cityProductDao.selectStcokProduct(param);
    }

    @Transactional
    public void update(CityProduct cityProduct) {
        cityProductDao.update(cityProduct);
    }


    @Transactional
    public void up(CityProduct cityProduct, Spec spec) throws BusinessException {

        // 上架校验在mgr

        // 城市商品上架
        cityProductDao.up(cityProduct.getCityProductId(), cityProduct.getDisablePrice(), cityProduct.getUpdateUserId());
        saveSpec(spec);
    }

    public boolean preDown(Integer cityId, Integer cityProductId) {
        //获取产销预测商品列表
        List<Integer> productIds = productCacheManager.getTodayCityForecastProductIds(cityId);
        if (CollectionUtils.isEmpty(productIds)) {
            return true;
        }

        if (productIds.contains(cityProductId) && forecastTime()) {
            return false;
        }
        return true;
    }

    public List<Integer> getTodayCityForecastProductIds(Integer cityId) {
        return productCacheManager.getTodayCityForecastProductIds(cityId);
    }

    private boolean forecastTime() {
        //TODO nacos config
        return true;
    }

    @Transactional
    public void down(Integer cityId, Integer cityProductId, Integer userId, boolean force) throws BusinessException {
        //检查是否产销预测商品
        if (!force) {
            boolean available = preDown(cityId, cityProductId);
            if (!available) {
                throw new BusinessException(ResponseCodeEnum.PRODUCT_UNSHELVE_FORECAST_FAIL);
            }
        }
        cityProductDao.down(userId, cityProductId);
    }

    @Transactional
    public List<String> getShowNames(Map<String, Object> param) {
        return cityProductDao.getShowNames(param);
    }

    @Transactional
    public List<DDLCityProduct> getDDLCityProducts(Map<String, Object> param) {
        return cityProductDao.getDDLCityProducts(param);
    }

    @Transactional
    public List<TACityProduct> getTACityProducts(Integer cityId) {
        return cityProductDao.getTACityProducts(cityId);
    }

    @Transactional
    public List<TACityProduct> getTACityProductsV2(Integer cityId, String keyword) {
        return cityProductDao.getTACityProductsV2(cityId, keyword);
    }

    public List<TACityProduct> getTACityProductsV3(Integer cityId, String keyword) {
        return cityProductDao.getTACityProductsV3(cityId, keyword);
    }

    public List<TACityProduct> getTACityProductsByKeyWord(TACityProductQueryDto queryDto) {
        return cityProductDao.getTACityProductsByKeyWord(queryDto);
    }

    @Transactional
    public List<TACityProduct> getOfflineTACityProductsV2(Integer cityId, String keyword) {
        return cityProductDao.getOfflineTACityProductsV2(cityId, keyword);
    }

    @Transactional
    public List<TACityProduct> getTACityProductsGift(Integer cityId) {
        return cityProductDao.getTACityProductsGift(cityId);
    }

    public void newUserPro(CityProductVo cityProductVo) {
        cityProductDao.newUserPro(cityProductVo);
    }

    public void isUnder(CityProductVo cityProductVo) {
        // TODO Auto-generated method stub
        cityProductDao.isUnder(cityProductVo);
    }

    @Transactional
    public void underProduct(CityProduct cityProduct, UnSpec unSpec) {
        // TODO Auto-generated method stub
        cityProductDao.underProduct(cityProduct);
        if (unSpec.getUndSpecId() != null) {
            unSpecdao.update(unSpec);
        } else {
            unSpecdao.create(unSpec);
        }
    }

    @Transactional
    public List<TACityProduct> getMsgList() {
        return cityProductDao.getMsgList();
    }

    public void vipLimit(CityProductVo cityProductVo, boolean isMultiBargain) {
        // TODO Auto-generated method stub
        Map<String, Object> param = new HashMap<>();
        param.put("cityProduct", cityProductVo);
        param.put("isMultiBargain", isMultiBargain);
        cityProductDao.vipLimit(param);
    }

    //获取已经开通城市
    public List<OpenCity> getOpenCity() {
        return cityProductDao.getOpenCity();
    }

    //根据CityId获取MsgList
    @Transactional
    public List<TACityProduct> getMsgListByCityId(OpenCity openCity) {
        return cityProductDao.getMsgListByCityId(openCity);
    }

    //查询指定商品no对应的城市商品列表
    public List<CityProduct> selectCityProductNosList(Map<String, Object> param) {
        return cityProductDao.selectCityProductNosList(param);
    }


    public Integer getCountProductByProductNo(Map param) {
        return cityProductDao.getCountProductByProductNo(param);
    }

    @Transactional
    public void cityProductCopyOtherCity(CityProductSyncCity cityProductSyncCity) {
        log.info("cityProductCopyOtherCity 发货时间属性赋值 {},", JSON.toJSONString(cityProductSyncCity));
        Integer oldCityProductId = cityProductSyncCity.getCityProductId();
        cityProductDao.cityProductCopyOtherCity(cityProductSyncCity);
        Integer cityProductId = cityProductSyncCity.getCityProductId();

        // 判断商品是否设置发货时间属性
        if (Objects.equals(ProductDeliveryModeEnum.DELIVERY_HOME.getCode(), Integer.valueOf(cityProductSyncCity.getDeliveryMode()))
                && cityProductSyncCity.getArrivalDay() != null) {
            log.info("cityProductCopyOtherCity 发货时间属性赋值 {},{}", oldCityProductId, cityProductId);
            copyProductSyncExtra(cityProductSyncCity);
        }
    }

    /**
     * @param param
     * @return
     */
    public CityProductSyncCity getCityProductById(Map param) {
        CityProductSyncCity cityProductSyncCity = cityProductDao.getCityProductById(param);
        if (Objects.equals(ProductDeliveryModeEnum.DELIVERY_HOME.getCode(), Integer.valueOf(cityProductSyncCity.getDeliveryMode()))) {
            Integer cityProductId = (Integer) param.get("cityProductId");
            Map<Integer, ProductDeliveryHomeAttr> productExtraAttrs = getProductExtraAttrs(Lists.newArrayList(cityProductId), ProductDeliveryHomeAttr.class);
            Integer arrivalDay = Optional.ofNullable(productExtraAttrs.get(cityProductId)).map(ProductDeliveryHomeAttr::getArrivalDay).orElse(null);
            cityProductSyncCity.setArrivalDay(arrivalDay);
        }
        return cityProductSyncCity;
    }

    public void updateProductByUpdateCityIds(CityProductSyncCity cityProduct) {
        log.info("updateProductByUpdateCityIds,发货时间属性赋值,{}", JSON.toJSONString(cityProduct));
        cityProductDao.updateProductByUpdateCityIds(cityProduct);
        // 判断商品是否设置发货时间属性
        if (Objects.equals(ProductDeliveryModeEnum.DELIVERY_HOME.getCode(), Integer.valueOf(cityProduct.getDeliveryMode()))
                && cityProduct.getArrivalDay() != null) {
            CityProductSyncCity cityProductSyncCity = cityProductDao.getByCityIdAndProductNo(cityProduct.getCityId(), cityProduct.getProductNoNew());
            cityProductSyncCity.setArrivalDay(cityProduct.getArrivalDay());
            copyProductSyncExtra(cityProductSyncCity);
        }
    }

    private void copyProductSyncExtra(CityProductSyncCity cityProduct) {
        ProductExtraAttrPutRequest attrPutRequest = new ProductExtraAttrPutRequest();
        attrPutRequest.setAttrType(ProductExtraAttrTypeEnum.DELIVERY_HOME);
        attrPutRequest.setCityProductId(cityProduct.getCityProductId());
        attrPutRequest.setUserId(cityProduct.getUserId());
        ProductDeliveryHomeAttr deliveryHomeAttr = new ProductDeliveryHomeAttr();
        deliveryHomeAttr.setArrivalDay(cityProduct.getArrivalDay());
        deliveryHomeAttr.setMerchantName("test");
        attrPutRequest.setAttr(deliveryHomeAttr);
        productManager.putProductExtraAttr(attrPutRequest);
    }


    public void pointProduct(Map<String, Object> param) {
        cityProductDao.pointProduct(param);
    }

    public void isPoint(CityProductVo cityProductVo) {
        cityProductDao.isPoint(cityProductVo);
    }

    public StandardResult checkStandardProduct(CityProductVo cityProductVo) {
        try {
            Integer flag = cityProductDao.checkStandardProduct(cityProductVo);
            if (flag != null && flag.intValue() == 1) {
                return StandardResult.getDefaultSuccess();
            }
            return StandardResult.getDefaultFailedMsg("积分商城只支持是标品，该商品不是标品！");

        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }

    public void updateL1L2Names(Integer classifyId, String classifyName) {
        ProductClassify productClassify = classifyDao.getById(classifyId);
        if (productClassify == null) {
            return;
        }
        Map<Integer, String> secondClassifysMap = new HashMap<>();
        if (productClassify.getFatherId() == 0) {
            //二级分类
            List<ProductClassify> secondClassifys = classifyDao.getProductClassifySecond(classifyId);
            if (secondClassifys.size() == 0) {
                return;
            }
            secondClassifys.forEach(secondClassify ->
                    secondClassifysMap.put(secondClassify.getClassifyId(), secondClassify.getClassifyName()));
        } else {
            //一级分类
            productClassify = classifyDao.getById(productClassify.getFatherId());
            //二级分类
            secondClassifysMap.put(classifyId, classifyName);
        }
        List<Integer> allHpProductIds = new ArrayList<>();
        //查询受影响的hpProductId
        for (Map.Entry<Integer, String> entry : secondClassifysMap.entrySet()) {
            List<HqProduct> hqProducts = hqProductService.findByClassifyId(entry.getKey());
            if (hqProducts.size() == 0) {
                continue;
            }
            //更新
            String newL1L2Names = productClassify.getClassifyName() + '_' + entry.getValue();
            List<Integer> hpProductIds = hqProducts.stream().map(hqProduct -> hqProduct.getHqProductId()).collect(Collectors.toList());
            cityProductDao.updateL1L2NamesByHqProductIds(newL1L2Names, hpProductIds);
            allHpProductIds.addAll(hpProductIds);
        }
        //删除redis
        if (allHpProductIds.size() > 0) {
            List<CityProduct> cityProducts = cityProductDao.findByHqProductIds(allHpProductIds);
            cityProducts.forEach(cityProduct -> {
                if (cityProduct.getIsUnder() != null && cityProduct.getIsUnder() == 1) {
                    //TODO 清除线下
//                    RedisKeyGenerator.generateUnCityProductKey(cityProduct.getCityId(),cityProduct.get);
                } else {
                    String cityProductKey = RedisKeyGenerator.generateCityProductKey(cityProduct.getCityId(), cityProduct.getCityProductId());
                    JedisProxy.getInstance().delKey(cityProductKey);
                }
            });

        }
    }

    public void batchDown(Integer userId, Integer cityId, List<Integer> cityProductIdList) {

        // 修改数据状态
        cityProductDao.batchDown(userId, cityProductIdList);

        // 清空一分购
        String day = new java.sql.Date(new Date().getTime()).toString();
        String dayBegin = day + " 00:00:00";
        String dayEnd = day + " 23:59:59";

        Map<String, Object> param = new HashMap<>();
        param.put("from", dayBegin);
        param.put("to", dayEnd);
        param.put("cityProductIds", cityProductIdList);
        // 当天执行的定时调价任务
        List<ActProductSign> actProductSigns = multiBargainService.selectByEffectTime(param);
        List<Integer> productSignTaskIds = new ArrayList<>();
        for (ActProductSign actProductSign : actProductSigns) {
            String signVal = actProductSign.getSignVal();
            if (StringUtils.isNotEmpty(signVal)) {
                String[] split = signVal.split("-");
                if (split.length == 2 && "1".equals(split[0])) {
                    productSignTaskIds.add(actProductSign.getMultiBargainTaskId());
                }
            }
        }
        if (productSignTaskIds.size() > 0) {
            // 删除一分购的商品
            multiBargainService.deleteProductSignByTaskIds(productSignTaskIds);
        }

        //刷新缓存
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    LoggerUtils.getLogger().info("/prdmgtByCity/city/batchDown,线程开始刷新Redis,城市：{},商品{}", cityId, cityProductIdList);
                    cityProductIdList.forEach(cid -> soaHPService.shelveProduct(cityId, cid));
                } catch (Exception e) {
                    LoggerUtils.getLogger().info("/prdmgtByCity/city/batchDown,线程刷新Redis异常,城市：{},商品{}", cityId, cityProductIdList);
                    singleThreadExecutor.shutdown();
                } finally {
                    singleThreadExecutor.shutdown();
                    LoggerUtils.getLogger().info("/prdmgtByCity/city/batchDown,线程结束刷新Redis,线程状态：{}", singleThreadExecutor.isTerminated());
                }
            }
        });


        // 删除 积分商城redis
        JedisProxy proxy = JedisProxy.getInstance();
        String pointProductKey = RedisKeyGenerator.generatePointProductKey(cityId);
        proxy.delKey(pointProductKey);
    }

    public List<CityProduct> selectCityProductListByProductNos(Integer cityId, List<String> productNos) {
        return cityProductDao.selectCityProductListByProductNos(cityId, productNos);
    }


    public void saveImportProductUpLog(List<ImportGroundingProduct> list, Integer createuser) {
        Map param = new HashMap();
        param.put("list", list);
        param.put("createuser", createuser);
        cityProductDao.saveImportProductUpLog(param);
    }

    /**
     * 根据cityId 与 productNo 获取本市商品
     *
     * @param cityId    城市id
     * @param productNo 商品编号
     * @return
     */
    public CityProductSyncCity getByCityIdAndProductNo(Integer cityId, String productNo) {
        return cityProductDao.getByCityIdAndProductNo(cityId, productNo);
    }


    public List<CityProductVo> getCityProductList(ProductListQueryRequest request) {
        return cityProductDao.getCityProductList(request);
    }

    public CityProductVo loadCityProduct(SingleCityProductQueryRequest singleCityProductQueryRequest) {
        return cityProductDao.loadCityProduct(singleCityProductQueryRequest);
    }


    public Boolean productBatchOnShelf(Integer cityId, List<Integer> cityProductIds, Integer userId) {
        List<String> redisToDel = cityProductIds.stream()
                .map(e -> {
                    return RedisKeyGenerator.generateCityProductKey(cityId, e);
                })
                .collect(Collectors.toList());
        try {
            cityProductDao.productBatchOnShelf(cityProductIds, userId);

            JedisProxy.getInstance().delMultiKey(redisToDel.toArray(new String[]{}));
        } catch (Exception e) {
            log.error("批量上架商品失败，msg: {}", e.getMessage());
            return Boolean.FALSE;
        }

        soaHpService.delProductClassify(cityId, cityProductIds);

        return Boolean.TRUE;
    }

    public Boolean productBatchOffShelf(Integer cityId, List<Integer> cityProductIds, Integer userId) {
        List<String> redisToDel = cityProductIds.stream()
                .map(e -> {
                    return RedisKeyGenerator.generateCityProductKey(cityId, e);
                })
                .collect(Collectors.toList());
        try {
            cityProductDao.productBatchOffShelf(cityProductIds, userId);
            JedisProxy.getInstance().delMultiKey(redisToDel.toArray(new String[]{}));
        } catch (Exception e) {
            log.error("批量下架商品失败，msg: {}", e.getMessage());
            return Boolean.FALSE;
        }

        /* 商品当天一分购状态，与产品和运营确认，不做清理操作，
           如果当天不上架，第二天凌晨定时任务会清理（一分购状态时间上也会自动失效，
           如果当天还会上架，一分购的状态是要的，
        */

        soaHpService.delProductClassify(cityId, cityProductIds);

        // 删除 积分商城redis
        String pointProductKey = RedisKeyGenerator.generatePointProductKey(cityId);
        JedisProxy.getInstance().delKey(pointProductKey);

        return Boolean.TRUE;
    }

    public void syncHqProductExtraAttr(HqProductSyncExtraAttrRequest attrRequest) {
        log.info("同步总部商品属性>>{}", JSON.toJSONString(attrRequest));
        List<CityProductDto> cityProductDtos = SOAHPMapper.getProductByCityHqProductId(attrRequest.getHqProduct().getHqProductId());

        Integer deliverMode = ProductDeliveryModeEnum.DELIVERY_HOME.getCode();
        if (attrRequest.getAttrAction().equals(HqProductExtraAttrAction.DELETE)) {
            deliverMode = ProductDeliveryModeEnum.STORE_PICK.getCode();
        }
        CityProduct cityProduct = new CityProduct();
        cityProduct.setHqProductId(attrRequest.getHqProduct().getHqProductId());
        cityProduct.setDeliveryMode(deliverMode.byteValue());
        cityProductDao.updateByHqProductId(cityProduct);

        cityProductDtos.forEach(product -> {
            ProductExtraAttrPutRequest attrPutRequest = new ProductExtraAttrPutRequest();
            attrPutRequest.setAttrType(ProductExtraAttrTypeEnum.DELIVERY_HOME);
            attrPutRequest.setCityProductId(product.getCityProductId());
            attrPutRequest.setUserId(attrRequest.getUserId());
            if (!attrRequest.getAttrAction().equals(HqProductExtraAttrAction.DELETE)) {
                ProductDeliveryHomeAttr deliveryHomeAttr = new ProductDeliveryHomeAttr();
                deliveryHomeAttr.setArrivalDay(attrRequest.getHqProduct().getAttrDTO().getArrivalDay());
                deliveryHomeAttr.setMerchantName("test");
                attrPutRequest.setAttr(deliveryHomeAttr);
            }
            productManager.putProductExtraAttr(attrPutRequest);
        });

    }

    public Map<Integer, List<SOAProductExtraAttr>> getProductExtraAttrs(List<Integer> cityProductIds) {

        List<ProductExtraAttr> productExtraAttrs = productExtraAttrDao.selectListByCityProductIds(cityProductIds, null);

        Map<Integer, List<ProductExtraAttr>> groupMap = productExtraAttrs.stream()
                .collect(Collectors.groupingBy(ProductExtraAttr::getCityProductId, Collectors.toList()));


        Map<Integer, List<SOAProductExtraAttr>> extraMap = new HashMap<>(cityProductIds.size());
        cityProductIds.forEach(it -> {
            List<ProductExtraAttr> productExtraAttrs1 = groupMap.get(it);
            if (CollectionUtils.isNotEmpty(productExtraAttrs1)) {
                Map<ProductExtraAttrTypeEnum, List<ProductExtraAttr>> collect = productExtraAttrs1.stream().collect(Collectors.groupingBy(ProductExtraAttr::getAttrType, Collectors.toList()));
                List<SOAProductExtraAttr> hqProductExtraAttrDTOS = Lists.newArrayList();
                collect.forEach((key, value) -> {
                    SOAProductExtraAttr extraAttrDTO = null;
                    Map<String, Object> productExtraAttrMap = value.stream().collect(Collectors.toMap(ProductExtraAttr::getAttrName, ProductExtraAttr::getAttrValue));
                    try {
                        Class<?> aClass = Class.forName(key.getCode());
                        extraAttrDTO = (SOAProductExtraAttr) aClass.newInstance();
                    } catch (Exception e) {

                    }

                    BeanMapUtils.transMap2Bean(productExtraAttrMap, extraAttrDTO);
                    hqProductExtraAttrDTOS.add(extraAttrDTO);
                });

                extraMap.put(it, hqProductExtraAttrDTOS);
            } else {
                extraMap.put(it, Lists.newArrayList());
            }
        });

        return extraMap;
    }

    public <T extends SOAProductExtraAttr> Map<Integer, T> getProductExtraAttrs(List<Integer> cityProductIds, Class<T> clazz) {
        if (CollectionUtils.isEmpty(cityProductIds)) {
            return Collections.emptyMap();
        }

        Map<Integer, List<SOAProductExtraAttr>> extraMap = getProductExtraAttrs(cityProductIds);

        Map<Integer, T> retMap = new HashMap<>(cityProductIds.size());

        for (Map.Entry<Integer, List<SOAProductExtraAttr>> entry : extraMap.entrySet()) {
            Integer cityProductId = entry.getKey();
            List<SOAProductExtraAttr> extraList = entry.getValue();
            if (CollectionUtils.isNotEmpty(extraList)) {
                for (SOAProductExtraAttr extra : extraList) {
                    if (Objects.equals(extra.getClass(), clazz)) {
                        retMap.put(cityProductId, (T) extra);
                    }
                }
            }
        }
        return retMap;
    }

    public void saveSpec(Spec spec) {
        if (spec.getSpecId() != null) {
            // 规格更新
            specDao.update(spec);
        } else {
            // 规格创建
            specDao.create(spec);
        }
    }
}
