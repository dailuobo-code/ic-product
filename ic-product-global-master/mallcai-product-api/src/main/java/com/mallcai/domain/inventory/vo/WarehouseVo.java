package com.mallcai.domain.inventory.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-07-31 17:48
 */
@Data
public class WarehouseVo implements Serializable {

    private static final long serialVersionUID = 007L;


    private Integer warehouseId;
    private Integer cityId;
    private String name;
    private Integer type;


}
