package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemDetailParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.SkuParam;
import lombok.*;

import java.util.List;

/**
 * ItemCreateRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 10:52<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemCreateRequest extends AbstractRequest {

    private static final long serialVersionUID = -5182618140982902573L;

    private ItemParam itemParam;

    private List<SkuParam> skuParamList;

    private ItemDetailParam itemDetailParam;

    private Long sellerId;

    private String sellerName;

    @Override
    public void checkParam() {
        super.checkParam();

        nonNull(getUpdatedBy(), "updated.by.is.null");
        nonNull(sellerId, "seller.id.is.null");
        nonNull(itemParam, "item.is.null");
        notEmpty(skuParamList, "sku.is.empty");
        nonNull(itemDetailParam, "item.detail.is.null");

        setDefaultValue();
    }

    private void setDefaultValue() {
        itemParam.setUpdatedBy(getUpdatedBy());
        itemParam.setSellerId(sellerId);
        itemParam.setSellerName(sellerName);
        itemParam.setSellerId(sellerId);
        skuParamList.forEach(it -> {
            it.setUpdatedBy(getUpdatedBy());
            it.setSellerId(sellerId);
        });
        itemDetailParam.setUpdatedBy(getUpdatedBy());
    }
}
