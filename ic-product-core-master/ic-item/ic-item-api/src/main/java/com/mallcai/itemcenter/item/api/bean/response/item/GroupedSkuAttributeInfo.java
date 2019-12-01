package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import com.mallcai.itemcenter.item.api.bean.response.sku.SkuAttributeInfo;
import lombok.*;

import java.util.List;

/**
 * GroupedSkuAttributeParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 11:30<br/>
 */
@ToString
@EqualsAndHashCode(of = "attrKey", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupedSkuAttributeInfo extends ApiExtraInfo {
    private static final long serialVersionUID = 6604695091767397994L;

    /**
     * sku属性名
     */
    private String attrKey;

    /**
     * sku属性值
     */
    private List<SkuAttributeInfo> skuAttributes;
}
