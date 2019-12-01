package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import com.dailuobo.api.domain.entity.UnSpec;
import com.dailuobo.api.domain.vo.UndProductVo;
import com.dailuobo.api.domain.vo.UndSpecVo;

public interface UnSpecMapper {

	UnSpec getDefaultSpec(Map<String, Object> param);

	void update(UnSpec unSpec);

	void create(UnSpec unSpec);

	List<UndSpecVo> getUnSpecs(Map<String, Object> param);

	List<UndSpecVo> getStoreUnSpecs(Map<String, Object> param);

	List<UndProductVo> selectUnderProducts(Map<String, Object> param);

}
