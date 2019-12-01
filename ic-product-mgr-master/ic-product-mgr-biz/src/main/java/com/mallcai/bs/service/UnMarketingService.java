package com.mallcai.bs.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mallcai.bs.dao.UnMarketingDao;
import com.dailuobo.api.domain.soa.SOAUnStoreMarketingInventory;
import com.dailuobo.api.domain.vo.UndProductVo;

@Service
public class UnMarketingService {
	@Autowired
    private UnMarketingDao unMarketingDao;

	public List<SOAUnStoreMarketingInventory> getStoreMarketingInventories(
			Map<String, Object> param) {
		List<SOAUnStoreMarketingInventory> soaUnStoreMarketingInventoryList=unMarketingDao.getStoreMarketingInventories(param);
		Integer cityId= (Integer) param.get("cityId");
		Set<Integer> cityProductIdSet=Sets.newHashSet();
		if(CollectionUtils.isNotEmpty(soaUnStoreMarketingInventoryList)){
			for(SOAUnStoreMarketingInventory soaUnStoreMarketingInventory:soaUnStoreMarketingInventoryList){
				Integer cityProductId=soaUnStoreMarketingInventory.getCityProductId();
				cityProductIdSet.add(cityProductId);
			}
		}
		Map<Integer,Integer> dataMap= Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(cityProductIdSet)){
			List<Map<String,Object>> mapList=unMarketingDao.getCityProductTotalAvailable(Lists.newArrayList(cityId),Lists.newArrayList(cityProductIdSet));
			for(Map<String,Object> map:mapList){
				Integer cityProductId= (Integer) map.get("productId");
				BigDecimal totalAvailable= (BigDecimal) map.get("totalAvailable");
				dataMap.put(cityProductId,totalAvailable.intValue());

			}
		}
		if(MapUtils.isNotEmpty(dataMap)){
			for(SOAUnStoreMarketingInventory soaUnStoreMarketingInventory:soaUnStoreMarketingInventoryList){
				Integer cityProductId=soaUnStoreMarketingInventory.getCityProductId();
				Integer totalAvailable=dataMap.get(cityProductId);
				soaUnStoreMarketingInventory.setTotalAvailable(totalAvailable);
			}
		}
		return soaUnStoreMarketingInventoryList;
	}

	public List<SOAUnStoreMarketingInventory> getUnMarketingInventories(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unMarketingDao.getUnMarketingInventories(param);
	}

	public int updateAvailable(Map<String, Object> param) {
		return unMarketingDao.updateAvailable(param);
	}

	public int insertUnMarketingInventory(Map<String, Object> param) {
		return unMarketingDao.insertUnMarketingInventory(param);
	}

	public int countUnMarketingInventory(Map<String, Object> param) {
		return unMarketingDao.countUnMarketingInventory(param);
	}
	public List<UndProductVo> getCityProductTotalAvailable(List<UndProductVo> undProductVoList){
		if(CollectionUtils.isEmpty(undProductVoList)){
			return undProductVoList;
		}
		Set<Integer> cityIdSet= Sets.newHashSet();
		Set<Integer> cityProductIdSet=Sets.newHashSet();
		for(UndProductVo undProductVo:undProductVoList){
			if(undProductVo.getCityId()!=null){
				cityIdSet.add(undProductVo.getCityId());
			}
			if(undProductVo.getCityProductId()!=null){
				cityProductIdSet.add(undProductVo.getCityProductId());
			}
		}
		Map<Integer,Integer> dataMap= Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(cityIdSet)&&CollectionUtils.isNotEmpty(cityProductIdSet)){
			List<Map<String,Object>> mapList=unMarketingDao.getCityProductTotalAvailable(new ArrayList<>(cityIdSet),new ArrayList<>(cityProductIdSet));
			for(Map<String,Object> map:mapList){
				Integer cityProductId= (Integer) map.get("productId");
				BigDecimal totalAvailable= (BigDecimal) map.get("totalAvailable");
				dataMap.put(cityProductId,totalAvailable.intValue());
			}
		}
		if(MapUtils.isNotEmpty(dataMap)){
			for(UndProductVo undProductVo:undProductVoList){
				Integer cityProductId=undProductVo.getCityProductId();
				Integer totalAvailable=dataMap.get(cityProductId);
				undProductVo.setTotalAvailable(totalAvailable);
			}
		}
		return undProductVoList;
	}

}
