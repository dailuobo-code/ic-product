package com.mallcai.itemcenter.sku.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SkuAttribute
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:55<br/>
 */
@Data
@EqualsAndHashCode(of = {"id", "attrKey", "attrVal"})
public class SkuAttribute {
    @ApiModelProperty("属性id")
    private Long id;

    @ApiModelProperty("属性所属组 , 如颜色")
    private String attrKey;

    @ApiModelProperty("属性值, 如红色")
    private String attrVal;

    @ApiModelProperty("决定是否sku图片是否展现")
    private Boolean showImage;

    @ApiModelProperty("属性对应的缩略图")
    private String thumbnail;

    @ApiModelProperty("属性对应的原图")
    private String image;
}
