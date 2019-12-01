package com.mallcai.domain.product.dto;

import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
public class ProductTopicDTO implements Serializable {
    private static final long serialVersionUID = -2346660616331142130L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("主题词名")
    private String name;
    @ApiModelProperty("关联类目")
    private List<CategoryDTO> classifies;
    @ApiModelProperty("关联类目 ID")
    private List<Integer> classifyIds;
    @ApiModelProperty("排序")
    private Integer order;
    @ApiModelProperty("状态")
    private CommonStatus status;

    @ApiModelProperty("最后操作人ID")
    private Integer operatorId;
    @ApiModelProperty("最后操作人")
    private String operator;

    @ApiModelProperty("创建时间")
    private Date updateTime;
    @ApiModelProperty("更新时间")
    private Date createTime;
}
