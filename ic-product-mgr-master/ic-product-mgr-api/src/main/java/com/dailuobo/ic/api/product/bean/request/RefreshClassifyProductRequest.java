package com.dailuobo.ic.api.product.bean.request;

import com.dailuobo.ic.api.base.ICBaseRequest;
import com.dailuobo.ic.api.enums.ModifyTypeEnum;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RefreshClassifyProductRequest extends ICBaseRequest {
    private Integer cityProductId;
    private Integer hqProduct;
    private Integer beforeClassifyId;
    private Integer afterClassifyId;
    private Integer cityId;

    /**
     * 更新类型
     */
    private ModifyTypeEnum modifyTypeEnum;

    @Override
    public boolean checkParams() {
        if (modifyTypeEnum == null) {
            throw new IllegalArgumentException("商品变更类型缺失");
        }
        if (ModifyTypeEnum.HqProduct.equals(modifyTypeEnum)) {
            if (beforeClassifyId == null || afterClassifyId == null) {
                throw new IllegalArgumentException("总部商品分类变更需要给出变更前后的分类id");
            }
        }
        if (ModifyTypeEnum.CityProduct.equals(modifyTypeEnum)) {
            if (cityProductId == null) {
                throw new IllegalArgumentException("CityProductId不能为空");
            }
            if (cityId == null) {
                throw new IllegalArgumentException("CityId不能为空");
            }
        }
        return true;
    }
}
