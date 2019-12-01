package com.dailuobo.ic.api.request.product;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AsyncProductShelveRequest implements Serializable {
    private Integer cityId;
    private Long itemId;
    private Integer status;
    private Integer updateBy;

    List<SkuSpec> skuSpecList;

    @Data
    public static class SkuSpec implements Serializable {
        private String barcode;
        private Double price;
        private Double disablePrice;
        private Integer saleType;
        private Integer threshold;
        private Double avgPrice;
        private Long skuId;


    }

}
