package com.mallcai.itemcenter.item.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * GroupedOtherAttribute
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:00<br/>
 */
@ToString
@EqualsAndHashCode(of = "group")
@NoArgsConstructor
@AllArgsConstructor
public class GroupedOtherAttribute implements Serializable {
    private static final long serialVersionUID = 7755317516453283794L;

    /**
     * 属性组名
     */
    @Getter
    @Setter
    private String group;

    /**
     * 属性键值对列表
     */
    @Getter
    @Setter
    private List<OtherAttribute> otherAttributes;
}

