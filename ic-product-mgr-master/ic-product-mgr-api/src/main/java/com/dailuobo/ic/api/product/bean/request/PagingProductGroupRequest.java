package com.dailuobo.ic.api.product.bean.request;

import com.dailuobo.ic.api.base.CityBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PagingProductGroupRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 11:14<br/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("分页查询多规格商品组")
public class PagingProductGroupRequest extends CityBaseRequest {
    private static final long serialVersionUID = -1827474244264477148L;

    @ApiModelProperty("多规格商品组ID")
    private Integer id;
    @ApiModelProperty("多规格商品组名")
    private String name;
    @ApiModelProperty("商品编码")
    private String productNo;
}
