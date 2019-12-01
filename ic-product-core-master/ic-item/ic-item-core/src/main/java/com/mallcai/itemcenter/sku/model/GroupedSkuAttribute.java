package com.mallcai.itemcenter.sku.model;

import lombok.*;

import java.util.List;

/**
 * GroupedSkuAttribute
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:56<br/>
 */
@ToString
@EqualsAndHashCode(of = "attrKey")
@NoArgsConstructor
@AllArgsConstructor
public class GroupedSkuAttribute {
    /**
     * sku属性名
     */
    @Getter
    @Setter
    private String attrKey;

    /**
     * sku属性值
     */
    @Getter
    @Setter
    private List<SkuAttribute> skuAttributes;
}
