package com.mallcai.itemcenter.item.api.facade;

import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;

/**
 * ItemReadFacade
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:19<br/>
 */
public interface ItemReadFacade {
    /**
     * 分页获取商品列表
     */
    ICResponse<Paging<ItemInfo>> paging(ItemPagingRequest request);

    /**
     * 获取用于编辑的商品详情
     */
    ICResponse<FullItemWithDetailInfo> queryItemForEdit(FullItemQueryRequest request);

    /**
     * 获取商品、SKU，不包括详情，但是会返回轮播图
     */
    ICResponse<FullItemInfo> queryFullItem(FullItemQueryRequest request);

    /**
     * 获取商品、SKU、与商品详情
     */
    ICResponse<FullItemWithDetailInfo> queryFullItemWithDetail(FullItemWithDetailQueryRequest request);
}
