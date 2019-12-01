package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.Homebu;
import com.mallcai.bs.mapper.HomebuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Aministartor on 2016/12/12.
 */
@Repository
public class HomebuDao {
    @Autowired
    HomebuMapper homebuMapper;

    public List<Homebu> selectAll(Map<String,Object> map) {
        return homebuMapper.selectAll(map);
    }

    public int add(Map<String,Object> map) {
        return homebuMapper.add(map);
    }

    public int del(Integer[] ids) {
        return homebuMapper.del(ids);
    }
}
