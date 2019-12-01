package com.dailuobo.service.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.ic.api.request.ProductUnShelveRequest;
import com.dailuobo.service.BaseRpcTest;
import com.dailuobo.service.annotation.DubboParameter;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author wangshifeng
 * @date 2019-08-06 11:20
 */
/*@RunWith(SpringRunner.class)*/
@DubboParameter(interfaceClass = MgrCityProductService.class, zookeeper = "10.111.27.2:2560", version = "1.0.0")
public class ProductServiceTest extends BaseRpcTest {

    @Test
    public void testDown() {
        ProductUnShelveRequest request = new ProductUnShelveRequest();
        ICResponse<Boolean> response = this.invoke(
                "down",
                new Class[]{ProductUnShelveRequest.class},
                new Object[]{request},
                new TypeReference<ICResponse<Boolean>>(){}
        );

        Assert.notNull(response);
        System.out.println(JSON.toJSONString(response));
    }


}
