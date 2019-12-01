package com.mallcai.product.repository;

import com.mallcai.BaseTransactionalTest;
import com.mallcai.backend.search.common.utils.JsonMapper;
import com.mallcai.biz.product.dao.mapper.AuditBillMapper;
import com.mallcai.biz.product.dao.mapper.AuditItemMapper;
import com.mallcai.biz.product.model.AuditBillDO;
import com.mallcai.biz.product.model.AuditItemDO;
import com.mallcai.domain.enums.AuditStatus;
import com.mallcai.domain.enums.AuditType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductAuditDaoTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 17:23<br/>
 */
public class ProductAuditDaoTest extends BaseTransactionalTest {
    @Autowired
    private AuditBillMapper auditBillMapper;
    @Autowired
    private AuditItemMapper auditItemMapper;

    private Long billId;
    private AuditBillDO bill;
    private AuditItemDO item;

    @Before
    public void setup() {
        bill = new AuditBillDO();
        bill.setType(AuditType.HQ_PRODUCT_CREATE.name());
        bill.setStatus(AuditStatus.NORMAL.name());
        bill.setComment("test");
        bill.setOperator("test-admin");
        bill.setOperatorId(11111);
        auditBillMapper.create(bill);
        billId = bill.getId();
        assertThat(bill.getId()).isNotNull();

        item = new AuditItemDO();
        item.setItemId(123123L);
        item.setType(bill.getType());
        item.setStatus(bill.getStatus());
        item.setBillId(billId);
        auditItemMapper.creates(Collections.singletonList(item));
        assertThat(item.getId()).isNotNull();
    }

    @After
    public void tearDown() {
        auditBillMapper.delete(billId);
        auditItemMapper.deleteByBill(billId);
    }

    @Test
    public void testBasic() {
        AuditBillDO bill = auditBillMapper.findById(billId);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(bill));
        assertThat(bill).isNotNull();

        List<AuditItemDO> items = auditItemMapper.findByBill(billId);
        System.out.println(JsonMapper.nonEmptyMapper().toJson(items));
        assertThat(items).isNotEmpty();
    }

    @Test
    public void testFindByBillsAndItems() {
        List<AuditItemDO> found = auditItemMapper.findByBillsAndItems(Collections.singleton(bill.getId()), Collections.singleton(item.getItemId()));
        System.out.println(found);
        assertThat(found).isNotEmpty();
    }
}
