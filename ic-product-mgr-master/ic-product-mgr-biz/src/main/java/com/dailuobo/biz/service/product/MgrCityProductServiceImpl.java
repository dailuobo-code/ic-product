package com.dailuobo.biz.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.util.Constant;
import com.dailuobo.api.domain.util.ExcelData;
import com.dailuobo.api.domain.vo.*;
import com.dailuobo.api.enums.CityStatusEnum;
import com.dailuobo.api.enums.ForecastFlagEnum;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.biz.manager.product.ProductManager;
import com.dailuobo.biz.service.ICResponseHandler;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.request.GetCityProductListRequest;
import com.dailuobo.ic.api.request.ProductForecastFlagChangeRequest;
import com.dailuobo.ic.api.request.ProductUnShelveRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.dailuobo.ic.domain.dao.model.product.ProductGroupItemDO;
import com.dailuobo.ic.dto.spec.ProductAttributeDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.dao.HqProductDao;
import com.mallcai.bs.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lgh
 */
@Slf4j
@Service("mgrCityProductService")
public class MgrCityProductServiceImpl implements MgrCityProductService {

    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private CityProductService cityProductService;

    @Autowired
    private SpecService specService;

    @Autowired
    private SOACityGlobalService SOACityGlobalService;

    @Autowired
    private SOAMarketingInventoryService soaMarketingInventoryService;

    @Autowired
    private SOAHPService soaHPService;

    @Autowired
    private MultiBargainService multiBargainService;

    @Autowired
    private MarketingInventoryService marketingInventoryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private HqProductDao hqProductDao;



    /**
     * 城市商品批量查询
     *
     * @param listRequest
     * @return
     */
    @Override
    public ICResponse<List<CityProduct>> selectAll(GetCityProductListRequest listRequest) {
        Integer cityId = listRequest.getCityId();
        Integer classifyId = listRequest.getClassifyId();
        Integer parentClassifyId = listRequest.getParentClassifyId();
        String productNo = listRequest.getProductNo();
        String isUndSearch = listRequest.getIsUndSearch();
        String isNewSearch = listRequest.getIsNewSearch();
        String hqProductName = listRequest.getHqProductName();
        String showName = listRequest.getShowName();
        Integer currentStatus = listRequest.getCurrentStatus();
        Integer[] cityProductIds = listRequest.getCityProductIds();
        Byte deliveryMode = listRequest.getDeliveryMode();
        String isShare = listRequest.getIsShare();
        String cityStatus = listRequest.getCityStatus();
        Integer groupSearch = listRequest.getGroupSearch();
        Integer offset = listRequest.getOffset();
        Integer limit = listRequest.getLimit();
        Integer goodsTypeSearch = listRequest.getGoodsTypeSearch();
        String isPointSearch = listRequest.getIsPointSearch();
        List<Integer> merchantIdList = listRequest.getMerchantIdList();
        ForecastFlagEnum forecastFlagEnum = listRequest.getFlagEnum();
        Integer newArrivalType = listRequest.getNewArrivalType();
        Integer seasonal = listRequest.getSeasonal();
        Integer isGoodsRel = listRequest.getIsGoodsRel();

        Map<String, Object> param = new HashMap<>();
        if (cityId != null) {
            param.put("cityId", cityId);
        }
        if (classifyId != null) {
            param.put("classifyId", classifyId);
        }
        if (parentClassifyId != null) {
            param.put("parentClassifyId", parentClassifyId);
        }
        if (!StringUtils.isEmpty(productNo)) {
            param.put("productNo", productNo);
        }
        if (!StringUtils.isEmpty(isUndSearch)) {
            param.put("isUnd", isUndSearch);
        }
        if (!StringUtils.isEmpty(isNewSearch)) {
            param.put("isNew", isNewSearch);
        }
        if (!StringUtils.isEmpty(hqProductName)) {
            param.put("hqProductName", hqProductName);
        }
        if (!StringUtils.isEmpty(showName)) {
            param.put("showName", showName);
        }
        if (currentStatus != null) {
            param.put("currentStatus", currentStatus);
        }
        if (cityProductIds != null) {
            param.put("cityProductIds", Arrays.asList(cityProductIds));
        }
        if (deliveryMode != null) {
            param.put("deliveryMode", deliveryMode);
        }
        if (isShare != null && !isShare.equals("all")) {
            param.put("isShare", isShare);
        }
        if (cityStatus != null) {
            param.put("currentStatus", cityStatus);
        }
        if (groupSearch != null) {
            param.put("groupSearch", groupSearch);
        }
        if (offset != null) {
            PageHelper.startPage(offset / limit + 1, limit);
        }
        if (goodsTypeSearch != null) {
            param.put("goodsType", goodsTypeSearch);
        }
        if (!StringUtils.isEmpty(isPointSearch)) {
            param.put("isPointSearch", isPointSearch);
        }
        if (CollectionUtils.isNotEmpty(merchantIdList)) {
            param.put("merchantIdList", merchantIdList);
        }
        if (forecastFlagEnum != null) {
            param.put("isforeCast", forecastFlagEnum.getCode());
        }
        // add 商品新属性
        if (newArrivalType != null) {
            param.put("newArrivalType", newArrivalType);
        }
        if (seasonal != null) {
            param.put("seasonal", seasonal);
        }
        if (isGoodsRel != null) {
            param.put("isGoodsRel", isGoodsRel);
        }

        return ICResponseHandler.template(() -> {
            List<CityProduct> cityProducts = cityProductService.selectAll(param);
            // 兼容货品关联数据表中没有关联数据情形
            cityProducts.forEach(cityProduct -> {
                if (null == cityProduct.getIsGoodsRel()) {
                    cityProduct.setIsGoodsRel(0);
                }
            });


            PageInfo<CityProduct> pageInfo = new PageInfo<>(cityProducts);
            return ICResponse.success(cityProducts, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectAll", param);
    }



    private List<ProductAttributeDTO> productAttributeDTOS(Integer cityId, List<Integer> cityProductIdList) {
        List<ProductAttributeDTO> productAttributeDTOS = cityProductDao.listProductAttributes(cityId, cityProductIdList);
        return productAttributeDTOS;
    }

    /**
     * 商品属性更新
     *
     * @param cityProduct
     * @return
     */
    @Override
    public ICResponse<Boolean> update(CityProduct cityProduct) {
        log.info("com.dailuobo.biz.service.product.MgrCityProductServiceImpl.update:{}",JSON.toJSONString(cityProduct));
        return ICResponseHandler.template(() -> {
            cityProductService.update(cityProduct);
            return ICResponse.success(true);
        }, "update", cityProduct);
    }


    @Override
    public ICResponse<Boolean> updateProductV2(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            SOACityGlobalService.updateProductV2(cityProductId, null);
            return ICResponse.success(true);
        }, "updateProductV2", cityProductId);
    }

    @Override
    public ICResponse<Boolean> establishExcelNewToOneCity(Integer cityId) {
        return ICResponseHandler.template(() -> {
            cityService.establishExcelNewToOneCity(cityId);//更新商品信息更新电子秤表
            return ICResponse.success(true);
        }, "establishExcelNewToOneCity", cityId);

    }

    @Override
    public ICResponse<List<ExcelData>> establishExcelNewToOneCityV2(Integer cityId) {
        return ICResponseHandler.template(() -> {
            List<ExcelData> data = cityService.establishExcelNewToOneCityV2(cityId);//更新商品信息更新电子秤表
            return ICResponse.success(data);
        }, "establishExcelNewToOneCity", cityId);

    }


    @Override
    public ICResponse<Boolean> saveSpec(Spec spec) {
        log.info("##saveSpec ,param=>cityProduct:{},spec:{}", spec.getCityProductId(), JSON.toJSONString(spec));
        return ICResponseHandler.template(() -> {
            cityProductService.saveSpec(spec);
            return ICResponse.success(true);
        }, "saveSpec", spec);
    }

    /**
     * @param cityProduct
     * @param spec
     * @return
     */
    @Override
    public ICResponse<Boolean> up(CityProduct cityProduct, Spec spec) {
        log.info("##up ,param=>cityProduct:{},spec:{}", JSON.toJSONString(cityProduct), JSON.toJSONString(spec));
        Map param1 = Maps.newHashMap();
        param1.put("cityProduct", cityProduct);
        param1.put("spec", spec);
        return ICResponseHandler.template(() -> {
            cityProductService.up(cityProduct, spec);
            return ICResponse.success(true);
        }, "up", param1);
    }

    @Override
    public ICResponse<Boolean> shelveProduct(Integer cityId, Integer cityProductId) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("cityProductId", cityProductId);

        return ICResponseHandler.template(() -> {
            soaHPService.shelveProduct(cityId, cityProductId);
            return ICResponse.success(true);
        }, "shelveProduct", param1);

    }

    @Override
    public ICResponse<Boolean> preDown(ProductUnShelveRequest request) {
        Integer cityId = request.getCityId();
        Integer cityProductId = request.getCityProductId();
        Map<String, Object> param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("cityProductId", cityProductId);

        return ICResponseHandler.template(() -> {
            boolean available = cityProductService.preDown(cityId, cityProductId);
            return ICResponse.success(available);
        }, "preDown", param1);
    }

    @Override
    public ICResponse<Boolean> down(ProductUnShelveRequest request) {
        log.info("##down ,param=>request:{}", JSON.toJSONString(request));
        Integer userId = request.getUserId();
        Integer cityId = request.getCityId();
        Integer cityProductId = request.getCityProductId();
        boolean force = request.isForce();
        Map<String, Object> param1 = Maps.newHashMap();
        param1.put("userId", userId);
        param1.put("cityProductId", cityProductId);

        return ICResponseHandler.template(() -> {
            cityProductService.down(cityId, cityProductId, userId, force);
            return ICResponse.success(true);
        }, "down", param1);
    }

    @Override
    public ICResponse<List<ActProductSign>> selectByEffectTime(String from, String to, Integer cityProductId) {
        Map param1 = Maps.newHashMap();
        param1.put("cityProductId", cityProductId);
        param1.put("from", from);
        param1.put("to", to);
        return ICResponseHandler.template(() -> {
            // 清空一分购
            String day = new java.sql.Date(new Date().getTime()).toString();
            String dayBegin = day + " 00:00:00";
            String dayEnd = day + " 23:59:59";
            Map<String, Object> param = new HashMap<>();
            param.put("from", dayBegin);
            param.put("to", dayEnd);
            param.put("cityProductId", cityProductId);
            // 当天执行的定时调价任务
            List<ActProductSign> actProductSigns = multiBargainService.selectByEffectTime(param);
            return ICResponse.success(actProductSigns);
        }, "selectByEffectTime", param1);

    }

    @Override
    public ICResponse<Boolean> deleteProductSignByTaskIds(List<Integer> productSignTaskIds) {
        return ICResponseHandler.template(() -> {
            multiBargainService.deleteProductSignByTaskIds(productSignTaskIds);
            return ICResponse.success(true);
        }, "deleteProductSignByTaskIds", productSignTaskIds);

    }

    @Override
    public ICResponse<Boolean> delPointProductFromRedis(Integer cityId) {
        return ICResponseHandler.template(() -> {
            // 删除 积分商城redis
            JedisProxy proxy = JedisProxy.getInstance();
            String pointProductKey = RedisKeyGenerator.generatePointProductKey(cityId);
            proxy.delKey(pointProductKey);
            return ICResponse.success(true);
        }, "delPointProductFromRedis", cityId);
    }

    @Override
    public ICResponse<List<String>> getShowNames(Integer cityId, Integer c1Id, Integer c2Id, String showName) {
        Map<String, Object> param = new HashMap<>();
        if (cityId != null) {
            param.put("cityId", cityId);
        }
        if (c1Id != null) {
            param.put("c1Id", c1Id);
        }
        if (c2Id != null) {
            param.put("c2Id", c2Id);
        }
        if (!StringUtils.isEmpty(showName)) {
            param.put("showName", showName);
        }
        return ICResponseHandler.template(() -> {
            List<String> showNames = cityProductService.getShowNames(param);
            return ICResponse.success(showNames);
        }, "getShowNames", param);
    }

    @Override
    public ICResponse<List<DDLCityProduct>> getDDLCityProducts(Integer cityId, Integer c1Id, Integer c2Id, String showName) {
        Map<String, Object> param = new HashMap<>();
        if (cityId != null) {
            param.put("cityId", cityId);
        }
        if (c1Id != null) {
            param.put("c1Id", c1Id);
        }
        if (c2Id != null) {
            param.put("c2Id", c2Id);
        }
        if (!StringUtils.isEmpty(showName)) {
            param.put("showName", showName);
        }
        return ICResponseHandler.template(() -> {
            List<DDLCityProduct> cityProducts = cityProductService.getDDLCityProducts(param);
            return ICResponse.success(cityProducts);
        }, "getDDLCityProducts", param);
    }

    @Override
    public ICResponse<List<TACityProduct>> getTACityProducts(Integer cityId) {
        return ICResponseHandler.template(() -> {
            List<TACityProduct> list = cityProductService.getTACityProducts(cityId);
            PageInfo<TACityProduct> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(10, pageInfo.getTotal(), 0));
        }, "getTACityProducts", cityId);

    }

    @Override
    public ICResponse<List<TACityProduct>> getTACityProductsV2(Integer cityId, String keyword) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("keyword", keyword);

        return ICResponseHandler.template(() -> {
            List<TACityProduct> list = cityProductService.getTACityProductsV2(cityId, keyword);
            PageInfo<TACityProduct> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(10, pageInfo.getTotal(), 0));
        }, "getTACityProductsV2", param1);
    }

    @Override
    public ICResponse<List<TACityProduct>> getTACityProductsByKeyWord(TACityProductQueryDto dto) {
        return ICResponseHandler.template(() -> {
            PageHelper.startPage(dto.getOffset() / dto.getLimit() + 1, dto.getLimit());
            List<TACityProduct> list = cityProductService.getTACityProductsByKeyWord(dto);
            PageInfo<TACityProduct> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(dto.getLimit() != null ? dto.getLimit() : 0, pageInfo.getTotal(), dto.getOffset() != null ? (dto.getOffset() / dto.getLimit() + 1) : 0));
        }, "getTACityProductsByKeyWord", dto);
    }

    @Override
    public ICResponse<List<TACityProduct>> getOfflineTACityProductsV2(Integer cityId, String keyword) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("keyword", keyword);

        return ICResponseHandler.template(() -> {
            List<TACityProduct> list = cityProductService.getOfflineTACityProductsV2(cityId, keyword);
            PageInfo<TACityProduct> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list);
        }, "getOfflineTACityProductsV2", param1);

    }

    @Override
    public ICResponse<List<OfflineProductInventory>> getOfflineTACityProductList(Integer cityId, String keyword) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("keyword", keyword);
        return ICResponseHandler.template(() -> {
            //1.查询商品
            Map<String, Object> productParam = new HashMap<>();
            productParam.put("cityId", cityId);
            productParam.put("keyword", keyword);
            productParam.put("smokeClassifyId", Constant.SMOKE_CLASSIFY_ID);
            List<OfflineProductInventory> productList = cityProductDao.getOfflineTACityProductList(productParam);
            return ICResponse.success(productList);
        }, "getOfflineTACityProductList", param1);


    }

    @Override
    public ICResponse<List<TACityProduct>> getTACityProductsGift(Integer cityId) {

        return ICResponseHandler.template(() -> {
            List<TACityProduct> list = cityProductService.getTACityProductsGift(cityId);
            PageInfo<TACityProduct> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list);
        }, "getTACityProductsGift", cityId);


    }

    @Override
    public ICResponse<Boolean> newUserPro(CityProductVo cityProductVo) {
        return ICResponseHandler.template(() -> {
            cityProductService.newUserPro(cityProductVo);
            return ICResponse.success(true);
        }, "newUserPro", cityProductVo);

    }

    @Override
    public ICResponse<Boolean> underProduct(CityProduct cityProduct, UnSpec unSpec) {
        Map param1 = Maps.newHashMap();
        param1.put("cityProduct", cityProduct);
        param1.put("unSpec", unSpec);

        return ICResponseHandler.template(() -> {
            cityProductService.underProduct(cityProduct, unSpec);
            return ICResponse.success(true);
        }, "underProduct", param1);

    }

    @Override
    public ICResponse<Boolean> isUnder(CityProductVo cityProductVo) {
        return ICResponseHandler.template(() -> {
            cityProductService.isUnder(cityProductVo);
            return ICResponse.success(true);
        }, "isUnder", cityProductVo);


    }

    @Override
    public ICResponse<Boolean> vipLimit(CityProductVo cityProductVo) {
        return ICResponseHandler.template(() -> {
            cityProductService.vipLimit(cityProductVo, false);
            return ICResponse.success(true);
        }, "vipLimit", cityProductVo);

    }

    /**
     * @param cityProductId
     * @return
     */
    @Override
    public ICResponse<CityProductSyncCity> getCityProductById(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            Map map = new HashMap();
            map.put("cityProductId", cityProductId);
            CityProductSyncCity cityProduct = cityProductService.getCityProductById(map);
            return ICResponse.success(cityProduct);
        }, "getCityProductById", cityProductId);


    }

    @Override
    public ICResponse<Spec> getDefaultSpecBySyncProduct(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            Spec spec = specService.getDefaultSpecBySyncProduct(cityProductId);
            return ICResponse.success(spec);
        }, "getDefaultSpecBySyncProduct", cityProductId);


    }

    /**
     * @param cityId
     * @param productNo
     * @return
     */
    @Override
    public ICResponse<CityProductSyncCity> getByCityIdAndProductNo(Integer cityId, String productNo) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("productNo", productNo);

        return ICResponseHandler.template(() -> {
            CityProductSyncCity dbCpsc = cityProductService.getByCityIdAndProductNo(cityId, productNo);
            return ICResponse.success(dbCpsc);
        }, "getByCityIdAndProductNo", param1);

    }

    @Override
    public PlainResult<Paging<CityProductVo>> getCityProductList(ProductListQueryRequest request) {
        request.checkParams();

        PageHelper.offsetPage(request.getOffset(), request.getLimit());
        List<CityProductVo> byCityIdAndProductNo = cityProductService.getCityProductList(request);
        long total = ((Page) byCityIdAndProductNo).getTotal();
        Paging page = new Paging(total, byCityIdAndProductNo);
        return PlainResult.getDefaultSuccess(page);
    }

    @Override
    public PlainResult<CityProductVo> getSingleCityProductList(SingleCityProductQueryRequest singleCityProductQueryRequest) {
        singleCityProductQueryRequest.checkParams();
        CityProductVo cityProductVo = cityProductService.loadCityProduct(singleCityProductQueryRequest);
        return PlainResult.getDefaultSuccess(cityProductVo);

    }

    @Override
    public ICResponse<Integer> getCountProductByProductNo(String productNo, Integer cityId) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("productNo", productNo);

        return ICResponseHandler.template(() -> {
            Map map = new HashMap();
            map.put("productNo", productNo);
            map.put("cityId", cityId);
            Integer cityUpdateId = cityProductService.getCountProductByProductNo(map);//这些城市下有该商品
            return ICResponse.success(cityUpdateId);
        }, "getCountProductByProductNo", param1);


    }

    /**
     * 根据copy商品更新城市商品
     *
     * @param cityProductSyncCity
     * @return
     */
    @Override
    public ICResponse<Boolean> updateProductByUpdateCityIds(CityProductSyncCity cityProductSyncCity) {
        return ICResponseHandler.template(() -> {
            cityProductService.updateProductByUpdateCityIds(cityProductSyncCity);
            return ICResponse.success(true);
        }, "updateProductByUpdateCityIds", cityProductSyncCity);


    }

    @Override
    public ICResponse<Boolean> cityProductCopyOtherCity(CityProductSyncCity cityProductSyncCity) {
        return ICResponseHandler.template(() -> {
            cityProductService.cityProductCopyOtherCity(cityProductSyncCity);
            return ICResponse.success(true);
        }, "cityProductCopyOtherCity", cityProductSyncCity);


    }

    @Override
    public ICResponse<Boolean> syncProductSpecCreate(Spec spec) {
        return ICResponseHandler.template(() -> {
            specService.syncProductSpecUpdate(spec);
            return ICResponse.success(true);
        }, "syncProductSpecCreate", spec);


    }

    public ICResponse<Boolean> pointProduct(Integer userId, Integer cityProductId, Integer pointPrice) {
        Map param1 = Maps.newHashMap();
        param1.put("userId", userId);
        param1.put("cityProductId", cityProductId);
        param1.put("pointPrice", pointPrice);
        return ICResponseHandler.template(() -> {
            Map<String, Object> param = new HashMap<>();
            param.put("userId", userId);
            param.put("cityProductId", cityProductId);
            param.put("pointPrice", pointPrice);

            cityProductService.pointProduct(param);
            return ICResponse.success(true);
        }, "pointProduct", param1);


    }

    @Override
    public ICResponse<Boolean> isPoint(CityProductVo cityProductVo) {
        return ICResponseHandler.template(() -> {
            cityProductService.isPoint(cityProductVo);
            return ICResponse.success(true);
        }, "isPoint", cityProductVo);

    }

    @Override
    public ICResponse<StandardResult> checkStandardProduct(CityProductVo cityProductVo) {
        return ICResponseHandler.template(() -> {
            StandardResult result = cityProductService.checkStandardProduct(cityProductVo);
            return ICResponse.success(result);
        }, "checkStandardProduct", cityProductVo);


    }

    @Override
    public ICResponse<List<CityProduct>> selectCityProductListByProductNos(Integer cityId, List<String> productNos) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("productNos", productNos);

        return ICResponseHandler.template(() -> {
            List<CityProduct> productList = cityProductService.selectCityProductListByProductNos(cityId, productNos);
            return ICResponse.success(productList);
        }, "selectCityProductListByProductNos", param1);


    }

    @Override
    public ICResponse<Boolean> batchDown(Integer userId, Integer cityId, List<Integer> cityProductIdList) {
        Map param1 = Maps.newHashMap();
        param1.put("userId", userId);
        param1.put("cityId", cityId);
        param1.put("cityProductIdList", cityProductIdList);
        return ICResponseHandler.template(() -> {
            cityProductService.batchDown(userId, cityId, cityProductIdList);
            return ICResponse.success(true);
        }, "batchDown", param1);


    }

    @Override
    public ICResponse<Integer> queryCityProductId(String productNo, Integer cityId) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("productNo", productNo);

        return ICResponseHandler.template(() -> {
            Integer cityProductId = marketingInventoryService.queryCityProductId(productNo, cityId);
            return ICResponse.success(cityProductId);
        }, "queryCityProductId", param1);


    }

    @Override
    public ICResponse<Boolean> saveImportProductUpLog(List<ImportGroundingProduct> importProductList, Integer userId) {
        log.info("##saveImportProductUpLog ,param=>userId:{},importProductList:{}", importProductList, userId);
        Map param1 = Maps.newHashMap();
        param1.put("importProductList", importProductList);
        param1.put("userId", userId);

        return ICResponseHandler.template(() -> {
            cityProductService.saveImportProductUpLog(importProductList, userId);
            return ICResponse.success(true);
        }, "saveImportProductUpLog", param1);


    }

    @Override
    public ICResponse<Spec> getDefaultSpec(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            Spec copySpec = specService.getDefaultSpec(cityProductId);
            return ICResponse.success(copySpec);
        }, "getDefaultSpec", cityProductId);


    }

    @Override
    public ICResponse<List<CityProduct>> selectCityProductByCityProductIds(List<Integer> cityProductIds) {
        return ICResponseHandler.template(() -> {
            return ICResponse.success(cityProductDao.selectCityProductByCityProductIds(cityProductIds));
        }, "selectCityProductByCityProductIds", cityProductIds);

    }

    @Override
    public ICResponse<List<TACityProduct>> getMsgListByCityId(OpenCity openCity) {
        return ICResponseHandler.template(() -> {
            return ICResponse.success(cityProductDao.getMsgListByCityId(openCity));
        }, "getMsgListByCityId", openCity);


    }

    @Override
    public ICResponse<Boolean> updateCityProductStatus(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            hqProductDao.updateCityProductStatus(param);
            return ICResponse.success(true);
        }, "updateCityProductStatus", param);


    }

    @Override
    public ICResponse<List<CityProductIdAndMode>> getCityProductIds(List<Integer> hqProductIds) {
        return ICResponseHandler.template(() -> {
            List<CityProductIdAndMode> list = hqProductDao.getCityProductIds(hqProductIds);
            return ICResponse.success(list);
        }, "getCityProductIds", hqProductIds);


    }

    @Override
    public ICResponse<Boolean> updateByHqProductId(CityProduct cityProduct) {
        return ICResponseHandler.template(() -> {
            cityProductDao.updateByHqProductId(cityProduct);
            return ICResponse.success(true);
        }, "updateByHqProductId", cityProduct);


    }

    @Override
    public ICResponse<List<TACityProduct>> getTACityProductsV3(Integer cityId, String keyword) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("keyword", keyword);

        return ICResponseHandler.template(() -> {
            return ICResponse.success(cityProductService.getTACityProductsV3(cityId, keyword));
        }, "getTACityProductsV3", param1);
    }

    @Override
    public ICResponse<Boolean> changeForecastFlag(ProductForecastFlagChangeRequest productForecastFlagChangeRequest) {
        if (productForecastFlagChangeRequest == null) {
            throw new IllegalArgumentException("Param is null");
        }
        log.info("##changeForecastFlag,requestParams:{}", productForecastFlagChangeRequest);
        productForecastFlagChangeRequest.checkParams();
        try {
            Integer integer = cityProductDao.cityProductForecastFlagChange(productForecastFlagChangeRequest.getCityId(), productForecastFlagChangeRequest.getCityProductIdList(), productForecastFlagChangeRequest.getOperatorId(), productForecastFlagChangeRequest.getForecastFlagEnum().getCode());

            return ICResponse.success(integer > 0);

        } catch (Exception ex) {
            log.error(String.format("##changeForecastFlag exception,requestParam:%s", productForecastFlagChangeRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }

    }

    @Override
    public ICResponse<List<String>> createProductAllInOnce(List<ProductCreateVo> productCreateVos) {
        if (productCreateVos == null || productCreateVos.size() == 0) {
            return ICResponse.fail("参数为空");
        }

        try {
            log.info("开始批量创建商家商品，总数是:{}", productCreateVos.size());
            cityProductDao.createProductAllInOnce(productCreateVos);
        } catch (Exception e) {
            return ICResponse.fail(e.getMessage());
        }

        return ICResponse.success(new ArrayList());
    }

    @Override
    public ICResponse<Boolean> batchUpdateCityStatus(Integer cityId, List<Integer> cityProductIds,
                                                     Integer cityStatus, Integer userId) {
        if (cityProductIds == null || cityProductIds.size() == 0) {
            return ICResponse.fail("cityProductId数量为0，异常");
        }

        Boolean ret = Boolean.FALSE;
        if (cityStatus == CityStatusEnum.ON_SHELF.getStatus()) {
            ret = cityProductService.productBatchOnShelf(cityId, cityProductIds, userId);
        } else if (cityStatus == CityStatusEnum.OFF_SHELF.getStatus()) {
            ret = cityProductService.productBatchOffShelf(cityId, cityProductIds, userId);
        } else {
            return ICResponse.fail("非法的上下架状态");
        }

        if (ret.equals(Boolean.TRUE)) {
            return ICResponse.success(ret);
        } else {
            return ICResponse.fail("上下架失败");
        }
    }

    @Override
    public ICResponse<Map<Integer, Boolean>> isTodayForcastProduct(Integer cityId, List<Integer> cityProductIds) {
        if (cityProductIds == null || cityProductIds.size() == 0) {
            return ICResponse.success(new HashMap<>());
        }

        List<Integer> forecastProductIds = cityProductService.getTodayCityForecastProductIds(cityId);
        Map<Integer, Boolean> ret;
        if (forecastProductIds == null || forecastProductIds.size() == 0) {
            ret = cityProductIds.stream()
                    .collect(HashMap::new, (m, e) -> m.put(e, Boolean.FALSE), HashMap::putAll);
        } else {
            ret = cityProductIds.stream()
                    .collect(HashMap::new, (m, e) -> {
                        if (forecastProductIds.contains(e)) {
                            m.put(e, Boolean.TRUE);
                        } else {
                            m.put(e, Boolean.FALSE);
                        }
                    }, HashMap::putAll);
        }
        return ICResponse.success(ret);
    }

    @Override
    public ICResponse<Map<Integer, Boolean>> isCityProductHasDefaultSpec(List<Integer> cityProductIds) {
        if (cityProductIds == null || cityProductIds.size() == 0) {
            return ICResponse.success(new HashMap<>());
        }

        List<Spec> specs = specService.getDefaultSpecByProductIds(cityProductIds);
        if (specs == null || specs.size() == 0) {
            return ICResponse.success(new HashMap<>());
        } else {
            Map<Integer, Boolean> ret = specs.stream()
                    .collect(HashMap::new, (m, e) -> m.put(e.getCityProductId(), Boolean.TRUE), HashMap::putAll);
            return ICResponse.success(ret);
        }
    }

    @Override
    public ICResponse<Boolean> isCityProductExist(Integer cityProductId) {
        List<CityProduct> ret = cityProductDao.selectCityProductListByCityProductIds(Arrays.asList(cityProductId));
        if (ret != null && ret.size() > 0) {
            return ICResponse.success(Boolean.TRUE);
        } else {
            return ICResponse.success(Boolean.FALSE);
        }
    }

    @Override
    public ICResponse<Boolean> localizeProductToAllCity(List<ProductCreateVo>  localizeRequest) {
        if (localizeRequest == null || localizeRequest.size() == 0) {
            return ICResponse.fail("参数为空");
        }

        try { log.info("开始批量创建商家商品，总数是:{}", localizeRequest.size());

            final Integer cityId = localizeRequest.get(0).getCityProduct().getCityId();

            List<String> productNOs = localizeRequest.stream()
                    .filter(e -> { return e.getCityProduct() != null
                            && StringUtils.isNotBlank(e.getCityProduct().getProductNo());})
                    .map(e -> { return e.getCityProduct().getProductNo(); })
                    .collect(Collectors.toList());

            if (productNOs.size() != localizeRequest.size()) {
                return ICResponse.fail("存在无效的productNO");
            }

            List<CityProduct> cityProductList = localizeRequest.stream()
                    .map(ProductCreateVo::getCityProduct)
                    .collect(Collectors.toList());
            Map<String, Spec> specMap = localizeRequest.stream()
                    .collect(HashMap::new,
                            (m, e) -> m.put(e.getCityProduct().getProductNo(), e.getSpec()),
                            HashMap::putAll);
            cityProductDao.batchLocalizeProduct(cityId, productNOs, cityProductList, specMap);

            List<Integer> classifyIds = hqProductDao.getClassifyIdsByProductNos(productNOs);

            List<String> delKeys = classifyIds.stream()
                    .filter(Objects::nonNull)
                    .map(e -> {
                        return RedisKeyGenerator.generateClassifyProdListKey(e, cityId);
                    }).collect(Collectors.toList());

            Long ret = JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));
            log.info("删除二级分类商品key {}个", delKeys.size());


        } catch (Exception e) {
            return ICResponse.fail("异常:"  + e.getMessage());
        }

        return ICResponse.success(Boolean.TRUE);
    }

}
