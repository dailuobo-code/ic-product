package com.mallcai.itemcenter.item.service;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.component.ItemAccompanySkuIndex;
import com.mallcai.itemcenter.item.component.ItemPersistenceHelper;
import com.mallcai.itemcenter.item.manager.ItemManager;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.model.FullItemOperateBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ItemDomainService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 11:46<br/>
 */
@Slf4j
@Service
public class ItemDomainWriteService {
    private final ItemManager itemManager;
    private final ItemPersistenceHelper itemPersistenceHelper;
    private final ItemAccompanySkuIndex itemAccompanySkuIndex;
    private final ItemDomainReadService itemDomainReadService;

    public ItemDomainWriteService(ItemManager itemManager,
                                  ItemPersistenceHelper itemPersistenceHelper,
                                  ItemAccompanySkuIndex itemAccompanySkuIndex,
                                  ItemDomainReadService itemDomainReadService) {
        this.itemManager = itemManager;
        this.itemPersistenceHelper = itemPersistenceHelper;
        this.itemAccompanySkuIndex = itemAccompanySkuIndex;
        this.itemDomainReadService = itemDomainReadService;
    }

    public Long createFullItem(FullItemBO fullItemBO, Long sellerId) {
        Item item = fullItemBO.getItem();
        List<Sku> skuList = fullItemBO.getSkuList();
        ItemDetail itemDetail = fullItemBO.getItemDetail();
        // todo: type & status check

        try {
            FullItemOperateBO resultPack = itemPersistenceHelper.createHandler(item, skuList, itemDetail, sellerId);

            Long itemId = itemManager.create(resultPack);
            // todo: send event
            // rocketMQSendHelper.sendMessage(new ItemCreateEvent(itemId));
            return itemId;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to create full item with fullItemBO: {}, shopId: {}, cause: {}",
                    fullItemBO, sellerId, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.create.fail");
        }
    }

    public Boolean updateFullItem(FullItemBO fullItemBO, Long sellerId, Long updatedBy) {
        try {
            Item item = fullItemBO.getItem();
            List<Sku> skuList = fullItemBO.getSkuList();
            ItemDetail itemDetail = fullItemBO.getItemDetail();

            // 回填下关键属性
            backFillKeyProperties(item.getId(), item, skuList);

            FullItemOperateBO bo = itemPersistenceHelper.updateHandler(item, skuList, itemDetail, sellerId, updatedBy);

            itemManager.update(bo);

            // 清除item <-> sku 双向id索引缓存
            itemAccompanySkuIndex.releaseCache(bo.getItem().getId());

            // todo: 发送业务消息
            // sendMessage(new ItemUpdateEvent(item.getId(), tenantId));
            return Boolean.TRUE;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to update full item with fullItemBO: {}, shopId: {}, updatedBy: {}, cause: {}", fullItemBO, sellerId, updatedBy, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.update.fail");
        }
    }

    /**
     * 回填关键属性
     *
     * @param itemId       商品id
     * @param toUpdateItem 待更新item信息
     * @param toUpdateSkus 待更新sku集合信息
     */
    private void backFillKeyProperties(Long itemId, Item toUpdateItem, @Nullable Collection<Sku> toUpdateSkus) {
        Item persistedItem = itemDomainReadService.findById(itemId);
        Assert.nonNull(persistedItem, "item.not.found");

        Assert.nonNull(toUpdateItem, "to.update.item.is.null");
        toUpdateItem.setType(persistedItem.getType());

        if (CollectionUtils.isEmpty(toUpdateSkus)) {
            return;
        }

        for (Sku toUpdateSku : toUpdateSkus) {
            toUpdateSku.setType(persistedItem.getType());
        }
    }

    public Boolean delete(List<IdVersionPair> targetList, Long sellerId, Long updatedBy) {
        try {
            for (IdVersionPair pair : targetList) {
                assertItemVersion(pair.getId(), sellerId, pair.getVersion());
            }

            Set<Long> itemIdSet = targetList.stream().map(IdVersionPair::getId).collect(Collectors.toSet());
            Set<Long> deletedSkuIdSet = itemManager.sellerDelete(targetList, sellerId, updatedBy);

            // sendMessage(new ItemDeleteEvent(new LinkedList<>(itemIdSet), new LinkedList<>(deletedSkuIdSet)));
            return Boolean.TRUE;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to delete item by targetList: {}, sellerId: {}, updatedBy: {}, cause: {}",
                    targetList, sellerId, updatedBy, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.delete.fail");
        }
    }

    private void assertItemVersion(Long itemId, Long sellerId, Integer version) {
        // todo: finish me
    }
}
