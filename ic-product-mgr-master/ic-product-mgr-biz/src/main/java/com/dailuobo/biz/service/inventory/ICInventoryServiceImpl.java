package com.dailuobo.biz.service.inventory;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.inventory.ICMarketingInventoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.backend.common.api.Response;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.service.MarketingInventoryService;
import com.mallcai.service.inventory.marketing.api.IMarketingInventory;
import com.mallcai.service.inventory.marketing.request.market.AvailableForActivityRequest;
import com.mallcai.service.inventory.marketing.request.market.InventoryAvailableActivityRequest;
import com.mallcai.service.inventory.marketing.response.ActivityAvailableResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("iCMarketingInventoryService")
@Slf4j
public class ICInventoryServiceImpl implements ICMarketingInventoryService {

    @Autowired
    private MarketingInventoryService marketingInventoryService;

    @Autowired
    private IMarketingInventory iMarketingInventoryServ;

    @Autowired
    private CityProductDao cityProductDao;


    @Override
    @Transactional
    public ICResponse<List<StoreMarketingInventory>> getStoreMarketingInventories(Map<String, Object> param) {

        int offset = 0;
        int limit = 0;
        if (param.get("offset") != null && param.get("limit") != null) {
            offset = (int) param.get("offset");
            limit = (int) param.get("limit");
            PageHelper.offsetPage(offset, limit);
        }

        try {
            List<StoreMarketingInventory> storeMarketingInventories = marketingInventoryService.getStoreMarketingInventories(param);

            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) storeMarketingInventories).getTotal(), offset / limit + 1);
                return ICResponse.success(storeMarketingInventories, pageDTO);
            }
            return ICResponse.success(storeMarketingInventories);
        } catch (Exception e) {
            log.error(String.format("getStoreMarketingInventories error,request:%s", JSON.toJSONString(param)), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<List<MarketingInventory>> getMarketingInventories(Integer cityId, Integer cityProductId) {
        try {
            List<MarketingInventory> marketingInventories = marketingInventoryService.getMarketingInventories(cityId, cityProductId);
            return ICResponse.success(marketingInventories);
        } catch (Exception e) {
            log.error(String.format("##getMarketingInventories  throw exception,request=>cityId:%s.cityProductId:%s", cityId, cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<List<MarketingInventory>> getMarketingShares(Integer storeId, Integer cityProductId) {
        List<MarketingInventory> marketingShares = marketingInventoryService.getMarketingShares(storeId, cityProductId);
        return ICResponse.success(marketingShares);
    }

    @Override
    public ICResponse<List<MarketingInventory>> getBulkMarketingShares(List<Integer> cityProductIds) {
        if(cityProductIds == null || cityProductIds.size() <= 0) {
            return ICResponse.fail("入参为空");
        }
        Map<String,Object> param = new HashMap<>(1);
        List<Integer> tempList = new ArrayList<>(1);
        tempList.add(cityProductIds.get(0));
        param.put("cityProductIds", tempList);
        List<CityProduct> dataProduct = cityProductDao.selectAll(param);
        if(CollectionUtils.isEmpty(dataProduct)){
            return ICResponse.fail("商品未查询到");
        }
        Integer cityId = dataProduct.get(0).getCityId();
        InventoryAvailableActivityRequest inventoryAvailableListRequest = new InventoryAvailableActivityRequest();
        inventoryAvailableListRequest.setCityId(cityId);

        List<AvailableForActivityRequest> availableForActivityRequests = new ArrayList<>(cityProductIds.size());
        for (Integer cityProductId : cityProductIds){
            AvailableForActivityRequest availableForActivityRequest = new AvailableForActivityRequest();
            availableForActivityRequest.setCityId(cityId);
            availableForActivityRequest.setCityProductId(cityProductId);
            availableForActivityRequests.add(availableForActivityRequest);
        }
        inventoryAvailableListRequest.setProductList(availableForActivityRequests);
        Response<List<ActivityAvailableResponse>> response = iMarketingInventoryServ.getavailableList(inventoryAvailableListRequest);
        if(!response.isSuccess()){
            return ICResponse.fail(response.getMessage());
        }
        List<ActivityAvailableResponse> data = response.getData();
        Map<Integer, Integer> map = new HashMap<>(data.size());
        for(ActivityAvailableResponse activityAvailableResponse : data) {
            map.put(activityAvailableResponse.getCityProductId(),activityAvailableResponse.getActivityAvailable());
        }
        List<MarketingInventory> marketingInventories = new ArrayList<>(data.size());
        for (Integer cityProductId : cityProductIds){
            Integer available = map.get(cityProductId);
            MarketingInventory marketingInventory = new MarketingInventory();
            if(available == null){
                marketingInventory.setCityProductId(cityProductId);
                marketingInventory.setAvailable(0);
                marketingInventory.setAvailableBase(0);
            }else {
                marketingInventory.setCityProductId(cityProductId);
                marketingInventory.setAvailable(available);
                marketingInventory.setAvailableBase(null);
            }
            marketingInventories.add(marketingInventory);
        }
        return ICResponse.success(marketingInventories);
    }

    @Override
    public ICResponse<List<MarketingInventory>> getWareHouseMarketingShares(Integer storeId, Integer cityProductId, Integer cityId) {
        try {
            List<MarketingInventory> wareHouseMarketingShares = marketingInventoryService.getWareHouseMarketingShares(storeId, cityProductId, cityId);
            return ICResponse.success(wareHouseMarketingShares);
        } catch (Exception e) {
            log.error(String.format("##getWareHouseMarketingShares  throw exception,request=>cityId:%s,storeId:%s,cityProductId:%s", cityId, storeId, cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    /**
     * 获取单个商品仓库类型.
     *
     * @param cityProductId
     * @return
     */
    @Override
    public ICResponse<Integer> getCityProductWareHouseTypeByCityProductId(Integer cityProductId) {
        try {
            List<Integer> cityProductIdList = new ArrayList<>();
            cityProductIdList.add(cityProductId);
            ICResponse<Map<Integer, Integer>> cityProductWareHouseType = getCityProductWareHouseType(cityProductIdList);
            if (cityProductWareHouseType.getData() != null) {
                Integer wareHouseType = cityProductWareHouseType.getData().get(cityProductId);
                return ICResponse.success(wareHouseType == null ? -1 : wareHouseType);
            }
            return ICResponse.fail("未查询到商品对应仓库");
        } catch (Exception e) {
            log.error(String.format("##getCityProductWareHouseTypeByCityProductId exception,request:cityProcutId",cityProductId),e);
            return ICResponse.fail("商品对应仓库失败"+e.getMessage());
        }

    }

    /**
     * 获取商品仓库类型
     *
     * @param cityProductIdList
     * @return
     */
    @Override
    public ICResponse<Map<Integer, Integer>> getCityProductWareHouseType(List<Integer> cityProductIdList) {
        if (cityProductIdList == null || cityProductIdList.isEmpty()) {
            return ICResponse.success(new HashMap<>());
        }
        try {
            Map<Integer, Integer> cityProductWareHouseType = marketingInventoryService.getCityProductWareHouseType(cityProductIdList);

            return ICResponse.success(cityProductWareHouseType);
        } catch (Exception e) {
            log.error(String.format("##getCityProductWareHouseType  throw exception,request=>cityProductIdList:%s", JSON.toJSONString(cityProductIdList)), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ICResponse updateMarketingShares(Integer isShare, Integer cityId,
                                            Integer cityProductId, Integer updateUserId) {
        // TODO 根据isShare更新
        try {
            marketingInventoryService.updateMarketingShares(isShare, cityId, cityProductId, updateUserId);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##updateMarketingShares  throw exception,request=>cityId:%s,isShare:%s,cityProductId:%s", cityId, isShare, cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<String> checkProduct(Integer cityProductId) {
        // TODO Auto-generated method stub
        try {
            String s = marketingInventoryService.checkProduct(cityProductId);
            return ICResponse.success(s);
        } catch (Exception e) {
            log.error(String.format("##updateMarketingShares  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    @Deprecated
    public ICResponse<Integer> checkProductPiece(Integer cityProductId) {
        // TODO Auto-generated method stub
       /*marketingInventoryService.checkProduct()*/
        return ICResponse.success(1);
    }

    @Override
    public ICResponse updateMarketLog(Map<String, Object> param) {
        // TODO Auto-generated method stub
        try {
            marketingInventoryService.updateMarketLog(param);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##updateMarketLog  throw exception,request=>param:%s", JSON.toJSONString(param)), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse updateBannerProductOrder(Integer cityProductId) {
        try {
            marketingInventoryService.updateBannerProductOrder(cityProductId);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##updateBannerProductOrder  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse updateTileProductOrder(Integer cityProductId) {
        try {
            marketingInventoryService.updateTileProductOrder(cityProductId);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##updateTileProductOrder  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse updateThemeProductOrder(Integer cityProductId) {
        try {
            marketingInventoryService.updateThemeProductOrder(cityProductId);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##updateThemeProductOrder  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<List<Integer>> getBannerIdsByProductId(Integer cityProductId) {
        try {
            List<Integer> bannerIdsByProductId = marketingInventoryService.getBannerIdsByProductId(cityProductId);
            return ICResponse.success(bannerIdsByProductId);
        } catch (Exception e) {
            log.error(String.format("##getBannerIdsByProductId  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<List<Integer>> getTileIdsByProductId(Integer cityProductId) {
        try {
            List<Integer> tileIdsByProductId = marketingInventoryService.getTileIdsByProductId(cityProductId);
            return ICResponse.success(tileIdsByProductId);
        } catch (Exception e) {
            log.error(String.format("##getBannerIdsByProductId  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<List<Integer>> getThemeIdsByProductId(Integer cityProductId) {
        try {
            List<Integer> themeIdsByProductId = marketingInventoryService.getThemeIdsByProductId(cityProductId);
            return ICResponse.success(themeIdsByProductId);
        } catch (Exception e) {
            log.error(String.format("##getThemeIdsByProductId  throw exception,request=>cityProductId:%s", cityProductId), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<String> queryNoIsshareProduct(List<StoreMarketingInventory> list, Integer cityId) {
        try {
            String s = marketingInventoryService.queryNoIsshareProduct(list, cityId);
            return ICResponse.success(s);
        } catch (Exception e) {
            log.error(String.format("##queryNoIsshareProduct  throw exception,request=>cityId:%s,list:%s", cityId,JSON.toJSONString(list)), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse<Integer> queryCityProductId(String productNo, Integer cityId) {
        try {
            Integer integer = marketingInventoryService.queryCityProductId(productNo, cityId);
            return ICResponse.success(integer);
        } catch (Exception e) {
            log.error(String.format("##queryCityProductId  throw exception,request=>cityId:%s,productNo:%s", cityId,productNo), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    /**
     * 批量设置导入商品的库存信息
     * 事务控制
     *
     * @return
     */
    @Override
    @Transactional
    public ICResponse ManipulationData(List<StoreMarketingInventory> list, Integer userId, Integer cityId) throws Exception {
        try {
            marketingInventoryService.ManipulationData(list, userId, cityId);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##ManipulationData  throw exception,request=>cityId:%s,userId:%s,list:%s", cityId,userId,JSON.toJSONString(list)), e);
            return ICResponse.fail(e.getMessage());
        }
    }


    @Override
    public ICResponse refreshMarketing(List<StoreMarketingInventory> list) {
        try {
            marketingInventoryService.refreshMarketing(list);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##refreshMarketing  throw exception,request=>list:%s",JSON.toJSONString(list)), e);
            return ICResponse.fail(e.getMessage());
        }
    }

    @Override
    public ICResponse saveImportLog(List<StoreMarketingInventory> list, Integer createuser) {
        try {
            marketingInventoryService.saveImportLog(list, createuser);
            return ICResponse.success();
        } catch (Exception e) {
            log.error(String.format("##refreshMarketing  throw exception,request=>creator:%s,list:%s",createuser,JSON.toJSONString(list)), e);
            return ICResponse.fail(e.getMessage());
        }
    }
}
