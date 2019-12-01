package com.mallcai.biz.product.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: HqProductTax
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/8/27 下午4:46
 * @Version: 1.0
 **/
@Data
public class HqProductTax implements Serializable {
    private Integer id;
    private String taxClassifyCode;
    private BigDecimal taxRate;
}
