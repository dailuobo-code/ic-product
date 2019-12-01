package com.mallcai.itemcenter.item.enums;

import com.mallcai.itemcenter.common.ConditionReadable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ItemStatus
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 11:43<br/>
 */
@Getter
@AllArgsConstructor
public enum ItemStatus implements ConditionReadable {

    /**
     * 上架、可用
     */
    ON_SHELF(1, "上架"),

    /**
     * 不可用状态时，不作任何sku相关的校验（允许无价格）
     */
    DISABLE(0, "不可用"),
    OFF_SHELF(-1, "下架"),
    FROZEN(-2, "冻结"),
    DELETED(-3, "删除");

    private final int value;
    private final String description;

    public static ItemStatus fromValue(int value) {
        for (ItemStatus itemStatus : ItemStatus.values()) {
            if (itemStatus.value == value) {
                return itemStatus;
            }
        }

        return null;
    }

    public static void assertStatus(int value) {
        for (ItemStatus itemStatus : ItemStatus.values()) {
            if (itemStatus.value == value) {
                return;
            }
        }

        throw new IllegalArgumentException("invalid.item.status");
    }
}
