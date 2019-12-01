package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.HisSales;

import java.util.List;
import java.util.Map;

/**
 * Created by Aministartor on 2016/12/1.
 */
public interface HisSalesMapper {
    public List<HisSales> selectAll(Map<String,Object> params);

    List<HisSales> selectCity(Map<String,Object> params);
}
