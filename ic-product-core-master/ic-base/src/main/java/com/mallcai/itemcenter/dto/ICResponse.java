package com.mallcai.itemcenter.dto;

import com.google.common.base.MoreObjects;
import com.mallcai.itemcenter.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * ICICResponse
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 17:38<br/>
 */
public class ICResponse<T> implements Serializable {
    private static final long serialVersionUID = -750644833749014618L;

    private static final Logger logger = LoggerFactory.getLogger(ICResponse.class);

    private boolean success; //调用是否成功
    private T result;       // 如果success = true,则通过result可以获得调用结果
    private String code;    // 错误码
    private String error;   // 如果success = false,则通过error可以查看错误信息
    private String sourceIp;    // 出问题的服务IP
    private String sourceStack;     // 问题堆栈

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean errorOf(String codeToCompare) {
        return Objects.equals(code, codeToCompare);
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceStack(String sourceStack) {
        this.sourceStack = sourceStack;
    }

    public String getSourceStack() {
        return sourceStack;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }

    public void setError(String code, String error) {
        this.success = false;
        this.code = code;
        this.error = error;
    }

    public void setError(String code, String error, String sourceStack) {
        this.success = false;
        this.code = code;
        this.error = error;
        this.sourceStack = sourceStack;
    }



    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("success", success)
                .add("result", result)
                .add("code", code)
                .add("error", error)
                .add("sourceIp", sourceIp)
                .add("sourceStack", sourceStack)
                .omitNullValues()
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ICResponse)) return false;
        ICResponse other = (ICResponse) o;
        if (!Objects.equals(this.success, other.success)) return false;
        if (!Objects.equals(this.result, other.result)) return false;
        if (!Objects.equals(this.code, other.code)) return false;
        if (!Objects.equals(this.error, other.error)) return false;
        if (!Objects.equals(this.sourceIp, other.sourceIp)) return false;
        if (!Objects.equals(this.sourceStack, other.sourceStack)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = (result*PRIME) + (this.success ? 1 : 0);
        result = (result*PRIME) + (this.result == null ? 0 : this.result.hashCode());
        result = (result*PRIME) + (this.code == null ? 0 : this.code.hashCode());
        result = (result*PRIME) + (this.error == null ? 0 : this.error.hashCode());
        result = (result*PRIME) + (this.sourceIp == null ? 0 : this.sourceIp.hashCode());
        result = (result*PRIME) + (this.sourceStack == null ? 0 : this.sourceStack.hashCode());
        return result;
    }


    public static <T> ICResponse<T> ok(T data) {
        ICResponse<T> resp = new ICResponse<>();
        resp.setResult(data);
        return resp;
    }

    public static <T> ICResponse<T> ok() {
        return ICResponse.ok(null);
    }

    public static <T> ICResponse<T> fail(String error) {
        ICResponse<T> resp = new ICResponse<>();
        resp.setError(error);
        return resp;
    }

    /**
     * usage: ICResponse&lt;String&gt; resp = ICResponse.get(() -> someDAO.getSomeStringResult(), "error.code")
     * <br>
     * 这个封装有个不太好的地方，无法打印出自定义错误日志……
     *
     * @param supplier lambda
     * @param errorCode error code
     * @param <T> anything
     * @return ICResponse
     */
    public static <T> ICResponse<T> get(Supplier<T> supplier, String errorCode) {
        try {
            T result = supplier.get();
            return ICResponse.ok(result);
        } catch (Exception e) {
            logger.error("error when get ICResponse call", e);
            return ICResponse.fail(errorCode);
        }
    }

    public static <T> ICResponse<T> fail(String code, String error) {
        ICResponse<T> resp = new ICResponse<>();
        resp.setError(code, error);
        return resp;
    }

    public static <T> ICResponse<T> fail(String code, String error, String sourceStack) {
        ICResponse<T> resp = new ICResponse<>();
        resp.setError(code, error, sourceStack);
        return resp;
    }

    public ICResponse<T> code(String code) {
        this.code = code;
        return this;
    }


    public ICResponse<T> sourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
        return this;
    }

    public ICResponse<T> sourceStack(String sourceStack) {
        this.sourceStack = sourceStack;
        return this;
    }

    public static <R> R asserts(ICResponse<R> response) {
        if (!response.isSuccess()) {
            throw new ServiceException(response.getError());
        }

        return response.getResult();
    }
}
