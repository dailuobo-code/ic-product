package com.mallcai.domain.inventory.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-02 19:30
 */
@Data
public class WarehouseDTO implements Serializable {

    private Integer type;
    private Integer warehouseId;
}
