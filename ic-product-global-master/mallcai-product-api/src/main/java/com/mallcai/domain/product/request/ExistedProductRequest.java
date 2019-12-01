package com.mallcai.domain.product.request;

import java.io.Serializable;

public class ExistedProductRequest implements Serializable {

    private static final long serialVersionUID = -2267625930020443788L;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 类目ID
     */
    private Integer categoryId;

    private String productName;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
