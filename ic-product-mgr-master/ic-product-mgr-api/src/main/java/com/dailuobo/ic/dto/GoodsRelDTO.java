package com.dailuobo.ic.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 货品基础信息DTO
 *
 * @author lgh
 * @date 2019/9/29
 */
@Data
public class GoodsRelDTO extends BaseDTO{

    /** 货品编号 **/
    @NotBlank(message = "货品编号不能为空")
    private String goodsId;

    /** 货品名称 **/
    @NotBlank(message = "货品名称不能为空")
    private String goodsName;

    /** 货品计量单位 **/
    @NotBlank(message = "货品计量单位不能为空")
    private String goodsUnit;

    /** 货品关联数量 **/
    @NotNull(message = "货品关联数量不能为空")
    private BigDecimal relNum;

    /** 货品存储方式 **/
    private Integer keepType;

    /** 是否标品 **/
    private Integer isStandard;

}
