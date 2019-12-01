package com.mallcai.itemcenter.item.api.facade;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.api.converter.input.ItemParamConverter;
import com.mallcai.itemcenter.item.api.converter.output.ItemInfoConverter;
import com.mallcai.itemcenter.item.api.facade.internal.BackItemCategoryReadFacade;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.service.ItemDomainReadService;
import com.mallcai.itemcenter.utils.GenericConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * ItemReadFacadeImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:22<br/>
 */
@Slf4j
@Component
public class ItemReadFacadeImpl implements ItemReadFacade {
    private final ItemParamConverter itemParamConverter;
    private final ItemInfoConverter itemInfoConverter;
    private final ItemDomainReadService itemDomainReadService;

    public ItemReadFacadeImpl(ItemParamConverter itemParamConverter,
                              ItemInfoConverter itemInfoConverter,
                              ItemDomainReadService itemDomainReadService) {
        this.itemParamConverter = itemParamConverter;
        this.itemInfoConverter = itemInfoConverter;
        this.itemDomainReadService = itemDomainReadService;
    }

    @Override
    public ICResponse<Paging<ItemInfo>> paging(ItemPagingRequest request) {
        // todo: 使用试图查询替换
        try {
            Paging<Item> items = itemDomainReadService.paging(request);
            return ICResponse.ok(GenericConverter.batchConvert(items, itemInfoConverter::domain2dto));
        } catch (Exception e) {
            log.error("failed to paging with {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<FullItemWithDetailInfo> queryItemForEdit(FullItemQueryRequest request) {
        try {
            FullItemBO fullItemBO = itemDomainReadService.findFullItemByIdWithDetail(request.getItemId(), false);
            if (fullItemBO.getItem() == null || fullItemBO.getItemDetail() == null || CollectionUtils.isEmpty(fullItemBO.getSkuList())) {
                return ICResponse.fail("item.not.found");
            }

            // todo: 1. 提供扩展入口
            // todo: 2. 聚合类目属性

            return ICResponse.ok(itemInfoConverter.getWithDetail(fullItemBO));
        } catch (Exception e) {
            log.error("failed to queryItemForEdit with {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<FullItemInfo> queryFullItem(FullItemQueryRequest request) {
        try {
            FullItemBO fullItemBO = itemDomainReadService.findFullItemById(request.getItemId());
            if (fullItemBO.getItem() == null || fullItemBO.getItemDetail() == null || CollectionUtils.isEmpty(fullItemBO.getSkuList())) {
                return ICResponse.fail("item.not.found");
            }

            // todo: 提供扩展入口

            return ICResponse.ok(itemInfoConverter.getWithImgs(fullItemBO));
        } catch (Exception e) {
            log.error("failed to queryFullItem with {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<FullItemWithDetailInfo> queryFullItemWithDetail(FullItemWithDetailQueryRequest request) {
        try {
            FullItemBO fullItemBO = itemDomainReadService.findFullItemByIdWithDetail(request.getItemId());
            if (fullItemBO.getItem() == null || fullItemBO.getItemDetail() == null || CollectionUtils.isEmpty(fullItemBO.getSkuList())) {
                return ICResponse.fail("item.not.found");
            }

            // todo: 提供扩展入口

            return ICResponse.ok(itemInfoConverter.getWithDetail(fullItemBO));
        } catch (Exception e) {
            log.error("failed to queryFullItemWithDetail with {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }
}
