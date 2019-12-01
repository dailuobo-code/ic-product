package com.mallcai.bs.service;

import com.alibaba.fastjson.JSON;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.bs.dao.NoSaleDao;
import com.dailuobo.api.domain.entity.NoSaleDto;
import com.dailuobo.api.domain.entity.StoreNoSale;
import com.dailuobo.api.domain.vo.NoSaleVo;
import com.mallcai.backend.common.redis.JedisProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoSaleService {

		@Autowired
	    private NoSaleDao noSaleDao;

		public List<NoSaleVo> selectAll(Map<String, Object> param) {
			
			return noSaleDao.selectAll(param);
		}
		@Transactional
		public void add(List<StoreNoSale> storeNoSales) {
			noSaleDao.add(storeNoSales);
//			List<String> keys = new ArrayList<>();
			for (StoreNoSale storeNoSale : storeNoSales) {
				updateCityStoreNoSaleProduct(storeNoSale.getCityId(),storeNoSale.getStoreId());
//				keys.add(RedisKeyGenerator.generateCityStoreNoSaleProduct(storeNoSale.getCityId(),storeNoSale.getStoreId()));
			}
//			if(keys.size()>0){
//				JedisProxy.getInstance().delMultiKey(keys.toArray(new String[]{}));
//			}
		}
		
		@Transactional
		public void delete(List<Integer> ids, Integer userId,Integer cityId,Integer[] storeIds) {
			noSaleDao.delete(ids,userId);
			for (Integer storeId : storeIds) {
            	updateCityStoreNoSaleProduct(cityId, storeId);
			}
		}
		
		/**
		 * 根据数据库查询情况刷新redis中的数据
		 * @param mouldId
		 */
		public void updateCityStoreNoSaleProduct(Integer cityId, Integer storeId) {
			Map<String,Object> map = new HashMap<>();
			map.put("city_id", cityId);
			map.put("store_id", storeId);
			List<NoSaleDto> noSaleList = noSaleDao.getCityStoreNoSaleProduct(map);
			String redisKey = RedisKeyGenerator.generateCityStoreNoSaleProduct(cityId,storeId);
			if(noSaleList == null || noSaleList.isEmpty()) {
				JedisProxy.getInstance().delKey(redisKey);
				return;
			}
			Map<String,Double> data = new HashMap<>();
			for(NoSaleDto noSale : noSaleList) {
				data.put(JSON.toJSONString(noSale), Double.valueOf(noSale.getCityProductId()));
			}
			JedisProxy.getInstance().delKey(redisKey);
			JedisProxy.getInstance().setSortedSetMultiMember(redisKey,data);
		}

}
