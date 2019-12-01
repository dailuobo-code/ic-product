package com.mallcai.itemcenter.item.api.facade;

import com.mallcai.itemcenter.IntegrationTestBootstrap;
import com.mallcai.itemcenter.category.api.bean.request.AttributeQueryRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.api.bean.response.CategoryAttributeInfo;
import com.mallcai.itemcenter.category.api.facade.AttributeBaseReadFacade;
import com.mallcai.itemcenter.category.api.facade.BackCategoryReadFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.utils.JsonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BackCategoryFacadeTest extends IntegrationTestBootstrap {

    @Autowired
    private BackCategoryReadFacade backCategoryReadFacade;

    @Autowired
    private AttributeBaseReadFacade attributeBaseReadFacade;

    @Test
    public void test() {
        ICResponse<List<BackCategoryInfo>> findR = backCategoryReadFacade.findChildrenByPid(BackCategoryQueryChildrenRequest.builder().pid(0L).build());
        System.out.println(JsonMapper.nonEmptyMapper().toJson(findR.getResult()));
        assertThat(findR.isSuccess()).isTrue();
        assertThat(findR.getResult()).isNotEmpty();

        ICResponse<List<CategoryAttributeInfo>> queryR = attributeBaseReadFacade.findAllByCategoryId(AttributeQueryRequest.builder().categoryId(3L).build());
        System.out.println(JsonMapper.nonEmptyMapper().toJson(queryR.getResult()));
        assertThat(queryR.isSuccess()).isTrue();
        assertThat(queryR.getResult()).isNotEmpty();
    }
}
