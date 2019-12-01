package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.ivy.IVYStockDTO;
import com.dailuobo.api.domain.vo.InventoryExportVo;
import com.mallcai.backend.common.api.Response;

import java.util.List;
import java.util.Map;

/**
 * mgr库存相关读写操作
 * @author tianjunwei
 * @date 2019-09-18
 */
public interface IVYMarketingInventoryReadWriteService {

    /**
     * 批量写库存
     *
     * @param request
     * @return
     */
    ICResponse<Boolean> batchWriteStock(Integer cityId, List<IVYStockDTO> request);


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
    ICResponse<Boolean> adjustingStock(Integer cityId, Integer cityProductId, Integer available);

    /**
     * 导出库存
     * @param cityIds
     * @param classifyIds
     * @param userId
     * @param username
     * @return
     */
    ICResponse<Integer> startExportInventoryOfCities(List<Integer> cityIds, List<Integer> classifyIds, Integer userId, String username);


    /**
     * 查找库存可用量
     * @param cityId
     * @param cityProductList
     * @return
     */
    ICResponse<Map<Integer, Integer>> availableList(Integer cityId, List<Integer> cityProductList);

    /**
     * check  wms mq标品 是否同步过
     *
     * @param cityId
     * @param cityProductList
     * @return
     */
    ICResponse<Map<Integer, Boolean>> checkSyncTime(Integer cityId, List<Integer> cityProductList);

    /**
     * 根据id查询库存导出文件当前状态
     * @param exportId
     * @return
     */
    ICResponse<InventoryExportVo> getInventoryExportStatus(Integer exportId);

}
