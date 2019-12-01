package com.dailuobo.ic.api.request.product;

import com.dailuobo.api.enums.HqProductExtraAttrAction;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HqProductSyncExtraAttrRequest implements Serializable {

    private HqProductExtraAttrAction attrAction;

    private HqProductDTO hqProduct;

    private Integer userId;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HqProductDTO implements Serializable{
        private Integer hqProductId;

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
