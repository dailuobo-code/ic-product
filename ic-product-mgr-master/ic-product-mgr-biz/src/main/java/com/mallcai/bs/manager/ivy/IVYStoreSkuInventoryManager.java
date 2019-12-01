package com.mallcai.bs.manager.ivy;


import com.dailuobo.api.common.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.mapper.ivy.IvyStoreSkuInventoryMapper;
import com.mallcai.bs.model.ivy.IvyStoreSkuInventoryDO;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IVYStoreSkuInventoryManager {

    @Resource
    private IvyStoreSkuInventoryMapper ivyStoreSkuInventoryMapper;

    public Page<IvyStoreSkuInventoryDO> getStoreSkuInventory(Integer marketingInventoryId, Integer cityProductId, PageDTO pageDTO){
        Page<IvyStoreSkuInventoryDO> page = PageHelper.startPage(pageDTO.getCurrentPage(),pageDTO.getPageSize());
        ivyStoreSkuInventoryMapper.getStoreSkuInventory(marketingInventoryId,cityProductId);
        return page;
    }

    public Integer updateIVYStoreSkuInventory(IvyStoreSkuInventoryDO ivyStoreSkuInventoryDO) {
        return ivyStoreSkuInventoryMapper.updateIVYStoreSkuInventory(ivyStoreSkuInventoryDO);
    }
}
