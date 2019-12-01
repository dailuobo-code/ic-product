package com.mallcai.domain.product.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: HqProductVo
 * @Description: 批量导入商家商品使用的总部商品类
 * @Author: zhangxuefeng
 * @Date: 2019/8/30 下午1:54
 * @Version: 1.0
 **/
@Data
public class HqProductVo implements Serializable {

    private Integer hqProductId;
    private String productNo;
    private String hqProductName;
    private String remark;
    private Integer status;
    private String hqProductPic;
    private String hqProductIcon;
    private Integer classifyId;
    private Integer localizeFlag;
    private Byte deliveryMode;
    private String barCode;
    private Integer val;
    private Integer keepType;
    private Integer qualityTime;
    private Integer isStandard;
    private Integer pickClassify;
    private BigDecimal productRate;
    private String length;
    private String wides;
    private String high;
    private Integer goodsType;
    private Integer productTaxId;
    private String taxClassifyCode;
    private Integer thirdPlatType;

    private Integer createUserId;
    private Integer updateUserId;
}
