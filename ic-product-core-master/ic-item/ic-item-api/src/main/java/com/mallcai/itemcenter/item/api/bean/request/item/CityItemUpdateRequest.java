package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.param.CityItemParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.CitySkuParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CityItemUpdateRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:55<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityItemUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = 5285310631268150872L;

    private Long sellerId;

    @ApiModelProperty("城市商品信息")
    private CityItemParam cityItemParam;

    private List<CitySkuParam> citySkuParamList;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(sellerId, "seller.id.is.null");
        nonNull(cityItemParam, "item.is.null");
        notEmpty(citySkuParamList, "sku.list.is.null");
    }
}
