package com.mallcai.domain.product.dto;

import com.mallcai.domain.enums.AuditStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * AuditResultDTo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/4 17:06<br/>
 */
@Data
@ApiModel("审批结果")
public class AuditResultDTO implements Serializable {
    private static final long serialVersionUID = -5653080666195316904L;

    @ApiModelProperty("审批单关联商品ID")
    private Long id;
    @ApiModelProperty("审批单ID")
    private Long billId;
    @ApiModelProperty("商品ID")
    private Integer productId;
    @ApiModelProperty("审批状态")
    private AuditStatus status;
    @ApiModelProperty("审批意见")
    private String comment;
}
