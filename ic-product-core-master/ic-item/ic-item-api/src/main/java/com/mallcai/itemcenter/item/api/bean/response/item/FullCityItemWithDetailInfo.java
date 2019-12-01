package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.CitySkuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FullCityItemWithDetailInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:49<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "城市商品信息Info对象", description = "此对象包括item信息和sku信息")
public class FullCityItemWithDetailInfo extends ApiExtraInfo {
    private static final long serialVersionUID = -5262879198484768085L;

    @ApiModelProperty("item信息")
    private CityItemInfo itemInfo;

    @ApiModelProperty("sku信息集合")
    private List<CitySkuInfo> skuInfoList;

    @ApiModelProperty("商品详情")
    private CityItemDetailInfo itemDetailInfo;

    public Long getCityId() {
        return itemInfo.getCityId();
    }
}
