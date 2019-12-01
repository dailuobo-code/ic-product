package com.dailuobo.biz.service.inventory;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.vo.MarketingVo;
import com.dailuobo.api.inventory.ICMarketingSearchService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.service.MarketingSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("iCMarketingSearchService")
public class ICMarketingSearchServiceImpl implements ICMarketingSearchService {

    @Autowired
    private MarketingSearchService marketingSearchService;

    @Override
    public ICResponse<List<MarketingVo>> selectAll(Map<String, Object> param) {
        // TODO Auto-generated method stub

        int offset = 0;
        int limit = 0;
        if (param.get("offset") != null && param.get("limit") != null) {
            offset = (int) param.get("offset");
            limit = (int) param.get("limit");
            PageHelper.offsetPage(offset, limit);
        }
        try {
            List<MarketingVo> marketingVos = marketingSearchService.selectAll(param);
            if (offset >= 0 && limit > 0 && CollectionUtils.isNotEmpty(marketingVos)) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) marketingVos).getTotal(), offset / limit + 1);
                return ICResponse.success(marketingVos, pageDTO);
            }
            return ICResponse.success(marketingVos);
        }catch (Exception ex){
            log.error(String.format("selectAll error,request:%s", JSON.toJSONString(param)), ex);
            return ICResponse.fail(ex.getMessage());
        }

    }
}
