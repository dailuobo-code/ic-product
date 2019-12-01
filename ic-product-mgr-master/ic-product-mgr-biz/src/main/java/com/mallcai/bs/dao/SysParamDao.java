package com.mallcai.bs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailuobo.api.domain.entity.SysParam;
import com.mallcai.bs.mapper.SysParamMapper;

@Repository
public class SysParamDao {
	@Autowired
    private SysParamMapper sysParamMapper;

	public List<SysParam> selectAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysParamMapper.selectAll(param);
	}

	public void edit(SysParam sysParam) {
		// TODO Auto-generated method stub
		sysParamMapper.edit(sysParam);
	}

	public void add(SysParam sysParam) {
		// TODO Auto-generated method stub
		sysParamMapper.add(sysParam);
	}

	public String getGroupUserIsAduit(String paramType){
		return sysParamMapper.getGroupUserIsAduit(paramType);
	}
}
