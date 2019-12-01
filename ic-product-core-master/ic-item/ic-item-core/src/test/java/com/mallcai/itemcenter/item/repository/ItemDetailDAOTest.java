package com.mallcai.itemcenter.item.repository;

import com.mallcai.itemcenter.DaoTestBootstrap;
import com.mallcai.itemcenter.item.model.ItemDetail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ItemDetail
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:04<br/>
 */
public class ItemDetailDAOTest extends DaoTestBootstrap {
    private ItemDetail init;

    @Autowired
    private ItemDetailDAO itemDetail;

    @Before
    public void setup() {
        init = create();
        itemDetail.create(init);

        assertThat(init.getId()).isNotNull();
    }

    private ItemDetail create() {
        ItemDetail c = new ItemDetail();
        c.setItemId(1L);
        c.setImageJson("[]");
        c.setRemark("remark");
        c.setDetail("detail");
        c.setExtraJson("{}");
        c.setUpdatedBy(1L);
        return c;
    }

    @Test
    public void testCrud() {
        ItemDetail found = itemDetail.findById(init.getId());
        assertThat(found).isNotNull();
    }
}
