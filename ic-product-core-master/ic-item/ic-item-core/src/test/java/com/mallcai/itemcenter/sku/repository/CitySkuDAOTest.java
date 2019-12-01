package com.mallcai.itemcenter.sku.repository;

import com.mallcai.itemcenter.DaoTestBootstrap;
import com.mallcai.itemcenter.sku.model.CitySku;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CitySku
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 14:39<br/>
 */
public class CitySkuDAOTest extends DaoTestBootstrap {
    private CitySku init;

    @Autowired
    private CitySkuDAO citySkuDAO;

    @Before
    public void setup() {
        init = create();
        citySkuDAO.create(init);

        assertThat(init.getId()).isNotNull();
    }

    private CitySku create() {
        CitySku c = new CitySku();
        c.setItemId(1L);
        c.setCityId(1L);
        c.setSkuId(1L);
        c.setStatus(0);
        c.setUpdatedBy(1L);
        return c;
    }

    @Test
    public void testCrud() {
        CitySku found = citySkuDAO.findById(init.getId());
        assertThat(found).isNotNull();
    }
}
