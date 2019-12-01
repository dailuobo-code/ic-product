package com.dailuobo.ic.api.request.goods;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.Data;
import org.apache.dubbo.common.utils.CollectionUtils;

import java.util.List;

/**
 * @author lgh
 * @date 2019/10/15
 */
@Data
public class BatchQueryProductGoodsRelRequest extends CityBaseRequest {

    /** 城市商品ids **/
    private List<Integer> cityProductIds;

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

        if(CollectionUtils.isEmpty(cityProductIds)){
            return false;
        }

        return true;
    }

}
