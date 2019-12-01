package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import com.dailuobo.api.domain.vo.MarketingVo;

public interface MarketingSearchMapper {

	List<MarketingVo> selectAll2(Map<String, Object> param);

    List<MarketingVo> selectAvailableAndThreshold(Map<String, Object> param);

	List<MarketingVo> selectAvailableBase(Map<String, Object> param);
}
