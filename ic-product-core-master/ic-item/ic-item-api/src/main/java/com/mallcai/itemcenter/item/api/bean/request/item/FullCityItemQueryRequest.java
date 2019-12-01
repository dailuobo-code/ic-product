package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.BaseCacheableRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FullCityItemQueryRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 15:45<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullCityItemQueryRequest extends BaseCacheableRequest {
    private static final long serialVersionUID = 1191359217564747865L;

    @ApiModelProperty("商品id")
    private Long itemId;
    @ApiModelProperty("城市id")
    private Long cityId;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(itemId, "item.id.is.null");
        nonNull(itemId, "city.id.is.null");
    }
}
