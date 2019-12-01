package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemDetailParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.SkuParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ItemUpdateRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 15:25<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = 5285310631268150872L;

    private ItemParam itemParam;

    private List<SkuParam> skuParamList;

    private ItemDetailParam itemDetailParam;

    private Long sellerId;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(itemParam, "item.param.is.null");
        nonNull(itemParam.getId(), "item.param.id.is.null");

        nonNull(sellerId, "sellerId.id.is.null");
        notEmpty(skuParamList, "sku.param.list.is.null");

        nonNull(itemDetailParam, "item.detail.is.null");
        nonNull(itemDetailParam.getItemId(), "item.detail.item.id.is.null");
    }
}
