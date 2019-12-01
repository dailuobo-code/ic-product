package com.dailuobo.ic.api.base;

import lombok.Data;

/**
 * 城市服务基础请求
 *
 * @author wangshifeng
 * @date 2019-08-08 17:35
 */
@Data
public class CityBaseRequest extends ICBaseRequest{

    /**
     * 城市id
     */
    protected Integer cityId;
}
