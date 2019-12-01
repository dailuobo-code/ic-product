package com.mallcai.itemcenter.item.api.converter.input;

import com.mallcai.itemcenter.common.BaseConverter;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemDetailParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemParam;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import org.mapstruct.Mapper;

/**
 * ItemApiConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:39<br/>
 */
@Mapper
public interface ItemParamConverter extends BaseConverter {
    Item dto2domain(ItemParam s);

    ItemDetail dto2domain(ItemDetailParam s);
}
