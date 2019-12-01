package com.dailuobo.biz.service.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.domain.soa.SOAMarketingInventoryVo;
import com.dailuobo.api.domain.soa.SOAStoreProduct;
import com.dailuobo.api.domain.util.LoggerUtils;
import com.dailuobo.api.inventory.ICSOAMarketingInventoryService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.SOAMarketingInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iCSOAMarketingInventoryService")
public class ICSOAMarketingInventoryServiceImpl implements ICSOAMarketingInventoryService {

    @Autowired
    private SOAMarketingInventoryService soaMarketingInventoryService;



    private boolean isTrue(String value) {
        return "1".equals(value);
    }

    public ICResponse<Map<Integer, Boolean>> isAvailable(Map<SOAStoreProduct, Integer> map) {
        return ICResponseHandler.template(() -> {
            Map<Integer, Boolean> available = soaMarketingInventoryService.isAvailable(map);
            return ICResponse.success(available);
        }, "", map);
    }

    public ICResponse setMarketingInventoryV2(SOAMarketingInventoryVo miv) {
        try {
            soaMarketingInventoryService.setMarketingInventoryV2(miv);
            return ICResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return ICResponse.fail(e.getMessage());
        }
    }


    public ICResponse setMarketingInventoryShare(Integer cityProductId, Integer available, Integer threshold, Integer type, Integer delta, Integer updateUserId) {
        try {
            soaMarketingInventoryService.setMarketingInventoryShare(cityProductId, available, threshold, type, delta, updateUserId);
            return ICResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    public ICResponse setMarketingInventoryShareWarehouse(StoreMarketingInventory inventory) {
        try {
            soaMarketingInventoryService.setMarketingInventoryShareWarehouse(inventory);
            return ICResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return ICResponse.fail(e.getMessage());
        }
    }


    public ICResponse setMarketingInventory(Integer cityProductId, Integer orgId, Integer delta, Integer threshold, Integer source) throws Exception {
        try {
            soaMarketingInventoryService.setMarketingInventory(cityProductId, orgId, delta, threshold, source);
            return ICResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    public ICResponse deleteMarketingInventory(List<Integer> cityProductIds) {
        try {
            //删除redis
            soaMarketingInventoryService.deleteMarketingInventory(cityProductIds);
            return ICResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    public ICResponse marketingInit(boolean flag, Integer cityId) {
        Map param1= Maps.newHashMap();
        param1.put("flag",flag);
        param1.put("cityId",cityId);

        return ICResponseHandler.template(() -> {
            soaMarketingInventoryService.marketingInit(flag, cityId);
            return ICResponse.success();
        }, "marketingInit", param1);
    }

    public ICResponse setMarketingInventoryTask(Integer cityProductId, Integer orgId, Integer delta) {
        try {
            soaMarketingInventoryService.setMarketingInventoryTask(cityProductId, orgId, delta);
            return ICResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return ICResponse.success(e.getMessage());
        }
    }

    //对共享库存进行操作

    public ICResponse checkPiece(Integer cityProductId, Integer delta, Integer cityId) {
        Map param= Maps.newHashMap();
        param.put("cityProductId",cityProductId);
        param.put("delta",delta);
        param.put("cityId",cityId);
        return ICResponseHandler.template(() -> {
            soaMarketingInventoryService.checkPiece(cityProductId, delta, cityId);
            return ICResponse.success();
        }, "checkPiece", param);

    }
}
