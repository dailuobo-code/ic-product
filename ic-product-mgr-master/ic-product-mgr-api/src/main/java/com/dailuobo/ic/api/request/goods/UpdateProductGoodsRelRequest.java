package com.dailuobo.ic.api.request.goods;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author lgh
 * @date 2019/10/6
 */
@Data
public class UpdateProductGoodsRelRequest extends AddProductGoodsRelRequest{

    @NotNull(message = "货品关联id不能为空")
    private Integer orgRelId;

    /**
     * 参数校验
     *
     * @return
     */
    @Override
    public boolean checkParams() {

        if(null == orgRelId){
            return false;
        }

        return super.checkParams();
    }

}
