package com.mallcai.domain.product.request;

import com.mallcai.domain.product.dto.SkuAttributeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新商品模型城市商品规格变更同步
 */
@Data
public class ProductSpecModifyAsyncQueryRequest implements Serializable {
    private static final long serialVersionUID = -2349518000894407794L;

    private Integer cityId;
    private Long itemId;
    private Integer updateUserId;
    private Integer itemStatus;
    /**
     * 变更规格信息
     */
    private List<SkuUpdateDTO> skuUpdateDTOList;


    @Data
    public static class SkuUpdateDTO implements Serializable {
        private Long skuId;
        private Integer threshold;
        /**
         * 售价
         */
        private Integer price;
        private String barCode;
        private String priceJSON;
        private Long updateBy;
        /**
         * sku状态
         */
        private Integer updateStatus;

        /**
         * 扩展属性
         * 1。销售方式 salesType
         * 2.销售数量 packageWeight
         * 3.销售重量 packageQuantity
         */
        private String extraJson;

    }


}
