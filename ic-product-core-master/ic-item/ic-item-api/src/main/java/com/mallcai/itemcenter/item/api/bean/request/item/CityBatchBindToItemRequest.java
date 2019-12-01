package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * BatchBindItemToCityRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:54<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityBatchBindToItemRequest extends AbstractRequest {
    private static final long serialVersionUID = 7973804812033519720L;

    @ApiModelProperty("商品 ID")
    private Long itemId;

    @ApiModelProperty("城市 ID 列表")
    private List<Long> cityIds;

    @ApiModelProperty("操作人")
    private Long sellerId;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(getUpdatedBy(), "update.by.is.null");
        nonNull(sellerId, "seller.id.is.null");
        nonNull(itemId, "item.id.is.null");
        notEmpty(cityIds, "city.ids.is.empty");
    }
}
