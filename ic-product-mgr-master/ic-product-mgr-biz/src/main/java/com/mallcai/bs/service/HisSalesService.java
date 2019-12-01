package com.mallcai.bs.service;

import com.mallcai.bs.dao.HisSalesDao;
import com.dailuobo.api.domain.entity.HisSales;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aministartor on 2016/12/1.
 */
@Service
@Transactional(readOnly = true)
public class HisSalesService {
    @Autowired
    HisSalesDao hisSalesDao;

    public List<HisSales> selectAll(Integer storeId, Integer cityProductId, String hisTime, Integer cityId) {
        Map<String,Object> params = new HashMap<>(3);
        params.put("storeId",(storeId != null && storeId == 0) ? null : storeId);
        params.put("cityProductId",cityProductId);
        params.put("hisTime", StringUtils.isBlank(hisTime) ? null : hisTime);
        params.put("cityId",cityId);
        return hisSalesDao.selectAll(params);
    }

    public List<HisSales> selectCity(Integer cityProductId, String hisTime, Integer cityId) {
        Map<String,Object> params = new HashMap<>();
        params.put("cityProductId",cityProductId);
        params.put("hisTime", StringUtils.isBlank(hisTime) ? null : hisTime);
        params.put("cityId",cityId);
        return hisSalesDao.selectCity(params);
    }
}
