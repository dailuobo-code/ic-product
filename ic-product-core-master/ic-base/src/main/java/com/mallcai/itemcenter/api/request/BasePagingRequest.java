package com.mallcai.itemcenter.api.request;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BasePagingRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 14:48<br/>
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public abstract class BasePagingRequest extends AbstractRequest {
    private static final long serialVersionUID = 5711688477508984316L;

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private Integer limit;

    private Integer offset;

    public Integer getLimit() {
        if (limit != null) {
            return limit;
        }

        this.pageSize = MoreObjects.firstNonNull(this.pageSize, 20);
        this.limit = pageSize > 0 ? pageSize : 20;
        return this.limit;
    }

    public Integer getOffset() {
        if (offset != null) {
            return offset;
        }

        this.pageSize = MoreObjects.firstNonNull(this.pageSize, 20);
        this.pageNo = MoreObjects.firstNonNull(this.pageNo, 1);
        this.offset = (pageNo - 1) * pageSize;
        this.offset = this.offset > 0 ? this.offset : 0;

        return offset;
    }
}
