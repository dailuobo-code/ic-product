package com.dailuobo.ic.api.request.goods;

import com.dailuobo.ic.api.base.CityBaseRequest;
import com.dailuobo.ic.dto.GoodsRelDTO;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 新增商品货品关联关系请求
 *
 * @author lgh
 * @date 2019/9/29
 */
@Data
public class AddProductGoodsRelRequest extends CityBaseRequest {

    @NotNull(message = "城市商品id不能为空")
    private Integer cityProductId;

    @NotNull(message = "操作人id不能为空")
    private Integer operatorId;

    @NotEmpty(message = "货品关联关系不能为空")
    private List<GoodsRelDTO> goodsRelDTOS;

    /**
     * 参数校验
     *
     * @return
     */
    @Override
    public boolean checkParams() {

        if(null == cityId){
            return false;
        }

        if(null == cityProductId){
            return false;
        }

        if(null == operatorId){
            return false;
        }

        if(CollectionUtils.isEmpty(goodsRelDTOS)){
            return false;
        }

        for(GoodsRelDTO relDTO : goodsRelDTOS){
            if(!checkGoodsRel(relDTO)){
                return false;
            }
        }

        return true;
    }

    /**
     * 货品属性校验
     *
     * @param relDTO
     * @return
     */
    private boolean checkGoodsRel(GoodsRelDTO relDTO){
        if(StringUtils.isBlank(relDTO.getGoodsId())){
            return false;
        }

        if(StringUtils.isBlank(relDTO.getGoodsName())){
            return false;
        }

        if(StringUtils.isBlank(relDTO.getGoodsUnit())){
            return false;
        }

        if(null == relDTO.getRelNum()){
            return false;
        }

        if(null == relDTO.getKeepType() || null == relDTO.getIsStandard()){
            return false;
        }

        return true;
    }
}
