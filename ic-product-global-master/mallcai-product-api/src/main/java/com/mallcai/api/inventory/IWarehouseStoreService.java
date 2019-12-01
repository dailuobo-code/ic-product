package com.mallcai.api.inventory;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.inventory.request.StoreWarehouseRequest;
import com.mallcai.domain.inventory.vo.WarehouseVo;



import java.util.List;
import java.util.Map;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-07-31 17:27
 */
public interface IWarehouseStoreService {

    /**
     * 根据门店id查询对应供应仓库列表
     * @param storeId
     * @return
     */
    ICResponse<List<WarehouseVo>> getWarehouseListByStoreId(Integer storeId);


    ICResponse<Map<Integer,List<WarehouseVo>>> getWarehouseListByStoreIds(List<Integer> storeIds);


    /**
     * 绑定仓库和门店的关系
     * @param request :warehouseId,storeIds,createUserId
     *
     * @return
     */
    ICResponse<String> bindStoreWarehouse(StoreWarehouseRequest request);

    /**
     * 根据城市id查询下辖的仓库列表
     * @param cityId
     * @return
     */
    ICResponse<List<WarehouseVo>> getWarehouseListByCityId(Integer cityId);


}
