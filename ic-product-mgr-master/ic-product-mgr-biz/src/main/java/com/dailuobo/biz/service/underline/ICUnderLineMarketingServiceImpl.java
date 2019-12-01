package com.dailuobo.biz.service.underline;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.soa.SOAUnStoreMarketingInventory;
import com.dailuobo.api.domain.vo.UndProductVo;
import com.dailuobo.api.underLine.ICUnderLineMarketingService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.service.UnMarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("underLineMarketingService")
public class ICUnderLineMarketingServiceImpl implements ICUnderLineMarketingService {
    @Autowired
    private UnMarketingService unMarketingService;

    public ICResponse<List<SOAUnStoreMarketingInventory>> getStoreMarketingInventories(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.offsetPage(offset, limit);
            }
            List<SOAUnStoreMarketingInventory> storeMarketingInventories = unMarketingService.getStoreMarketingInventories(param);
            //int pageSize, long totalNumber, int currentPage

            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) storeMarketingInventories).getTotal(), offset / limit + 1);
                return ICResponse.success(storeMarketingInventories, pageDTO);
            }

            return ICResponse.success(storeMarketingInventories);
        }, "getStoreMarketingInventories", param);


    }

    public ICResponse<List<SOAUnStoreMarketingInventory>> getUnMarketingInventories(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            List<SOAUnStoreMarketingInventory> unMarketingInventories = unMarketingService.getUnMarketingInventories(param);
            return ICResponse.success(unMarketingInventories);
        }, "getUnMarketingInventories", param);

    }

    public ICResponse<Integer> updateAvailable(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int i = unMarketingService.updateAvailable(param);
            return ICResponse.success(i);
        }, "updateAvailable", param);

    }

    public ICResponse<Integer> insertUnMarketingInventory(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int i = unMarketingService.insertUnMarketingInventory(param);
            return ICResponse.success(i);
        }, "insertUnMarketingInventory", param);


    }

    public ICResponse<Integer> countUnMarketingInventory(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int i = unMarketingService.countUnMarketingInventory(param);
            return ICResponse.success(i);
        }, "countUnMarketingInventory", param);


    }

    public ICResponse<List<UndProductVo>> getCityProductTotalAvailable(List<UndProductVo> undProductVoList) {
        return ICResponseHandler.template(() -> {
            unMarketingService.getCityProductTotalAvailable(undProductVoList);
            return ICResponse.success(undProductVoList);
        }, "getCityProductTotalAvailable", undProductVoList);


    }

}
