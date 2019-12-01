package com.dailuobo.ic.api.request.product;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: lekaijun
 * @Date: 2019-10-22
 * @Description: 商家后台查询营销商品列表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantProductMarketRequest implements Serializable {

    private static final long serialVersionUID = -7275184039501795311L;

    private Integer cityId;

    private Integer merchantId;

    private String productName;

    private Integer page;

    private Integer pageSize = 20;

    private List<Integer> cityProductIds;
}
