package com.mallcai.domain.product.dto;

import com.mallcai.domain.enums.ChargeMethod;
import com.mallcai.domain.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ServiceFeeTemplate
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 11:51<br/>
 */
@Data
@ApiModel("商品服务费模板")
public class ServiceFeeTemplateDTO implements Serializable {
    private static final long serialVersionUID = -9091444601582330070L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 城市id，0 为全局
     */
    @ApiModelProperty("城市id，0 为全局")
    private Integer cityId;

    /**
     * 模板名称
     */
    @ApiModelProperty("模板名称")
    private String name;

    /**
     * 是否是默认模板
     */
    @ApiModelProperty("是否是默认模板")
    private Boolean isDefault;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private CommonStatus status;

    /**
     * 计价方式: 固定-FIXED, 按量计费-AMOUNT
     */
    @ApiModelProperty("计价方式: 固定-FIXED, 按量计费-AMOUNT")
    private ChargeMethod chargeMethod;

    /********** 按固定运费 ************/

    /**
     * 运费,当计价方式为固定运费时使用
     */
    @ApiModelProperty("运费,当计价方式为固定运费时使用,单位-分")
    private BigDecimal fee;

    /********** 按计量单位 ************/

    /**
     * 首费数量
     */
    @ApiModelProperty("首费数量")
    private Integer initAmount;

    /**
     * 首费金额
     */
    @ApiModelProperty("首费金额,单位-分")
    private BigDecimal initFee;

    /**
     * 增费数量
     */
    @ApiModelProperty("增费数量")
    private Integer incrAmount;

    /**
     * 增费金额
     */
    @ApiModelProperty("增费金额,单位-分")
    private BigDecimal incrFee;

    /**
     * 操作人 ID
     */
    @ApiModelProperty("操作人 ID")
    private Integer operatorId;

    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
}
