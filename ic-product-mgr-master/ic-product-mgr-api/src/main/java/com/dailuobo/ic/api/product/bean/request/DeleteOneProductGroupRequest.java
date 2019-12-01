package com.dailuobo.ic.api.product.bean.request;

import com.dailuobo.ic.api.base.CityBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateProductGroupRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 11:20<br/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除多规格商品组")
public class DeleteOneProductGroupRequest extends CityBaseRequest {
    private static final long serialVersionUID = -3117624497773963891L;

    @ApiModelProperty(value = "多规格商品组ID", required = true)
    private Integer id;

    @ApiModelProperty(value = "最后操作人ID", required = true)
    private Integer operatorId;
    @ApiModelProperty(value = "最后操作人", required = true)
    private String operator;
}
