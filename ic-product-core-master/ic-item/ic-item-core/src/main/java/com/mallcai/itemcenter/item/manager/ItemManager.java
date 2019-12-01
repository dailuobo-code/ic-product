package com.mallcai.itemcenter.item.manager;

import com.google.common.collect.Sets;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.component.Digestor;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.item.model.FullItemOperateBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import com.mallcai.itemcenter.item.repository.ItemDAO;
import com.mallcai.itemcenter.item.repository.ItemDetailDAO;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.sku.repository.SkuDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ItemManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:36<br/>
 */
@Slf4j
@Component
public class ItemManager {
    private final ItemDAO itemDAO;
    private final SkuDAO skuDAO;
    private final ItemDetailDAO itemDetailDAO;

    public ItemManager(ItemDAO itemDAO,
                       SkuDAO skuDAO,
                       ItemDetailDAO itemDetailDAO) {
        this.itemDAO = itemDAO;
        this.skuDAO = skuDAO;
        this.itemDetailDAO = itemDetailDAO;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long create(FullItemOperateBO bo) {
        // 前置扩展
        beforeCreateExtension(bo);

        Item item = bo.getItem();
        List<Sku> skuList = bo.getToCreateSkuList();
        ItemDetail itemDetail = bo.getItemDetail();

        Boolean isOk;

        // PART 1: 创建item对象
        isOk = itemDAO.create(item);
        Assert.isTrue(isOk, "item.create.fail");

        if (log.isDebugEnabled()) {
            log.debug("[ITEM CREATE] Successfully(TRA) create item with: {}", item);
        }

        // PART 2: 创建sku对象集合
        for (Sku sku : skuList) {
            // todo: 使用生成的 ID
            sku.setItemId(item.getId());
            isOk = skuDAO.create(sku);
            Assert.isTrue(isOk, "sku.create.fail");

            if (log.isDebugEnabled()) {
                log.debug("[ITEM CREATE] Successfully(TRA) create sku with: {}", sku);
            }
        }

        // PART 3: 创建itemDetail对象
        // todo: 使用生成的 id
        itemDetail.setItemId(item.getId());
        isOk = itemDetailDAO.create(itemDetail);
        Assert.isTrue(isOk, "item.detail.create.fail");

        if (log.isDebugEnabled()) {
            log.debug("[ITEM CREATE] Successfully(TRA) create item detail with: {}", itemDetail);
        }

        // 后置扩展
        onCreateExtension(bo);

        // todo: 位标事务处理

        return item.getId();
    }

    private void beforeCreateExtension(FullItemOperateBO bo) {
        // todo: 业务扩展
    }


    // todo: 业务扩展
    private void onCreateExtension(FullItemOperateBO bo) {
        // todo: 业务生成编码

        Item i = new Item();
        i.setId(bo.getItem().getId());
        i.setItemCode(i.getId().toString());
        i.setVersion(0);

        bo.getItem().setItemCode(i.getItemCode());
        String hash = Digestor.itemDigest(bo.getItem(), bo.getItemDetail());
        i.setHashCode(hash);
        Boolean isOk = itemDAO.update(i);
        Assert.isTrue(isOk, "item.code.update.fail");

        for (Sku sku : bo.getToCreateSkuList()) {
            // 使用主键作为默认 sku code
            if (StringUtils.isEmpty(sku.getSkuCode())) {
                Sku s = new Sku();
                s.setId(sku.getId());
                s.setSkuCode(s.getId().toString());
                s.setVersion(1);
                isOk = skuDAO.update(s);
                Assert.isTrue(isOk, "sku.code.update.fail");
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Set<Long> update(FullItemOperateBO bo) {

        // 前置扩展
        beforeUpdateExtension(bo);

        Set<Long> originalSkuIdSet = new HashSet<>();

        Boolean isOk;

        // PART 1: 更新item
        isOk = itemDAO.update(bo.getItem());
        Assert.isTrue(isOk, "item.update.fail");

        if (log.isDebugEnabled()) {
            log.debug("[ITEM UPDATE] Successfully(TRA) update item with: {}", bo.getItem());
        }

        // PART 2: 更新sku
        for (Sku sku : bo.getToUpdateSkuList()) {
            isOk = skuDAO.update(sku);
            Assert.isTrue(isOk, "sku.update.fail");

            if (log.isDebugEnabled()) {
                log.debug("[ITEM UPDATE] Successfully(TRA) update sku with: {}", sku);
            }

            originalSkuIdSet.add(sku.getId());
        }

        // PART 3: 删除sku
        for (Sku sku : bo.getToDeleteSkuList()) {
            isOk = skuDAO.updateStatus(sku.getId(), sku.getVersion(), ItemStatus.DELETED.getValue(), sku.getUpdatedBy());

            Assert.isTrue(isOk, "sku.delete.fail");

            if (log.isDebugEnabled()) {
                log.debug("[ITEM UPDATE] Successfully(TRA) delete sku with: {}", sku);
            }

            originalSkuIdSet.add(sku.getId());
        }

        // PART 4: 创建sku
        for (Sku sku : bo.getToCreateSkuList()) {
            isOk = skuDAO.create(sku);
            Assert.isTrue(isOk, "sku.create.fail");

            if (log.isDebugEnabled()) {
                log.debug("[ITEM UPDATE] Successfully(TRA) create sku with: {}", sku);
            }
        }

        // PART 5: 更新itemDetail
        isOk = itemDetailDAO.updateByItemId(bo.getItemDetail());
        Assert.isTrue(isOk, "item.detail.update.fail");

        if (log.isDebugEnabled()) {
            log.debug("[ITEM UPDATE] Successfully(TRA) update itemDetail with: {}", bo.getItemDetail());
        }

        // PART 6: 更新item->sku索引
        skuDAO.releaseItemIdIndex(bo.getItem().getId());

        // 后置扩展
        onUpdateExtension(bo);

        // todo: 处理 feature

        return originalSkuIdSet;
    }

    private void beforeUpdateExtension(FullItemOperateBO bo) {
        // todo: 前置扩展
    }

    private void onUpdateExtension(FullItemOperateBO bo) {
        // todo: 后置扩展
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Set<Long> sellerDelete(Long itemId, Long sellerId, Integer version, Long updatedBy) {

        Boolean isOk;

        // PART 1: 删除Item
        isOk = itemDAO.updateStatusCheckSellerId(itemId, version, ItemStatus.DELETED.getValue(), sellerId, updatedBy);

        if (!isOk) {
            throw new ServiceException("item.delete.fail");
        }

        if (log.isDebugEnabled()) {
            log.debug("[ITEM DELETE] Successfully(TRA) delete item by id: {}", itemId);
        }

        // PART 2: 删除sku
        List<Sku> skuList = skuDAO.findByItemId(itemId);

        if (CollectionUtils.isEmpty(skuList)) {
            throw new ServiceException("data.error.for.missing.sku");
        }

        Set<Long> deletedSkuIdSet = Sets.newLinkedHashSetWithExpectedSize(skuList.size());

        for (Sku sku : skuList) {
            isOk = skuDAO.updateStatusCheckSellerId(sku.getId(), sku.getVersion(), ItemStatus.DELETED.getValue(), sellerId, updatedBy);

            if (!isOk) {
                throw new ServiceException("sku.delete.fail");
            }

            if (log.isDebugEnabled()) {
                log.debug("[ITEM DELETE] Successfully(TRA) delete sku by id: {}", sku.getId());
            }

            deletedSkuIdSet.add(sku.getId());
        }

        // PART 3: 更新item->sku索引
        skuDAO.releaseItemIdIndex(itemId);
        // PART 4: 失效缓存
        itemDetailDAO.invalid(itemId);

        return deletedSkuIdSet;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Set<Long> sellerDelete(List<IdVersionPair> targetList, Long sellerId, Long updatedBy) {
        Set<Long> deletedSkuIdSet = new HashSet<>();

        for (IdVersionPair pair : targetList) {
            Set<Long> partial = this.sellerDelete(pair.getId(), sellerId, pair.getVersion(), updatedBy);
            deletedSkuIdSet.addAll(partial);
        }

        return deletedSkuIdSet;
    }
}
