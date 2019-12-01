package com.dailuobo.ic.api.base;

import java.io.Serializable;

/**
 * @author wangshifeng
 * @date 2019-08-08 16:16
 */
public class ICBaseRequest implements Serializable {

    /** serialVersionUID **/
    private static final long serialVersionUID = -1L;

    /**
     * 参数校验
     *
     * @return
     */
    public boolean checkParams() {
        return true;
    }
}
