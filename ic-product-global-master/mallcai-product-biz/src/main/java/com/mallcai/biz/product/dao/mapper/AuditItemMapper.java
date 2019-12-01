package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.AuditItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * ProductAuditMapper
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 16:52<br/>
 */
public interface AuditItemMapper {
    void create(AuditItemDO item);

    void deleteByBill(Long billId);

    List<AuditItemDO> findByBill(Long billId);

    void creates(List<AuditItemDO> items);

    void update(AuditItemDO update);

    List<AuditItemDO> findByBillsAndItems(@Param("billIds") Set<Long> billIds, @Param("itemIds") Set<Long> itemIds);
}
