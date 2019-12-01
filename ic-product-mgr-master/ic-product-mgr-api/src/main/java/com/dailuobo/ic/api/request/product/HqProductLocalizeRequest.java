package com.dailuobo.ic.api.request.product;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HqProductLocalizeRequest implements Serializable {

    /**
     * 总部商品
     */
    private List<HqProductDTO> hqProducts;

    private Integer userId;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HqProductDTO implements Serializable{
        private Integer hqProductId;

        private Integer merchantId;

        private Integer cityId;

        private Integer isShare;

        private HqProductDeliverHomeAttrDTO attrDTO;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HqProductDeliverHomeAttrDTO implements Serializable{
        private Integer arrivalDay;
    }

}
