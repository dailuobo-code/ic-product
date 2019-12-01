package com.dailuobo.ic.api.request.goods;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.Data;
import org.apache.dubbo.common.utils.StringUtils;


/**
 * 查询关联关系请求
 * <b>商品id & 货品编号不能同时为空</b>
 *
 * @author lgh
 * @date 2019/9/29
 */
@Data
public class QueryProductGoodsRelRequest extends CityBaseRequest {

    /** 城市商品id **/
    private Integer cityProductId;

    /** 货品编号 **/
    private String goodsNo;

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

        if(null == cityProductId && StringUtils.isBlank(goodsNo)){
           return false;
        }

        return true;
    }

}
