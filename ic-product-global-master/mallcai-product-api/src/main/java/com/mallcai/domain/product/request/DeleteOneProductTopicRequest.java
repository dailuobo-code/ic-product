package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DeleteOneProductTopicRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 11:47<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("删除一个首页分类主题")
public class DeleteOneProductTopicRequest extends RequestEntity {
    private static final long serialVersionUID = 6265314297081142740L;

    @ApiModelProperty(value = "ID", required = true)
    private Integer id;
    @ApiModelProperty(value = "最后操作人ID", required = true)
    private Integer operatorId;
    @ApiModelProperty(value = "最后操作人", required = true)
    private String operator;
}
