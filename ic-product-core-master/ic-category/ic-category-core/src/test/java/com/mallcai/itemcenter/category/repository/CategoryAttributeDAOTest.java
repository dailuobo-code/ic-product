package com.mallcai.itemcenter.category.repository;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mallcai.itemcenter.category.DaoTestBootstrap;
import com.mallcai.itemcenter.category.api.bean.request.param.CategoryAttributeParam;
import com.mallcai.itemcenter.category.api.converter.input.CategoryAttributeParamConverter;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.utils.GenericConverter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CategoryAttributeDAOTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 21:59<br/>
 */
public class CategoryAttributeDAOTest extends DaoTestBootstrap {
    private CategoryAttribute init;

    @Autowired
    private CategoryAttributeDAO categoryAttributeDAO;

    @Autowired
    private CategoryAttributeParamConverter categoryAttributeParamConverter;

    // @Before
    public void setup() {
        init = create(1, 1);
        categoryAttributeDAO.create(init);

        assertThat(init.getId()).isNotNull();
    }

    private CategoryAttribute create(long cid, int idx) {
        CategoryAttribute c = new CategoryAttribute();
        c.setCategoryId(cid);
        c.setIndex(idx);
        c.setStatus(1);
        c.setGroup("group");
        c.setAttrKey("key");
        c.setAttrValsJson("val");
        c.setAttrMetasJson("meta");
        c.setExtraJson("extra");
        c.setUpdatedBy(123L);
        return c;
    }

    @Test
    public void testCrud() {
        setup();

        CategoryAttribute found = categoryAttributeDAO.findById(init.getId());
        System.out.println(found);
        assertThat(found).isNotNull();

        CategoryAttribute c = create(1, 2);
        categoryAttributeDAO.create(c);

        List<CategoryAttribute> founds = categoryAttributeDAO.listByCategoryId(1l);
        assertThat(founds).isNotEmpty().hasSize(2);
    }

    @Test
    public void testCreates() {
        CategoryAttributeParam ca1 = make();
        CategoryAttributeParam ca2 = make();
        ca2.setIndex(2L);
        ca2.setAttrKey("宽");
        CategoryAttributeParam ca3 = make();
        ca3.setIndex(3L);
        ca3.setAttrKey("高");
        CategoryAttributeParam cas1 = make();
        cas1.setAttrKey("内存");
        cas1.setAttrMetas(
                ImmutableMap.<String, String>builder()
                        .put("REQUIRED", "true")
                        .put("VALUE_TYPE", "STRING")
                        .put("SKU_CANDIDATE", "TRUE")
                        .build());
        cas1.setAttrVals(Lists.newArrayList("64G", "128G", "256G"));
        cas1.setIndex(4L);
        CategoryAttributeParam cas2 = make();
        cas2.setAttrKey("颜色");
        cas2.setAttrMetas(
                ImmutableMap.<String, String>builder()
                        .put("REQUIRED", "true")
                        .put("VALUE_TYPE", "STRING")
                        .put("SKU_CANDIDATE", "TRUE")
                        .build());
        cas2.setAttrVals(Lists.newArrayList("红色", "绿色", "金色"));
        cas2.setIndex(5L);
        ArrayList<CategoryAttributeParam> r = Lists.newArrayList(ca1, ca2, ca3, cas1, cas2);
        Integer isOk = categoryAttributeDAO.creates(GenericConverter.batchConvert(r, categoryAttributeParamConverter::dto2domain));
        assertThat(isOk).isPositive();
    }

    private static CategoryAttributeParam make() {
        CategoryAttributeParam ca = new CategoryAttributeParam();
        ca.setCategoryId(3L);
        ca.setAttrKey("长");
        ca.setGroup("DEFAULT");
        ca.setIndex(1L);
        ca.setIsExtended(false);
        ca.setStatus(1);
        ca.setExtra(Collections.emptyMap());
        ca.setAttrMetas(
                ImmutableMap.<String, String>builder()
                        .put("REQUIRED", "true")
                        .put("VALUE_TYPE", "NUMBER")
                        .build());
        ca.setAttrVals(Lists.newArrayList("10", "20"));
        ca.setUpdatedBy(1L);
        return ca;
    }
}
