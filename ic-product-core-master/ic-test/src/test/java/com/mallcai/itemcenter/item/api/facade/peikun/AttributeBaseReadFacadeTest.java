package com.mallcai.itemcenter.item.api.facade.peikun;

import com.mallcai.itemcenter.BaseTest;
import com.mallcai.itemcenter.category.api.bean.request.AttributeQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.category.api.facade.AttributeBaseReadFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.utils.JsonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FrontProductServiceImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/3 20:12<br/>
 */
public class AttributeBaseReadFacadeTest extends BaseTest {

    @Autowired
    private AttributeBaseReadFacade attributeBaseReadFacade;

    @Test
    public void test() {
        ICResponse<List<CategoryAttributeInfo>> queryR = attributeBaseReadFacade.findAllByCategoryId(AttributeQueryRequest.builder().categoryId(3L).build());
        System.out.println(JsonMapper.nonEmptyMapper().toJson(queryR.getResult()));
        assertThat(queryR.isSuccess()).isTrue();
        assertThat(queryR.getResult()).isNotEmpty();
    }
}
