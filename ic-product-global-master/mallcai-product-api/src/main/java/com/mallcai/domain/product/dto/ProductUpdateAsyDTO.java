package com.mallcai.domain.product.dto;

import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.mallcai.domain.product.request.ModifyProductRequest;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class ProductUpdateAsyDTO implements Serializable {
    /**
     * 总部商品修改
     */
    public ModifyProductRequest modifyProductRequest;

    /**
     * 城市商品修改
     */
    public CityProductCreateRequest cityProductCreateRequest;
}
