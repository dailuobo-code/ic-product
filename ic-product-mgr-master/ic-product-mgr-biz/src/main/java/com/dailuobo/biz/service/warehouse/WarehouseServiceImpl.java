package com.dailuobo.biz.service.warehouse;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.vo.DDLWarehouse;
import com.dailuobo.api.warehouse.WarehouseService;
import com.mallcai.bs.mapper.CityWarehouseMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 仓相关查询
 * @author tianjunwei
 * @date 2019-09-21
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private CityWarehouseMapper cityWarehouseMapper;

    /**
     * 查询仓库列表
     *
     * @param cityIds
     * @param name
     * @param type
     * @param status
     * @return
     */
    @Override
    public ICResponse<List<DDLWarehouse>> getWarehouse(List<Integer> cityIds, String name, Integer type, Integer status) {

        try {
            List<DDLWarehouse> ddlWarehouses = cityWarehouseMapper.getWareHouse(cityIds,type,status,name);
            return ICResponse.success(ddlWarehouses);
        }catch (Exception e){
            return ICResponse.fail(e.toString());
        }
    }
}
