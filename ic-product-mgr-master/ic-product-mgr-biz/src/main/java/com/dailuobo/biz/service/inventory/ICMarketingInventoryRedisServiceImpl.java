package com.dailuobo.biz.service.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.inventory.ICMarketingInventoryRedisService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.mallcai.bs.service.MarketingInventoryRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iCMarketingInventoryRedisService")
public class ICMarketingInventoryRedisServiceImpl implements ICMarketingInventoryRedisService {

    @Autowired
    private MarketingInventoryRedisService marketingInventoryRedisService;

    /**
     * 删除库存，刷新redis.
     *
     * @param cityProductIdList
     * @return
     */
    @Override
    public ICResponse deleteInventoryByCityProductIds(List<Integer> cityProductIdList) {
        return ICResponseHandler.template(new ICResponseHandler.ICResponseCallback() {
            @Override
            public ICResponse handle() {
                if (cityProductIdList == null || cityProductIdList.isEmpty()) {
                    return ICResponse.success();
                }

                marketingInventoryRedisService.deleteInventoryByCityProductIds(cityProductIdList);
                return ICResponse.success();
            }
        }, "deleteInventoryByCityProductIds", cityProductIdList);
    }

    /**
     * 刷新商品库存.
     *
     * @param cityProductIdList
     * @return
     */
    public ICResponse refreshInventoryByCityProductIds(List<Integer> cityProductIdList) {
        return ICResponseHandler.template(() -> {
            if (cityProductIdList == null || cityProductIdList.isEmpty()) {
                return null;
            }
            marketingInventoryRedisService.refreshInventoryByCityProductIds(cityProductIdList);
            return ICResponse.success();
        }, "refreshInventoryByCityProductIds", cityProductIdList);
    }

    /**
     * 刷新商品库存--未分仓.
     *
     * @param cityProductIdList
     * @return
     */
    public ICResponse refreshInventoryByCityProductIdsFromOld(List<Integer> cityProductIdList) {
        return ICResponseHandler.template(() -> {
            if (cityProductIdList == null || cityProductIdList.isEmpty()) {
                return ICResponse.success();
            }
            marketingInventoryRedisService.refreshInventoryByCityProductIdsFromOld(cityProductIdList);
            return ICResponse.success();
        }, "refreshInventoryByCityProductIdsFromOld", cityProductIdList);

    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     *
     * @param cityProductIdList
     * @return
     */
    public ICResponse<Map<Integer, Boolean>> judgeCityProductIsNeedWarehouse(List<Integer> cityProductIdList) {
        return ICResponseHandler.template(() -> {
            //共享标品 多仓 开发城市
            Map<Integer, Boolean> result = marketingInventoryRedisService.judgeCityProductIsNeedWarehouse(cityProductIdList);
            return ICResponse.success(result);
        }, "judgeCityProductIsNeedWarehouse", cityProductIdList);
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     *
     * @param cityProductIdList
     * @return
     */
    public ICResponse<Map<Integer, Boolean>> judgeCityProductIsNeedWarehouseOld(List<Integer> cityProductIdList) {
        return ICResponseHandler.template(() -> {
            //共享标品 多仓 开发城市
            Map<Integer, Boolean> result = marketingInventoryRedisService.judgeCityProductIsNeedWarehouseOld(cityProductIdList);
            return ICResponse.success(result);
        }, "judgeCityProductIsNeedWarehouseOld", cityProductIdList);
    }

    /**
     * 判断商品是否可以设置共享标品多仓
     *
     * @param cityProductIdList
     * @return
     */
    public ICResponse<Map<Integer, Boolean>> judgeCityProductIsAllowSetShare(List<Integer> cityProductIdList) {
        return ICResponseHandler.template(() -> {
            //共享标品 多仓 开发城市
            Map<Integer, Boolean> result = marketingInventoryRedisService.judgeCityProductIsAllowSetShare(cityProductIdList);
            return ICResponse.success(result);
        },"judgeCityProductIsAllowSetShare",cityProductIdList);
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     * 非标品，走生鲜仓
     *
     * @param cityId
     * @param isShare
     * @param isStandard
     * @return
     */
    private boolean judgeCityProductIsShareAndStandardOld(Integer cityId, Integer isShare, Integer isStandard) {
        return judgeCityIsHaveMoreWarehouse(cityId).getData() && isShare == 1 && isStandard == 1;
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     * 非标品，走生鲜仓
     *
     * @param cityId
     * @param isShare
     * @param isStandard
     * @return
     */
    private boolean judgeCityProductIsShareAndStandard(Integer cityId, Integer isShare, Integer isStandard) {
        return judgeCityIsHaveMoreWarehouse(cityId).getData() && isShare == 1;
    }

    /**
     * 判断城市是否在 开发多仓的城市里.
     *
     * @param cityId
     * @return
     */
    public ICResponse<Boolean> judgeCityIsHaveMoreWarehouse(Integer cityId) {
        return ICResponseHandler.template(() -> {
            marketingInventoryRedisService.judgeCityIsHaveMoreWarehouse(cityId);
            return ICResponse.success(false);
        },"judgeCityIsHaveMoreWarehouse",cityId);
    }

    private String makeStringAppendComma(String str) {
        return "," + str + ",";
    }
}
