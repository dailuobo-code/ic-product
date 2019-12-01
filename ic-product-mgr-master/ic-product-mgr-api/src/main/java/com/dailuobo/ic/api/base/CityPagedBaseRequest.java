package com.dailuobo.ic.api.base;

import lombok.Data;

/**
 * @author wangshifeng
 * @date 2019-08-08 17:35
 */
@Data
public class CityPagedBaseRequest extends CityBaseRequest{
    protected int offset = 0;

    protected int limit = 20;
}
