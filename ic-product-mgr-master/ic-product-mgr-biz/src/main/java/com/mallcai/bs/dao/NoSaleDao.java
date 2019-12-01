package com.mallcai.bs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailuobo.api.domain.entity.NoSaleDto;
import com.dailuobo.api.domain.entity.StoreNoSale;
import com.mallcai.bs.mapper.NoSaleMapper;
import com.dailuobo.api.domain.vo.NoSaleVo;

@Repository
public class NoSaleDao {
	@Autowired
    private NoSaleMapper NoSaleMapper;

	public List<NoSaleVo> selectAll(Map<String, Object> param) {
		return NoSaleMapper.selectAll(param);
	}

	public void add(List<StoreNoSale> storeNoSales) {
		NoSaleMapper.add(storeNoSales);
	}

	public void delete(List<Integer> ids, Integer userId) {
		NoSaleMapper.delete(ids,userId);
	}

	public List<NoSaleDto> getCityStoreNoSaleProduct(Map<String, Object> map) {
		return NoSaleMapper.getCityStoreNoSaleProduct(map);
	}
	


}
