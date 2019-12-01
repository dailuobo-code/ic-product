package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ItemCityRelationInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 16:07<br/>
 */
@Data
@ApiModel("商品城市关联关系")
public class ItemCityRelationInfo extends ApiExtraInfo {
    private static final long serialVersionUID = -8820890874792755963L;

    @ApiModelProperty("商品 ID")
    private Long itemId;

    @ApiModelProperty("城市 ID")
    private Long cityId;
}
