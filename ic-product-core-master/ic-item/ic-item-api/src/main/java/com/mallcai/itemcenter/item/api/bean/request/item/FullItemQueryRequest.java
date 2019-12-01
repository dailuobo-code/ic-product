package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.BaseCacheableRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FullItemQueryRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:49<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullItemQueryRequest extends BaseCacheableRequest {
    private static final long serialVersionUID = 1191359217564747865L;

    @ApiModelProperty("商品id")
    private Long itemId;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(itemId, "item.id.is.null");
    }
}
