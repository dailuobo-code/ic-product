package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.PieceGroup;
import com.dailuobo.api.domain.entity.PieceProduct;
import com.mallcai.bs.mapper.PieceProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PieceProductDao {

    @Autowired
    private PieceProductMapper pieceProductMapper;

	public List<Map> selectAll(Map<String, Object> param) {
		return pieceProductMapper.selectAll(param);
	}

	public void add(PieceProduct pieceProduct) {
		pieceProductMapper.add(pieceProduct);
	}

	public void update(PieceProduct pieceProduct) {
		pieceProductMapper.update(pieceProduct);
	}

	public List<Map> getPieceByCityProductId(Integer cityProductId){
		return pieceProductMapper.getPieceByCityProductId(cityProductId);
	}

	public void updateAvaible(Integer delta,List<Integer> ids){
		pieceProductMapper.updatePieceAvaible(delta,ids);
	}










	public void delete(Integer topId) {
		pieceProductMapper.delete(topId);
	}

	public PieceGroup getPieceInfoById(Map param){
		return pieceProductMapper.getPieceInfoById(param);
	}

	public PieceGroup getPieceGroupInfoById(Map param){
		return pieceProductMapper.getPieceGroupInfoById(param);
	}
}
