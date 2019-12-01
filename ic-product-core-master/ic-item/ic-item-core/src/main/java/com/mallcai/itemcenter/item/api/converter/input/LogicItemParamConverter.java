package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemParam;
import com.mallcai.itemcenter.item.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * LogicItemApiConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/23 21:30<br/>
 */
@Slf4j
@Component
public class LogicItemParamConverter extends ItemParamConverterImpl implements ItemParamConverter {
    @Override
    public Item dto2domain(ItemParam s) {
        Item item = super.dto2domain(s);

        Map<String, String> extra = item.getExtra() != null ? item.getExtra() : new HashMap<>();
        if (s.getTax() != null) {
            extra.put("tax", s.getTax());
        }
        if (s.getTaxCode() != null) {
            extra.put("taxCode", s.getTaxCode());
        }
        if (s.getIsStandard() != null) {
            extra.put("isStandard", s.getIsStandard().toString());
        }
        if (s.getNewArrivalType() != null) {
            extra.put("newArrivalType", s.getNewArrivalType().toString());
        }
        if (s.getSeasonal() != null) {
            extra.put("seasonal", s.getSeasonal().toString());
        }
        if (s.getDeliveryMode() != null) {
            extra.put("deliveryMode", s.getDeliveryMode().toString());
        }
        if (s.getQualityTime() != null) {
            extra.put("qualityTime", s.getQualityTime().toString());
        }
        if (s.getArrivalDay() != null) {
            extra.put("arrivalDay", s.getArrivalDay().toString());
        }

        item.setExtra(extra);
        return item;
    }
}
