package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.Homebu;

import java.util.List;
import java.util.Map;

/**
 * Created by Aministartor on 2016/12/12.
 */
public interface HomebuMapper {
    public List<Homebu> selectAll(Map<String,Object> map);

    public int add(Map<String,Object> map);

    public int del(Integer[] ids);
}
