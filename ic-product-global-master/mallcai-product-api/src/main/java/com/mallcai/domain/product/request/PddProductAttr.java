package com.mallcai.domain.product.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: PddProductAttr
 * @Description: 拼多多商品特有属性
 * @Author: zhangxuefeng
 * @Date: 2019/11/5 下午2:36
 * @Version: 1.0
 **/
@Data
public class PddProductAttr implements Serializable {
    private Integer id;
    private String productNo;
    private String pddGoodId;
    private String pddUrl;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
