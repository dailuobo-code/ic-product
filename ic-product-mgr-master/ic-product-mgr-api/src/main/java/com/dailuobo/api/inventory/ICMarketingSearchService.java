package com.dailuobo.api.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.vo.MarketingVo;

import java.util.List;
import java.util.Map;

public interface ICMarketingSearchService {
    ICResponse<List<MarketingVo>> selectAll(Map<String, Object> param);
}
