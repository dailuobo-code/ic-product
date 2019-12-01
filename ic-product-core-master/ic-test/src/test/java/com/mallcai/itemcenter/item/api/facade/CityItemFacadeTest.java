package com.mallcai.itemcenter.item.api.facade;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.itemcenter.IntegrationTestBootstrap;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.*;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullCityItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.utils.JsonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CityItemFacadeTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:36<br/>
 */
public class CityItemFacadeTest extends IntegrationTestBootstrap {
    @Autowired
    private ItemWriteFacade itemWriteFacade;
    @Autowired
    private CityItemReadFacade cityItemReadFacade;
    @Autowired
    private CityItemWriteFacade cityItemWriteFacade;

    private Long itemId;

    public void setup() throws IOException {
        // 创建一个商品
        String data = loadInputJson("ItemWriteFacadeTest_testCreateItem");
        ItemCreateRequest r = JsonMapper.nonEmptyMapper().fromJson(data, ItemCreateRequest.class);
        ICResponse<Long> createR = itemWriteFacade.create(r);
        assertThat(createR.isSuccess()).isTrue();
        itemId = createR.getResult();

        // 绑定城市 30、32
        data = loadInputJson("CityItemFacadeTest_testBatchBind");
        CityBatchBindToItemRequest bind = JsonMapper.nonEmptyMapper().fromJson(data, CityBatchBindToItemRequest.class);
        // 更新商品 ID
        bind.setItemId(itemId);
        ICResponse<Boolean> bindR = cityItemWriteFacade.batchBindCity(bind);
        System.out.println(bindR);
        assertThat(bindR.isSuccess()).isTrue();
        assertThat(bindR.getResult()).isTrue();
    }

    @Test
    public void testBatchBind() throws IOException {
        setup();

        // 校验 1
        ICResponse<FullCityItemInfo> queryR = cityItemReadFacade.queryFullCityItem(new FullCityItemQueryRequest(itemId, 30L));
        assertThat(queryR.isSuccess()).isTrue();
        assertThat(queryR.getResult()).isNotNull();
        assertThat(JsonMapper.nonEmptyMapper().toJson(removeTime(queryR.getResult()))).isEqualTo(loadOutputJson("CityItemFacadeTest_testQueryFullCityItem"));
        // 校验 2，含详情查询
        ICResponse<FullCityItemWithDetailInfo> queryWithDetailR = cityItemReadFacade.queryFullCityItemWithDetail(new FullCityItemWithDetailQueryRequest(itemId, 30L));
        assertThat(queryWithDetailR.isSuccess()).isTrue();
        assertThat(queryWithDetailR.getResult()).isNotNull();
        assertThat(JsonMapper.nonEmptyMapper().toJson(removeTime(queryWithDetailR.getResult()))).isEqualTo(loadOutputJson("CityItemFacadeTest_testQueryFullCityItemWithDetail"));
        // 校验 3，关联关系查询
        ICResponse<Map<Long, List<CityItemInfo>>> listR = cityItemReadFacade.listItemCityRelations(new CityItemListRelationRequest(Lists.newArrayList(itemId)));
        assertThat(listR.isSuccess()).isTrue();
        assertThat(listR.getResult()).isNotEmpty().hasSize(1);
        assertThat(listR.getResult().get(itemId)).hasSize(2);
        assertThat(JsonMapper.nonEmptyMapper().toJson(removeTime(listR.getResult()))).isEqualTo(loadOutputJson("CityItemFacadeTest_testListItemCityRelations"));
        // 校验 4, 分页
        CityItemPagingRequest page = CityItemPagingRequest.builder().itemName("am").status(ItemStatus.OFF_SHELF).build();
        ICResponse<Paging<CityItemInfo>> pageR = cityItemReadFacade.paging(page);
        assertThat(pageR.isSuccess()).isTrue();
        assertThat(pageR.getResult().isEmpty()).isFalse();
        assertThat(JsonMapper.nonEmptyMapper().toJson(removeTime(pageR.getResult()))).isEqualTo(loadOutputJson("CityItemFacadeTest_testPaging"));
    }

    @Test
    public void testUpdateStatus() throws IOException {
        setup();

        String data = loadInputJson("CityItemFacadeTest_testSellerUpdateStatus");
        CityItemUpdateStatusRequest update = JsonMapper.nonEmptyMapper().fromJson(data, CityItemUpdateStatusRequest.class);
        ICResponse<Boolean> updateR = cityItemWriteFacade.sellerUpdateStatus(update);
        assertThat(updateR.isSuccess()).isTrue();
        assertThat(updateR.getResult()).isTrue();

        // 校验，30 城市状态
        ICResponse<FullCityItemInfo> queryR = cityItemReadFacade.queryFullCityItem(FullCityItemQueryRequest.builder().cityId(30L).itemId(itemId).build());
        assertThat(queryR.isSuccess()).isTrue();
        assertThat(queryR.getResult()).isNotNull();
        assertThat(JsonMapper.nonEmptyMapper().toJson(removeTime(queryR.getResult()))).isEqualTo(loadOutputJson("CityItemFacadeTest_testQueryFullCityItem_updateStatus"));
    }

    @Test
    public void testUpdate() throws IOException {
        setup();

        String data = loadInputJson("CityItemFacadeTest_testUpdate");
        CityItemUpdateRequest update = JsonMapper.nonEmptyMapper().fromJson(data, CityItemUpdateRequest.class);
        ICResponse<Boolean> updateR = cityItemWriteFacade.update(update);
        assertThat(updateR.isSuccess()).isTrue();
        assertThat(updateR.getResult()).isTrue();

        // 校验，30 城市价格
        ICResponse<FullCityItemInfo> queryR = cityItemReadFacade.queryFullCityItem(FullCityItemQueryRequest.builder().cityId(30L).itemId(itemId).build());
        assertThat(queryR.isSuccess()).isTrue();
        assertThat(queryR.getResult()).isNotNull();
        assertThat(JsonMapper.nonEmptyMapper().toJson(removeTime(queryR.getResult()))).isEqualTo(loadOutputJson("CityItemFacadeTest_testQueryFullCityItem_update"));
    }

    //================//
    // Private helper //
    //================//

    private FullCityItemInfo removeTime(FullCityItemInfo s) {
        s.getItemInfo().setUpdateTime(null);
        s.getItemInfo().setCreateTime(null);
        for (CitySkuInfo skuInfo : s.getSkuInfoList()) {
            skuInfo.setUpdateTime(null);
            skuInfo.setCreateTime(null);
        }
        return s;
    }

    private FullCityItemWithDetailInfo removeTime(FullCityItemWithDetailInfo s) {
        s.getItemInfo().setUpdateTime(null);
        s.getItemInfo().setCreateTime(null);
        s.getItemDetailInfo().setUpdateTime(null);
        s.getItemDetailInfo().setCreateTime(null);
        for (CitySkuInfo skuInfo : s.getSkuInfoList()) {
            skuInfo.setUpdateTime(null);
            skuInfo.setCreateTime(null);
        }
        return s;
    }

    private Map<Long, List<CityItemInfo>> removeTime(Map<Long, List<CityItemInfo>> s) {
        Map<Long, List<CityItemInfo>> t = Maps.newHashMap();
        s.forEach((k, v) -> t.put(k, removeTime(v)));
        return t;
    }
    private Paging<CityItemInfo> removeTime(Paging<CityItemInfo> s) {
        return Paging.of(s.getTotal(), removeTime(s.getData()));
    }

    private List<CityItemInfo> removeTime(List<CityItemInfo> s) {
        s.forEach(it -> {
                it.setUpdateTime(null);
                it.setCreateTime(null);
        });
        return s;
    }
}
