package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.Homebu;
import com.dailuobo.api.product.MgrHomebuService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.HomebuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("mgrHomebuService")
public class MgrHomebuServiceImpl implements MgrHomebuService {

    @Autowired
    HomebuService homebuService;

    @Override
    public ICResponse<List<Homebu>> selectAll(String productNo, Integer cityProductId, Integer cityId, Integer offset, Integer limit) {
        Map param1 = Maps.newHashMap();
        param1.put("cityProductId", cityProductId);
        param1.put("offset", offset);
        param1.put("limit", limit);

        return ICResponseHandler.template(() -> {
            if (offset != null)
                PageHelper.startPage(offset / limit + 1, limit);
            List<Homebu> list = homebuService.selectAll(productNo, cityProductId, cityId);
            PageInfo<Homebu> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectAll", param1);
    }

    @Override
    public ICResponse<Boolean> add(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            homebuService.add(cityProductId);
            return ICResponse.success(true);
        }, "add", cityProductId);

    }

    @Override
    public ICResponse<Boolean> del(Integer[] ids) {
        return ICResponseHandler.template(() -> {
            homebuService.del(ids);
            return ICResponse.success(true);
        }, "del", ids);

    }
}
