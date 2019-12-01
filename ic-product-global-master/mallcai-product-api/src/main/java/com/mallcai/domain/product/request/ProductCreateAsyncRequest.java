package com.mallcai.domain.product.request;


import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.ic.dto.spec.SkuAttributeDTO;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ImageParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemDetailParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.SkuParam;
import com.mallcai.itemcenter.item.api.bean.response.item.ImageInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuAttributeInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class ProductCreateAsyncRequest implements Serializable {

    private static final long serialVersionUID = -357641658291856165L;
    /**
     *
     */
    private ItemInfo itemInfo;
    /**
     * sku列表
     */
    private List<SkuInfo> skuInfoList;
    /**
     *
     */
    private ItemDetailInfo itemDetailInfo;
    /**
     * 卖家Id
     */
    private Long sellerId;

    /**
     * 关联城市
     */
    private List<Long> distributeCityIdList;

    public List<Long> getDistributeCityIdList() {
        return distributeCityIdList;
    }

    public void setDistributeCityIdList(List<Long> distributeCityIdList) {
        this.distributeCityIdList = distributeCityIdList;
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public List<SkuInfo> getSkuInfoList() {
        return skuInfoList;
    }

    public void setSkuInfoList(List<SkuInfo> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }


    public ItemDetailInfo getItemDetailInfo() {
        return itemDetailInfo;
    }

    public void setItemDetailInfo(ItemDetailInfo itemDetailInfo) {
        this.itemDetailInfo = itemDetailInfo;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }


}
