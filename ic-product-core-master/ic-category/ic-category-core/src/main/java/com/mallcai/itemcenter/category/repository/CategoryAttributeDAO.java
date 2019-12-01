package com.mallcai.itemcenter.category.repository;

import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * DAO层
 *
 * @author [ your email ]
 * @date 2019-10-17 20:27:29
 * Created by CodeGen .
 */
@Repository
public class CategoryAttributeDAO extends MyBatisDAO<CategoryAttribute> {
    public List<CategoryAttribute> listByCategoryId(Long categoryId) {
        return getSqlSession().selectList(sqlId("listByCategoryId"), categoryId);
    }
}
