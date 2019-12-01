package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import com.mallcai.domain.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PagingProductTopicRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 11:45<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("首页分类主题")
public class PagingProductTopicRequest extends RequestEntity {
    private static final long serialVersionUID = 1347615544755989401L;

    @ApiModelProperty("分类主题名称")
    private String name;
    @ApiModelProperty("分类主题状态")
    private CommonStatus status;
}
