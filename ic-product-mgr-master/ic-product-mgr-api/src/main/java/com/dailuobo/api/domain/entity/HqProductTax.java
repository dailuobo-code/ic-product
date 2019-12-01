package com.dailuobo.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: HqProductTax
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/8/23 下午3:05
 * @Version: 1.0
 **/
@Data
public class HqProductTax implements Serializable {

    private Integer id;
    private String taxClassifyCode;
    private BigDecimal taxRate;
}
