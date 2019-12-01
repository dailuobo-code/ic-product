package com.mallcai.bs.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dailuobo.api.domain.entity.SignValue;
import com.dailuobo.api.domain.soa.SOAAdvertsProductList;
import com.dailuobo.api.domain.soa.SOAHeadline;
import com.dailuobo.api.domain.soa.SOAHeadlineProduct;
import com.dailuobo.api.domain.soa.SOAStore;
import com.dailuobo.api.domain.soa.city.CityProductDto;
import com.dailuobo.api.domain.soa.city.CityProductIds;
import com.dailuobo.api.domain.soa.city.SalesSpecDto;
import com.dailuobo.api.domain.soa.city.SystemParamDto;
import com.dailuobo.api.domain.util.BeanMapUtils;
import com.mallcai.backend.common.dao.vo.StorageStore;
import com.mallcai.backend.common.redis.DefaultMasterJedisProxy;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.bs.mapper.MysqlProductSignMapper;
import com.mallcai.bs.mapper.SOACityGlobalMapper;
import com.mallcai.bs.mapper.SOAHPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SOACityGlobalService extends BaseService{
	@Autowired
	SOACityGlobalMapper SOACityGlobalMapper;
	
	@Autowired
	SOAHPMapper SOAHPMapper;

    @Autowired
    private MysqlProductSignMapper mysqlProductSignMapper;


		public void updateProductV2(Integer cityProductId, Boolean timer) {
				// 更新商品信息
			//TODO 2019/5/14 
			CityProductDto cityProductDto = SOACityGlobalMapper.getProductByCityProductId(cityProductId);
			String redisKey = RedisKeyGenerator.generateCityProductKey(cityProductDto.getCityId(), cityProductId);
			JedisProxy.getInstance().delKey(redisKey);
			JedisProxy.getInstance().set(redisKey, JSON.toJSONString(cityProductDto));

			if (Boolean.TRUE.equals(timer)) {
				// 现场保护日志
				logger.info("==== 定时调价，generateCityProductKey cityProductDto: {}",JedisProxy.getInstance().get(redisKey));
			}


			// 更新商品价格信息
			List<SalesSpecDto> salesSpecDtos = SOACityGlobalMapper.getProductSpecByCityProductId(cityProductId);
			Map<String,String> writeData = new HashMap<>(salesSpecDtos.size());
			for(SalesSpecDto salesSpecDto : salesSpecDtos) {
				if(salesSpecDto.getStoreId() == null) continue;

				SignValue signValue = mysqlProductSignMapper.getSignValue(salesSpecDto.getCityId(), salesSpecDto.getStoreId(), salesSpecDto.getCityProductId());
				String strSignValue = Optional.ofNullable(signValue).filter(s -> s.getEffectiveTime().before(new Date())).map(s -> s.getPennySignValue()).orElse(null);
				salesSpecDto.setPennySignValue(strSignValue);

				if (strSignValue == null){
					strSignValue = "";
				}
				String signValueKey = RedisKeyGenerator.generateOnePointSignKey(salesSpecDto.getCityId(), salesSpecDto.getStoreId(), cityProductId);
				JedisProxy.getInstance().set(signValueKey,strSignValue);

				String key = RedisKeyGenerator.generateStoreSalesSpecKey(cityProductDto.getCityId(), salesSpecDto.getStoreId(),
						salesSpecDto.getCityProductId());
				writeData.put(key, JSON.toJSONString(salesSpecDto));
			}

			if (Boolean.TRUE.equals(timer)) {
				// 现场保护日志
				for (Map.Entry<String, String> stringStringEntry : writeData.entrySet()) {
					logger.info("==== 定时调价，: writeData to redis, key: {}, value:{}",stringStringEntry.getKey(), stringStringEntry.getValue());
				}
			}
			
			delStoreSalesSpec(cityProductDto.getCityId(), cityProductId);
			JedisProxy.getInstance().setMultiKey(writeData);

			if (Boolean.TRUE.equals(timer)) {
				// 现场保护日志
				for (Map.Entry<String, String> stringStringEntry : writeData.entrySet()) {
					logger.info("==== 定时调价，: writeData from redis, key: {}, value:{}",stringStringEntry.getKey(),JedisProxy.getInstance().get(stringStringEntry.getKey()));
				}
			}
		}
		
		private void delStoreSalesSpec(Integer cityId, Integer cityProductId) {
			List<SOAStore> soaStores = getSOACityStoreList(cityId);
			List<String> delKeys = new ArrayList<>();
			for(SOAStore soaStore : soaStores) {
				if(soaStore.getStoreId() == null) continue;
				String key = RedisKeyGenerator.generateStoreSalesSpecKey(cityId, soaStore.getStoreId(), cityProductId);
				delKeys.add(key);
			}
			
			delKeys.add(RedisKeyGenerator.generateStoreSalesSpecKey(cityId, 0, cityProductId));
			
			JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));
		}
		
		public List<SOAStore> getSOACityStoreList(Integer cityId) {
			try{
				List<SOAStore> soaStoreList = getCityStore(cityId);
				if(soaStoreList.size() == 0){
					refreshCityStore(cityId);
					soaStoreList = getCityStore(cityId);
				}
				return soaStoreList;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}
		
		public void refreshCityStore(Integer cityId) throws IOException{
			List<StorageStore> mongoStoreList = SOACityGlobalMapper.getCityStoreList(cityId);
			writeCityStoreList(cityId, mongoStoreList);
		}
		
		public List<SOAStore> getCityStore(Integer cityId) throws Exception {
			List<SOAStore> result = new ArrayList<SOAStore>();
			String redisKey = RedisKeyGenerator.generateCityStoreKey(cityId);
			Set<String> mongoStoreStrList = JedisProxy.getInstance().getSortedSetAllMember(redisKey);
			for(String mongoStoreStr:mongoStoreStrList){
				StorageStore mongoStore = JSON.parseObject(mongoStoreStr, new TypeReference<StorageStore>() {});
				SOAStore soaStore = new SOAStore();
				BeanMapUtils.bean2Bean(mongoStore, soaStore);
				result.add(soaStore);
			}
			return result;
		}
		
		public void writeCityStoreList(Integer cityId, List<StorageStore> mongoStoreList) throws IOException {
			String redisKey = RedisKeyGenerator.generateCityStoreKey(cityId);
			String cityStoreHashKey = RedisKeyGenerator.generateCityStoreHashKey(cityId);
			String cityStoreHashKeyNew = RedisKeyGenerator.generateCityStoreHashKeyNew(cityId);
			Map<String, Double> writeMembers = new HashMap<String, Double>();
			Map<String, String> hashStoreMembers = new HashMap<String, String>();
			for(StorageStore mongoStore:mongoStoreList){
				String jsonMonogStore = JSON.toJSONString(mongoStore);
				writeMembers.put(jsonMonogStore, Double.valueOf(mongoStore.getStoreId()));
				hashStoreMembers.put(String.valueOf(mongoStore.getStoreId()), jsonMonogStore);
			}
			/** 双写到redis，一份是有序集合，用于排序得到，一份是hash结构，用于给设置提货点时调用*/
			JedisProxy.getInstance().delMultiKey(redisKey, cityStoreHashKey, cityStoreHashKeyNew);
			JedisProxy.getInstance().setSortedSetMultiMember(redisKey, writeMembers);
			JedisProxy.getInstance().setHashMultiField(cityStoreHashKey, hashStoreMembers);
		}
		
		public void refreshHeadLine(Integer cityId) {
			delCityStoreData(cityId, "StoreHeadline");
			List<SOAHeadline> soaHeadlines = SOAHPMapper.getHeadlineByCityId(cityId);
			if(soaHeadlines == null || soaHeadlines.isEmpty()) return;
			Map<String,String> writeData = new HashMap<>();
			for(SOAHeadline soaHeadline : soaHeadlines) {
				writeData.put(RedisKeyGenerator.generateHeadlineKey(cityId, soaHeadline.getHeadlineId()),
						com.alibaba.fastjson.JSON.toJSONString(soaHeadline));
			}
			JedisProxy.getInstance().setMultiKey(writeData);
		}
		
		/**
		 * 删除以城市id和门店id为redis的key值的相关数据
		 * @param cityId
		 */
		public void delCityStoreData(Integer cityId, String type) {
			try {
				List<SOAStore> soaStores = getCityStore(cityId);
				if(soaStores == null || soaStores.isEmpty()) {
					refreshCityStore(cityId);
					soaStores = getCityStore(cityId);
				}
				if(soaStores != null && !soaStores.isEmpty()) {
					List<String> keys = new ArrayList<>();
					for(SOAStore soaStore : soaStores) {
						if(soaStore.getStoreId() == null) continue;
						switch (type) {
						case "HPBanner":
							keys.add(RedisKeyGenerator.generateHPBannerSetKey(cityId, soaStore.getStoreId()));
							break;
						case "HPTile":
							keys.add(RedisKeyGenerator.generateHPTileSetKey(cityId, soaStore.getStoreId()));
							break;
						case "StoreTheme":
							keys.add(RedisKeyGenerator.generateStoreThemeKey(cityId, soaStore.getStoreId()));
							break;
						case "StoreHeadline":
							keys.add(RedisKeyGenerator.generateStoreHeadlineKey(cityId, soaStore.getStoreId()));
							break;
						case "HPProduct":
							keys.add(RedisKeyGenerator.generateHPProductKey(cityId, soaStore.getStoreId()));
						default:
							break;
						}
					}
					
					JedisProxy.getInstance().delMultiKey(keys.toArray(new String[]{}));
				} else {
					logger.warn("delCityStoreData:cityId=" + cityId + "没有门店");
				}
			} catch(Throwable t) {
				logger.error(t.getMessage(),t);
			}
		}
		
		public void refreshHeadlineProductList(Integer headlineId) {
			List<SOAHeadlineProduct> soaHeadlineProducts = SOACityGlobalMapper.getHeadlineProductListByHeadlineId(headlineId);
			String key = RedisKeyGenerator.generateHeadlineProdListKey(headlineId);
			if(soaHeadlineProducts == null || soaHeadlineProducts.isEmpty()) { 
				JedisProxy.getInstance().delKey(key);
				return;
			}
			/*Map<String,Double> writeData = new HashMap<>();
			for(SOAHeadlineProduct soaHeadlineProduct : soaHeadlineProducts) {
				writeData.put(com.alibaba.fastjson.JSON.toJSONString(soaHeadlineProduct), soaHeadlineProduct.getHeadlineProductOrder());
			}*/
			JedisProxy.getInstance().delKey(key);
			//JedisProxy.getInstance().setSortedSetMultiMember(key, writeData);
		}


		public String getSysParams(String paramType) {
			// TODO Auto-generated method stub
			String redisKey = RedisKeyGenerator.generateSysParams();
			 
			String value = DefaultMasterJedisProxy.getInstance().getHashField(redisKey,paramType);
			if(value==null){
				refreshSysParams();
				return DefaultMasterJedisProxy.getInstance().getHashField(redisKey,paramType);
			}else{
				return value;
			}
		}
		
		public void refreshSysParams() {
			String redisKey = RedisKeyGenerator.generateSysParams();
			List<SystemParamDto> sysParamList = SOACityGlobalMapper.getSysParam();
			try {
				DefaultMasterJedisProxy proxy = DefaultMasterJedisProxy.getInstance();
				for (SystemParamDto systemParamDto : sysParamList) {
					proxy.setHashField(redisKey,systemParamDto.getParamType(),systemParamDto.getParamVal());
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void updateProductHQ(Integer hqProductId) {
			// 更新商品信息
			// TODO: 2019/5/14  redis处理
			List<CityProductDto> cityProductDtos = SOACityGlobalMapper.getProductByCityHqProductId(hqProductId);
			Map<String,String> writeProducts = new HashMap<>(cityProductDtos.size());
			for(CityProductDto cityProductDto : cityProductDtos) {
				writeProducts.put(RedisKeyGenerator.generateCityProductKey(cityProductDto.getCityId(), cityProductDto.getCityProductId()),
						JSON.toJSONString(cityProductDto));
			}
			JedisProxy.getInstance().setMultiKey(writeProducts);
			
			// 更新商品价格信息
			List<SalesSpecDto> salesSpecDtos = SOACityGlobalMapper.getProductSpecByHqProductId(hqProductId);
			Map<String,String> writeData = new HashMap<>(salesSpecDtos.size());
			for(SalesSpecDto salesSpecDto : salesSpecDtos) {
				if(salesSpecDto.getStoreId() == null) continue;
				SignValue signValue = mysqlProductSignMapper.getSignValue(salesSpecDto.getCityId(), salesSpecDto.getStoreId(), salesSpecDto.getCityProductId());

				String strSignValue = Optional.ofNullable(signValue).filter(s -> s.getEffectiveTime().before(new Date())).map(s -> s.getPennySignValue()).orElse(null);
				salesSpecDto.setPennySignValue(strSignValue);
				String key = RedisKeyGenerator.generateStoreSalesSpecKey(salesSpecDto.getCityId(), salesSpecDto.getStoreId(), 
						salesSpecDto.getCityProductId());
				writeData.put(key, JSON.toJSONString(salesSpecDto));

				JedisProxy.getInstance().delKey(key);
			}
			JedisProxy.getInstance().setMultiKey(writeData);
		}


		public void refreshProductListAll() {
			List<CityProductIds> result = SOACityGlobalMapper.getAllProductIds();
			if(result == null || result.isEmpty()) return;
			Map<Integer,StringBuffer> map = new HashMap<>();
			
			for(CityProductIds prod : result) {
				if(!map.containsKey(prod.getCityId())) map.put(prod.getCityId(), new StringBuffer());
				map.get(prod.getCityId()).append(prod.getProductIds()).append(",");
			}
			
			Iterator<Map.Entry<Integer, StringBuffer>> iter = map.entrySet().iterator();
			Map<String,String> writeData = new HashMap<>();
			while(iter.hasNext()) {
				Map.Entry<Integer, StringBuffer> entry = iter.next();
				String jsonData = entry.getValue().toString();
				jsonData = jsonData.substring(0, jsonData.length() - 1);
				writeData.put(RedisKeyGenerator.generateCityProductList(entry.getKey()), jsonData);
			}
			
			JedisProxy.getInstance().setMultiKey(writeData);
		}

		public void updateSalesSpec(Map<String, Object> param) {
			// TODO Auto-generated method stub
			// 更新商品价格信息
			List<SalesSpecDto> salesSpecDtos = SOACityGlobalMapper.getProductSpecByCityProductIds(param);
			Map<String,String> writeData = new HashMap<>(salesSpecDtos.size());
			for(SalesSpecDto salesSpecDto : salesSpecDtos) {
				if(salesSpecDto.getStoreId() == null) continue;
				SignValue signValue = mysqlProductSignMapper.getSignValue(salesSpecDto.getCityId(), salesSpecDto.getStoreId(), salesSpecDto.getCityProductId());

				String strSignValue = Optional.ofNullable(signValue).filter(s -> s.getEffectiveTime().before(new Date())).map(s -> s.getPennySignValue()).orElse(null);
				salesSpecDto.setPennySignValue(strSignValue);
				String key = RedisKeyGenerator.generateStoreSalesSpecKey(Integer.parseInt(param.get("cityId").toString()), salesSpecDto.getStoreId(), 
						salesSpecDto.getCityProductId());
				writeData.put(key, JSON.toJSONString(salesSpecDto));
			}
			
			delStoreSalesSpecs(param);
			JedisProxy.getInstance().setMultiKey(writeData);
		}
		
		private void delStoreSalesSpecs(Map<String, Object> param) {
			List<SOAStore> soaStores = getSOACityStoreList(Integer.parseInt(param.get("cityId").toString()));
			List<String> delKeys = new ArrayList<>();
			List<String> cityProductIds = (List<String> )param.get("cityProductIds");
			for(SOAStore soaStore : soaStores) {
				if(soaStore.getStoreId() == null) continue;
				//1.门店+商品Id
				for (String cityProductId : cityProductIds) {
					/*System.out.println(Integer.parseInt(param.get("cityId").toString()));*/
					String key = RedisKeyGenerator.generateStoreSalesSpecKey(Integer.parseInt(param.get("cityId").toString()), soaStore.getStoreId(), Integer.parseInt(cityProductId));
					delKeys.add(key);
				}
				
			}
			//2.storeId = 0 + 商品Id
			for (String cityProductId : cityProductIds) {
				delKeys.add(RedisKeyGenerator.generateStoreSalesSpecKey(Integer.parseInt(param.get("cityId").toString()), 0, Integer.parseInt(cityProductId)));
			}
			
			
			JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));
		}
}
