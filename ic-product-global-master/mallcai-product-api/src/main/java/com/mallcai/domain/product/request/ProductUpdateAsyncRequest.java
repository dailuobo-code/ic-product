package com.mallcai.domain.product.request;

import com.mallcai.itemcenter.item.api.bean.response.item.ItemDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductUpdateAsyncRequest implements Serializable {
    private static final long serialVersionUID = 1962283524002054132L;
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
     * 发布城市列表
     */
    private List<Long> cityIds;
}
