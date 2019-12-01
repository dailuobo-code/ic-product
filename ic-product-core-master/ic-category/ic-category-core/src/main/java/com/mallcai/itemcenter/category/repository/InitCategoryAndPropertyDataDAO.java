package com.mallcai.itemcenter.category.repository;

import com.mallcai.itemcenter.category.model.BackCategory;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InitCategoryAndPropertyDataDAO {
    @Autowired
    protected SqlSession sqlSession;

    public List<BackCategory> selectIcCategoryByName(String pname) {
        return sqlSession.selectList("initData.selectIcCategoryByName", pname);
    }

    public Long selectHqClassifyIdByName(String pname) {
        return sqlSession.selectOne("initData.selectHqClassifyIdByName", pname);
    }

    public int insertIcCategory(BackCategory insertIcCategory) {
        return sqlSession.insert("initData.insertIcCategory", insertIcCategory);
    }

    public int insertCategoryAttr(List<CategoryAttribute> attributes) {
        return sqlSession.insert("initData.insertCategoryAttr", attributes);
    }
}
