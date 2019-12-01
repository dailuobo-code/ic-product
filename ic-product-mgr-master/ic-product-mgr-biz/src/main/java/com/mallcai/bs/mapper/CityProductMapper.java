package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.domain.vo.*;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.dailuobo.ic.api.request.product.MerchantProductMarketRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.mallcai.bs.common.CustomDataSource;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface CityProductMapper {
    List<CityProduct> selectHqAll(Map<String, Object> param);

    void localize(Map<String, Object> param);

    List<HqProductLocalize> selectLocalizeHqProduct(@Param("hqProductIds") List<Integer> hqProductIds);

    void localizeHqProduct(List<HqProductLocalize> list);

    int updateLocalizeProductFlag(@Param("hqProductIds") List<Integer> hqProductIds, @Param("userId") Integer userId);

    List<CityProduct> selectAll(Map<String, Object> param);

    void update(CityProduct cityProduct);


    @CustomDataSource(CustomDataSource.CAICAI)
    void updateByhqProductId(CityProduct cityProduct);

    void up(@Param("cityProductId") Integer cityProductId, @Param("disablePrice") Double disablePrice, @Param("updateUserId") Integer updateUserId);

    void down(@Param("userId") Integer userId, @Param("cityProductId") Integer cityProductId);

    List<String> getShowNames(Map<String, Object> param);

    List<DDLCityProduct> getDDLCityProducts(Map<String, Object> param);

    List<TACityProduct> getTACityProducts(@Param("cityId") Integer cityId);

    List<TACityProduct> getTACityProductsV2(@Param("cityId") Integer cityId, @Param("keyword") String keyword);

    List<TACityProduct> getTACityProductsV3(@Param("cityId") Integer cityId, @Param("keyword") String keyword);

    List<TACityProduct> getTACityProductsByKeyWord(TACityProductQueryDto queryDto);

    List<TACityProduct> getOfflineTACityProductsV2(@Param("cityId") Integer cityId, @Param("keyword") String keyword);

    List<OfflineProductInventory> getOfflineTACityProductList(Map<String, Object> param);

    List<TACityProduct> getTACityProductsGift(@Param("cityId") Integer cityId);

    void updateDefaultSpec(@Param("cityProductId") Integer cityProductId, @Param("disablePrice") Double disablePrice, @Param("updateUserId") Integer updateUserId);

    void newUserPro(CityProductVo cityProductVo);

    void isUnder(CityProductVo cityProductVo);

    void underProduct(CityProduct cityProduct);

    List<TACityProduct> getMsgList();

    List<Integer> selectCityProductIds(@Param("hpProductIds") List<Integer> hpProductIds);

    void vipLimit(Map<String, Object> param);

    List<OfflineProductInventory> queryOfflineProducts(@Param("cityId") Integer cityId,
                                                       @Param("cityProductIds") List<Integer> cityProductIds);

    OfflineProductInventory queryInventoryProducts(@Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId);

    int existBarCode(CityProduct cityProduct);//查看条形码是否重复

    List<OpenCity> getOpenCity();//获取已经开通城市

    List<TACityProduct> getMsgListByCityId(OpenCity openCity);//根据CityId获取MsgList

    List<CityProduct> selectStcokProduct(Map<String, Object> param);

    void addProduct(List<StockProduct> StockProduct);

    void deleteProduct(Map map);

    int findStockProductByID(Integer cityProductId);

    List<CityProduct> selectCityProductNos(@Param("cityId") Integer cityId);

    List<CityProduct> selectCityProductList(@Param("cityId") Integer cityId);

    List<CityProduct> selectCityProductNosList(Map<String, Object> param);

    Integer getCountProductByProductNo(Map param);

    void cityProductCopyOtherCity(CityProductSyncCity cityProductSyncCity);

    CityProductSyncCity getCityProductById(Map param);

    void updateProductByUpdateCityIds(CityProductSyncCity cityProduct);

    void pointProduct(Map<String, Object> param);

    void isPoint(CityProductVo cityProductVo);

    Integer checkStandardProduct(CityProductVo cityProductVo);

    List<CityProduct> selectAllProductNo(Map<String, Object> param);

    String selectAllCityProductIds(Map<String, Object> param);

    /**
     * 通过cityProductIdList查询城市商品信息.
     *
     * @param cityProductIdList
     * @return
     */
    List<CityProduct> selectCityProductByIdList(@Param("cityProductIdList") List<Integer> cityProductIdList);

    List<CityProduct> selectCityProductListByCityProductIds(@Param("cityProductIds") List<Integer> cityProductIds);

    int updateL1L2NamesByHqProductIds(@Param("l1l2Names") String l1L2Names, @Param("hpProductIds") List<Integer> hpProductIds);

    List<CityProduct> findByHqProductIds(@Param("hqProductIds") List<Integer> hqProductIds);

    List<CityProduct> selectCityProductListByProductNos(@Param("cityId") Integer cityId, @Param("productNos") List<String> productNos);

    void batchUp(@Param("userId") Integer userId, @Param("cityProductIds") List<Integer> cityProductIds);

    void batchDown(@Param("userId") Integer userId, @Param("cityProductIds") List<Integer> cityProductIds);

    void saveImportProductUpLog(Map param);

    List<CityProduct> selectCityProductByCityProductIds(@Param("cityProductIds") List<Integer> cityProductIdList);

    /**
     * 根据cityId 与 productNo 获取本市商品
     *
     * @param cityId    城市id
     * @param productNo 商品编号
     * @return
     */
    CityProductSyncCity getByCityIdAndProductNo(@Param("cityId") Integer cityId, @Param("productNo") String productNo);

    List<CityProductVo> getCityProductList(ProductListQueryRequest request);

    CityProductVo loadCityProduct(SingleCityProductQueryRequest request);


    Integer cityProductForecastFlagChange(@Param("cityId") Integer cityId, @Param("cityProductIdList") List<Integer> cityProductId, @Param("operatorId") Integer operatorId, @Param("foreCastFlag") Integer foreCastFlag);

    void addCityProduct(CityProduct cityProduct);

    void insertMerchantProductExtraAttr(@Param("cityProductId") Integer cityProductId,
                                        @Param("attrType") ProductExtraAttrTypeEnum attrType,
                                        @Param("showName") String showName,
                                        @Param("createUserId") Integer createUserId,
                                        @Param("startHour") String startHour,
                                        @Param("endHour") String endHour);


    List<CityProduct> productListByCity(CityProductQueryRequest request);


    List<CityProduct> findCityProductByCityId(CityProductSearchRequest request);

    List<CityProduct> listCityProductIdsByCityId(@Param("cityId") Integer cityId, @Param("isShare") Integer isShare, @Param("isStandard") Integer isStandard, @Param("cityProductIds") List<Integer> cityProductIds);

    void addOrUpdateMerchantProductRate(MerchantProductRate merchantProductRate);

    Integer createCityProduct(CityProduct cityProduct);

    void batchAddCityProduct(@Param("list") List<CityProduct> cityProducts);

    List<MerchantCityProduct> selectMerchantCityProduct(MerchantProductMarketRequest marketRequest);


    CityProduct loadCityProductByItemIdAndSkuId(@Param("cityId") Integer cityId, @Param("itemId") Long itemId, @Param("skuId") Long skuId);

}
