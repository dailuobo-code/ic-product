package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ItemDetailInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:16<br/>
 */
@Data
@ApiModel("商品-详情")
public class ItemDetailInfo extends ApiExtraInfo {
    private static final long serialVersionUID = 1202347122782852580L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long itemId;

    /**
     * should use {@link #detail}
     */
    @Deprecated
    @ApiModelProperty("商品描述")
    private String remark;

    @ApiModelProperty("图片信息")
    private List<ImageInfo> images;

    @ApiModelProperty(value = "文本商品详情")
    private List<ItemDetailContentInfo> detail;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("更新者id")
    private Long updatedBy;

    @ApiModelProperty("创建者id")
    private Long createdBy;
}
