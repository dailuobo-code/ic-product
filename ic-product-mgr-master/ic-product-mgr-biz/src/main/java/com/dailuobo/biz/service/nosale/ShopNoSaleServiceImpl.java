package com.dailuobo.biz.service.nosale;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.StoreNoSale;
import com.dailuobo.api.domain.vo.NoSaleVo;
import com.dailuobo.api.nosale.ShopNoSaleService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.NoSaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("shopNoSaleService")
public class ShopNoSaleServiceImpl implements ShopNoSaleService {
    @Autowired
    private NoSaleService noSaleService;

    @Override
    public ICResponse<List<NoSaleVo>> selectAll(Map<String, Object> param) {
        int offset = 0;
        int limit = 0;
        if (param.get("offset") != null && param.get("limit") != null) {
            offset = (int) param.get("offset");
            limit = (int) param.get("limit");
            PageHelper.offsetPage(offset, limit);
        }
        try {
            List<NoSaleVo> noSaleVos = noSaleService.selectAll(param);
            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) noSaleVos).getTotal(), offset / limit + 1);
                return ICResponse.success(noSaleVos, pageDTO);
            }
            return ICResponse.success(noSaleVos);
        } catch (Exception ex) {
            log.error(String.format("##selectAll error,request:%s", JSON.toJSONString(param)), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ICResponse add(List<StoreNoSale> storeNoSales) {
        return ICResponseHandler.template(() -> {
            noSaleService.add(storeNoSales);
            return ICResponse.success();
        }, "add", storeNoSales);
    }

    @Override
    @Transactional
    public ICResponse delete(List<Integer> ids, Integer userId, Integer cityId, Integer[] storeIds) {
        Map param1= Maps.newHashMap();
        param1.put("ids",ids);
        param1.put("userId",userId);
        param1.put("storeIds",storeIds);

        return ICResponseHandler.template(() -> {
            noSaleService.delete(ids, userId, cityId, storeIds);
            return ICResponse.success();
        }, "delete", param1);

    }

    /**
     * 根据数据库查询情况刷新redis中的数据
     *
     * @return
     */
    @Override
    public ICResponse updateCityStoreNoSaleProduct(Integer cityId, Integer storeId) {
        Map param1= Maps.newHashMap();
        param1.put("storeId",storeId);
        param1.put("cityId",cityId);
        return ICResponseHandler.template(() -> {
            noSaleService.updateCityStoreNoSaleProduct(cityId, storeId);
            return ICResponse.success();
        }, "", param1);
    }
}
