package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * SubmitProductAuditRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/29 19:35<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("提交商品审批")
public class SubmitProductAuditRequest extends RequestEntity implements Serializable {
    private static final long serialVersionUID = -3002145236851709201L;

    private String comment;
    private List<Integer> productIds;
    @ApiModelProperty(value = "最后操作人ID", required = true)
    private Integer operatorId;
    @ApiModelProperty(value = "最后操作人", required = true)
    private String operator;

    @Override
    public boolean checkParam() {
        notEmpty(productIds, "productIds");
        notNull(operatorId, "operatorId");
        nonBlank(operator, "operator");
        return true;
    }
}
