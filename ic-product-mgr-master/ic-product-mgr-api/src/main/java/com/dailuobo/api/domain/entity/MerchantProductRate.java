package com.dailuobo.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: MerchantProductRate
 * @Description: 商家商品抽佣比例
 * @Author: zhangxuefeng
 * @Date: 2019/9/24 下午8:14
 * @Version: 1.0
 **/
@Data
public class MerchantProductRate implements Serializable {
    private Integer id;
    private Integer cityProductId;
    private Integer merchantId;
    private BigDecimal merchantRate;
    private Date createTime;
    private Date updateTime;
}
