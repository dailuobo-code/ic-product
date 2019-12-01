package com.mallcai.domain.product.request;

import com.mallcai.itemcenter.item.api.bean.response.item.ItemDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品绑定城市同步
 */

@Data
public class DistributedCityProductAsyncRequest implements Serializable {
    private static final long serialVersionUID = -2049610556072001851L;

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
     * 需要同步的城市列表
     */
    private List<Long> distributeCityIdList;
}
