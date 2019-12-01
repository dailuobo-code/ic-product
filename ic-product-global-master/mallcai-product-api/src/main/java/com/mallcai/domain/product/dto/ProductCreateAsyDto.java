package com.mallcai.domain.product.dto;

import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.mallcai.domain.product.request.AddProductRequest;
import com.mallcai.domain.product.request.ProductCreateAsyncRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCreateAsyDto implements Serializable {
    /**
     * 新增总部商品请求
     */
    public AddProductRequest addProductRequest;

    /**
     * 新增城市商品请求
     */
    public CityProductCreateRequest cityProductCreateRequest;
}
