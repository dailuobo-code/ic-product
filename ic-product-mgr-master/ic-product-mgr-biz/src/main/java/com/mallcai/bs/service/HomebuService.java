package com.mallcai.bs.service;

import com.mallcai.bs.dao.HomebuDao;
import com.dailuobo.api.domain.entity.Homebu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aministartor on 2016/12/12.
 */
@Service
@Transactional(readOnly = true)
public class HomebuService {
    @Autowired
    HomebuDao homebuDao;

    public List<Homebu> selectAll(String productNo,Integer cityProductId,Integer cityId) {
        Map<String,Object> map = new HashMap<>();
        map.put("cityProductId", cityProductId);
        map.put("productNo", productNo);
        map.put("cityId", cityId);
        return homebuDao.selectAll(map);
    }

    @Transactional(readOnly = false)
    public int add(Integer cityProductId) {
        Map<String,Object> map = new HashMap<>();
        map.put("cityProductId", cityProductId);
        return homebuDao.add(map);
    }

    @Transactional(readOnly = false)
    public int del(Integer[] ids) {
        return homebuDao.del(ids);
    }
}
