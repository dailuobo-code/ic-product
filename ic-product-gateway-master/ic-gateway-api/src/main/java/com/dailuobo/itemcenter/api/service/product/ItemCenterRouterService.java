package com.dailuobo.itemcenter.api.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.dto.merchant.MerchantProductMarketDTO;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.vo.CityProductVo;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.api.product.MgrProductWriteService;
import com.dailuobo.api.product.MgrHqProductForCityService;
import com.dailuobo.api.specification.SpecWriteService;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.goods.IProductGoodsRelService;
import com.dailuobo.ic.api.product.service.MgrCityProductReadService;
import com.dailuobo.ic.api.request.goods.QueryProductGoodsRelRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.dailuobo.ic.api.request.product.HqProductSyncExtraAttrRequest;
import com.dailuobo.ic.api.request.product.ProductListQueryRequest;
import com.dailuobo.ic.api.spec.SpecUpdateAsyncQueryRequest;
import com.dailuobo.ic.api.request.product.*;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.api.SOAStandardPage;
import com.mallcai.router.api.Router;
import com.mallcai.router.api.RouterType;
import com.mallcai.router.client.RouterReference;
import com.mallcai.service.api.ICityGlobalService;
import com.mallcai.service.api.IProductGoodsRelReadService;
import com.mallcai.service.api.IProductGroupWriteService;
import com.mallcai.service.erp.IProductPurchaseService;
import com.mallcai.service.request.GoodsProductRelQueryRequest;
import com.mallcai.service.request.ProductGoodsRelQueryRequest;
import com.mallcai.service.request.product.AsyProductCreateGroupRequest;
import com.mallcai.service.vo.Product4MgrVO;
import com.mallcai.service.vo.ProductGoodsRelVO;
import com.mallcai.service.vo.ProductPurchaseVO;

import java.util.List;
import java.util.Map;

/**
 * 城市商品网管路由接口
 */

public interface ItemCenterRouterService {

    @Router(group = "itemCenter", serviceClass = MgrCityProductReadService.class, methodName = "findCityProductByCityId", routeType = RouterType.SINGLE)
    PlainResult<List<CityProduct>> findCityProductByCityId(Map<Integer, CityProductSearchRequest> requestMap);

    @Router(group = "itemCenter", serviceClass = MgrCityProductReadService.class, methodName = "findCityProductByCityId", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<List<CityProduct>>> findMultiCityProductByCityId(Map<Integer, CityProductSearchRequest> requestMap);


    @Router(group = "itemCenter", serviceClass = MgrCityProductReadService.class, methodName = "productListByCity", routeType = RouterType.SINGLE)
    PlainResult<Paging<CityProduct>> productListByCity(Map<Integer, CityProductQueryRequest> request);

    /**
     * 根据城市商品id列表 批量查询商品相关信息
     *
     * <b> mgr使用,单笔最多返回30条 </b>
     *
     * @param map 城市Id=》商品Id列表
     * @return
     */

    @Router(group = "itemCenter", serviceClass = ICityGlobalService.class, methodName = "listStoreProduct", routeType = RouterType.SINGLE)
    Response<List<Product4MgrVO>> listStoreProduct(Map<Integer, List<Integer>> map);

    /**
     * 根据cityProdtid查询采购信息 -> 批量查询接口
     *
     * @param map
     * @return
     */
    @Router(group = "itemCenter", serviceClass = IProductPurchaseService.class, methodName = "getProductPurchaseInfo", routeType = RouterType.SINGLE)
    List<ProductPurchaseVO> getProductPurchaseInfo(Map<Integer, List<Integer>> map);


    /**
     * 分页查询商品数量，
     * 如果根据商品Id查询，商品Id限制100条
     *
     * @param request 提供城市商品查询接口
     * @return
     */
    @Router(group = "itemCenter", serviceClass = MgrCityProductService.class, methodName = "getCityProductList", routeType = RouterType.SINGLE)
    PlainResult<Paging<CityProductVo>> getCityProductList(Map<Integer, ProductListQueryRequest> request);


    @Router(group = "itemCenter", serviceClass = MgrCityProductService.class, methodName = "getSingleCityProductList", routeType = RouterType.SINGLE)
    PlainResult<CityProductVo> getSingleCityProductList(Map<Integer, SingleCityProductQueryRequest> singleCityProductQueryRequest);


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 商品货品相关服务接口 ~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * 多城商品货品关联关系"单笔"查询
     *
     * @param requestMap
     * @return
     */
    @Router(group = "itemCenter", serviceClass = IProductGoodsRelService.class, methodName = "queryByGoodsNo", routeType = RouterType.SINGLE)
    ICResponse<List<com.dailuobo.ic.api.vo.ProductGoodsRelVO>> queryByGoodsNo(Map<Integer, QueryProductGoodsRelRequest> requestMap);

    /**
     * 多城商品货品关联关系"批量"查询
     *
     * @param requestMap
     * @return
     */
    @Router(group = "itemCenter", serviceClass = IProductGoodsRelReadService.class, methodName = "queryByCityProductIds", routeType = RouterType.MULTI)
    Map<Integer, Response<List<ProductGoodsRelVO>>> queryByCityProductIds(Map<Integer, ProductGoodsRelQueryRequest> requestMap);


    /**
     * 多城商品货品关联关系"单笔"查询
     *
     * @param requestMap
     * @return
     */
    @Router(group = "itemCenter", serviceClass = IProductGoodsRelReadService.class, methodName = "queryByCityProductId", routeType = RouterType.MULTI)
    Map<Integer, Response<List<ProductGoodsRelVO>>> queryByCityProductId(Map<Integer, ProductGoodsRelQueryRequest> requestMap);


    /**
     * 根据货品编号批量查询 绑定的关联关系
     * <b>单次最多不超过 100 条</b>
     *
     * @param queryRequest
     * @return
     */
    @Router(group = "itemCenter", serviceClass = IProductGoodsRelReadService.class, methodName = "queryRelGoodsInfoByGoodNo", routeType = RouterType.MULTI)
    Map<Integer, Response<Map<String, List<ProductGoodsRelVO>>>> queryRelGoodsInfoByGoodNo(Map<Integer, GoodsProductRelQueryRequest> queryRequest);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 商品货品相关服务接口 END ~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * 总部商品同步到分城服务
     *
     * @param attrRequest
     * @return
     */
    @Router(group = "itemCenter", serviceClass = MgrHqProductForCityService.class, methodName = "syncHqProductExtraAttr", routeType = RouterType.MULTI)
    Map<Integer, ICResponse<Boolean>> syncHqProductExtraAttr(Map<Integer, HqProductSyncExtraAttrRequest> attrRequest);


    @Router(group = "itemCenter", serviceClass = MgrProductWriteService.class, methodName = "createProduct", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<Boolean>> multiCityCreateProduct(Map<Integer, CityProductCreateRequest> requestMap);


    /**
     * 新商品规格变更更新商品规格信息
     *
     * @param requestMap
     * @return
     */
    @Router(group = "itemCenter", serviceClass = SpecWriteService.class, methodName = "specUpdateAsy", routeType = RouterType.SINGLE)
    PlainResult<Boolean> specUpdateAsy(Map<Integer, SpecUpdateAsyncQueryRequest> requestMap);

    @Router(group = "itemCenter", serviceClass = IProductGroupWriteService.class, methodName = "asyProductCreateProductGroup", routeType = RouterType.SINGLE)
    Response<Integer> asyProductCreateProductGroup(Map<Integer, AsyProductCreateGroupRequest> requestMap);

    /**
     * 商家后台、营销配置分页获取商品列表
     *
     * @return
     */
    @Router(group = "itemCenter", serviceClass = MgrCityProductReadService.class, methodName = "getMerchantProductMarketPage", routeType = RouterType.SINGLE)
    PlainResult<SOAStandardPage<MerchantProductMarketDTO>> getMerchantProductMarketPage(Map<Integer, MerchantProductMarketRequest> requestMap);

    /**
     * 商家后台、营销配置 商品id获取商品
     *
     * @return
     */
    @Router(group = "itemCenter", serviceClass = MgrCityProductReadService.class, methodName = "getMerchantProductMarketList", routeType = RouterType.SINGLE)
    PlainResult<List<MerchantProductMarketDTO>> getMerchantProductMarketList(Map<Integer, MerchantProductMarketRequest> requestMap);


    @Router(group = "itemCenter", serviceClass = MgrProductWriteService.class, methodName = "asyncProductShelve", routeType = RouterType.SINGLE)
    PlainResult<Boolean> asyncProductShelve(Map<Integer, AsyncProductShelveRequest> requestMap);
}
