package com.mallcai.domain.product.dto;

import com.mallcai.domain.BaseEntity;
import com.mallcai.domain.enums.AuditStatus;
import com.mallcai.domain.enums.AuditType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * AuditBillDTO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/27 17:46<br/>
 */
@Data
@ApiModel("审批单")
public class AuditBillDTO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6507087200618872487L;

    @ApiModelProperty("审批单 ID")
    private Long id;
    @ApiModelProperty("发起审批留言")
    private String comment;;
    @ApiModelProperty("审批意见")
    private String reply;
    @ApiModelProperty("审批回复结果")
    private String flowId;
    @ApiModelProperty("审批结果")
    private AuditStatus status;
    @ApiModelProperty("审批类型")
    private AuditType type;
    @ApiModelProperty(value = "操作人ID", required = true)
    private Integer operatorId;
    @ApiModelProperty(value = "操作人", required = true)
    private String operator;

    private Date createTime;
    private Date updateTime;
}
