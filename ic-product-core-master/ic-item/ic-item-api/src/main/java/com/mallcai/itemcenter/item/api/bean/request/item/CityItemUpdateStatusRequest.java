package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import com.mallcai.itemcenter.dto.IdVersionPair;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CityItemUpdateStatusRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 16:14<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityItemUpdateStatusRequest extends AbstractRequest {

    @ApiModelProperty(value = "目标城市商品对象集合", notes = "id与version对")
    private List<IdVersionPair> targetList;

    @ApiModelProperty("店铺id")
    private Long sellerId;

    @ApiModelProperty("城市id")
    private Long cityId;

    @ApiModelProperty("目标状态")
    private ItemStatus status;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(targetList, "item.id.set.is.null");
        nonNull(sellerId, "sellerId.id.is.null");
        nonNull(cityId, "cityId.id.is.null");
        nonNull(status, "status.is.null");
    }
}
