package com.mallcai.itemcenter.item.api.facade;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemDeleteRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateStatusRequest;
import com.mallcai.itemcenter.item.api.converter.input.ItemParamConverter;
import com.mallcai.itemcenter.item.api.converter.input.SkuParamConverter;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.service.ItemDomainWriteService;
import com.mallcai.itemcenter.utils.GenericConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * ItemWriteFacade
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:22<br/>
 */
@Slf4j
@Component
public class ItemWriteFacadeImpl implements ItemWriteFacade {

    private final SkuParamConverter skuParamConverter;
    private final ItemParamConverter itemParamConverter;
    private final ItemDomainWriteService itemDomainWriteService;

    public ItemWriteFacadeImpl(SkuParamConverter skuParamConverter,
                               ItemParamConverter itemParamConverter,
                               ItemDomainWriteService itemDomainWriteService) {
        this.skuParamConverter = skuParamConverter;
        this.itemParamConverter = itemParamConverter;
        this.itemDomainWriteService = itemDomainWriteService;
    }

    @Override
    public ICResponse<Long> create(ItemCreateRequest request) {
        try {
            FullItemBO fullItemBO = new FullItemBO();
            fullItemBO.setItem(itemParamConverter.dto2domain(request.getItemParam()));
            fullItemBO.setSkuList(GenericConverter.batchConvert(request.getSkuParamList(), skuParamConverter::dto2domain));
            fullItemBO.setItemDetail(itemParamConverter.dto2domain(request.getItemDetailParam()));

            Long itemId = itemDomainWriteService.createFullItem(fullItemBO, request.getSellerId());
            return ICResponse.ok(itemId);
        } catch (Exception e) {
            log.error("failed to create by {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> update(ItemUpdateRequest request) {
        try {
            FullItemBO fullItemBO = new FullItemBO();
            fullItemBO.setItem(itemParamConverter.dto2domain(request.getItemParam()));
            fullItemBO.setSkuList(GenericConverter.batchConvert(request.getSkuParamList(), skuParamConverter::dto2domain));
            fullItemBO.setItemDetail(itemParamConverter.dto2domain(request.getItemDetailParam()));

            Boolean isOk = itemDomainWriteService.updateFullItem(fullItemBO, request.getSellerId(), request.getUpdatedBy());
            return ICResponse.ok(isOk);
        } catch (Exception e) {
            log.error("failed to update by {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> delete(ItemDeleteRequest request) {
        try {
            Boolean isOk = itemDomainWriteService.delete(request.getTargetList(), request.getSellerId(), request.getUpdatedBy());
            return ICResponse.ok(isOk);
        } catch (Exception e) {
            log.error("failed to delete by {}, cause: {}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> sellerUpdateStatus(ItemUpdateStatusRequest request) {
        return null;
    }
}
