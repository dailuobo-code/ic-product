package com.mallcai.itemcenter.item.api.facade.peikun;

import com.mallcai.itemcenter.BaseTest;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.api.facade.ItemReadFacade;
import com.mallcai.itemcenter.item.api.facade.ItemWriteFacade;
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
public class ItemFacadeTest extends BaseTest {
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

    @Test
    public void testPaging() throws IOException {
        ItemPagingRequest request=new ItemPagingRequest();
        //request.setItemName("yyn002");
        //request.setCategoryId(5L);
        request.setLimit(10);
        request.setOffset(0);
        //request.setItemName("培坤ceshi_focusz052");
        ICResponse<Paging<ItemInfo>> fullItemR = itemReadFacade.paging(request);
        assertThat(fullItemR.isSuccess()).isFalse();
        assertThat(fullItemR.getError()).isEqualTo("item.not.found");
    }

}
