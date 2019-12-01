package com.dailuobo.ic.dto;

import com.dailuobo.api.domain.entity.ProductClassify;
import com.dailuobo.ic.api.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ProductTopicDTO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:23<br/>
 */
@Data
@ApiModel("商品推荐主题")
public class ProductTopicDTO {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("主题词名")
    private String name;
    @ApiModelProperty("关联类目")
    private List<ProductClassify> classifies;
    @ApiModelProperty("排序")
    private Integer order;
    @ApiModelProperty("状态")
    private CommonStatus status;

    @ApiModelProperty("最后操作人ID")
    private Integer operatorId;
    @ApiModelProperty("最后操作人")
    private String operator;

    @ApiModelProperty("创建时间")
    private Date createdAt;
    @ApiModelProperty("更新时间")
    private Date updatedAt;
}
