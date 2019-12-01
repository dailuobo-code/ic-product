package com.mallcai.itemcenter.item.api.converter.output;

import com.mallcai.itemcenter.base.converter.BaseItemConverter;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.FullItemWithDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemDetailInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import org.mapstruct.Mapper;

/**
 * ItemInfoConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/22 21:16<br/>
 */
@Mapper
public interface ItemInfoConverter extends BaseItemConverter {
    ItemInfo domain2dto(Item s);

    ItemDetailInfo domain2dto(ItemDetail s);

    default FullItemWithDetailInfo getWithDetail(FullItemBO fullItemBO) {
        return null;
    }

    default FullItemInfo getWithImgs(FullItemBO fullItemBO) {
        return null;
    }
}
