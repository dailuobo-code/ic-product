package com.mallcai.biz.product.service;

import com.alibaba.fastjson.JSON;
import com.mallcai.api.product.backend.ItemWriteFacade;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.biz.product.model.HqProductCheckDO;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.product.request.AddProductRequest;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemCreateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemDeleteRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemUpdateStatusRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.param.ItemParam;
import com.mallcai.itemcenter.item.api.bean.request.item.param.SkuParam;
import com.mallcai.service.request.HqProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("mgrItemWriteFacade")
public class ItemWriteService implements ItemWriteFacade {

    @Autowired
    com.mallcai.itemcenter.item.api.facade.ItemWriteFacade itemWriteFacade;

    @Autowired
    MgrProductService mgrProductService;

    @Autowired
    private MgrProductServiceHelper mgrProductServiceHelper;

    @Override
    public ICResponse<Long> create(ItemCreateRequest request) {
        try {
            if (request == null) {
                throw new IllegalArgumentException("request is null");
            }
            log.info("##create request param:{}", JSON.toJSONString(request));
            ItemParam itemParam = request.getItemParam();
            request.setSellerId(itemParam.getSellerId());
            request.setSellerName(itemParam.getSellerName());
            request.checkParam();
            //todo check request skuNo
            List<ProductDTO> productDTOS = checkProductDump(request);
            if (CollectionUtils.isNotEmpty(productDTOS)) {
                throw new IllegalArgumentException(String.format(" 检查名称/barcode，sku_code是否重复创建，%s", JSON.toJSONString(productDTOS)));
            }
            return itemWriteFacade.create(request);
        } catch (Exception ex) {
            log.error("create ex", ex);
            return ICResponse.fail(ex.getMessage());
        }

    }


    private List<ProductDTO> checkProductDump(ItemCreateRequest request) {
        List<HqProductCheckDO> list = request.getSkuParamList().stream().map(sku -> {
            HqProductCheckDO hqProductCheckDO = new HqProductCheckDO();
            hqProductCheckDO.setSkuName(sku.getName());
            hqProductCheckDO.setBarCode(sku.getBarcode());
            hqProductCheckDO.setSkuCode(sku.getSkuCode());
            return hqProductCheckDO;
        }).collect(Collectors.toList());
        List<ProductDTO> productDTOS = mgrProductServiceHelper.checkDumpHqProduct(list);
        return productDTOS;
    }

    @Override
    public ICResponse<Boolean> update(ItemUpdateRequest request) {
        log.info("##update request param:{}", JSON.toJSONString(request));
        ICResponse<Boolean> update = itemWriteFacade.update(request);
        return update;
    }

    @Override
    public ICResponse<Boolean> delete(ItemDeleteRequest request) {
        log.info("##delete request param:{}", JSON.toJSONString(request));
        ICResponse<Boolean> delete = itemWriteFacade.delete(request);
        return delete;
    }

    @Override
    public ICResponse<Boolean> sellerUpdateStatus(ItemUpdateStatusRequest request) {
        log.info("##sellerUpdateStatus request param:{}", JSON.toJSONString(request));
        ICResponse<Boolean> booleanICResponse = itemWriteFacade.sellerUpdateStatus(request);

        return booleanICResponse;
    }
}
