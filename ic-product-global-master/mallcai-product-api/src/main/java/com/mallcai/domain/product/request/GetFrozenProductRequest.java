package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;

import java.io.Serializable;

/**
 * 冻品
 * @author tianjunwei
 * @date 2019-09-02
 */
public class GetFrozenProductRequest extends RequestEntity implements Serializable {


    /**
     * 冻品类型为 3
     */
    private Integer keepType;

    public Integer getKeepType() {
        return keepType;
    }

    public void setKeepType(Integer keepType) {
        this.keepType = keepType;
    }
}
