package com.dailuobo.api.warehouse;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.vo.DDLWarehouse;

import java.util.List;

public interface WarehouseService {

    /**
     * 查询仓库列表
     * @param cityIds
     * @param name
     * @param type
     * @param status
     * @return
     */
    ICResponse<List<DDLWarehouse>> getWarehouse(List<Integer> cityIds, String name, Integer type, Integer status);
}
