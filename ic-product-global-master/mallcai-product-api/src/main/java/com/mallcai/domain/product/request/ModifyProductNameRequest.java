package com.mallcai.domain.product.request;

import java.io.Serializable;

public class ModifyProductNameRequest implements Serializable {

    private static final long serialVersionUID = 8040248620712154533L;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 商品名称
     */
    String productName;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
