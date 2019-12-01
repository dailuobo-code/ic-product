package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.BaseCacheableRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FullItemWithDetailQueryRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:45<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullItemWithDetailQueryRequest extends BaseCacheableRequest {
    private static final long serialVersionUID = -7374113941405509915L;

    @ApiModelProperty("商品id")
    private Long itemId;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(itemId, "item.id.is.null");
    }
}
