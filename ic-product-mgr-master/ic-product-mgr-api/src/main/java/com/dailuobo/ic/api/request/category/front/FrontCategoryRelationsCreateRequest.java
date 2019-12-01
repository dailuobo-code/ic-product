package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.CityBaseRequest;
import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import com.dailuobo.ic.api.enums.EffectScopeEnum;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 创建前台类目关联关系请求
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FrontCategoryRelationsCreateRequest extends CityBaseRequest implements Serializable {

    /**
     * 关联关系主键Id
     */
    private BigInteger id;

    /**
     * 前台二级类目id
     */
    private BigInteger frontCategoryId;

    /**
     * 关联类型 ：1。后台类目 2。指定商品
     */
    private Integer relationType;

    /**
     * 生效范围
     *
     * @see EffectScopeEnum
     */
    private Integer effectScope;

    /**
     * 操作人Id
     */
    private Integer operatorId;


    /**
     * 生效范围（门店组Id列表或者门店id列表）
     */
    private List<Integer> effectEntityIdList;


    /**
     * 关联的后台类目Id;
     */
    private List<FCategoryRelatedBCategory> relatedBCategoryList;


    /**
     * 关联的后台类目Id
     */
    private List<FCategoryRelatedCityProduct> relatedCityProductList;


    public String getEffectEntityIdListStr() {
        if (this.effectEntityIdList == null) {
            return "";
        }
        String[] objects = effectEntityIdList.stream().map(String::valueOf).toArray(String[]::new);
        return StringUtils.join(objects, ",");

    }


    @Override
    public boolean checkParams() {
        if (relationType == null || frontCategoryId == null || effectScope == null || operatorId == null || cityId == null) {
            throw new IllegalArgumentException("参数错误");
        }
        if (CategoryRelationTypeEnum.getByStatus(relationType) == null) {
            throw new IllegalArgumentException("非法的relationType，请检查参数");
        }
        if (relationType == 1 && CollectionUtils.isEmpty(relatedBCategoryList)) {
            throw new IllegalArgumentException("关联类型与relatedBCategoryList不匹配");
        }
        if (relationType == 2 && CollectionUtils.isEmpty(relatedCityProductList)) {
            throw new IllegalArgumentException("关联类型与relatedCityProductList不匹配");
        }
        return true;
    }

}
