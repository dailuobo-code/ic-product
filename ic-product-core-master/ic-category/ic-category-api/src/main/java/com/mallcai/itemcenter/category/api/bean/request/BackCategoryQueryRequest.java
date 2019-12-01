package com.mallcai.itemcenter.category.api.bean.request;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import lombok.*;

import java.util.Set;

/**
 * BackCategoryQueryByIdRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:12<br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackCategoryQueryRequest extends AbstractRequest {
    private static final long serialVersionUID = -4369455807615676646L;

    /**
     * id
     */
    private Long id;
    /**
     * 父级id
     */
    private Long pid;

    /**
     * id列表
     */
    private Set<Long> ids;
}
