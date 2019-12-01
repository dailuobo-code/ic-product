package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.item.api.bean.request.item.param.SkuParam;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.sku.model.Sku;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * LogicSkuApiConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/23 21:31<br/>
 */
@Component
public class LogicSkuParamConverter extends SkuParamConverterImpl implements SkuParamConverter {
    @Override
    public Sku dto2domain(SkuParam s) {
        Sku sku = super.dto2domain(s);

        Map<String, String> extra = sku.getExtra() != null ? sku.getExtra() : new HashMap<>();
        if (s.getSalesType() != null) {
            extra.put("salesType", s.getSalesType().name());
        }
        if (s.getPackageWeight() != null) {
            extra.put("packageWeight", s.getPackageWeight().toString());
        }
        if (s.getPackageQuantity() != null) {
            extra.put("packageQuantity", s.getPackageQuantity().toString());
        }
        Boolean isEnable = s.getEnable();
        if (sku.getStatus() == null) {
            if (isEnable == null || isEnable) {
                sku.setStatus(ItemStatus.ON_SHELF.getValue());
            } else {
                sku.setStatus(ItemStatus.OFF_SHELF.getValue());
            }
        }
        sku.setExtra(extra);
        return sku;
    }
}
