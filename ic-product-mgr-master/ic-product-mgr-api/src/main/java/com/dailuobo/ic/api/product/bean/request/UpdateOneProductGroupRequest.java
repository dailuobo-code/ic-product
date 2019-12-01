package com.dailuobo.ic.api.product.bean.request;

import com.dailuobo.ic.api.base.CityBaseRequest;
import com.dailuobo.ic.api.enums.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@ApiModel("创建多规格商品组")
public class UpdateOneProductGroupRequest extends CityBaseRequest {
    private static final long serialVersionUID = -3117624497773963891L;

    @ApiModelProperty(value = "多规格商品组ID", required = true)
    private Integer id;
    @ApiModelProperty("多规格商品组名")
    private String name;
    @ApiModelProperty("关联商品 ID 列表")
    private List<Integer> cityProductIds;
    @ApiModelProperty("状态，默认 DISABLE")
    private CommonStatus status = CommonStatus.DISABLE;

    @ApiModelProperty(value = "最后操作人ID", required = true)
    private Integer operatorId;
    @ApiModelProperty(value = "最后操作人", required = true)
    private String operator;
}
