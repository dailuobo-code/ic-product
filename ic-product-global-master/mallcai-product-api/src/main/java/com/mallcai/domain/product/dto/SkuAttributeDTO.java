package com.mallcai.domain.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("sku 销售属性")
public class SkuAttributeDTO implements Serializable {
    @ApiModelProperty(value = "sku 销售属性",example = "颜色、尺寸等")
    private String attrKey;
    @ApiModelProperty(value = "sku 销售属性的值",example = "红色，42 码等")
    private String attrVal;
}