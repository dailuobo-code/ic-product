package com.mallcai.biz.product.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.exception.ServiceException;
import com.mallcai.biz.product.converter.ProductConverter;
import com.mallcai.biz.product.dao.mapper.AuditBillMapper;
import com.mallcai.biz.product.dao.mapper.AuditItemMapper;
import com.mallcai.biz.product.dao.mapper.TblHqProductMapper;
import com.mallcai.biz.product.model.AuditBillDO;
import com.mallcai.biz.product.model.AuditItemDO;
import com.mallcai.domain.dataobject.product.TblHqProduct;
import com.mallcai.domain.product.dto.AuditResultDTO;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.enums.AuditStatus;
import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.open.api.model.product.ProductBaseDTO;
import com.mallcai.open.api.model.product.ProductReviewRespDTO;
import com.mallcai.open.api.model.product.ProductSubmitReviewReqDTO;
import com.mallcai.open.api.service.product.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AuditBillManager
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 20:04<br/>
 */
@Slf4j
@Component
public class AuditBillManager {
    private final AuditBillMapper auditBillMapper;
    private final AuditItemMapper auditItemMapper;
    private final ProductConverter productConverter;
    private final TblHqProductMapper tblHqProductMapper;

    @Reference(registry = "global", timeout = 30000, check = false)
    private IProductService iProductService;

    public AuditBillManager(AuditBillMapper auditBillMapper,
                            AuditItemMapper auditItemMapper,
                            TblHqProductMapper tblHqProductMapper,
                            ProductConverter productConverter) {
        this.auditBillMapper = auditBillMapper;
        this.auditItemMapper = auditItemMapper;
        this.tblHqProductMapper = tblHqProductMapper;
        this.productConverter = productConverter;
    }

    @Transactional
    public Long submit(AuditBillDO bill, List<AuditItemDO> items, List<ProductDTO> products) {
        // 创建审批单
        auditBillMapper.create(bill);
        items.forEach(it -> {
            it.setBillId(bill.getId());
            it.setStatus(bill.getStatus());
            it.setType(bill.getType());
            it.setReply("");
        });
        auditItemMapper.creates(items);

        // 发起审批
        ProductSubmitReviewReqDTO submit = new ProductSubmitReviewReqDTO();
        submit.setReviewId(bill.getId().intValue());
        submit.setRemark(bill.getComment());
        submit.setSubmitUserName(bill.getOperator());
        List<ProductBaseDTO> baseProducts = Lists.newArrayListWithExpectedSize(products.size());
        for (ProductDTO product : products) {
            ProductBaseDTO baseProduct = productConverter.dto2review(product);
            baseProduct.setFirstClassifyId(product.getCategoryDTO().getClassifyId());
            baseProducts.add(baseProduct);
        }
        submit.setProductList(baseProducts);
        PlainResult<ProductReviewRespDTO> submitR = iProductService.submitReview(submit);
        if (!submitR.isSuccess()) {
            log.error("failed to submit {} to audit, cause: {}", submit, submitR);
            throw new ServiceException(submitR.getError());
        }

        // 回写审批流 id
        AuditBillDO updateBill = new AuditBillDO();
        updateBill.setId(bill.getId());
        updateBill.setFlowId(submitR.getResult().getWorkflowId());
        auditBillMapper.update(updateBill);
        // 更新审批审批状态
        for (ProductDTO product : products) {
            TblHqProduct updateProduct = new TblHqProduct();
            updateProduct.setHqProductId(product.getProductId());
            updateProduct.setAuditStatus(bill.getStatus());
            updateProduct.setAuditBillId(bill.getId());
            tblHqProductMapper.updateByPrimaryKeySelective(updateProduct);
        }
        return bill.getId();
    }

    @Transactional
    public void update(AuditBillDO bill, List<AuditItemDO> items) {
        auditBillMapper.update(bill);
        for (AuditItemDO it : items) {
            AuditItemDO update = new AuditItemDO();
            update.setId(it.getId());
            update.setStatus(bill.getStatus());
            update.setReply(bill.getReply());
            auditItemMapper.update(update);
        }
        // 更新审批审批状态
        for (AuditItemDO item : items) {
            TblHqProduct updateProduct = new TblHqProduct();
            updateProduct.setHqProductId(item.getItemId().intValue());
            updateProduct.setAuditStatus(bill.getStatus());
            updateProduct.setAuditBillId(bill.getId());
            tblHqProductMapper.updateByPrimaryKeySelective(updateProduct);
        }
    }

    public Map<Integer, AuditResultDTO> batchFindAuditResult(Set<Long> billIds, Set<Integer> productIds) {
        if (CollectionUtils.isEmpty(billIds) || CollectionUtils.isEmpty(productIds)) {
            return Collections.emptyMap();
        }
        // 审批单关联商品
        Set<Long> itemIds = productIds.stream().map(Integer::longValue).collect(Collectors.toSet());
        List<AuditItemDO> items = auditItemMapper.findByBillsAndItems(billIds, itemIds);
        if (items.isEmpty()) {
            return Collections.emptyMap();
        }
        // 转换
        Map<Integer, AuditResultDTO> result = Maps.newHashMap();
        for (AuditItemDO item : items) {
            AuditResultDTO dto = new AuditResultDTO();
            dto.setId(item.getId());
            dto.setProductId(item.getItemId().intValue());
            dto.setBillId(item.getBillId());
            dto.setStatus(AuditStatus.fromName(item.getStatus()));
            dto.setComment(item.getReply());
            result.put(dto.getProductId(), dto);
        }
        return result;
    }
}
