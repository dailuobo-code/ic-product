package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.AuditBillDO;

import java.util.List;
import java.util.Set;

/**
 * ProductAuditMapper
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 16:52<br/>
 */
public interface AuditBillMapper {
    void create(AuditBillDO bill);

    void delete(Long billId);

    AuditBillDO findById(Long billId);

    void update(AuditBillDO bill);

    List<AuditBillDO> findByIds(Set<Long> billIds);
}
