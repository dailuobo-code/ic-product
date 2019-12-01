package com.mallcai.itemcenter.category.api.bean.response;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BackCategoryInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 13:51<br/>
 */
@Data
@ApiModel("后台类目")
public class BackCategoryInfo extends ApiExtraInfo {
    private static final long serialVersionUID = 5771811459329988176L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("父级id")
    private Long pid;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("级别")
    private Integer level;

    @ApiModelProperty("是否有下级类目")
    private Boolean hasChildren;

    @ApiModelProperty("是否有spu关联")
    private Boolean hasSpu;

    @ApiModelProperty("操作员id")
    private Long updatedBy;
}
