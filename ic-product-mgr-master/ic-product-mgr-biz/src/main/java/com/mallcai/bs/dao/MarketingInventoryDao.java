package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.entity.OrderInventory;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.google.common.collect.Maps;
import com.mallcai.bs.mapper.MarketingInventoryMapper;
import com.dailuobo.api.domain.soa.inventory.MarketingInventoryVo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
@Repository
public class MarketingInventoryDao {
    @Autowired
    private MarketingInventoryMapper marketingInventoryMapper;

    public List<StoreMarketingInventory> getStoreMarketingInventories(Map<String, Object> param) {
        return marketingInventoryMapper.getStoreMarketingInventories(param);
    }

    public List<MarketingInventory> getMarketingInventories(Integer cityId, Integer cityProductId) {
        return marketingInventoryMapper.getMarketingInventories(cityId, cityProductId);
    }

	public List<MarketingInventory> getMarketingShares(Integer storeId,
			Integer cityProductId) {
		// TODO Auto-generated method stub
		return marketingInventoryMapper.getMarketingShares(storeId, cityProductId);
	}

	public List<MarketingInventory> getBulkMarketingShares(List<Integer> cityProductIds) {
		return marketingInventoryMapper.getBulkMarketingShares(cityProductIds);
	}

	public List<MarketingInventory> getWareHouseMarketingShares(Integer storeId,
													   Integer cityProductId,Integer cityId,Integer wareHouseType) {
		return marketingInventoryMapper.getWareHouseMarketingShares(storeId, cityProductId,cityId,wareHouseType);
	}


	public void updateMarketingShares(Integer isShare, Integer cityId,
			Integer cityProductId,Integer updateUserId) {
		// TODO Auto-generated method stub
		marketingInventoryMapper.updateMarketingShares(isShare,cityId,cityProductId,updateUserId);
	}

	public String checkProduct(Integer cityProductId) {
		// TODO Auto-generated method stub
		return marketingInventoryMapper.checkProduct(cityProductId);
	}
	public int checkProductPiece(Integer cityProductId) {
		// TODO Auto-generated method stub
		return marketingInventoryMapper.checkProductPiece(cityProductId);
	}

	public void updateMarketLog(Map<String, Object> param) {
		// TODO Auto-generated method stub
		if(param.get("warehouseId") == null || StringUtils.isEmpty(param.get("warehouseId").toString())){
			param.put("warehouseId",-1);
		}
		marketingInventoryMapper.updateMarketLog(param);
	}

	public void updateBannerProductOrder(Integer cityProductId) {
		marketingInventoryMapper.updateBannerProductOrder(cityProductId);
	}

	public void updateTileProductOrder(Integer cityProductId) {
		marketingInventoryMapper.updateTileProductOrder(cityProductId);
	}

	public void updateThemeProductOrder(Integer cityProductId) {
		marketingInventoryMapper.updateThemeProductOrder(cityProductId);
	}

	public List<Integer> getBannerIdsByProductId(Integer cityProductId) {
		return marketingInventoryMapper.getBannerIdsByProductId(cityProductId);
	}

	public List<Integer> getTileIdsByProductId(Integer cityProductId) {
		return marketingInventoryMapper.getTileIdsByProductId(cityProductId);
	}

	public List<Integer> getThemeIdsByProductId(Integer cityProductId) {
		return marketingInventoryMapper.getThemeIdsByProductId(cityProductId);
	}

	/**
	 * 查询不是共享商品的cityProductIds
	 *
	 */
	public String queryNoIsshareProduct( Map<String,Object> param){
    	return marketingInventoryMapper.queryNoIsshareProduct(param);
	}
	/**
	 * 查询存在与否该商品cityProductIds
	 *
	 */
	public Integer queryNoProduct(Map<String,Object> param){
		return marketingInventoryMapper.queryNoProduct(param);
	}

	/**
	 * 查询存在萝卜拼商品的cityProductIds
	 */
	public String queryPieceByCityProductIds( Map<String,Object> param){
		return marketingInventoryMapper.queryPieceByCityProductIds(param);
	}


	/**
	 * 批量设置共享商品库存
	 */
	public void updateMarketingInventory(Map<String,Object> param){
		marketingInventoryMapper.updateMarketingInventory(param);
	}


	/**
	 * 查询更新后的共享商品库存信息
	 *
	 */

	public 	List<MarketingInventoryVo> selectCityProductId(Map<String,Object> param){
		return marketingInventoryMapper.selectCityProductId(param);
	}

	/**
	* 保存导入文件的日志
	*
	*/
	public void saveImportLog( Map<String, Object> param){
		marketingInventoryMapper.saveImportLog(param);
	}
	/**
	 * 查询当日支付订单数
	 * @param cityProductId
	 * @return
	 */
	public List<OrderInventory> getOrderInventoryListByCityProductId(Integer cityProductId) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		Map<String,Object> mapParam = Maps.newHashMap() ;
		mapParam.put("cityProductId",cityProductId) ;
		mapParam.put("beginDate",fmt.print(LocalDate.now())) ;
		mapParam.put("endDate",fmt.print(LocalDate.now().plusDays(1))) ;
		return marketingInventoryMapper.getOrderInventoryListByParam(mapParam) ;
	}

	public List<OrderInventory> batchQueryOrderInventory(List<Integer> cityProductIds) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		Map<String,Object> mapParam = Maps.newHashMap() ;
		mapParam.put("list",cityProductIds) ;
		mapParam.put("beginDate",fmt.print(LocalDate.now())) ;
		mapParam.put("endDate",fmt.print(LocalDate.now().plusDays(1))) ;
		return marketingInventoryMapper.batchQueryOrderInventoryByParam(mapParam) ;
	}

	public int countMultiSpec(List<Integer> cityProductIds) {
		Map<String,Object> mapParam = Maps.newHashMap() ;
		mapParam.put("list",cityProductIds) ;
		return marketingInventoryMapper.countMultiSpec(mapParam) ;
	}
}
