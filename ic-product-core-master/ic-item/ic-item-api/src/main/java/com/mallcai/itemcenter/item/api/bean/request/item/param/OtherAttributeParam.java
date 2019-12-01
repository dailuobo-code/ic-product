package com.mallcai.itemcenter.item.api.bean.request.item.param;

import com.mallcai.itemcenter.api.response.param.ApiExtraParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * OtherAttributeParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 11:33<br/>
 */
@Data
@ToString
@EqualsAndHashCode(of = {"id", "attrKey", "attrVal"}, callSuper = false)
public class OtherAttributeParam extends ApiExtraParam {
    private static final long serialVersionUID = -5902560097169834655L;

    @ApiModelProperty("属性id")
    private Long id;

    @ApiModelProperty("属性名")
    private String attrKey;

    @ApiModelProperty("属性值")
    private String attrVal;

    @ApiModelProperty("属性组名")
    private String group;

    @ApiModelProperty("表示该属性是否允许商家编辑")
    private Boolean readOnlyBySeller;
}
