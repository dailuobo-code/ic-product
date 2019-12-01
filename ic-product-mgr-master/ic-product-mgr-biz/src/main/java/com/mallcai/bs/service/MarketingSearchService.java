package com.mallcai.bs.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dailuobo.api.domain.entity.OrderInventory;
import com.google.common.collect.Lists;
import com.mallcai.bs.dao.MarketingInventoryDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mallcai.bs.dao.MarketingSearchDao;
import com.dailuobo.api.domain.vo.MarketingVo;

@Service
@Slf4j
public class MarketingSearchService {
    @Autowired
    private MarketingSearchDao marketingSearchDao;

	@Autowired
	private MarketingInventoryDao marketingInventoryDao;


	public List<MarketingVo> selectAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Long selectAllBeginTime = System.currentTimeMillis() ;
		List<MarketingVo> marketingVoList = marketingSearchDao.selectAll(param);
		if(CollectionUtils.isEmpty(marketingVoList)){
			log.warn("##MarketingSearchService#selectAll Dao request return empty,param:{}",param);
			return Lists.newArrayList();
		}
		log.info("selectAll_spendTime " +(System.currentTimeMillis() - selectAllBeginTime));
		//查询city_product_id对应的可用量available,限购查询threshold
		Long selectAvailableAndThresholdBeginTime = System.currentTimeMillis() ;
		List<MarketingVo> availableAndThresholdmarketingVoList = marketingSearchDao.selectAvailableAndThreshold(param) ;
		Map<Integer, MarketingVo> availableAndThresholdmarketingVoMap = new HashMap<>();
		if(null != availableAndThresholdmarketingVoList && !availableAndThresholdmarketingVoList.isEmpty()) {
			availableAndThresholdmarketingVoList.forEach(marketingVo -> availableAndThresholdmarketingVoMap.put(marketingVo.getCityProductId(), marketingVo));
		}
		log.info("selectAvailableAndThreshold_spendTime " +(System.currentTimeMillis() - selectAvailableAndThresholdBeginTime));
		//查询city_product_id对应的库存量availableBase
		Long selectAvailableBaseBeginTime = System.currentTimeMillis() ;
		List<MarketingVo> availableBaseMarketingVoList = marketingSearchDao.selectAvailableBase(param) ;
		Map<Integer, MarketingVo> availableBaseMarketingVoMap = new HashMap<>();
		if(null != availableBaseMarketingVoList && !availableBaseMarketingVoList.isEmpty()) {
			availableBaseMarketingVoList.forEach(marketingVo -> availableBaseMarketingVoMap.put(marketingVo.getCityProductId(), marketingVo));
		}
		log.info("selectAvailableBase_spendTime " +(System.currentTimeMillis() - selectAvailableBaseBeginTime));
		Long beginTime = System.currentTimeMillis() ;
		List<Integer> cityProductIds = Lists.newArrayList() ;
		if(null != marketingVoList){
			for (MarketingVo marketingVo : marketingVoList) {
				String l1L2Names = marketingVo.getL1L2Names() ;
				if(StringUtils.isNotEmpty(l1L2Names)){
					String[] strs = l1L2Names.split("_") ;
					marketingVo.setC1Name(strs[0]);
					marketingVo.setC2Name(strs[1]);
				}

				MarketingVo availableAndThresholdmarketingVo = availableAndThresholdmarketingVoMap.get(marketingVo.getCityProductId()) ;
				if(null != availableAndThresholdmarketingVo){
					marketingVo.setAvailable(availableAndThresholdmarketingVo.getAvailable());
					marketingVo.setThreshold(availableAndThresholdmarketingVo.getThreshold());
				}
				MarketingVo availableBaseMarketingVo = availableBaseMarketingVoMap.get(marketingVo.getCityProductId()) ;
				if(null != availableBaseMarketingVo){
					marketingVo.setAvailableBase(availableBaseMarketingVo.getAvailableBase());
				}
				cityProductIds.add(marketingVo.getCityProductId()) ;
			}
		}
		log.info("marketingVoList_Handle_spendTime " +(System.currentTimeMillis() - beginTime));
		//在单量查询
		Long batchQueryOrderInventoryBeginTime = System.currentTimeMillis() ;
		log.info("cityProductIds: " +cityProductIds);
		if(!cityProductIds.isEmpty()){
			boolean isMultiSpec = isMultiSpec(cityProductIds) ;
			if(isMultiSpec){
				List<OrderInventory> orderInventoryList = marketingInventoryDao.batchQueryOrderInventory(cityProductIds) ;
				Map<Integer, OrderInventory> orderInventoryMap = new HashMap<>();
				if(null != orderInventoryList && !orderInventoryList.isEmpty()) {
					orderInventoryList.forEach(orderInventory -> orderInventoryMap.put(orderInventory.getCityProductId(), orderInventory));
				}
				for (MarketingVo marketingVo : marketingVoList) {
					OrderInventory orderInventory = orderInventoryMap.get(marketingVo.getCityProductId()) ;
					if(null != orderInventory){
						marketingVo.setInQuantity((null == marketingVo.getInQuantity()?0:marketingVo.getInQuantity()) + (null == orderInventory.getProductNum()?0:orderInventory.getProductNum()));
					}
				}
			}else{
				Iterator<MarketingVo> marketingVoIterator = marketingVoList.iterator();
				while (marketingVoIterator.hasNext()) {
					MarketingVo marketingVo = marketingVoIterator.next();
					if(availableBaseMarketingVoMap.containsKey(marketingVo.getCityProductId())) {
						if(marketingVo.getAvailableBase() != null && marketingVo.getAvailable() != null) {
							marketingVo.setInQuantity(marketingVo.getAvailableBase() - marketingVo.getAvailable());
						}
					}else {
						marketingVoIterator.remove();
						continue;
					}
				}
			}
		}
		log.info("orderInventoryList_Handle_spendTime " +(System.currentTimeMillis() - batchQueryOrderInventoryBeginTime));
		return marketingVoList;
	}

	private Boolean isMultiSpec(List<Integer> cityProductIds){
		int count = marketingInventoryDao.countMultiSpec(cityProductIds) ;
		return count >0;
	}
}
