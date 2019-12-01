package com.dailuobo.ic.api.request.goods;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 删除商品货品关联关系请求
 *
 * @author lgh
 * @date 2019/9/29
 */
@Data
public class DelProductGoodsRelRequest extends CityBaseRequest {

    @NotNull(message = "货品关联id不能为空")
    private Integer relId;

    @NotNull(message = "操作人id不能为空")
    private Integer operatorId;

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

        if(null == relId){
            return false;
        }

        if(null == operatorId){
            return false;
        }

        return true;
    }
}
