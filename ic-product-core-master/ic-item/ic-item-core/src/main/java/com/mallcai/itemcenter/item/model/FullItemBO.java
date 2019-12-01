package com.mallcai.itemcenter.item.model;

import com.mallcai.itemcenter.sku.model.Sku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FullItemBO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:36<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullItemBO {

    private Item item;

    private List<Sku> skuList;

    private ItemDetail itemDetail;
}
