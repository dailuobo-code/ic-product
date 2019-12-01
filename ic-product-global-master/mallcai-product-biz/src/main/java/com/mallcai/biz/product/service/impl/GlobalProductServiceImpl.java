package com.mallcai.biz.product.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mallcai.api.product.backend.GlobalProductService;
import com.mallcai.backend.common.exception.ServiceException;
import com.mallcai.biz.product.dao.mapper.AuditBillMapper;
import com.mallcai.biz.product.dao.mapper.AuditItemMapper;
import com.mallcai.biz.product.manager.AuditBillManager;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.biz.product.model.AuditBillDO;
import com.mallcai.biz.product.model.AuditItemDO;
import com.mallcai.biz.product.model.HqProductTax;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.product.request.SubmitProductAuditRequest;
import com.mallcai.domain.product.request.UpdateAuditResultRequest;
import com.mallcai.domain.enums.AuditStatus;
import com.mallcai.domain.enums.AuditType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * GlobalProductServiceImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/9/9 19:33<br/>
 */
@Slf4j
@Service
public class GlobalProductServiceImpl implements GlobalProductService {
    @Resource
    private AuditBillManager auditBillManager;
    @Resource
    private AuditBillMapper auditBillMapper;
    @Resource
    private AuditItemMapper auditItemMapper;
    @Resource
    private MgrProductServiceHelper mgrProductServiceHelper;

    /**
     * 提交商品审批
     *
     * @param request
     * @return
     */
    @Override
    public ICResponse<Long> submitProductAudit(SubmitProductAuditRequest request) {
        request.checkParam();
        try {
            AuditBillDO bill = new AuditBillDO();
            bill.setFlowId("");
            bill.setReply("");
            bill.setComment("");
            bill.setOperatorId(request.getOperatorId());
            bill.setOperator(request.getOperator());
            bill.setComment(request.getComment());
            bill.setStatus(AuditStatus.AUDITING.name());
            bill.setType(AuditType.HQ_PRODUCT_CREATE.name());

            List<ProductDTO> productList = Lists.newArrayList();
            List<AuditItemDO> items = Lists.newArrayList();
            Set<Integer> taxIds = Sets.newHashSet();
            List<ProductDTO> products = mgrProductServiceHelper.getProduct(request.getProductIds());
            if (products.isEmpty()) {
                log.warn("product not found by id: {}", request.getProductIds());
                return ICResponse.fail("商品不存在");
            }

            for (ProductDTO product : products) {
                productList.add(product);
                AuditItemDO item = new AuditItemDO();
                item.setItemId(product.getProductId().longValue());
                items.add(item);
                taxIds.add(product.getProductTaxId());
            }

            Map<Integer, HqProductTax> taxCodeMap = mgrProductServiceHelper.getProductTaxesMapByCodeList(new ArrayList<>(taxIds));
            productList.forEach(it -> {
                if (taxCodeMap.containsKey(it.getProductTaxId())) {
                    HqProductTax tax = taxCodeMap.get(it.getProductTaxId());
                    it.setTaxClassifyCode(tax.getTaxClassifyCode());
                    it.setProductRate(tax.getTaxRate());
                }
            });

            Long id = auditBillManager.submit(bill, items, productList);
            return ICResponse.success(id);
        } catch (ServiceException e) {
            log.error("fail to submitProductAudit by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to submitProductAudit by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("提交商品审批失败");
        }
    }

    /**
     * 更新审批结果
     *
     * @param request 更新审批结果请求
     * @return 操作结果
     */
    @Override
    public ICResponse<Boolean> updateAuditResult(UpdateAuditResultRequest request) {
        request.checkParam();
        try {
            AuditBillDO bill = auditBillMapper.findById(request.getId());
            if (bill == null) {
                log.error("bill not found by {}", request);
                return ICResponse.fail("没有对应审批单");
            }
            if (Objects.equals(bill.getStatus(), AuditStatus.NORMAL.name())) {
                log.warn("bill status abnormal, bill={}, request={}", bill, request);
                return ICResponse.success(false);
            }
            bill.setReply(request.getMessage());
            bill.setStatus(request.getStatus().name());

            List<AuditItemDO> items = auditItemMapper.findByBill(bill.getId());
            auditBillManager.update(bill, items);
            return ICResponse.success();
        } catch (ServiceException e) {
            log.error("fail to updateAuditResult by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to updateAuditResult by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("更新审批单状态失败");
        }
    }
}
