package com.dailuobo.ic.api.request.product;

import com.dailuobo.ic.api.base.CityBaseRequest;
import com.dailuobo.ic.api.enums.ImportBusinessEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CityProductSearchRequest extends CityBaseRequest {
    /**
     * 城市商品ID
     */
    @ApiModelProperty(value = "商品编号")
    private String productNo;


    @ApiModelProperty(value = "商品编号集合")
    private List<String> productNoList;


    /**
     * 城市ID
     */
    @ApiModelProperty(value = "城市ID")
    private Integer cityId;

    @ApiModelProperty(value = "门店ID(0表示城市默认值)")
    private Integer storeId = 0;

    @ApiModelProperty(value = "商品类型，1:普通商品，2:新人专享")
    private Integer goodsType;

    @ApiModelProperty(value = "城市状态：0，待上架；1，上架；2，下架")
    private Integer cityStatus;

    @ApiModelProperty(value = "是否共享库存 0 -否  1-是")
    private Integer isShare;

    @ApiModelProperty(value = "商品上架状态：0，下架；1，上架")
    private Integer salesStatus;

    @ApiModelProperty(value = "总部商品状态：0，下架；1，上架")
    private Integer hqStatus;


    @ApiModelProperty(value = "导入业务类型匹配 fullReduction：多级满减 simple：通用导入", hidden = true)
    private ImportBusinessEnum businessEnum;
}
