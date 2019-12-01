package com.dailuobo.ic.dto;

import com.dailuobo.ic.api.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ProductGroupDTO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 11:11<br/>
 */
@Data
@ApiModel("多规格商品组")
public class ProductGroupDTO implements Serializable {
    private static final long serialVersionUID = -1465878838747472318L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("多规格商品组名")
    private String name;
    @ApiModelProperty("关联商品 ID 列表")
    private List<Integer> cityProductIds;
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
