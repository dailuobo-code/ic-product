package com.mallcai.itemcenter.sku.repository;

import com.mallcai.itemcenter.DaoTestBootstrap;
import com.mallcai.itemcenter.sku.model.Sku;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sku
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 14:17<br/>
 */
public class SkuDAOTest extends DaoTestBootstrap {
    private Sku init;

    @Autowired
    private SkuDAO sku;

    @Before
    public void setup() {
        init = create();
        sku.create(init);

        assertThat(init.getId()).isNotNull();
    }

    private Sku create() {
        Sku c = new Sku();
        c.setId(0L);
        c.setItemId(0L);
        c.setSkuTemplateId(0L);
        c.setSkuCode("sku_code");
        c.setBarcode("barcode");
        c.setOuterId("outer_id");
        c.setSellerId(0L);
        c.setName("name");
        c.setImage("image");
        c.setPrice(0);
        c.setStatus(0);
        c.setPriceJson("{}");
        c.setAttrsJson("[]");
        c.setExtraJson("{}");
        c.setVersion(1);
        return c;
    }

    @Test
    public void testCrud() {
        Sku found = sku.findById(init.getId());
        assertThat(found).isNotNull();
    }
}
