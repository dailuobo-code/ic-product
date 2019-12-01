package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.HisSales;
import com.mallcai.bs.mapper.HisSalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Aministartor on 2016/12/1.
 */
@Repository
public class HisSalesDao {
    @Autowired
    HisSalesMapper hisSalesMapper;

    public List<HisSales> selectAll(Map<String,Object> params) {
        return  hisSalesMapper.selectAll(params);
    }

    public List<HisSales> selectCity(Map<String,Object> params) {
        return  hisSalesMapper.selectCity(params);
    }
}
