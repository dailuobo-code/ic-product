package com.mallcai.itemcenter.api.request;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * CacheableRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 15:01<br/>
 */
@Data
public abstract class BaseCacheableRequest extends AbstractRequest {
    private static final long serialVersionUID = -2114691980515287666L;

    @ApiModelProperty(value = "是否从缓存中获取值", notes = "非必须，默认使用缓存结果（命中时）")
    protected Boolean fromCache;

    @Override
    public void checkParam() {
        super.checkParam();
        fromCache = MoreObjects.firstNonNull(fromCache, true);
    }
}
