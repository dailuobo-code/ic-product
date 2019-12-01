package com.mallcai.category.mapper;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.biz.category.dao.CategoryDAO;
import com.mallcai.biz.category.dao.redisdao.CategoryRedisDao;
import com.mallcai.domain.dataobject.category.CategoryDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapperTest extends BaseTransactionalTest {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private CategoryRedisDao categoryRedisDao;

    @Test
    @Transactional
    public void testCreateCategory()throws Exception{
      CategoryDO categoryDO = new CategoryDO();
         /* Integer nullDo = categoryDAO.createCategory(categoryDO);
        Assert.assertTrue(nullDo<=0);
*/
        categoryDO.setClassifyName("test");
        categoryDO.setClassifyNo("999");
        categoryDO.setLevel(1);
        categoryDO.setShowOrder(66);
        categoryDO.setPickupOrder(88);
        categoryDO.setFatherId(0);
        categoryDO.setCreateUserId(0);
        categoryDO.setUpdateUserId(0);
        Integer createSuccess = categoryDAO.createCategory(categoryDO);
        Assert.assertTrue(createSuccess>0);
        CategoryDO newcategoryDo = categoryDAO.loadCategory(categoryDO.getClassifyId());
        Assert.assertEquals(categoryDO.getClassifyName(),newcategoryDo.getClassifyName());
        Assert.assertEquals(categoryDO.getLevel(),newcategoryDo.getLevel());
        Assert.assertEquals(categoryDO.getClassifyNo(),newcategoryDo.getClassifyNo());
        Assert.assertEquals(categoryDO.getShowOrder(),newcategoryDo.getShowOrder());


    }

    @Test
    @Transactional
    public void testEdit() throws Exception{
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setClassifyId(543);
        categoryDO.setClassifyNo("10241");
        categoryDO.setClassifyNo("测试");
        categoryDO.setIconUrl("wwww.baidu.com");
        categoryDO.setPickupOrder(100);
        categoryDO.setShowOrder(90);
        categoryDO.setUpdateUserId(14);
        categoryDAO.editCategory(categoryDO);
    }

    @Test
    @Transactional
    public void listTest()throws Exception{
        CategoryDO categoryDO = new CategoryDO();
        List<CategoryDO> nullRequest = categoryDAO.listCategory(categoryDO);
        //Assert.assertEquals(nullRequest,null);
        categoryDO.setLevel(1);
        List<CategoryDO> categoryDOList = new ArrayList<>();
        categoryDOList = categoryDAO.listCategory(categoryDO);
        Assert.assertTrue(!CollectionUtils.isEmpty(categoryDOList));
        Assert.assertTrue(categoryDOList.size()>0);
        categoryDOList.forEach(t->{
            Assert.assertEquals(t.getLevel(),Integer.valueOf(1));
        });

        categoryDO.setLevel(2);
        categoryDOList.clear();
        categoryDOList = categoryDAO.listCategory(categoryDO);
        Assert.assertTrue(!CollectionUtils.isEmpty(categoryDOList));
        categoryDOList.forEach(t->{
            Assert.assertEquals(t.getLevel(),Integer.valueOf(2));
        });

        categoryDO.setClassifyName("肉质");
        categoryDOList.clear();
        categoryDOList = categoryDAO.listCategory(categoryDO);
        Assert.assertEquals(categoryDOList.size(),1);

    }

    @Test
    @Transactional
    public void loadCategoryTest(){
        CategoryDO categoryDO = categoryDAO.loadCategory(438);
        /*Assert.assertTrue(categoryDO!=null);
        Assert.assertEquals(categoryDO.getClassifyName(),"叶菜类");
        Assert.assertEquals(categoryDO.getClassifyNo(),"2081");
        Assert.assertEquals(categoryDO.getFatherId(),Integer.valueOf(437));
        Assert.assertEquals(categoryDO.getShowOrder(),Integer.valueOf(9999));
        Assert.assertEquals(categoryDO.getPickupOrder(),Integer.valueOf(999));*/
        System.out.println(categoryDO);

        CategoryDO categoryDO1 = categoryDAO.loadCategory(439);
       /* Assert.assertEquals(categoryDO1.getClassifyName(),"茄果类");
        Assert.assertEquals(categoryDO1.getClassifyNo(),"2082");
        Assert.assertEquals(categoryDO1.getFatherId(),Integer.valueOf(437));
        Assert.assertEquals(categoryDO1.getShowOrder(),Integer.valueOf(9999));*/
        System.out.println(categoryDO1);

        CategoryDO categoryDO2 = categoryDAO.loadCategory(437);
        System.out.println(categoryDO2);

        List<Integer> categoryIdlist = Lists.newArrayList(438,439);
        List<CategoryDO> categoryDO3 = categoryDAO.findByCategories( categoryIdlist);
        System.out.println(JSON.toJSONString(categoryDO3));
    }

    /**
     * 测试获取所有包含二级分类的一级分类，验证查询出来的一级分类是否包含二级分类
     */
    @Test
    public void testAlllv1CateoryExistLv2(){
        List<CategoryDO> listAllLv1Category = categoryDAO.listAllLv1Category();
        Assert.assertFalse(CollectionUtils.isEmpty(listAllLv1Category));
        listAllLv1Category.forEach(t->{
            CategoryDO categoryDO = new CategoryDO();
            categoryDO.setFatherId(t.getClassifyId());
            List<CategoryDO> categoryLv2 = categoryDAO.listCategory(categoryDO);
            Assert.assertFalse(CollectionUtils.isEmpty(categoryLv2));
            categoryLv2.forEach(s->{
                Assert.assertEquals(s.getFatherId(),t.getClassifyId());
                Assert.assertEquals(s.getLevel(),Integer.valueOf(2));
                Assert.assertEquals(s.getDelFlag(),Byte.valueOf("1"));
            });
        });
    }


    @Test
    public void refreshAllCategoryTest()throws Exception{
        categoryRedisDao.refreshAllSecondCategory();
    }
}
