package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.BaseCacheableRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FullCityItemListRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 16:00<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityItemListRelationRequest extends BaseCacheableRequest {
    private static final long serialVersionUID = 1191359217564747865L;

    @ApiModelProperty("商品id")
    private List<Long> itemIds;

    @Override
    public void checkParam() {
        super.checkParam();

        notEmpty(itemIds, "item.id.is.empty");
    }
}
