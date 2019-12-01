package com.mallcai.bs.service;

import com.dailuobo.api.domain.entity.PieceGroup;
import com.mallcai.bs.dao.PieceProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class PieceProductService {

    @Autowired
    private PieceProductDao pieceProductDao;

	/**
	 * 拼团活动查询
	 * */
	public PieceGroup getPieceInfoById(Map param){
		return pieceProductDao.getPieceInfoById(param);
	}

}
