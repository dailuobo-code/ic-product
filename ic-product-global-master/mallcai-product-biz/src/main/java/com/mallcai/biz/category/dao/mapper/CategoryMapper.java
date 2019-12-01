package com.mallcai.biz.category.dao.mapper;

import com.mallcai.domain.dataobject.category.CategoryDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    /**
     * 创建分类
     * @param categoryDO
     * @return
     */
    Integer createCategory(CategoryDO categoryDO);

    /**修改分类
     * @param categoryDO
     * @return
     */
    Integer editCategory(CategoryDO categoryDO);

    /**
     * 查询分类
     * @param categoryDO
     * @return
     */
    List<CategoryDO> listCategory(CategoryDO categoryDO);

    /**
     * 查询有二级分类的一级分类
     * @return
     */
    List<CategoryDO> listAllLv1Category();


    CategoryDO loadCategory(Integer categoryId);

    boolean delByIdList(List<Integer> categoryIdList);

    List<CategoryDO> findByCategoryIdList(List<Integer> categoryIds);

    List<CategoryDO> findByCategoryNoList(List<String> categoryIds);
}
