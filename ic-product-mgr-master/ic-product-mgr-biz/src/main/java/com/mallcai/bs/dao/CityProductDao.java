package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.domain.vo.*;
import com.dailuobo.ic.dto.spec.ProductAttributeDTO;
import com.google.common.collect.Lists;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.dailuobo.ic.api.request.product.HqProductLocalizeRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.dailuobo.ic.domain.dao.model.product.ProductGroupItemDO;
import com.dailuobo.ic.api.request.product.*;
import com.google.common.collect.Lists;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mapper.HomebuMapper;
import com.mallcai.bs.mapper.SOAHPMapper;
import com.mallcai.bs.mapper.SpecMapper;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.request.HqProductVo;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;

import java.util.Optional;
import java.util.stream.Collectors;

import dailuobo.dao.mapper.ProductGoodsRelMapper;
import dailuobo.dao.mapper.ProductGroupMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CityProductDao {
    @Autowired
    private CityProductMapper cityProductMapper;

    @Autowired
    private SOAHPMapper soaHpMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    HomebuMapper homebuMapper;

    @Autowired
    private MgrProductService mgrProductService;

    @Autowired
    private ProductGoodsRelMapper productGoodsRelMapper;


    @Autowired
    private ProductGroupMapper productGroupMapper;

    public List<CityProduct> selectHqAll(Map<String, Object> param) {
        return cityProductMapper.selectHqAll(param);
    }

    public void localize(Map<String, Object> param) {
        cityProductMapper.localize(param);
    }


    public List<HqProductLocalize> localizeNew(HqProductLocalizeRequest localizeRequest) {
        List<Integer> hqProductIds = localizeRequest.getHqProducts().stream().map(HqProductLocalizeRequest.HqProductDTO::getHqProductId).distinct().collect(Collectors.toList());
        Map<Integer, List<HqProductLocalizeRequest.HqProductDTO>> productDTOMap = localizeRequest.getHqProducts().stream().collect(Collectors.groupingBy(HqProductLocalizeRequest.HqProductDTO::getHqProductId, Collectors.toList()));
        List<HqProductLocalize> hqProductLocalizes = cityProductMapper.selectLocalizeHqProduct(hqProductIds);

        List<HqProductLocalize> insetProduct = Lists.newArrayList();
        hqProductLocalizes.forEach(hqProduct -> {
            List<HqProductLocalizeRequest.HqProductDTO> hqProductDTOS = productDTOMap.get(hqProduct.getHqProductId());

            hqProductDTOS.forEach(hqProductDTO -> {
                HqProductLocalize hqProductLocalize = new HqProductLocalize();
                BeanUtils.copyProperties(hqProduct,hqProductLocalize);
                hqProductLocalize.setUserId(localizeRequest.getUserId());
                hqProductLocalize.setCityId(hqProductDTO.getCityId());
                hqProductLocalize.setMerchantId(hqProductDTO.getMerchantId());
                hqProductLocalize.setIsShare(hqProductDTO.getIsShare());
                insetProduct.add(hqProductLocalize);
            });
        });
        cityProductMapper.localizeHqProduct(insetProduct);
        cityProductMapper.updateLocalizeProductFlag(hqProductIds,localizeRequest.getUserId());

        return insetProduct;
    }

    public List<CityProduct> selectAll(Map<String, Object> param) {
        return cityProductMapper.selectAll(param);
    }

    public void update(CityProduct cityProduct) {
        cityProductMapper.update(cityProduct);
        addOrUpdateMerchantProductRate(cityProduct);
    }


    public void up(Integer cityProductId, Double disablePrice, Integer updateUserId) {
        cityProductMapper.up(cityProductId, disablePrice, updateUserId);
    }

    public void down(Integer userId, Integer cityProductId) {
        cityProductMapper.down(userId, cityProductId);
    }

    public List<String> getShowNames(Map<String, Object> param) {
        return cityProductMapper.getShowNames(param);
    }

    public List<DDLCityProduct> getDDLCityProducts(Map<String, Object> param) {
        return cityProductMapper.getDDLCityProducts(param);
    }

    public List<TACityProduct> getTACityProducts(Integer cityId) {
        return cityProductMapper.getTACityProducts(cityId);
    }

    public List<TACityProduct> getTACityProductsV2(Integer cityId, String keyword) {
        return cityProductMapper.getTACityProductsV2(cityId, keyword);
    }

    public List<TACityProduct> getTACityProductsV3(Integer cityId, String keyword) {
        return cityProductMapper.getTACityProductsV3(cityId, keyword);
    }

    public List<TACityProduct> getTACityProductsByKeyWord(TACityProductQueryDto queryDto) {
        return cityProductMapper.getTACityProductsByKeyWord(queryDto);
    }

    public List<TACityProduct> getOfflineTACityProductsV2(Integer cityId, String keyword) {
        return cityProductMapper.getOfflineTACityProductsV2(cityId, keyword);
    }

    public List<OfflineProductInventory> getOfflineTACityProductList(Map<String, Object> param) {
        return cityProductMapper.getOfflineTACityProductList(param);
    }

    public List<TACityProduct> getTACityProductsGift(Integer cityId) {
        return cityProductMapper.getTACityProductsGift(cityId);
    }

    public void updateDefaultSpec(Integer cityProductId, Double disablePrice, Integer updateUserId) {
        cityProductMapper.updateDefaultSpec(cityProductId, disablePrice, updateUserId);
    }

    public void newUserPro(CityProductVo cityProductVo) {
        cityProductMapper.newUserPro(cityProductVo);
    }

    public void isUnder(CityProductVo cityProductVo) {
        // TODO Auto-generated method stub
        cityProductMapper.isUnder(cityProductVo);
    }

    public void underProduct(CityProduct cityProduct) {
        // TODO Auto-generated method stub
        cityProductMapper.underProduct(cityProduct);
    }

    public List<TACityProduct> getMsgList() {
        return cityProductMapper.getMsgList();
    }

    public void vipLimit(Map<String, Object> param) {
        // TODO Auto-generated method stub
        cityProductMapper.vipLimit(param);
    }

    public List<Integer> selectCityProductIds(List<Integer> hpProductIds) {
        return cityProductMapper.selectCityProductIds(hpProductIds);
    }

    public List<OfflineProductInventory> queryOfflineProducts(Integer cityId, List<Integer> cityProductIds) {
        return cityProductMapper.queryOfflineProducts(cityId, cityProductIds);
    }

    public OfflineProductInventory queryInventoryProducts(Integer storeId, Integer cityProductId) {
        return cityProductMapper.queryInventoryProducts(storeId, cityProductId);
    }

    //查看条形码是否重复
    @Transactional
    public int existBarCode(CityProduct cityProduct) {
        return cityProductMapper.existBarCode(cityProduct);
    }

    //获取已经开通城市
    public List<OpenCity> getOpenCity() {
        return cityProductMapper.getOpenCity();
    }

    //根据CityId获取MsgList
    public List<TACityProduct> getMsgListByCityId(OpenCity openCity) {
        return cityProductMapper.getMsgListByCityId(openCity);
    }

    public List<CityProduct> selectStcokProduct(Map<String, Object> param) {
        return cityProductMapper.selectStcokProduct(param);
    }

    public void addProduct(List<StockProduct> StockProduct) {
        cityProductMapper.addProduct(StockProduct);
    }

    public void deleteProduct(Map map) {
        cityProductMapper.deleteProduct(map);
    }

    public int findStockProductByID(Integer cityProductId) {
        return cityProductMapper.findStockProductByID(cityProductId);
    }

    public List<CityProduct> selectCityProductNos(Integer cityId) {
        return cityProductMapper.selectCityProductNos(cityId);
    }

    public List<CityProduct> selectCityProductList(Integer cityId) {
        return cityProductMapper.selectCityProductList(cityId);
    }

    public List<CityProduct> selectCityProductNosList(Map<String, Object> param) {
        return cityProductMapper.selectCityProductNosList(param);
    }

    public Integer getCountProductByProductNo(Map param) {
        return cityProductMapper.getCountProductByProductNo(param);
    }

    public void cityProductCopyOtherCity(CityProductSyncCity cityProductSyncCity) {
        if(cityProductSyncCity.getVideoUrl() == null){
            cityProductSyncCity.setVideoUrl("");
        }
        cityProductMapper.cityProductCopyOtherCity(cityProductSyncCity);
    }

    public CityProductSyncCity getCityProductById(Map map) {
        return cityProductMapper.getCityProductById(map);
    }

    public void updateProductByUpdateCityIds(CityProductSyncCity cityProduct) {
        cityProductMapper.updateProductByUpdateCityIds(cityProduct);
    }

    public List<CityProduct> selectAllProductNo(Map<String, Object> param) {
        return cityProductMapper.selectAllProductNo(param);
    }

    public String selectAllCityProductIds(Map<String, Object> param) {
        return cityProductMapper.selectAllCityProductIds(param);
    }

    public void pointProduct(Map<String, Object> param) {
        cityProductMapper.pointProduct(param);
    }

    public void isPoint(CityProductVo cityProductVo) {
        cityProductMapper.isPoint(cityProductVo);
    }

    public Integer checkStandardProduct(CityProductVo cityProductVo) {
        return cityProductMapper.checkStandardProduct(cityProductVo);
    }

    public List<CityProduct> selectCityProductListByCityProductIds(List<Integer> cityProductIds) {
        if (cityProductIds == null || cityProductIds.isEmpty()) {
            return new ArrayList<>();
        }
        return cityProductMapper.selectCityProductListByCityProductIds(cityProductIds);
    }

    public void updateL1L2NamesByHqProductIds(String l1L2Names, List<Integer> hpProductIds) {
        cityProductMapper.updateL1L2NamesByHqProductIds(l1L2Names, hpProductIds);
    }

    public List<CityProduct> findByHqProductIds(List<Integer> hpProductIds) {
        return cityProductMapper.findByHqProductIds(hpProductIds);
    }

    public void batchDown(Integer userId, List<Integer> cityProductIds) {
        cityProductMapper.batchDown(userId, cityProductIds);
    }

    public List<CityProduct> selectCityProductListByProductNos(Integer cityId, List<String> productNos) {
        return cityProductMapper.selectCityProductListByProductNos(cityId, productNos);
    }

    /**
     * 保存批量上传上架导入文件的日志
     */
    public void saveImportProductUpLog(Map param) {
        cityProductMapper.saveImportProductUpLog(param);
    }

    public List<CityProduct> selectCityProductByCityProductIds(List<Integer> cityProductIds) {
        if (cityProductIds == null || cityProductIds.isEmpty()) {
            return new ArrayList<>();
        }
        return cityProductMapper.selectCityProductByCityProductIds(cityProductIds);
    }

    /**
     * 根据cityId 与 productNo 获取本市商品
     *
     * @param cityId    城市id
     * @param productNo 商品编号
     * @return
     */
    public CityProductSyncCity getByCityIdAndProductNo(Integer cityId, String productNo) {
        return cityProductMapper.getByCityIdAndProductNo(cityId, productNo);
    }

    public List<CityProductVo> getCityProductList(ProductListQueryRequest request) {
        return cityProductMapper.getCityProductList(request);
    }

    public CityProductVo loadCityProduct(SingleCityProductQueryRequest request) {
        return cityProductMapper.loadCityProduct(request);
    }

    public void updateByHqProductId(CityProduct cityProduct) {
        cityProductMapper.updateByhqProductId(cityProduct);
    }


    public Integer cityProductForecastFlagChange(Integer cityId, List<Integer> cityProductId, Integer operatorId, Integer foreCastFlag) {
        return cityProductMapper.cityProductForecastFlagChange(cityId, cityProductId, operatorId, foreCastFlag);

    }

    @Transactional
    public void createProductAllInOnce(List<ProductCreateVo> productCreateVos) {
        for (ProductCreateVo vo : productCreateVos) {
            CityProduct cityProduct = vo.getCityProduct();
            if (cityProduct == null) {
                throw new RuntimeException("cityProduct参数为空");
            }

            HqProductVo hqProductVo = new HqProductVo();
            BeanUtils.copyProperties(vo.getCityProduct(), hqProductVo);
            hqProductVo.setClassifyId(vo.getCityProduct().getClassify().getClassifyId());

            ICResponse<HqProductVo> ret = mgrProductService.addHqProduct(hqProductVo);
            HqProductVo retHqProductVo = ret.getData();
            if (ret.isSuccess() == false
                    || retHqProductVo == null
                    || retHqProductVo.getHqProductId() == null
                    || retHqProductVo.getHqProductId() <= 0) {
                throw new RuntimeException("productNo: " + cityProduct.getProductNo() + "生成总部商品失败");
            }

            cityProduct.setHqProductId(retHqProductVo.getHqProductId());

            if(cityProduct.getVideoUrl() == null){
                cityProduct.setVideoUrl("");
            }
            cityProductMapper.addCityProduct(cityProduct);
            if (cityProduct.getCityProductId() == null || cityProduct.getCityProductId() <= 0) {
                throw new RuntimeException("productNo: " + cityProduct.getProductNo() + "生成城市商品失败");
            }

            SellTime sellTime = vo.getSellTime();
            if (sellTime != null && sellTime.getStartHour() != null && sellTime.getEndHour() != null) {
                cityProductMapper.insertMerchantProductExtraAttr(cityProduct.getCityProductId(),
                        ProductExtraAttrTypeEnum.THIRD_PARTY, cityProduct.getShowName(),
                        cityProduct.getCreateUserId(), sellTime.getStartHour().toString(),
                        sellTime.getEndHour().toString());
            }

            addOrUpdateMerchantProductRate(cityProduct);

            Spec spec = vo.getSpec();
            if (spec == null) {
                throw new RuntimeException("spec参数为空");
            }

            // 商品设置了限购数时，加入不删除限购商品表
            if (spec.getThreshold() != null && spec.getThreshold() > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("cityProductId", cityProduct.getCityProductId());
                homebuMapper.add(map);
            }

            spec.setCityProductId(cityProduct.getCityProductId());
            specMapper.addSpec(spec, cityProduct.getCreateUserId());

            if (spec.getSpecId() == null || spec.getSpecId() <= 0) {
                throw new RuntimeException("productNo: " + cityProduct.getProductNo() + "生成商品规格失败");
            }
        }
    }


    public void batchLocalizeProduct(Integer cityId, List<String> productNOs,
            List<CityProduct> cityProductList, Map<String, Spec> specMap) {
        cityProductMapper.batchAddCityProduct(cityProductList);

        List<CityProduct> ret = cityProductMapper.selectCityProductListByProductNos(cityId, productNOs);

        for(CityProduct c : ret) {
            specMap.get(c.getProductNo()).setCityProductId(c.getCityProductId());
        }
        specMapper.batchAddSpec(specMap.values().stream().collect(Collectors.toList()));

        return;
    }


    public List<CityProduct> listCityProductByProductIdList(List<Integer> cityProductIdList) {
        List<CityProduct> cityProductList = cityProductMapper.selectCityProductByIdList(cityProductIdList);
        return cityProductList;
    }

    public List<CityProduct> listProductForStock(Integer cityId, List<Integer> cityProductIds, Integer isShare, Integer isStandard) {
        return cityProductMapper.listCityProductIdsByCityId(cityId, isShare, isStandard, cityProductIds);
    }

    public Boolean checkIfShareAndStandard(Integer cityId, Integer cityProductId, Integer isShare, Integer isStandard) {
        List<CityProduct> productIdLists = cityProductMapper.listCityProductIdsByCityId(cityId, isShare, isStandard, Lists
                .newArrayList(cityProductId));
        List<Integer> productIds = productIdLists.stream().map(n -> n.getCityProductId()).collect(Collectors.toList());
        return Optional.ofNullable(productIds).map(n -> n.contains(cityProductId)).orElse(false);
    }


    @Transactional
    public void productBatchOnShelf(List<Integer> cityProductIds, Integer userId) {
        cityProductMapper.batchUp(userId, cityProductIds);
    }

    @Transactional
    public void productBatchOffShelf(List<Integer> cityProductIds, Integer userId) {
        cityProductMapper.batchDown(userId, cityProductIds);
    }

    public void addOrUpdateMerchantProductRate(CityProduct cityProduct) {
        if (cityProduct.getMerchantRate() != null
                && cityProduct.getMerchantId() != null
                && cityProduct.getMerchantId() > 0) {
            MerchantProductRate rate = new MerchantProductRate();
            rate.setCityProductId(cityProduct.getCityProductId());
            rate.setMerchantId(cityProduct.getMerchantId());
            rate.setMerchantRate(cityProduct.getMerchantRate());
            cityProductMapper.addOrUpdateMerchantProductRate(rate);
        }
    }


    public List<CityProduct> productListByCity(CityProductQueryRequest request) {
        return cityProductMapper.productListByCity(request);
    }


    public List<CityProduct> findCityProductByCityId(CityProductSearchRequest request) {
        return cityProductMapper.findCityProductByCityId(request);

    }

    public List<ProductGroupItemDO> listAllGroupItems(Integer cityId) {
        return productGroupMapper.listAllGroupItems(cityId);
    }

    public List<ProductAttributeDTO> listProductAttributes(Integer cityId, List<Integer> cityProductId) {
        return productGroupMapper.listProductAttributes(cityId, cityProductId);
    }

    public Boolean creatCityProduct(CityProduct cityProduct) {
        Integer result = cityProductMapper.createCityProduct(cityProduct);
        return result == 1;
    }


    /**
     * 商户端查询可用的城市商品
     * @param marketRequest
     * @return
     */
    public List<MerchantCityProduct> selectMerchantCityProduct(MerchantProductMarketRequest marketRequest) {
        return cityProductMapper.selectMerchantCityProduct(marketRequest);

    }

    public Integer insertProductAttr(ProductAttributeDTO productAttributeDTO){
        return productGroupMapper.insertProductAttr(productAttributeDTO);
    }

    public CityProduct loadCityProductByItemIdAndSkuId(Integer cityId,Long skuId,Long itemId){
       return cityProductMapper.loadCityProductByItemIdAndSkuId(cityId,skuId,itemId);
    }
}
