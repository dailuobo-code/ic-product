package com.mallcai.itemcenter.item.model;

import com.mallcai.itemcenter.sku.model.Sku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FullItemOperateBO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:47<br/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullItemOperateBO {

    private Item item;

    private List<Sku> toCreateSkuList;

    private List<Sku> toUpdateSkuList;

    private List<Sku> toDeleteSkuList;

    private ItemDetail itemDetail;

    /**
     * 从创建对象生成
     *
     * @param item       商品对象
     * @param skuList    sku集合
     * @param itemDetail 商品详情
     * @return {@link FullItemOperateBO}对象
     */
    public static FullItemOperateBO fromCreate(Item item, List<Sku> skuList, ItemDetail itemDetail) {
        FullItemOperateBO bo = new FullItemOperateBO();

        bo.setItem(item);
        bo.setToCreateSkuList(skuList);
        bo.setItemDetail(itemDetail);

        return bo;
    }
}
