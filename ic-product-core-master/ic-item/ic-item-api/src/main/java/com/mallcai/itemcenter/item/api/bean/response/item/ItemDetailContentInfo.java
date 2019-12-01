package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItemDetailContentInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 21:08<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商品-详情-具体内容")
public class ItemDetailContentInfo extends ApiExtraInfo {

    private static final long serialVersionUID = 8934357789642812438L;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("具体内容")
    private String content;
}
