package com.mallcai.bs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailuobo.api.domain.entity.UnsaleCount;
import com.mallcai.bs.mapper.UndSaleMapper;


@Repository
public class UndSaleDao {
    @Autowired
    private UndSaleMapper undSaleMapper;

    public void pickupLog(Integer cityId, Integer storeId, Integer operatorId, String json) {
    	undSaleMapper.pickupLog(cityId, storeId, operatorId, json);
    }
    
	public List<UnsaleCount> selectCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return undSaleMapper.selectCount(param);
	}

	public Integer getCityProduct(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return undSaleMapper.getCityProduct(param);
	}

	public List<UnsaleCount> selectAllStoreCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return undSaleMapper.selectAllStoreCount(param);
	}

	public Boolean checkProductStatus(Map<String, Object> param) {
		int reason = undSaleMapper.checkProductStatus(param);
		return reason==0;
	}

	public Boolean checkProductClassify(Map<String, Object> param) {
		int reason = undSaleMapper.checkProductClassify(param);
		return reason==0;
	}

	public Integer checkIsSmoke(Map<String, Object> param) {
		return undSaleMapper.checkIsSmoke(param);
	}

	public Integer checkIsSmoke2(HashMap<String, Object> paramMap) {
		return undSaleMapper.checkIsSmoke2(paramMap);
	}
}
