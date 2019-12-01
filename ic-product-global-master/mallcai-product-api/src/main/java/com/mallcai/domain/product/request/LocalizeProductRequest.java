package com.mallcai.domain.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizeProductRequest implements Serializable {

    private static final long serialVersionUID = 8040248620712154533L;
    /**
     * 商品ID
     */
    private List<Integer> productIds;

    /**
     * 用户id
     */
    private Integer userId;

}
