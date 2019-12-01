package com.mallcai.itemcenter.category.repository;

import com.mallcai.itemcenter.category.DaoTestBootstrap;
import com.mallcai.itemcenter.category.model.BackCategory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * BackCategoryDaoTest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 14:10<br/>
 */
public class BackCategoryDAOTest extends DaoTestBootstrap {

    private BackCategory bc;

    @Autowired
    private BackCategoryDAO backCategoryDao;

    @Before
    public void setup() {
        bc = create("一级类目", 1, 0L);
        backCategoryDao.create(bc);
        assertThat(bc.getId()).isNotNull();
    }

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

    @Test
    public void testCRUD() {
        BackCategory found = backCategoryDao.findById(bc.getId());
        assertThat(found).isNotNull();
    }

    @Test
    public void testFindByPid() {
        BackCategory bc2 = create("一级类目1", 1, 0L);
        backCategoryDao.create(bc2);

        List<BackCategory> children = backCategoryDao.findByPid(0L);
        assertThat(children).isNotEmpty().hasSize(2);
    }
}
