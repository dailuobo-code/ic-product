package com.mallcai.itemcenter.item.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * OtherAttribute
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:01<br/>
 */
@Data
@ToString
@EqualsAndHashCode(of = {"id", "attrKey", "attrVal"})
public class OtherAttribute implements Serializable {

    private static final long serialVersionUID = -5740836205421186038L;

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
