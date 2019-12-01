package com.mallcai.bs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mallcai.bs.mapper.MarketingSearchMapper;
import com.dailuobo.api.domain.vo.MarketingVo;

@Repository
public class MarketingSearchDao {
    @Autowired
    private MarketingSearchMapper marketingSearchMapper;

	public List<MarketingVo> selectAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return marketingSearchMapper.selectAll2(param);
	}

	public List<MarketingVo> selectAvailableAndThreshold(Map<String, Object> param) {
		return marketingSearchMapper.selectAvailableAndThreshold(param) ;
	}

	public List<MarketingVo> selectAvailableBase(Map<String, Object> param) {
		return marketingSearchMapper.selectAvailableBase(param) ;
	}
}
