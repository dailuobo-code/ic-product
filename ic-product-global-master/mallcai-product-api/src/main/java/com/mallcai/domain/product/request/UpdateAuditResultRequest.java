package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import com.mallcai.domain.enums.AuditStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UpdateAuditResultRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/27 21:09<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("更新审批结果")
public class UpdateAuditResultRequest extends RequestEntity implements Serializable {
    private static final long serialVersionUID = 8977955785257218934L;

    @ApiModelProperty("审批单 ID")
    private Long id;
    @ApiModelProperty("审批意见")
    private String message;
    @ApiModelProperty("审批结果")
    private AuditStatus status;

    @Override
    public boolean checkParam() {
        notNull(id, "id");
        notNull(status, "status");
        return true;
    }
}
