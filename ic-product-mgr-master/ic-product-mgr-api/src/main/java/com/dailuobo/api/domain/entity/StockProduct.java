package com.dailuobo.api.domain.entity;

import java.io.Serializable;

public class StockProduct extends CityProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer storeId;
    private String storeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
