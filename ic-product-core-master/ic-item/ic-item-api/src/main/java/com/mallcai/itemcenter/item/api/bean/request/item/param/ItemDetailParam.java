package com.mallcai.itemcenter.item.api.bean.request.item.param;

import com.mallcai.itemcenter.api.response.param.ApiExtraParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ItemDetailParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 11:38<br/>
 */
@Data
@ApiModel("商品-详情")
public class ItemDetailParam extends ApiExtraParam {
    private static final long serialVersionUID = -5118472245467436763L;

    @ApiModelProperty("商品id")
    private Long itemId;

    /**
     * should use {@link #detail}
     */
    @Deprecated
    @ApiModelProperty("商品描述")
    private String remark;

    @ApiModelProperty("图片信息")
    private List<ImageParam> images;

    @ApiModelProperty(value = "富文本商品详情")
    private List<ItemDetailContentParam> detail;

    private Long updatedBy;
}
