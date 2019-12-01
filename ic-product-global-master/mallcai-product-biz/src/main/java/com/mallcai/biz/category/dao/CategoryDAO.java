package com.mallcai.biz.category.dao;

import com.mallcai.biz.category.dao.mapper.CategoryMapper;
import com.mallcai.domain.dataobject.category.CategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 创建分类
     * @param categoryDO
     * @return
     */
    public Integer createCategory(CategoryDO categoryDO){
        return categoryMapper.createCategory(categoryDO);
    }

    /**修改分类
     * @param categoryDO
     * @return
     */
    public Integer editCategory(CategoryDO categoryDO){
        return categoryMapper.editCategory(categoryDO);
    }

    /**
     * 查询分类
     * @param categoryDO
     * @return
     */
    public List<CategoryDO> listCategory(CategoryDO categoryDO){
        return categoryMapper.listCategory(categoryDO);
    }

    /**
     * 查询有二级分类的一级分类
     * @return
     */
    public List<CategoryDO> listAllLv1Category(){
        return categoryMapper.listAllLv1Category();
    }


    public CategoryDO loadCategory(Integer categoryId){
        return categoryMapper.loadCategory(categoryId);
    }

    public boolean delByCategoryList(List<Integer> categoryIds){
        return categoryMapper.delByIdList(categoryIds);
    }

    public List<CategoryDO> findByCategories(List<Integer> categoryIds){
        return categoryMapper.findByCategoryIdList(categoryIds);
    }

    public List<CategoryDO> findByCategoryNos(List<String> categoryNos){
        return categoryMapper.findByCategoryNoList(categoryNos);
    }
}
