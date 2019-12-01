package com.mallcai.domain.product.request;

import com.mallcai.domain.RequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * QueryOneProductTopicRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 20:14<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("查询一个主题信息")
public class QueryOneProductTopicRequest extends RequestEntity implements Serializable {
    private static final long serialVersionUID = 793319846503347504L;

    @ApiModelProperty("主题 ID")
    private Integer id;

    @Override
    public boolean checkParam() {
        notNull(id, "id");
        return true;
    }
}
