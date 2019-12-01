package com.mallcai.bs.service;

import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.domain.vo.DDLWarehouse;
import com.dailuobo.api.domain.vo.DefaultSpecVo;
import com.mallcai.backend.common.api.Response;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.dao.CityWarehouseDao;
import com.mallcai.bs.dao.SpecDao;
import com.mallcai.bs.enums.product.ProductShowRuleEnum;
import com.mallcai.service.api.IProductTagReadService;
import com.mallcai.service.inventory.marketing.api.IMarketingInventory;
import com.mallcai.service.inventory.marketing.request.AvaliableForProductRequest;
import com.mallcai.service.inventory.marketing.request.ProductItem;
import com.mallcai.service.request.product.GetTagsByCityProductRequest;
import com.mallcai.service.vo.BindingTagDTO;
import com.mallcai.service.vo.CityProductTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
@Service
public class SpecService {
    @Autowired
    private SpecDao specDao;

    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private CityWarehouseDao cityWarehouseDao;
    @Autowired
    private MarketingInventoryService marketingInventoryService;
    @Autowired
    private IProductTagReadService iProductTagReadServ;
    @Autowired
    private IMarketingInventory iMarketingInventoryServ;

    @Transactional
    public Spec getDefaultSpec(Integer cityProductId) {
        return specDao.getDefaultSpec(cityProductId);
    }

    @Transactional
    public SpecAdjustment getSpecAdjustment(Integer cityId, Integer storeId, Integer cityProductId) {
        return specDao.getSpecAdjustment(cityId, storeId, cityProductId);
    }

    public List<Spec> getSpecs(Integer cityId, Integer cityProductId) {
        List<Spec> list = specDao.getSpecs(cityId, cityProductId);
        List<Integer> storeIds = new ArrayList<>();
        if(list == null || list.isEmpty()){
            return list;
        }
        //获取商品对应的标签
        GetTagsByCityProductRequest request = new GetTagsByCityProductRequest();
        request.setCityProductId(cityProductId);
        Response<BindingTagDTO> response =  iProductTagReadServ.getTagsByCityProduct(request);
        CityProductTagDTO cityProductTagDTO = null;
        CityProductSyncCity cityProductSyncCity = null;
        if (response.isSuccess()) {
            BindingTagDTO bindingTagDTO = response.getData();
            cityProductTagDTO = bindingTagDTO.getTag(ProductShowRuleEnum.HIDE_ON_SOLD_OUT.getCode());
            Map<String,Object> map = new HashMap<>();
            map.put("cityProductId",cityProductId);
            cityProductSyncCity = cityProductDao.getCityProductById(map);
        }

        list.forEach(spec -> storeIds.add(spec.getStoreId()));
        //商品对应的仓库类型
        Integer wareHouseType = marketingInventoryService.getCityProductWareHouseTypeByCityProductId(cityProductId);
        Map<Integer, DDLWarehouse> storeWarehouseMap = cityWarehouseDao.getDDLWarehouseMapByStoreIdList(storeIds,wareHouseType);
        for (Spec spec:list) {
            DDLWarehouse ddlWarehouse = storeWarehouseMap.get(spec.getStoreId());
            if(ddlWarehouse != null){
                spec.setWarehouseId(ddlWarehouse.getWarehouseId());
                spec.setWarehouseName(ddlWarehouse.getWarehouseName());
            }
            //门店商品售罄是否显示
            spec.setSellOutShow(true);
            //该商品存在门店售罄不展示,查询门店库存
            if (cityProductTagDTO == null || cityProductSyncCity == null) {
                continue;
            }

            List<ProductItem> productItems = new ArrayList<>();
            ProductItem item = new ProductItem();
            item.setCityId(cityProductSyncCity.getCityId());
            item.setCityProductId(spec.getCityProductId());
            item.setHqProductId(cityProductSyncCity.getHqProductId());
            item.setIsShare(cityProductSyncCity.getIsShare());
            item.setStoreId(spec.getStoreId());
            // TODO: merchantId
            productItems.add(item);
            AvaliableForProductRequest avaliableForProductRequest = new AvaliableForProductRequest();
            avaliableForProductRequest.setProductItems(productItems);

            Map<Integer,Boolean> isAvailale = iMarketingInventoryServ.isAvailableV2(avaliableForProductRequest);
            if (isAvailale.size() == 0) {
               continue;
            }
            spec.setSellOutShow(isAvailale.get(spec.getCityProductId()));
        }
        return list;
    }

    @Transactional
    public void createOrUpdate(CityProduct cityProduct, Spec spec) {
        cityProductDao.update(cityProduct);
        specDao.createOrUpdate(spec);
    }

    @Transactional
    public List<StoreSpec> getStoreSpecs(Map<String, Object> param) {
        return specDao.getStoreSpecs(param);
    }

    @Transactional
    public void updateDefaultSpec(CityProduct cityProduct, Spec spec) {
        /*cityProductDao.up(cityProduct.getCityProductId(), cityProduct.getDisablePrice(), cityProduct.getUpdateUserId());*/
        cityProductDao.updateDefaultSpec(cityProduct.getCityProductId(), cityProduct.getDisablePrice(), cityProduct.getUpdateUserId());
        specDao.updateDefaultSpec(spec);
        if(spec.getThreshold().intValue()>0){
        	//Integer cityId, Integer cityProductId,Double realPrice, Double avgPrice, Integer threshold, Integer userId
        	DefaultSpecVo dsv = new DefaultSpecVo(spec.getCityId(),spec.getCityProductId(),spec.getRealPrice(),spec.getAvgPrice(),spec.getThreshold(),spec.getUpdateUserId());
        	List<Integer> storeIds = specDao.getNeedInsertStores(spec);
        	dsv.setStoreIds(storeIds);
        	if(storeIds.size()>0){
        		specDao.insertDefaultSpec(dsv);
        	}
        }
    }

    @Transactional
    public void updateAdjustmentFactor(Integer cityId, Integer storeId, Integer cityProductId, Integer unify, Double delta, Integer upperLimit, Integer lowerLimit, Integer userId) {
        specDao.updateAdjustmentFactor(cityId, storeId, cityProductId, unify, delta, upperLimit, lowerLimit, userId);
    }
    @Transactional
	public void updateDefaultVipCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		specDao.updateDefaultVipCount(param);
	}

    @Transactional
    public Spec getDefaultSpecBySyncProduct(Integer cityProductId) {
        return specDao.getDefaultSpecBySyncProduct(cityProductId);
    }

    public void syncProductSpecCreate(Spec spec) {
        specDao.syncProductSpecCreate(spec);
    }


    public void syncProductSpecUpdate(Spec spec) {
        specDao.syncProductSpecUpdate(spec);
    }

    public List<Spec> getDefaultSpecByProductIds(List<Integer> cityProductIds) {
        return specDao.getDefaultSpecByProductIds(cityProductIds);
    }

}
