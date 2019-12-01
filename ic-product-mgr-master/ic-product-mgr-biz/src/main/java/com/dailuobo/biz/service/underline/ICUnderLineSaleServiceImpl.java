package com.dailuobo.biz.service.underline;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.UnsaleCount;
import com.dailuobo.api.underLine.ICUnderLineSaleService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.SOACityGlobalService;
import com.mallcai.bs.service.UnSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("underLineSaleService")
public class ICUnderLineSaleServiceImpl implements ICUnderLineSaleService {
    @Autowired
    private UnSaleService unSaleService;

    @Autowired
    private SOACityGlobalService soaCityGlobalService;

    public ICResponse pickupLog(int cityId, int storeId, int userId, Integer[] proArr, Integer[] weightArr,
                                String[] productNos, String[] barcodes, String orderNo) {
        Map param = Maps.newHashMap();
        param.put("storeId", storeId);
        param.put("userId", userId);
        param.put("cityId", cityId);
        param.put("proArr", proArr);
        param.put("weightArr", weightArr);
        param.put("productNos", productNos);
        param.put("barcodes", barcodes);
        param.put("orderNo", orderNo);
        return ICResponseHandler.template(() -> {
            unSaleService.pickupLog(cityId, storeId, userId, proArr, weightArr, productNos, barcodes, orderNo);
            return ICResponse.success();
        }, "pickupLog", param);


    }

    public ICResponse<List<UnsaleCount>> selectCount(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            List<UnsaleCount> unsaleCounts = unSaleService.selectCount(param);
            return ICResponse.success(unsaleCounts);
        }, "selectCount", param);


    }

    public ICResponse<Integer> getCityProduct(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            Integer cityProduct = unSaleService.getCityProduct(param);
            return ICResponse.success(cityProduct);
        }, "getCityProduct", param);


    }

    public ICResponse<List<UnsaleCount>> selectAllStoreCount(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.offsetPage(offset, limit);
            }
            List<UnsaleCount> unsaleCounts = unSaleService.selectAllStoreCount(param);
            //int pageSize, long totalNumber, int currentPage

            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) unsaleCounts).getTotal(), offset / limit + 1);
                return ICResponse.success(unsaleCounts, pageDTO);
            }
            return ICResponse.success(unsaleCounts);
        }, "selectAllStoreCount", param);


    }


    public ICResponse<String> checkProductStatus(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            String s = unSaleService.checkProductStatus(param);
            return ICResponse.success(s);
        }, "checkProductStatus", param);


    }

}
