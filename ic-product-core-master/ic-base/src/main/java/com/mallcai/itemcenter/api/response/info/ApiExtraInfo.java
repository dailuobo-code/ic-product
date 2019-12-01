package com.mallcai.itemcenter.api.response.info;


import com.mallcai.itemcenter.api.ApiBean;
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
public class ApiExtraInfo extends ApiBean implements Serializable {
    private static final long serialVersionUID = 3999139117323367061L;

    @Getter
    @Setter
    private Map<String, String> extra;
}
