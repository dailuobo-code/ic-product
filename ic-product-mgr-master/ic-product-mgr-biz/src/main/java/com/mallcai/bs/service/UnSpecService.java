package com.mallcai.bs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mallcai.bs.dao.UnSpecDao;
import com.dailuobo.api.domain.entity.UnSpec;
import com.dailuobo.api.domain.vo.UndProductVo;
import com.dailuobo.api.domain.vo.UndSpecVo;

@Service
public class UnSpecService {
    @Autowired
    private UnSpecDao unSpecdao;

	public UnSpec getDefaultSpec(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecdao.getDefaultSpec(param);
	}

	public List<UndSpecVo> getUnSpecs(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecdao.getUnSpecs(param);
	}

	public List<UndSpecVo> getStoreUnSpecs(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecdao.getStoreUnSpecs(param);
	}

	public List<UndProductVo> selectUnderProducts(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return unSpecdao.selectUnderProducts(param);
	}
    
}
