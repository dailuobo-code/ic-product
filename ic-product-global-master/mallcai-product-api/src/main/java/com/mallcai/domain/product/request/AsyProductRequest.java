package com.mallcai.domain.product.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AsyProductRequest implements Serializable {
    /**
     *
     */
    private Long itemIds;
    private List<Long> skuId;

    private List<Long> cityIdList;

    private Long sellerId;
}
