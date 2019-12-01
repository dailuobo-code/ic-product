package com.dailuobo.biz.exception;

import com.dailuobo.api.common.ResponseCodeEnum;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangshifeng
 * @date 2019-08-09 10:35
 */
@Getter
public class BusinessException extends Exception {

    /**
     * 错误码
     */
    private ResponseCodeEnum errorCode;

    /**
     * 错误消息
     */
    private String errorMsg;

    private static final long serialVersionUID = -8213943026163641747L;

    public BusinessException(ResponseCodeEnum errorCode) {
        super();
        this.errorCode = errorCode;
        if (errorCode != null) {
            this.errorMsg = errorCode.getMsg();
        }
    }

    public BusinessException(ResponseCodeEnum errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        if (errorCode != null) {
            this.errorMsg = errorCode.getMsg();
        }
        if (StringUtils.isNotBlank(errorMsg)) {
            this.errorMsg = errorMsg;
        }
    }

}
