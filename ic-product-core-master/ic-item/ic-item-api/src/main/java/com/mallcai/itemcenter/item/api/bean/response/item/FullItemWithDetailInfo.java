package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * FullItemWithDetailInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:17<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "商品信息Info对象", description = "此对象包括item信息(商品详情)和sku信息")
public class FullItemWithDetailInfo implements Serializable {
    private static final long serialVersionUID = -1221320111604152122L;

    @ApiModelProperty("item信息")
    private ItemInfo itemInfo;

    @ApiModelProperty("sku信息集合")
    private List<SkuInfo> skuInfoList;

    @ApiModelProperty("商品详情")
    private ItemDetailInfo itemDetailInfo;
}
