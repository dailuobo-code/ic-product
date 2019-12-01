package com.mallcai.bs.model.ivy;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: mengjia
 * @Date: 2019-05-14 13:42
 * @Version 1.0
 */
@Data
public class MarketingInventoryBaseDO implements Serializable {

    private Integer id;

    private Integer cityProductId;

    private Integer available;
    /**
     * 同步时间
     */
    private Date syncCompleteTime;
}
