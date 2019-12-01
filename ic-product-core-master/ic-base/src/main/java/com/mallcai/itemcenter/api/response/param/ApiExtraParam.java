package com.mallcai.itemcenter.api.response.param;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * api
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 21:06<br/>
 */
public class ApiExtraParam extends ApiParam implements Serializable {
    private static final long serialVersionUID = 3999139117323367061L;

    @Getter
    @Setter
    private Map<String, String> extra;
}
