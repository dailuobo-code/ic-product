package com.mallcai.domain.inventory.request;


import com.alibaba.fastjson.JSONObject;

import com.mallcai.domain.inventory.dto.WarehouseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-07-31 18:04
 */
@Data
public class StoreWarehouseRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer storeId;
    private List<WarehouseDTO> warehouses;
    private Integer createUserId;



    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
