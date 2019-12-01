package com.mallcai.domain.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品附加费，服务费、运费等等
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/2 21:12<br/>
 */
@Data
@ApiModel("商品附加费")
public class ProductServiceFeeDTO implements Serializable {
    private static final long serialVersionUID = -8654368424163703679L;

    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("城市商品ID")
    private Integer cityProductId;
    @ApiModelProperty("城市ID")
    private Integer cityId;
    @ApiModelProperty("模板ID")
    private Long templateId;

    private ServiceFeeTemplateDTO serviceFeeTemplate;
}
