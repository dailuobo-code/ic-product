package com.dailuobo.biz.service.inventory.ivy;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.ivy.IVYStockDTO;
import com.dailuobo.api.domain.vo.InventoryExportVo;
import com.dailuobo.api.inventory.ivy.IVYMarketingInventoryReadWriteService;
import com.mallcai.bs.manager.ivy.IVYMarketingInventoryReadWriteManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * mgr 库存相关读写操作
 * @author tianjunwei
 * @date 2019-09-18
 */
@Slf4j
@Service("iVYMarketingInventoryReadWriteService")
public class IVYMarketingInventoryReadWriteServiceImpl implements IVYMarketingInventoryReadWriteService {


    @Resource
    private IVYMarketingInventoryReadWriteManager ivyMarketingInventoryReadWriteManager;

    /**
     * 批量写库存
     *
     * @param cityId
     * @param request
     * @return
     */
    @Override
    public ICResponse<Boolean> batchWriteStock(Integer cityId, List<IVYStockDTO> request) {

        try {
            if(cityId == null){
                return ICResponse.fail("cityId 为空");
            }
            if(CollectionUtils.isEmpty(request)){
                return ICResponse.fail("商品列表为空");
            }
            ivyMarketingInventoryReadWriteManager.batchWriteStock(cityId,request);
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 调整库存,提供给mgr设置共享标品单存
     * 前置条件 ：
     * 1、标品
     * 2、共享
     * 3、与可用库存作对比 取相对的小值
     * 4、返回失败message
     *
     * @param cityId
     * @param cityProductId
     * @param available
     * @return
     */
    @Override
    public ICResponse<Boolean> adjustingStock(Integer cityId, Integer cityProductId, Integer available) {
        try {
            if(cityId == null || cityProductId == null || available == null){
                return ICResponse.fail("参数为空");
            }
            if (available < 0) {
                return ICResponse.fail("可售库存，须等于0或正整数");
            }
            ivyMarketingInventoryReadWriteManager.adjustingStock(cityId,cityProductId,available);
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 导出库存
     *
     * @param cityIds
     * @param classifyIds
     * @param userId
     * @param username
     * @return
     */
    @Override
    public ICResponse<Integer> startExportInventoryOfCities(List<Integer> cityIds, List<Integer> classifyIds, Integer userId, String username) {

        try {
            Integer id = ivyMarketingInventoryReadWriteManager.startExportInventoryOfCities(cityIds,classifyIds,userId,username);
            return ICResponse.success(id);
        }catch (Exception e){
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 查找库存可用量
     *
     * @param cityId
     * @param cityProductList
     * @return
     */
    @Override
    public ICResponse<Map<Integer, Integer>> availableList(Integer cityId, List<Integer> cityProductList) {
        try {
            if(cityId == null || CollectionUtils.isEmpty(cityProductList)){
                return ICResponse.fail("参数为空");
            }
            Map<Integer, Integer> map = ivyMarketingInventoryReadWriteManager.availableList(cityId,cityProductList);
            return ICResponse.success(map);
        }catch (Exception e){
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * check  wms mq标品 是否同步过
     *
     * @param cityId
     * @param cityProductList
     * @return
     */
    @Override
    public ICResponse<Map<Integer, Boolean>> checkSyncTime(Integer cityId, List<Integer> cityProductList) {
        try {

            if(cityId == null || CollectionUtils.isEmpty(cityProductList)){
                return ICResponse.fail("参数为空");
            }
            Map<Integer, Boolean> map = ivyMarketingInventoryReadWriteManager.checkSyncTime(cityId, cityProductList);
            return ICResponse.success(map);
        }catch (Exception e){
            return ICResponse.fail(e.toString());
        }
    }

    @Override
    public ICResponse<InventoryExportVo> getInventoryExportStatus(Integer exportId) {
        InventoryExportVo vo = ivyMarketingInventoryReadWriteManager.getInventoryExportById(exportId);
        return ICResponse.success(vo);
    }
}
