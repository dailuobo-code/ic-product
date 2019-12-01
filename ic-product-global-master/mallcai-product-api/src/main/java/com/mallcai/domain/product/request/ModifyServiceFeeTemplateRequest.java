package com.mallcai.domain.product.request;

import com.mallcai.domain.enums.ChargeMethod;
import com.mallcai.domain.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ModifyServiceFeeTemplateRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/6 00:25<br/>
 */
@Data
@ApiModel("商品服务费模板")
public class ModifyServiceFeeTemplateRequest implements Serializable {
    private static final long serialVersionUID = 8361931882784350074L;

    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("城市id，0 为全局")
    private Integer cityId;
    @ApiModelProperty("模板名称")
    private String name;
    @ApiModelProperty("是否是默认模板")
    private Boolean isDefault;
    @ApiModelProperty("状态")
    private CommonStatus status;
    @ApiModelProperty("计价方式: 固定-FIXED, 按量计费-AMOUNT")
    private ChargeMethod chargeMethod;
    @ApiModelProperty("运费,当计价方式为固定运费时使用,单位-分")
    private BigDecimal fee;
    @ApiModelProperty("首费数量")
    private Integer initAmount;
    @ApiModelProperty("首费金额,单位-分")
    private BigDecimal initFee;
    @ApiModelProperty("增费数量")
    private Integer incrAmount;
    @ApiModelProperty("增费金额,单位-分")
    private BigDecimal incrFee;
}
