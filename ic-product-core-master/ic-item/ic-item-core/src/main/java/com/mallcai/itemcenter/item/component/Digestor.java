package com.mallcai.itemcenter.item.component;

import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Digstors
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:28<br/>
 */
@Slf4j
@Component
public class Digestor {

    private static final HashFunction hashing = Hashing.murmur3_128();

    /**
     * 商品信息快照摘要
     *
     * @param item       商品
     * @param itemDetail 商品详情
     * @return 商品信息快照摘要
     */
    public static String itemDigest(Item item, ItemDetail itemDetail) {
        return hashing.newHasher()
                .putString(MoreObjects.firstNonNull(item.getItemCode(), ""), Charsets.UTF_8)
                .putLong(item.getSellerId())
                .putString(MoreObjects.firstNonNull(item.getSellerName(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(item.getName(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(item.getMainImage(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(itemDetail.getImageJson(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(item.getAdvertise(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(item.getExtraJson(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(item.getSkuAttributesJson(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(item.getOtherAttributesJson(), ""), Charsets.UTF_8)
                .putString(MoreObjects.firstNonNull(itemDetail.getDetail(), ""), Charsets.UTF_8)
                .hash().toString();
    }
}
