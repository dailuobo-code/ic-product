package com.mallcai.bs.manager.ivy;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.common.ExcelField;
import com.mallcai.bs.mapper.ivy.IvyMarketingInventoryMapper;
import com.mallcai.bs.mapper.ivy.IvyStoreSkuInventoryMapper;
import com.mallcai.bs.model.ivy.IvyMarketingInventoryDO;
import javax.annotation.Resource;

import com.mallcai.bs.model.ivy.IvyStoreSkuInventoryDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IVYMarketingInventoryManager {

    @Resource
    private IvyMarketingInventoryMapper ivyMarketingInventoryMapper;

    @Resource
    private IvyStoreSkuInventoryMapper ivyStoreSkuInventoryMapper;

    /**
     * 根据城市id 和商品id 查找商品所在仓 库存信息
     *
     * @param cityId
     * @param cityProductId;
     * @return
     */
    public Page<IvyMarketingInventoryDO> getIVYWarehouseInventory(Integer cityId, Integer cityProductId) {

        Page page = PageHelper.startPage(1,1000);
        ivyMarketingInventoryMapper.getIVYMarketingInventory(cityId,cityProductId);
        return page;
    }

    public IvyMarketingInventoryDO getSingleIVYMarketingInventory(Integer id){
        return ivyMarketingInventoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改销售总库存
     *
     * @param ivyWarehouseInventory
     * @return
     */
    public int updateIVYWarehouseInventory(IvyMarketingInventoryDO ivyWarehouseInventory) {
        return ivyMarketingInventoryMapper.updateIVYMarketingInventory(ivyWarehouseInventory);
    }


    /**
     * 修改可售库存
     *
     * @param ivyWarehouseInventory
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateIVYWarehouseInventoryAvailable(IvyMarketingInventoryDO ivyWarehouseInventory) {
        int result = ivyMarketingInventoryMapper.updateIVYMarketingInventory(ivyWarehouseInventory);
        IvyStoreSkuInventoryDO ivyStoreSkuInventoryDO = new IvyStoreSkuInventoryDO();
        ivyStoreSkuInventoryDO.setAvailableNum(ivyWarehouseInventory.getAvailableNum());
        ivyStoreSkuInventoryDO.setMarketingInventoryId(ivyWarehouseInventory.getId());
        ivyStoreSkuInventoryMapper.updateIVYStoreSkuInventory(ivyStoreSkuInventoryDO);
        return result;
    }

    /**
     * 修改告警库存
     *
     * @param ivyWarehouseInventory
     * @return
     */
    public int updateIVYWarehouseInventoryAlarm(IvyMarketingInventoryDO ivyWarehouseInventory) {
        List<IvyStoreSkuInventoryDO> storeSkuInventoryDOList = ivyStoreSkuInventoryMapper.getStoreSkuInventory(ivyWarehouseInventory.getId(),ivyWarehouseInventory.getCityProductId());
        for (IvyStoreSkuInventoryDO ivyStoreSkuInventoryDO : storeSkuInventoryDOList){
            // 门店可售库存小于告警库存
            if(ivyStoreSkuInventoryDO.getAvailableNum() != null && ivyStoreSkuInventoryDO.getAvailableNum() < ivyWarehouseInventory.getAlarmNum() ){
                return 0;
            }
        }
        int result = ivyMarketingInventoryMapper.updateIVYMarketingInventoryAlarm(ivyWarehouseInventory);
        return result;
    }

}
