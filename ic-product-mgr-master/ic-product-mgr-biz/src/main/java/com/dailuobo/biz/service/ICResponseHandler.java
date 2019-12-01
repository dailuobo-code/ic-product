package com.dailuobo.biz.service;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.ResponseCodeEnum;
import com.dailuobo.biz.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.Security;

@Slf4j
public class ICResponseHandler {

    public static <T> ICResponse<T> template(ICResponseCallback<T> callback, String name, Object... args) {
        try {
            return callback.handle();
        } catch (BusinessException e) {
            ResponseCodeEnum errorCode = e.getErrorCode();
            String errorMsg = e.getErrorMsg();
            if (errorCode == null) {
                errorCode = ResponseCodeEnum.FAIL;
            }
            if (StringUtils.isBlank(errorMsg)) {
                errorMsg = errorCode.getMsg();
            }
            return ICResponse.fail(errorCode.getCode(), errorMsg);
        } catch (Exception ex) {
            if (StringUtils.isBlank(name)) {
                name = "template";
            }
            String params = StringUtils.EMPTY;
            if (ArrayUtils.isNotEmpty(args)) {
                params = JSON.toJSONString(args);
            }
            log.error("{} handle happen exception,param={}", name, params, ex);
            return ICResponse.fail("系统错误:" + ex.getMessage());
        }
    }

    public interface ICResponseCallback<T> {
        ICResponse<T> handle() throws Exception;
    }
}
