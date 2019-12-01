package com.mallcai.itemcenter.category.api.bean.request;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BackCategoryReadRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:40<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询后台类目子类目列表请求")
public class BackCategoryQueryChildrenRequest extends AbstractRequest {
    private static final long serialVersionUID = -6203770571471055804L;

    @ApiModelProperty("根据 ID 查询后台类目")
    private Long pid;
}
