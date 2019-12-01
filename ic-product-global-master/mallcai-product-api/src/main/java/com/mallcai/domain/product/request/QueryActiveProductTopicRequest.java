package com.mallcai.domain.product.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * QueryProductTopicRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 20:15<br/>
 */
@Data
@ApiModel("查询生效的商品主题")
public class QueryActiveProductTopicRequest implements Serializable {
    private static final long serialVersionUID = -6006921517211664114L;

    public static QueryActiveProductTopicRequest instance() {
        return new QueryActiveProductTopicRequest();
    }
}
