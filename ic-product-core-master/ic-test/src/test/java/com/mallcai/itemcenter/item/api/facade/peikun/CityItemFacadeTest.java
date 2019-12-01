package com.mallcai.itemcenter.item.api.facade.peikun;

import com.mallcai.itemcenter.BaseTest;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.facade.CityItemReadFacade;
import com.mallcai.itemcenter.item.repository.ItemDAO;
import com.mallcai.itemcenter.item.repository.ItemDetailDAO;
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
public class CityItemFacadeTest extends BaseTest {
    @Autowired
    private CityItemReadFacade cityItemReadFacade;

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

    @Test
    public void testPaging() throws IOException {
        CityItemPagingRequest request = new CityItemPagingRequest();
        request.setItemName("434");
        ICResponse<Paging<CityItemInfo>> fullItemR = cityItemReadFacade.paging(request);
        assertThat(fullItemR.isSuccess()).isFalse();
        assertThat(fullItemR.getError()).isEqualTo("item.not.found");
    }

}
