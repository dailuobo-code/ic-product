package com.mallcai.bs.mq.product.util;

import com.google.common.collect.Lists;
import com.dailuobo.api.domain.soa.city.CityProductDto;
import com.dailuobo.api.domain.soa.city.ProductClassifyRel;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import org.apache.commons.collections.CollectionUtils;
import com.mallcai.backend.common.redis.JedisProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Benson
 * @projectName app-admin
 * @packageName com.mallcai.bs.mq.product.util
 * @description TODO
 * @date 2019-06-17 10:25
 */

@Component
public class ProductInfoMsgUtils {
	
	@Autowired
	com.mallcai.bs.mapper.SOAHPMapper SOAHPMapper;
	
	
	@Autowired
	com.mallcai.bs.mapper.SOAClassifyMapper SOAClassifyMapper;
	/**
	 * 更新redis城市商品信息
	 *
	 * @param hpProductId
	 */
	public void updateCityProductInfo(Integer hpProductId) {
		// 更新商品信息
		List<CityProductDto> cityProductDtos = SOAHPMapper.getProductByCityHqProductId(hpProductId);
		Map<String, String> writeProducts = new HashMap<>(cityProductDtos.size());
		for (CityProductDto cityProductDto : cityProductDtos) {
			writeProducts.put(RedisKeyGenerator.generateCityProductKey(cityProductDto.getCityId(), cityProductDto.getCityProductId()), com.alibaba.fastjson.JSON.toJSONString(cityProductDto));
		}
		JedisProxy.getInstance().setMultiKey(writeProducts);
		
	}
	
	
	
	/**
	 * 更新二级分类与商品关系
	 *
	 * @param oldSencondClassifyId
	 */
	public void updateProductClassify(Integer oldSencondClassifyId) {
		
		
		
		//防止生产环境主从同步不致
		List<ProductClassifyRel> productClassify = SOAClassifyMapper.findProductClassify();
		if (CollectionUtils.isEmpty(productClassify)){
			return;
		}
		
		List<List<Integer>> partition = Lists.partition(productClassify.stream().map(ProductClassifyRel::getHqProductId).collect(Collectors.toList()), 500);
		List<ProductClassifyRel> productClassifyRels=new ArrayList<>(512);
		for (List<Integer> a:partition){
			List<ProductClassifyRel> tmp = SOAClassifyMapper.findCityProduct(a);
			productClassifyRels.addAll(tmp);
		}
		
		
		if (CollectionUtils.isEmpty(productClassifyRels)){
			return;
		}
		
		
		for (ProductClassifyRel t:productClassifyRels){
			for(ProductClassifyRel c:productClassify){
				if(t.getHqProductId().equals(c.getHqProductId())){
					t.setClassifyId(c.getClassifyId());
					t.setClassifyNo(c.getClassifyNo());
					break;
				}
			}
		}
		
		
		//List<ProductClassifyRel> productClassifyRelss = SOAClassifyMapper.getProductClassifyRel();

		Map<String, Map<String, Double>> data = new HashMap<>();
		List<String> delKeys = new ArrayList<>();
		for (ProductClassifyRel productClassifyRel : productClassifyRels) {
			String key = productClassifyRel.getCityId() + ":" + productClassifyRel.getClassifyId();
			if (!data.containsKey(key)) {
				data.put(key, new HashMap<String, Double>());
			}
			data.get(key).put(com.alibaba.fastjson.JSON.toJSONString(productClassifyRel), 1d);
			delKeys.add(RedisKeyGenerator.generateClassifyProdListKey(oldSencondClassifyId, productClassifyRel.getCityId()));
		}
		
		JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));
		
		Iterator<Map.Entry<String, Map<String, Double>>> iter = data.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Map<String, Double>> entry = iter.next();
			String[] keys = entry.getKey().split(":");
			Integer cityId = Integer.parseInt(keys[0]), classifyId = Integer.parseInt(keys[1]);
			String redisKey = RedisKeyGenerator.generateClassifyProdListKey(classifyId, cityId);
			JedisProxy.getInstance().delKey(redisKey);
			JedisProxy.getInstance().setSortedSetMultiMember(redisKey, entry.getValue());
		}
	}
}
