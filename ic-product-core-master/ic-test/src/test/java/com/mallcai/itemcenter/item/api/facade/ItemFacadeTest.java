package com.mallcai.itemcenter.item.api.facade;

import com.google.common.collect.Lists;
import com.mallcai.itemcenter.IntegrationTestBootstrap;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.item.api.bean.request.item.FullItemWithDetailQueryRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemDeleteRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import com.mallcai.itemcenter.item.repository.ItemDAO;
import com.mallcai.itemcenter.item.repository.ItemDetailDAO;
import com.mallcai.itemcenter.utils.JsonMapper;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ItemWriteFacadeTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 20:14<br/>
 */
public class ItemFacadeTest extends IntegrationTestBootstrap {
    @Autowired
    private ItemWriteFacade itemWriteFacade;
    @Autowired
    private ItemReadFacade itemReadFacade;

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private ItemDetailDAO itemDetailDAO;

    private Long itemId = 0L;

    @After
    public void cleanup() {
        itemDAO.invalid(itemId);
        itemDetailDAO.invalid(itemId);
    }

    public void setup() throws IOException {
        String data = loadInputJson("ItemWriteFacadeTest_testCreateItem");
        ItemCreateRequest r = JsonMapper.nonEmptyMapper().fromJson(data, ItemCreateRequest.class);
        ICResponse<Long> createR = itemWriteFacade.create(r);
        assertThat(createR.isSuccess()).isTrue();
        assertThat(createR.getResult()).isNotNull();
        System.out.println("created item, id: " + createR.getResult());
        itemId = createR.getResult();
    }

    @Test
    public void testCreateItem() throws IOException {
        setup();

        ICResponse<FullItemWithDetailInfo> fullItemR = itemReadFacade.queryFullItemWithDetail(FullItemWithDetailQueryRequest.builder().itemId(itemId).build());
        assertThat(fullItemR.isSuccess()).isTrue();
        assertThat(fullItemR.getResult()).isNotNull();
        String findJ = JsonMapper.nonEmptyMapper().toJson(removeTime(fullItemR.getResult()));
        System.out.println("found: " + findJ);
        assertThat(findJ).isEqualTo(loadOutputJson("ItemWriteFacadeTest_testFindById"));
    }

    @Test
    public void testUpdateItem() throws IOException {
        setup();

        String data = loadInputJson("ItemWriteFacadeTest_testUpdateItem");
        ItemUpdateRequest update = JsonMapper.nonEmptyMapper().fromJson(data, ItemUpdateRequest.class);
        ICResponse<Boolean> updateR = itemWriteFacade.update(update);
        assertThat(updateR.isSuccess()).isTrue();
        assertThat(updateR.getResult()).isTrue();

        ICResponse<FullItemWithDetailInfo> fullItemR = itemReadFacade.queryFullItemWithDetail(FullItemWithDetailQueryRequest.builder().itemId(itemId).build());
        assertThat(fullItemR.isSuccess()).isTrue();
        assertThat(fullItemR.getResult()).isNotNull();
        String findJ = JsonMapper.nonEmptyMapper().toJson(removeTime(fullItemR.getResult()));
        System.out.println("found: " + findJ);
        assertThat(findJ).isEqualTo(loadOutputJson("ItemWriteFacadeTest_testFindById_updated"));
    }

    @Test
    public void testDeleteItem() throws IOException {
        setup();

        ICResponse<Boolean> deleteR = itemWriteFacade.delete(ItemDeleteRequest.builder()
                .targetList(Lists.newArrayList(new IdVersionPair(itemId, 1)))
                .sellerId(1L)
                .build());
        assertThat(deleteR.isSuccess()).isTrue();
        assertThat(deleteR.getResult()).isTrue();

        ICResponse<FullItemWithDetailInfo> fullItemR = itemReadFacade.queryFullItemWithDetail(FullItemWithDetailQueryRequest.builder().itemId(itemId).build());
        System.out.println(JsonMapper.nonEmptyMapper().toJson(fullItemR));
        assertThat(fullItemR.isSuccess()).isFalse();
        assertThat(fullItemR.getError()).isEqualTo("item.not.found");
    }

    private FullItemWithDetailInfo removeTime(FullItemWithDetailInfo s) {
        s.getItemInfo().setUpdateTime(null);
        s.getItemInfo().setCreateTime(null);
        s.getItemDetailInfo().setCreateTime(null);
        s.getItemDetailInfo().setUpdateTime(null);
        for (SkuInfo skuInfo : s.getSkuInfoList()) {
            skuInfo.setUpdateTime(null);
            skuInfo.setCreateTime(null);
        }
        return s;
    }
}
