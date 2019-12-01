package com.mallcai.itemcenter.category.api.bean.request;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AttributeReadRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:43<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeQueryRequest extends AbstractRequest {
    private static final long serialVersionUID = 6975913311782186903L;

    @ApiModelProperty("类目 ID")
    private Long categoryId;
}
