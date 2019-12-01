package com.mallcai.itemcenter.item.api.bean.request.item.param;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * GroupedOtherAttributeParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 11:31<br/>
 */
@ToString
@EqualsAndHashCode(of = "group", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupedOtherAttributeParam extends ApiExtraInfo {
    private static final long serialVersionUID = 4164282360905738569L;

    @ApiModelProperty("属性组名")
    private String group;

    @ApiModelProperty("属性键值对列表")
    private List<OtherAttributeParam> otherAttributes;
}


