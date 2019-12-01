package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SellTime
 * @Description: 商品售卖时间
 * @Author: zhangxuefeng
 * @Date: 2019/10/21 上午9:59
 * @Version: 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellTime implements Serializable {

    private static final long serialVersionUID = 6305555824538153974L;

    private Integer cityProductId;
    private Integer startHour;
    private Integer endHour;
}
