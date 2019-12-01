package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.BasePagingRequest;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItemPagingRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:45<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPagingRequest extends BasePagingRequest {
    private static final long serialVersionUID = -9176958464960069879L;

    @ApiModelProperty(value = "商品名称，模糊匹配")
    private String itemName;

    @ApiModelProperty(value = "卖家id")
    private Long sellerId;

    @ApiModelProperty("类目id")
    private Long categoryId;

    @ApiModelProperty(value = "商品状态")
    private ItemStatus status;

    @ApiModelProperty(value = "商品类型")
    private Integer type;
}
