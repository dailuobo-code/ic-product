package com.mallcai.itemcenter.item.service;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.model.ItemDetail;
import com.mallcai.itemcenter.item.repository.ItemDetailDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ItemDetailDomainReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/22 18:08<br/>
 */
@Slf4j
@Service
public class ItemDetailDomainReadService {
    private final ItemDetailDAO itemDetailDAO;

    public ItemDetailDomainReadService(ItemDetailDAO itemDetailDAO) {
        this.itemDetailDAO = itemDetailDAO;
    }

    public ItemDetail findByItemId(Long itemId, boolean cached) {
        try {
            if (cached) {
                return itemDetailDAO.findByItemIdCached(itemId);
            } else {
                return itemDetailDAO.findByItemId(itemId);
            }
        } catch (Exception e) {
            log.error("fail to query item detail by itemId: {}, cause: {}", itemId, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.detail.find.fail");
        }
    }
}
