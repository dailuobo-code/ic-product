package com.mallcai.bs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mallcai.bs.dao.UndSaleDao;
import com.dailuobo.api.domain.entity.UndOrder;
import com.dailuobo.api.domain.entity.UndPickup;
import com.dailuobo.api.domain.entity.UndProduct;
import com.dailuobo.api.domain.entity.UnsaleCount;
import com.dailuobo.api.domain.util.Constant;

@Service
public class UnSaleService {
    @Autowired
    private UndSaleDao undSaleDao;

	@Autowired
	private SOACityGlobalService soaCityGlobalService;

	public void pickupLog(int cityId, int storeId, int userId,Integer[] proArr, Integer[] weightArr,
						String[] productNos,String[] barcodes, String orderNo) {
		// TODO Auto-generated method stub
		UndPickup upu = new UndPickup();
		UndOrder uo = new UndOrder();
		UndProduct[] up = new UndProduct[proArr.length];
		for (int i = 0; i < proArr.length; i++) {
			UndProduct undProduct = new UndProduct();
			
			undProduct.setBarcodes(barcodes[i]);
			undProduct.setCityProductId(proArr[i]);
			undProduct.setProductNo(productNos[i]);
			up[i] = undProduct;
		}
		uo.setProducts(up);
		uo.setUndOrderId(orderNo);
		upu.setOperatorId(userId);
		upu.setOrders(uo);
		String json = com.alibaba.fastjson.JSON.toJSONString(upu);
		undSaleDao.pickupLog(cityId, storeId, userId, json);
 	}
    
	public List<UnsaleCount> selectCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return undSaleDao.selectCount(param);
	}

	public Integer getCityProduct(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return undSaleDao.getCityProduct(param);
	}

	public List<UnsaleCount> selectAllStoreCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return undSaleDao.selectAllStoreCount(param);
	}


	public String checkProductStatus(Map<String, Object> param) {
		String reason = "";
		String productCode = param.get("productCode").toString();
		String isSmoke = soaCityGlobalService.getSysParams("sys_smoke");
		if(!productCode.substring(0,2).equals("27")){	//国标码
			if(!StringUtils.isEmpty(isSmoke)&&"0".equals(isSmoke)){
				HashMap<String, Object> paramMap = new HashMap<>();
				paramMap.put("barCode",productCode);
				paramMap.put("classifyId",Constant.SMOKE_CLASSIFY_ID);
				Integer smoke = undSaleDao.checkIsSmoke(paramMap);
				if (smoke!=null&&smoke.intValue()>0){
					reason = "烟草类商品暂停售卖";
					return reason;
				}
			}
			boolean result = undSaleDao.checkProductStatus(param);
			if(!result){
				reason = "该商品库存异常，暂停售卖";
			}
			
    	}else{
			String barCode = productCode.substring(2, 7);
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("barCode", barCode);
			paramMap.put("classifyId", Constant.SMOKE_CLASSIFY_ID); //烟草类别
			if(!StringUtils.isEmpty(isSmoke)&&"0".equals(isSmoke)){

				Integer smoke = undSaleDao.checkIsSmoke2(paramMap);
				if (smoke!=null&&smoke.intValue()>0){
					reason = "烟草类商品暂停售卖";
					return reason;
				}
			}
    		boolean result2 = undSaleDao.checkProductClassify(paramMap);

    		if(!result2){
				reason = "烟草类商品请扫描国标码进行售卖";
			}
    	}
		return reason;
	}
}
