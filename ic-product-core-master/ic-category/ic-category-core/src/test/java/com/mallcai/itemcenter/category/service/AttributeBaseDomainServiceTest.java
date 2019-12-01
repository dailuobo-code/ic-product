package com.mallcai.itemcenter.category.service;

import com.google.common.collect.Lists;
import com.mallcai.itemcenter.category.ServiceTestBootstrap;
import com.mallcai.itemcenter.category.model.BackCategory;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.category.repository.BackCategoryDAO;
import com.mallcai.itemcenter.category.repository.CategoryAttributeDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AttributeBaseDomainServiceTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/18 11:42<br/>
 */
public class AttributeBaseDomainServiceTest extends ServiceTestBootstrap {
    @Autowired
    private AttributeBaseDomainService attributeBaseDomainService;
    @Autowired
    private BackCategoryDAO backCategoryDAO;
    @Autowired
    private CategoryAttributeDAO categoryAttributeDAO;

    public BackCategory create(String name, int level, long pid) {
        BackCategory bc = new BackCategory();
        bc.setName(name);
        bc.setLevel(level);
        bc.setPid(pid);
        bc.setHasChildren(false);
        bc.setHasSpu(false);
        bc.setStatus(1);
        bc.setUpdatedBy(1L);
        return bc;
    }

    private CategoryAttribute create(long cid, int idx, String key, String group) {
        CategoryAttribute c = new CategoryAttribute();
        c.setCategoryId(cid);
        c.setIndex(idx);
        c.setStatus(1);
        c.setGroup(group);
        c.setAttrKey(key);
        c.setAttrValsJson(key + ":" + group + ":" + cid);
        c.setAttrMetasJson("meta");
        c.setExtraJson("extra");
        c.setUpdatedBy(123L);
        return c;
    }

    public Long setupTestFindAllByCategoryId() {
        BackCategory bc1 = create("一级类目", 1, 0);
        backCategoryDAO.create(bc1);

        BackCategory bc2 = create("二级类目", 2, bc1.getId());
        backCategoryDAO.create(bc2);
        BackCategory bc3 = create("三级类目", 3, bc2.getId());
        backCategoryDAO.create(bc3);

        CategoryAttribute a1 = create(bc1.getId(), 1, "一级类目属性", "DEFAULT");
        CategoryAttribute a12 = create(bc1.getId(), 1, "同名属性", "DEFAULT");
        CategoryAttribute a2 = create(bc2.getId(), 1, "二级类目属性", "DEFAULT");
        CategoryAttribute a22 = create(bc2.getId(), 1, "同名属性", "DEFAULT");
        CategoryAttribute a23 = create(bc3.getId(), 1, "同名属性-2", "DEFAULT");
        CategoryAttribute a3 = create(bc3.getId(), 1, "三级类目属性", "DEFAULT");
        CategoryAttribute a31 = create(bc3.getId(), 1, "同名属性-2", "DEFAULT");
        categoryAttributeDAO.creates(Lists.newArrayList(a1, a12, a2, a22, a23, a3, a31));
        return bc3.getId();
    }

    @Test
    public void testFindAllByCategoryId() {
        Long cid = setupTestFindAllByCategoryId();
        List<CategoryAttribute> attrs = attributeBaseDomainService.findAllByCategoryId(cid);
        attrs.stream().map(CategoryAttribute::getAttrValsJson).forEach(System.out::println);
        assertThat(attrs.stream().map(CategoryAttribute::getAttrKey).collect(Collectors.toList()))
                .isNotEmpty()
                .hasSize(5)
                .containsExactly("同名属性-2", "三级类目属性", "二级类目属性", "同名属性", "一级类目属性");
    }
}
