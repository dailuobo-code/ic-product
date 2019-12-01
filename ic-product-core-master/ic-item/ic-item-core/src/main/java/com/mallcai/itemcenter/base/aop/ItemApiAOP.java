package com.mallcai.itemcenter.base.aop;

import com.google.common.base.Throwables;
import com.mallcai.itemcenter.api.request.AbstractRequest;
import com.mallcai.itemcenter.dto.ICResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ItemApiAOP
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:03<br/>
 */
@Order(0)
@Aspect
@Component
@Slf4j
public class ItemApiAOP {
    @Pointcut("within(com.mallcai.itemcenter.item.api.facade..*)")
    public void itemApiPointcut() {
    }

    @Around("itemApiPointcut()")
    public Object processApi(ProceedingJoinPoint pjp) {
        try {
            if (pjp.getArgs()[0] instanceof AbstractRequest) {
                doApi(pjp);
            }
            return pjp.proceed();
            // } catch (ApiException apiException) {
            // return ICResponse.fail(apiException.getErrorMessage());
        } catch (Throwable throwable) {
            // todo: 日志统一处理
            log.error("failed to invoke service {}, cause: {}", pjp.getSignature().toString(), Throwables.getStackTraceAsString(throwable));
            return ICResponse.fail(throwable.getMessage());
        }
    }

    private void doApi(ProceedingJoinPoint joinPoint) {
        // 1. check params
        AbstractRequest request = (AbstractRequest) joinPoint.getArgs()[0];
        request.checkParam();
        // 2. todo: 埋点、审计、限流等
    }
}
