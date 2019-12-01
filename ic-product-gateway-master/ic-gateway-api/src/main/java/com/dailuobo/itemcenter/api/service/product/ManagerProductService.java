package com.dailuobo.itemcenter.api.service.product;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.vo.ProductCreateVo;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.api.product.MgrProductWriteService;
import com.dailuobo.ic.api.request.GetCityProductListRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.router.api.Router;
import com.mallcai.router.api.RouterType;


import java.util.List;
import java.util.Map;

public interface ManagerProductService {
    @Router(group = "itemCenter", serviceClass = MgrCityProductService.class, methodName = "selectAll", routeType = RouterType.MULTI)
    Map<Integer,PlainResult<List<CityProduct>>> selectAllCityProduct(Map<Integer, GetCityProductListRequest> requestMap);

    @Router(group = "itemCenter" ,serviceClass = MgrProductWriteService.class, methodName = "createProduct" ,routeType = RouterType.MULTI)
    Map<Integer,PlainResult<Boolean>> createProduct(Map<Integer, CityProductCreateRequest> createRequestMap);


    /**
     * 创建指定城市的城市商品和规格信息
     * @param localizeRequest
     * @return
     */
    @Router(group = "itemCenter", serviceClass = MgrCityProductService.class, methodName =
            "localizeProductToAllCity", routeType = RouterType.MULTI)
    Map<Integer, ICResponse<Boolean>> localizeProductToAllCity(Map<Integer, List<ProductCreateVo>> localizeRequest);

}
