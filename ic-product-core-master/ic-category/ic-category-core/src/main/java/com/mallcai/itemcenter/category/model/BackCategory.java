package com.mallcai.itemcenter.category.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * BackCategory
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 17:40<br/>
 */
@Data
public class BackCategory {
    /**
     * id
     */
    private Long id;
    /**
     * 父级id
     */
    private Long pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 状态,1启用,-1禁用
     */
    private Integer status;
    /**
     * 是否有子类目
     */
    private Boolean hasChildren;
    /**
     * 是否有spu关联
     */
    private Boolean hasSpu;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("更新操作用户id")
    private Long updatedBy;
}
