package com.dailuobo.api.domain.vo;

import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.SellTime;
import com.dailuobo.api.domain.entity.Spec;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ProductCreateVo
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/8/23 下午3:32
 * @Version: 1.0
 **/
@Data
public class ProductCreateVo implements Serializable {

    private CityProduct cityProduct;
    private Spec spec;
    // 此处由于位于商品一次性创建的入参包装类里，所以SellTime是null，需要在城市商品表创建数据后才知道具体值
    private SellTime sellTime;
}
