package com.mallcai.bs.dao;

import java.util.List;
import java.util.Map;

import com.mallcai.bs.mapper.UnMarketingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailuobo.api.domain.soa.SOAUnStoreMarketingInventory;
import com.dailuobo.api.domain.util.Constant;

@Repository
public class UnMarketingDao {
    @Autowired
    private UnMarketingMapper unMarketingMapper;

	public List<SOAUnStoreMarketingInventory> getStoreMarketingInventories(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		param.put("smokeClassifyId",Constant.SMOKE_CLASSIFY_ID);
		return unMarketingMapper.getStoreMarketingInventories(param);
	}

	public List<SOAUnStoreMarketingInventory> getUnMarketingInventories(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unMarketingMapper.getUnMarketingInventories(param);
	}

	public int countUnMarketingInventory(Map<String, Object> param) {
		return unMarketingMapper.countUnMarketingInventory(param);
	}

	public int insertUnMarketingInventory(Map<String, Object> param) {
		return unMarketingMapper.insertUnMarketingInventory(param);
	}

	public int updateAvailable(Map<String, Object> param) {
		return unMarketingMapper.updateAvailable(param);
	}

	public int replaceAvailable(Map<String, Object> param) {
		return unMarketingMapper.replaceAvailable(param);
	}

	public SOAUnStoreMarketingInventory getUndSalesSpec(Integer cityProductId) {
		return unMarketingMapper.getUndSalesSpec(cityProductId);
	}

	public List<Map<String,Object>> getCityProductTotalAvailable(List<Integer> cityIdList, List<Integer> cityProductIdList) {
		return unMarketingMapper.getCityProductTotalAvailable(cityIdList,cityProductIdList);
	}

	public int countAvailableInventory(Integer storeId, Integer cityProductId, int available) {
		return unMarketingMapper.countAvailableInventory(storeId, cityProductId, available);
	}
}
