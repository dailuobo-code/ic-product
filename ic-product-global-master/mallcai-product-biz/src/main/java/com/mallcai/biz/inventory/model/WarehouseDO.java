package com.mallcai.biz.inventory.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-05 16:03
 */
@Data
public class WarehouseDO {


    private Integer id;

    private Integer cityId;

    private String name;

    private String code;

    private Integer type;

    private Integer status;

    private String province;

    private String city;

    private String county;

    private String address;

    private String linkmanName;

    private String phone;

    private Integer linkmanId;

    private Integer createUserId;

    private Date createTime;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String memo;
}
