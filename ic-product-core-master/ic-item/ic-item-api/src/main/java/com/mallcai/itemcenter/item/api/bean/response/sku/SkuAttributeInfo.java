package com.mallcai.itemcenter.item.api.bean.response.sku;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SkuAttributeParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 11:36<br/>
 */
@Data
@EqualsAndHashCode(of = {"id", "attrKey", "attrVal"}, callSuper = false)
public class SkuAttributeInfo extends ApiExtraInfo {
    private static final long serialVersionUID = 406477521140222299L;

    @ApiModelProperty("属性id")
    private Long id;

    @ApiModelProperty("属性所属组 , 如颜色")
    private String attrKey;

    @ApiModelProperty("属性值, 如红色")
    private String attrVal;

    @ApiModelProperty(value = "决定是否sku图片是否展现", notes = "默认为 true")
    private Boolean showImage;

    @ApiModelProperty("属性对应的缩略图")
    private String thumbnail;

    @ApiModelProperty("属性对应的原图")
    private String image;

}

