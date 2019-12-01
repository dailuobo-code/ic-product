package com.mallcai.api.product.backend;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.request.SubmitProductAuditRequest;
import com.mallcai.domain.product.request.UpdateAuditResultRequest;

/**
 * GlobalProductService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/9 19:31<br/>
 */
public interface GlobalProductService {
    /**
     * 提交商品审批
     * @param request
     * @return
     */
    ICResponse<Long> submitProductAudit(SubmitProductAuditRequest request);

    /**
     * 更新审批结果
     *
     * @param request 更新审批结果请求
     * @return 操作结果
     */
    ICResponse<Boolean> updateAuditResult(UpdateAuditResultRequest request);
}
