package com.mallcai.bs.manager.ivy;


import com.dailuobo.api.common.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.mapper.ivy.IvyWhProductRatioMapper;
import com.mallcai.bs.model.ivy.IvyWhProductRatioDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IVYMarketingWhProductRatioManager {

    @Resource
    private IvyWhProductRatioMapper ivyWhProductRatioMapper;


   public Page<IvyWhProductRatioDO> getWarehouseProductInventoryRatio(Integer cityId, Integer warehouseInventoryatioId, PageDTO pageDTO){

        Page<IvyWhProductRatioDO> page = PageHelper.startPage(pageDTO.getCurrentPage(),pageDTO.getPageSize());
        ivyWhProductRatioMapper.getWhProductRatio(cityId, warehouseInventoryatioId);
        return page;
    }

}
