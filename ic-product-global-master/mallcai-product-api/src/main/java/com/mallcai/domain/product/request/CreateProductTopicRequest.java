package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import com.mallcai.domain.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CreateProductTopicRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 11:47<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("创建一个首页分类主题")
public class CreateProductTopicRequest extends RequestEntity {
    private static final long serialVersionUID = 7860410357317448723L;

    @ApiModelProperty("主题词名")
    private String name;
    @ApiModelProperty("关联类目ID")
    private List<Integer> classifies;
    @ApiModelProperty("关联类目编码列表")
    private List<String> classifyNos;
    @ApiModelProperty("排序")
    private Integer order;
    @ApiModelProperty("状态")
    private CommonStatus status = CommonStatus.ENABLE;

    @ApiModelProperty(value = "最后操作人ID", required = true)
    private Integer operatorId;
    @ApiModelProperty(value = "最后操作人", required = true)
    private String operator;

    @Override
    public boolean checkParam() {
        notNull(operatorId, "operatorId");
        nonBlank(name, "name");
        nonBlank(operator, "operator");
        return true;
    }
}
