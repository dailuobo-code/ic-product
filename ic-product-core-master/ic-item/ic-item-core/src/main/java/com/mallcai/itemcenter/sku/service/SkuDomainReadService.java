package com.mallcai.itemcenter.sku.service;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.sku.repository.SkuDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SkuDomainReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/22 17:59<br/>
 */
@Slf4j
@Service
public class SkuDomainReadService {
    private final SkuDAO skuDAO;

    public SkuDomainReadService(SkuDAO skuDAO) {
        this.skuDAO = skuDAO;
    }

    public List<Sku> findByItemId(Long itemId, boolean cached) {
        try {
            if (cached) {
                List<Long> skuIdList = skuDAO.findSkuIdByItemId(itemId);
                return findByIdSet(new HashSet<>(skuIdList));
            } else {
                return skuDAO.findByItemId(itemId);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to query sku by itemId: {}, cause: {}", itemId, Throwables.getStackTraceAsString(e));
            throw new ServiceException("sku.find.fail");
        }
    }


    public List<Sku> findByIdSet(Set<Long> idSet) {
        if (CollectionUtils.isEmpty(idSet)) {
            return Collections.emptyList();
        }

        try {
            // 批量查询方法，用于轮询获取带缓存的结果值
            return idSet.stream()
                    .map(skuDAO::findById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("fail to query sku list by idSet: {}, cause: {}", idSet, Throwables.getStackTraceAsString(e));
            throw new ServiceException("sku.find.fail");
        }
    }
}
