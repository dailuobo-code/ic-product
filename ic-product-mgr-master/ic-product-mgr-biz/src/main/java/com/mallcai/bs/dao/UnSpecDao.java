package com.mallcai.bs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailuobo.api.domain.entity.UnSpec;
import com.mallcai.bs.mapper.UnSpecMapper;
import com.dailuobo.api.domain.vo.UndProductVo;
import com.dailuobo.api.domain.vo.UndSpecVo;

@Repository
public class UnSpecDao {
    @Autowired
    private UnSpecMapper unSpecMapper;

	public UnSpec getDefaultSpec(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecMapper.getDefaultSpec(param);
	}

	public void update(UnSpec unSpec) {
		// TODO Auto-generated method stub
		unSpecMapper.update(unSpec);
	}

	public void create(UnSpec unSpec) {
		// TODO Auto-generated method stub
		unSpecMapper.create(unSpec);
	}

	public List<UndSpecVo> getUnSpecs(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecMapper.getUnSpecs(param);
	}

	public List<UndSpecVo> getStoreUnSpecs(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecMapper.getStoreUnSpecs(param);
	}

	public List<UndProductVo> selectUnderProducts(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecMapper.selectUnderProducts(param);
	}
}
