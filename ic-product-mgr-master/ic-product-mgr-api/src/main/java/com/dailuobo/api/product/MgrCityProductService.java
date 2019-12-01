package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.util.ExcelData;
import com.dailuobo.api.domain.vo.*;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.request.GetCityProductListRequest;
import com.dailuobo.ic.api.request.ProductForecastFlagChangeRequest;
import com.dailuobo.ic.api.request.ProductUnShelveRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import org.apache.dubbo.validation.MethodValidated;

import java.util.List;
import java.util.Map;

public interface MgrCityProductService {

    /**
     * 根据条件进行城市商品列表查询
     *
     * @param request
     * @return
     */
    ICResponse<List<CityProduct>> selectAll(GetCityProductListRequest request);

    /**
     * 城市商品更新
     *
     * @param cityProduct
     * @return
     */
    ICResponse<Boolean> update(CityProduct cityProduct);

    /**
     * @param cityProductId
     * @return
     */
    ICResponse<Boolean> updateProductV2(Integer cityProductId);

    /**
     * @param cityId
     * @return
     */
    ICResponse<Boolean> establishExcelNewToOneCity(Integer cityId);

    ICResponse<List<ExcelData>> establishExcelNewToOneCityV2(Integer cityId);

    ICResponse<Boolean> saveSpec(Spec spec);

    /**
     * 城市商品上架服务
     *
     * @param cityProduct
     * @param spec
     * @return
     */
    ICResponse<Boolean> up(CityProduct cityProduct, Spec spec);

    ICResponse<Boolean> shelveProduct(Integer cityId, Integer cityProductId);

    /**
     * 预检测商品是否可以下架
     *
     * @param request
     * @return
     */
    ICResponse<Boolean> preDown(ProductUnShelveRequest request);

    /**
     * 城市商品下架
     *
     * @param request
     * @return
     */
    @MethodValidated
    ICResponse down(ProductUnShelveRequest request);

    /**
     * @param from
     * @param to
     * @param cityProductId
     * @return
     */
    ICResponse<List<ActProductSign>> selectByEffectTime(String from, String to, Integer cityProductId);

    /**
     * @param productSignTaskIds
     * @return
     */
    ICResponse<Boolean> deleteProductSignByTaskIds(List<Integer> productSignTaskIds);

    ICResponse<Boolean> delPointProductFromRedis(Integer cityId);

    ICResponse<List<String>> getShowNames(Integer cityId, Integer c1Id, Integer c2Id, String showName);

    ICResponse<List<DDLCityProduct>> getDDLCityProducts(Integer cityId, Integer c1Id, Integer c2Id, String showName);

    ICResponse<List<TACityProduct>> getTACityProducts(Integer cityId);

    ICResponse<List<TACityProduct>> getTACityProductsV2(Integer cityId, String keyword);

    ICResponse<List<TACityProduct>> getTACityProductsByKeyWord(TACityProductQueryDto dto);

    ICResponse<List<TACityProduct>> getOfflineTACityProductsV2(Integer cityId, String keyword);

    ICResponse<List<OfflineProductInventory>> getOfflineTACityProductList(Integer cityId, String keyword);

    ICResponse<List<TACityProduct>> getTACityProductsGift(Integer cityId);

    ICResponse<Boolean> newUserPro(CityProductVo cityProductVo);

    ICResponse<Boolean> underProduct(CityProduct cityProduct, UnSpec unSpec);

    ICResponse<Boolean> isUnder(CityProductVo cityProductVo);

    ICResponse<Boolean> vipLimit(CityProductVo cityProductVo);

    /**
     * 根据城市商品id获取 CityProductSyncCity
     *
     * @param cityProductId
     * @return
     */
    ICResponse<CityProductSyncCity> getCityProductById(Integer cityProductId);

    ICResponse<Spec> getDefaultSpecBySyncProduct(Integer cityProductId);

    /**
     * 根据城市id && 商品编号获取 CityProductSyncCity
     *
     * @param cityId
     * @param productNo
     * @return
     */
    ICResponse<CityProductSyncCity> getByCityIdAndProductNo(Integer cityId, String productNo);


    /**
     * 查询城市商品的分页查询
     * 入参product 限制100条
     * * @param request 城市商品查询请求
     *
     * @return
     */
    PlainResult<Paging<CityProductVo>> getCityProductList(ProductListQueryRequest request);

    PlainResult<CityProductVo> getSingleCityProductList(SingleCityProductQueryRequest singleCityProductQueryRequest);

    /**
     * 根据城市id和商品编号统计城市商品数量
     *
     * @param productNo
     * @param cityId
     * @return
     */
    ICResponse<Integer> getCountProductByProductNo(String productNo, Integer cityId);

    /**
     * 城市商品copy更新
     *
     * @param cityProductSyncCity
     * @return
     */
    ICResponse<Boolean> updateProductByUpdateCityIds(CityProductSyncCity cityProductSyncCity);

    /**
     * 城市商品拷贝
     *
     * @param cityProductSyncCity
     * @return
     */
    ICResponse<Boolean> cityProductCopyOtherCity(CityProductSyncCity cityProductSyncCity);

    ICResponse<Boolean> syncProductSpecCreate(Spec spec);

    ICResponse<Boolean> pointProduct(Integer userId, Integer cityProductId, Integer pointPrice);

    ICResponse<Boolean> isPoint(CityProductVo cityProductVo);

    ICResponse<StandardResult> checkStandardProduct(CityProductVo cityProductVo);

    ICResponse<List<CityProduct>> selectCityProductListByProductNos(Integer cityId, List<String> productNos);

    ICResponse<Boolean> batchDown(Integer userId, Integer cityId, List<Integer> cityProductIdList);

    ICResponse<Integer> queryCityProductId(String productNo, Integer cityId);

    ICResponse<Boolean> saveImportProductUpLog(List<ImportGroundingProduct> importProductList, Integer userId);

    ICResponse<Spec> getDefaultSpec(Integer cityProductId);

    ICResponse<List<CityProduct>> selectCityProductByCityProductIds(List<Integer> cityProductIds);

    ICResponse<List<TACityProduct>> getMsgListByCityId(OpenCity openCity);

    ICResponse<Boolean> updateCityProductStatus(Map<String, Object> param);

    ICResponse<List<CityProductIdAndMode>> getCityProductIds(List<Integer> hqProductIds);

    ICResponse<Boolean> updateByHqProductId(CityProduct cityProduct);

    ICResponse<List<TACityProduct>> getTACityProductsV3(Integer cityId, String keyword);


    /**
     * 商品预测属性变更
     *
     * @param productForecastFlagChangeRequest 请求入参
     * @return 是否处理成功
     */
    ICResponse<Boolean> changeForecastFlag(ProductForecastFlagChangeRequest productForecastFlagChangeRequest);

    /**
     * @param productCreateVos
     * @return
     */
    ICResponse<List<String>> createProductAllInOnce(List<ProductCreateVo> productCreateVos);

    /**
     * 对城市商品批量做上下架操作
     *
     * @param cityProductIds
     * @param cityStatus
     * @return
     */
    ICResponse<Boolean> batchUpdateCityStatus(Integer cityId, List<Integer> cityProductIds,
                                              Integer cityStatus, Integer userId);


    /**
     * 判断商品是否是采销预测商品
     *
     * @param cityId
     * @param cityProductIds
     * @return
     */
    ICResponse<Map<Integer, Boolean>> isTodayForcastProduct(Integer cityId, List<Integer> cityProductIds);

    /**
     * 批量查询商品是否有默认规格(城市规格）
     *
     * @param cityProductIds
     * @return
     */
    ICResponse<Map<Integer, Boolean>> isCityProductHasDefaultSpec(List<Integer> cityProductIds);


    /**
     * 查询商品是否存在
     *
     * @param cityProductId
     * @return
     */
    ICResponse<Boolean> isCityProductExist(Integer cityProductId);


    /**
     * 本市化拼多多商品并创建规格
     * @param localizeRequest
     * @return
     */
    ICResponse<Boolean> localizeProductToAllCity(List<ProductCreateVo> localizeRequest);

}
