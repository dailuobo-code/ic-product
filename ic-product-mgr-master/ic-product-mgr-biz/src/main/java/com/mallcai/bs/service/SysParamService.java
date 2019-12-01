package com.mallcai.bs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mallcai.bs.dao.SysParamDao;
import com.dailuobo.api.domain.entity.SysParam;

@Service
public class SysParamService {
    @Autowired
    private SysParamDao sysParamDao;

	public List<SysParam> selectAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysParamDao.selectAll(param);
	}

	public void edit(SysParam sysParam) {
		// TODO Auto-generated method stub
		sysParamDao.edit(sysParam);
	}

	public void add(SysParam sysParam) {
		// TODO Auto-generated method stub
		sysParamDao.add(sysParam);
	}

	public String getGroupUserIsAduit(String paramType){
		return sysParamDao.getGroupUserIsAduit(paramType);
	}
}
