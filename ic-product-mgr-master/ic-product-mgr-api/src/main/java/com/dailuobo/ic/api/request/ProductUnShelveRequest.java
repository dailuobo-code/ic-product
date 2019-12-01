package com.dailuobo.ic.api.request;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangshifeng
 * @date 2019-08-08 16:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUnShelveRequest extends CityBaseRequest {

    /**
     * 操作人
     */
    Integer userId;

    /**
     * 商品Id
     */
    Integer cityProductId;

    /**
     * 强制下架
     */
    boolean force;
}
