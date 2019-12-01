package com.mallcai.itemcenter.item.api.converter.output;

import com.mallcai.itemcenter.item.api.bean.response.sku.SkuInfo;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.sku.enums.SalesType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * LogicSkuInfoConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 22:24<br/>
 */
@Component
public class LogicSkuInfoConverter extends SkuInfoConverterImpl implements SkuInfoConverter {
    @Override
    public SkuInfo domain2dto(Sku sku) {
        SkuInfo info = super.domain2dto(sku);

        Map<String, String> extra = sku.getExtra() != null ? sku.getExtra() : Collections.emptyMap();
        if (extra.containsKey("salesType")) {
            info.setSalesType(SalesType.valueOf(extra.get("salesType")));
        }
        if (extra.containsKey("packageWeight")) {
            info.setPackageWeight(Integer.valueOf(extra.get("packageWeight")));
        }
        if (extra.containsKey("packageQuantity")) {
            info.setPackageQuantity(Integer.valueOf(extra.get("packageQuantity")));
        }
        //补偿enable
        info.setEnable(Objects.equals(sku.getStatus(), ItemStatus.ON_SHELF.getValue()));
        return info;
    }
}
