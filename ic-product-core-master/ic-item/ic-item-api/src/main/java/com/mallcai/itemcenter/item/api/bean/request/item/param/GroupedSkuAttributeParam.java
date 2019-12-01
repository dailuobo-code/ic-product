package com.mallcai.itemcenter.item.api.bean.request.item.param;

import com.mallcai.itemcenter.api.response.param.ApiExtraParam;
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
public class GroupedSkuAttributeParam extends ApiExtraParam {
    private static final long serialVersionUID = 6604695091767397994L;

    /**
     * sku属性名
     */
    private String attrKey;

    /**
     * sku属性值
     */
    private List<SkuAttributeParam> skuAttributes;
}
