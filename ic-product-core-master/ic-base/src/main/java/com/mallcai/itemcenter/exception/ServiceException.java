package com.mallcai.itemcenter.exception;

/**
 * ServiceException
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 15:48<br/>
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 657378777056762471L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
