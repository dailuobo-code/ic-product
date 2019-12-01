package com.mallcai.itemcenter.item.api.bean.request.item.param;

import com.mallcai.itemcenter.api.response.param.ApiExtraParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItemDetailContentParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 11:40<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商品-详情-具体内容")
public class ItemDetailContentParam extends ApiExtraParam {
    private static final long serialVersionUID = 6695223284692484001L;

    @ApiModelProperty(value = "标题", notes = "用于分段详情，默认为空")
    private String title;

    @ApiModelProperty("具体内容")
    private String content;
}
