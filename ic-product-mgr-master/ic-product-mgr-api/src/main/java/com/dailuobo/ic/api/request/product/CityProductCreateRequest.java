package com.dailuobo.ic.api.request.product;

import com.dailuobo.api.domain.entity.BaseEntity;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.ic.dto.spec.SkuAttributeDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class CityProductCreateRequest extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer hqProductId;
    private String productNo;
    private Integer cityProductId;
    private Integer cityId;
    private Integer hqStatus;
    private Integer cityStatus;
    private String showName;
    private Double advisePrice;
    private Double disablePrice;
    private String weightUnit;
    private String numUnit;
    private String cityProductIcon;
    private String cityProductPic;
    private String origin;
    private String detailUrl;
    private String subtitle;
    private String keyword;
    private String iconTip;
    private String l1L2Names;
    private Integer changeFlag;
    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    private int isShare;//是否共享
    private Integer newUserPro; //新人专享  0不是，1是
    private Integer isUnder;
    private String barCode;
    private Double vipCount;
    private Integer vipLimit;//'会员限购 0：限购，1：不限购'
    private Integer threshold;
    private String length;
    private String wides;
    private String high;
    private Integer goodsType;

    private Integer pointMallStatus;//积分商城 上架状态  0：下架，1：上架
    private Integer pointPrice; //积分价格
    private Integer delFlag;//删除标识
    private Integer merchantId;//商家ID
    private String merchantName;//商家名称
    private String saleTime;
    private String productDesc;

    /**
     * 是否走采销预测
     */
    private Integer isForecast;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    private Integer available;

    /**
     * 商家抽佣比例，百分比转化成小数格式
     */
    private BigDecimal merchantRate;

    /**
     * 是否关联货品
     * 0表示未关联 1表示已关联
     */
    private Integer isGoodsRel;

    /**
     * 多规格销售属性
     */
    private List<SkuAttributeDTO> attributes;


    /**
     * 0 非多规格  1 多规格
     * 是否是多规格商品
     */
    private Integer isMultiProduct;


    /**
     * 新商品 itemId 2019-10-21 09:48:004
     */
    private Long itemId;
    /**
     * skuId  2019-10-21 09:47:49
     */
    private Long skuId;


    private Integer deliveryMode;


    private Integer keepType;

    private Integer qualityTime;


    /**
     * 是否首页显示
     */
    private Integer homeFlag;


    private Integer arrivalDay;


    /**
     * 商品规格
     */
    Spec spec;

    public void checkParams(){

        nonNull(hqProductId,"hq_product_id is null");
        nonNull(hqStatus,"hq_status is null");
        nonNull(cityStatus,"city status is null");
        nonNull(goodsType,"goodsType is null");
        nonNull(createUserId,"create_user_id is null");
    }

    public void nonNull(Object object, String msg) {
        if (null == object) {
            throw new IllegalArgumentException(msg);
        }
    }

}
