package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ItemFeatureInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 20:31<br/>
 */
@Data
@ApiModel("商品特性")
public class ItemFeatureInfo extends ApiExtraInfo {
    private static final long serialVersionUID = -7811934013556572750L;

    @ApiModelProperty("商品特性名称")
    private String name;

    @ApiModelProperty("商品特性值")
    private String value;

    @ApiModelProperty("商品特性类型")
    private Integer type;
}
