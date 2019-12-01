package com.mallcai.itemcenter.item.manager;

import com.google.common.collect.Sets;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.item.repository.CityItemDAO;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.repository.CitySkuDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CityItemManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:27<br/>
 */
@Slf4j
@Component
public class CityItemManager {
    private final CityItemDAO cityItemDAO;
    private final CitySkuDAO citySkuDAO;

    public CityItemManager(CityItemDAO cityItemDAO, CitySkuDAO citySkuDAO) {
        this.cityItemDAO = cityItemDAO;
        this.citySkuDAO = citySkuDAO;
    }

    @Transactional
    public Boolean creates(List<CityItem> cityItems, List<CitySku> citySkus) {
        cityItemDAO.creates(cityItems);
        citySkuDAO.creates(citySkus);
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Set<Long> sellerUpdateStatus(List<IdVersionPair> targetList, Long cityId, Long sellerId, Integer status, Long updatedBy) {
        Set<Long> updatedSkuIdSet = new HashSet<>();

        for (IdVersionPair pair : targetList) {
            Set<Long> partial = this.sellerUpdateStatus(pair.getId(), pair.getVersion(), cityId, sellerId, status, updatedBy);
            updatedSkuIdSet.addAll(partial);
        }

        return updatedSkuIdSet;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Set<Long> sellerUpdateStatus(Long id, Integer version, Long cityId, Long sellerId, Integer status, Long updatedBy) {
        Boolean isOk;

        // 更新item
        isOk = cityItemDAO.updateStatusCheckSellerId(id, version, status, sellerId, updatedBy);
        if (!isOk) {
            throw new ServiceException("city.item.update.fail");
        }
        if (log.isDebugEnabled()) {
            log.debug("[CITY ITEM UPDATE] Successfully(TRA) update item status by id: {}", id);
        }

        // PART 2: 更新sku
        /*CityItem cityItem = cityItemDAO.findById(id);
        List<CitySku> skuList = citySkuDAO.findByItemIdAndCityId(cityItem.getItemId(), cityId);
        if (CollectionUtils.isEmpty(skuList)) {
            throw new ServiceException("data.error.for.missing.city.sku");
        }

        Set<Long> updatedSkuIdSet = Sets.newLinkedHashSetWithExpectedSize(skuList.size());
        for (CitySku sku : skuList) {
            isOk = citySkuDAO.updateStatusCheckSellerId(sku.getId(), sku.getVersion(), status, sellerId, updatedBy);

            if (!isOk) {
                throw new ServiceException("city.sku.update.status.fail");
            }

            if (log.isDebugEnabled()) {
                log.debug("[ITEM UPDATE] Successfully(TRA) update sku status by id: {}", sku.getId());
            }
            updatedSkuIdSet.add(sku.getId());
        }*/
        return Sets.newHashSet();
    }

    @Transactional
    public Boolean update(CityItem cityItem, List<CitySku> toUpdate, Long sellerId) {
        Boolean isOk;
        for (CitySku citySku : toUpdate) {
            // todo: check seller
            isOk = citySkuDAO.update(citySku);
            if (!isOk) {
                throw new ServiceException("city.sku.update.fail");
            }
        }
        return true;
    }
}
