package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.HisSales;
import com.dailuobo.api.product.MgrHisSalesService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.HisSalesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("mgrHisSalesService")
public class MgrHisSalesServiceImpl implements MgrHisSalesService {

    @Autowired
    HisSalesService hisSalesService;

    @Override
    public ICResponse<List<HisSales>> selectCity(Integer cityId, String hdate, Integer storeId, Integer cityProductId, Integer offset, Integer limit, String isCity) {
        Map param = Maps.newHashMap();
        param.put("cityId", cityId);
        param.put("hdate", hdate);
        param.put("storeId", storeId);
        param.put("cityProductId", cityProductId);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("isCity", isCity);

        return ICResponseHandler.template(() -> {
            if (offset != null) {
                PageHelper.startPage(offset / limit + 1, limit);
            }
            List<HisSales> hisSalesList = new ArrayList<>();
            if (!StringUtils.isEmpty(isCity) && isCity.equals("1")) {
                hisSalesList = hisSalesService.selectCity(cityProductId, hdate, cityId);
            }
            PageInfo<HisSales> pageInfo = new PageInfo<>(hisSalesList);
            return ICResponse.success(hisSalesList, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));

        }, "selectCity", param);

    }

    @Override
    public ICResponse<List<HisSales>> selectAll(Integer cityId, String hdate, Integer storeId, Integer cityProductId, Integer offset, Integer limit, String isCity) {
        Map param = Maps.newHashMap();
        param.put("cityId", cityId);
        param.put("hdate", hdate);
        param.put("storeId", storeId);
        param.put("cityProductId", cityProductId);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("isCity", isCity);

        return ICResponseHandler.template(() -> {
            if (offset != null) {
                PageHelper.startPage(offset / limit + 1, limit);
            }
            List<HisSales> hisSalesList = new ArrayList<>();
            hisSalesList = hisSalesService.selectAll(storeId, cityProductId, hdate, cityId);
            PageInfo<HisSales> pageInfo = new PageInfo<>(hisSalesList);
            return ICResponse.success(hisSalesList, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));

        }, "selectAll", param);


    }
}
