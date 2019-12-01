package com.dailuobo.api.domain.dto.merchant;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: MerchantProductDto
 * @Description: 商家端营销配置商品列表
 * @Author: lekaijun
 * @Date: 2019/10/22
 * @Version: 1.0
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductMarketDTO implements Serializable {

    private static final long serialVersionUID = -2848902228002687882L;
    /* 商品图标 */
    private String cityProductIcon;
    private String productName;
    private Integer cityProductId;

    /**
     * 分类  (一级分类/二级分类)返回
     */
    private String category;

    /**
     * 库存数量
     */
    private Integer storeNum;

    /**
     * 实际销售价
     */
    private BigDecimal realPrice;
}
