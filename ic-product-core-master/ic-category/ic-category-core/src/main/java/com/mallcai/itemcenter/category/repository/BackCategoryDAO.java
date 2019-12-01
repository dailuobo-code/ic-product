package com.mallcai.itemcenter.category.repository;

import com.mallcai.itemcenter.category.model.BackCategory;
import com.mallcai.itemcenter.utils.MyBatisDAO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * DAO层
 *
 * @author [ your email ]
 * @date 2019-10-17 14:05:21
 * Created by CodeGen .
 */
@Repository
public class BackCategoryDAO extends MyBatisDAO<BackCategory> {
    /**
     * 根据父类目id查找对应子类目列表
     *
     * @param pid 父类目id
     * @return 对应的子类目列表
     */
    public List<BackCategory> findByPid(Long pid) {
        return getSqlSession().selectList(sqlId("findByPid"), pid);
    }
}
