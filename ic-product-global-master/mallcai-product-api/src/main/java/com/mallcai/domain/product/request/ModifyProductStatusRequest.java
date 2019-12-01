package com.mallcai.domain.product.request;

import java.io.Serializable;
import java.util.List;

public class ModifyProductStatusRequest implements Serializable {

    private static final long serialVersionUID = 5378237041493821063L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 商品id列表
     */
    private List<Integer> productIds;
    /**
     * 总部状态 0:下架 1:上架
     */
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
