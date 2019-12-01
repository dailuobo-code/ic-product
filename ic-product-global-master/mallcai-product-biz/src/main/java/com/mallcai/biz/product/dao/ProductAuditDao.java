package com.mallcai.biz.product.dao;

import com.mallcai.biz.product.dao.mapper.AuditBillMapper;
import com.mallcai.biz.product.dao.mapper.AuditItemMapper;
import org.springframework.stereotype.Repository;

/**
 * ProductAuditDao
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 17:22<br/>
 */
@Repository
public class ProductAuditDao {
    private final AuditBillMapper auditBillMapper;
    private final AuditItemMapper auditItemMapper;

    public ProductAuditDao(AuditBillMapper auditBillMapper,
                           AuditItemMapper auditItemMapper) {
        this.auditBillMapper = auditBillMapper;
        this.auditItemMapper = auditItemMapper;
    }
}
