package com.mallcai.product.service;

import com.alibaba.fastjson.JSON;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.product.backend.PartnerProductService;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.FrozenProductDTO;
import com.mallcai.domain.product.request.GetFrozenProductRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PartnerProductServiceTest extends BaseTransactionalTest {

    @Autowired
    private PartnerProductService partnerProductService;

    @Test
    public void getProduct(){
        ICResponse<List<FrozenProductDTO>> response = partnerProductService.getFrozenProduct(new GetFrozenProductRequest());
        assertThat(response.isSuccess()).isTrue();
        System.out.println(JSON.toJSONString(response));
    }

}
