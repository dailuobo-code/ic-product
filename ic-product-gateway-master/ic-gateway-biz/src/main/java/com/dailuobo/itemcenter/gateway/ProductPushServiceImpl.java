/*
package com.dailuobo.itemcenter.gateway;

import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.itemcenter.api.service.product.ManagerProductService;
import com.dailuobo.itemcenter.api.service.product.ProductPushService;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.router.client.RouterReference;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

*/
/**
 * 商品同步服务
 *//*

@Service
public class ProductPushServiceImpl implements ProductPushService {
    @RouterReference
    ManagerProductService itemCenterRouterService;

    @Autowired
    private MgrProductService mgrProductService;

    */
/**
     * @param request
     * @return
     *//*

    @Override
    public PlainResult<Boolean> pushProduct(ItemCreateRequest request) {
        //转换总部商品的创建入参
        */
/*List<AddProductRequest> addProductRequests = Item2ProductConvert.convert2AddHqProductRequest(request);
        //转换城市商品的创建入参
        List<CityProductCreateRequest> cityProductCreateRequests = Item2ProductConvert.convert2AddCityProductRequest(request);
        addProductRequests.forEach(t->mgrProductService.addProduct(t));
        cityProductCreateRequests.forEach(t->{
            Map<Integer,CityProductCreateRequest> map = new HashedMap();
            Map<Integer, PlainResult<Boolean>> product = managerProductService.createProduct(map);
        });*//*

        Map<Integer,CityProductCreateRequest> re = new HashedMap();
        CityProductCreateRequest request1 = new CityProductCreateRequest();
        request1.setOrigin("1");
        re.put(30,request1);
        itemCenterRouterService.createProduct(re);
        return null;
    }
}
*/
