package com.mallcai.itemcenter.item.api.converter.output;

import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.utils.GenericConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * LogicItemInfoConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/23 21:21<br/>
 */
@Slf4j
@Component
public class LogicItemInfoConverter extends ItemInfoConverterImpl implements ItemInfoConverter {
    private final SkuInfoConverter skuInfoConverter;

    public LogicItemInfoConverter(SkuInfoConverter skuInfoConverter) {this.skuInfoConverter = skuInfoConverter;}

    @Override
    public ItemInfo domain2dto(Item item) {
        if (item == null) {
            return null;
        }
        ItemInfo info = super.domain2dto(item);

        Map<String, String> extra = item.getExtra() != null ? item.getExtra() : Collections.emptyMap();
        if (extra.containsKey("tax")) {
            info.setTax(extra.get("tax"));
        }
        if (extra.containsKey("taxCode")) {
            info.setTaxCode(extra.get("taxCode"));
        }
        if (extra.containsKey("arrivalDay")) {
            info.setArrivalDay(Integer.valueOf(extra.get("arrivalDay")));
        }

        if(extra.containsKey("isStandard")){
            info.setIsStandard(Integer.valueOf(extra.get("isStandard")));
        }
        if(extra.containsKey("newArrivalType")){
            info.setNewArrivalType(Integer.valueOf(extra.get("newArrivalType")));
        }
        if(extra.containsKey("seasonal")){
            info.setSeasonal(Integer.valueOf(extra.get("seasonal")));
        }
        if(extra.containsKey("deliveryMode")){
            info.setDeliveryMode(Integer.valueOf(extra.get("deliveryMode")));
        }
        if(extra.containsKey("qualityTime")){
            info.setQualityTime(Integer.valueOf(extra.get("qualityTime")));
        }

        return info;
    }

    @Override
    public FullItemWithDetailInfo getWithDetail(FullItemBO fullItemBO) {
        if (fullItemBO == null) {
            return null;
        }

        FullItemWithDetailInfo fullItemWithDetailInfo = new FullItemWithDetailInfo();

        fullItemWithDetailInfo.setItemInfo(domain2dto(fullItemBO.getItem()));
        fullItemWithDetailInfo.setSkuInfoList(GenericConverter.batchConvert(fullItemBO.getSkuList(), skuInfoConverter::domain2dto));
        fullItemWithDetailInfo.setItemDetailInfo(domain2dto(fullItemBO.getItemDetail()));

        return fullItemWithDetailInfo;
    }

    @Override
    public FullItemInfo getWithImgs(FullItemBO fullItemBO) {
        if (fullItemBO == null) {
            return null;
        }

        FullItemInfo fullItemInfo = new FullItemInfo();

        fullItemInfo.setItemInfo(domain2dto(fullItemBO.getItem()));
        fullItemInfo.setImageList(domain2dto(fullItemBO.getItemDetail().getImages()));
        fullItemInfo.setSkuInfoList(GenericConverter.batchConvert(fullItemBO.getSkuList(), skuInfoConverter::domain2dto));

        return fullItemInfo;
    }
}
