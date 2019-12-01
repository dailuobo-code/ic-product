package com.mallcai.itemcenter.item.repository;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mallcai.itemcenter.DaoTestBootstrap;
import com.mallcai.itemcenter.item.model.CityItem;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CityItem
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 14:37<br/>
 */
public class CityItemDAOTest extends DaoTestBootstrap {
    private CityItem init;

    @Autowired
    private CityItemDAO cityItemDAO;

    @Before
    public void setup() {
        init = create(2L);
        cityItemDAO.create(init);

        assertThat(init.getId()).isNotNull();
    }

    private CityItem create(Long itemId) {
        CityItem c = new CityItem();
        c.setItemId(itemId);
        c.setCityId(1L);
        c.setCategoryId(1L);
        c.setSellerId(1L);
        c.setSellerName("seller");
        c.setStatus(0);
        c.setUpdatedBy(1L);
        return c;
    }

    @Test
    public void testCrud() {
        CityItem found = cityItemDAO.findById(init.getId());
        assertThat(found).isNotNull();
    }

    @Test
    public void testListBy() {
        CityItem c2 = create(2L);
        cityItemDAO.create(c2);

        List<CityItem> items = cityItemDAO.list(ImmutableMap.of("itemIds", Lists.newArrayList(1, 2)));
        assertThat(items).hasSize(2);
    }
}
