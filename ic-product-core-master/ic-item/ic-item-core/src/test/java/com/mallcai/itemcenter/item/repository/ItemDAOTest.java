package com.mallcai.itemcenter.item.repository;

import com.google.common.collect.ImmutableMap;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.DaoTestBootstrap;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.utils.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Item
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 11:50<br/>
 */
public class ItemDAOTest extends DaoTestBootstrap {
    private Item init;

    @Autowired
    private ItemDAO itemDAO;

    @Before
    public void setup() {
        init = create();
        itemDAO.create(init);
        assertThat(init.getId()).isNotNull();
    }

    private Item create() {
        Item c = new Item();
        c.setSpuId(1L);
        c.setCategoryId(1L);
        c.setItemCode("code");
        c.setSellerId(1L);
        c.setSellerName("seller");
        c.setServiceFeeTempId(1L);
        c.setName("name");
        c.setKeywords("keywords");
        c.setAdvertise("adv");
        c.setMainImage("img");
        c.setVideoUrl("video");
        c.setStatus(0);
        c.setType(0);
        c.setStoreType(0);
        c.setPickType(0);
        c.setHighPrice(100L);
        c.setLowPrice(100L);
        c.setSkuAttributesJson("[]");
        c.setOtherAttributesJson("[]");
        c.setExtraJson("{}");
        c.setHashCode("hashCode");
        c.setVersion(1);
        c.setUpdatedBy(1L);
        return c;
    }

    @Test
    public void testCrud() {
        Item found = itemDAO.findById(init.getId());
        assertThat(found).isNotNull();
    }

    @Test
    public void testPaging() {
        Item item2 = create();
        item2.setName("this-item-name-odd");
        item2.setItemCode("c2");
        item2.setSellerId(2L);
        item2.setCategoryId(2L);
        item2.setStatus(ItemStatus.ON_SHELF.getValue());
        itemDAO.create(item2);

        Map<String, Object> criteria = new PageInfo(1, 10).toMap();

        Paging<Item> page = itemDAO.paging(ImmutableMap.<String, Object>builder().putAll(criteria).put("nameLike", "item-name").build());
        assertThat(page.isEmpty()).isFalse();
        assertThat(page.getData().get(0).getName()).isEqualTo(item2.getName());

        page = itemDAO.paging(ImmutableMap.<String, Object>builder().putAll(criteria).put("sellerId", 2).build());
        assertThat(page.isEmpty()).isFalse();
        assertThat(page.getData().get(0).getSellerId()).isEqualTo(2);

        page = itemDAO.paging(ImmutableMap.<String, Object>builder().putAll(criteria).put("categoryId", 2).build());
        assertThat(page.isEmpty()).isFalse();
        assertThat(page.getData().get(0).getCategoryId()).isEqualTo(2);

        page = itemDAO.paging(ImmutableMap.<String, Object>builder().putAll(criteria).put("status", 1).build());
        assertThat(page.isEmpty()).isFalse();
        assertThat(page.getData().get(0).getStatus()).isEqualTo(1);
    }
}
