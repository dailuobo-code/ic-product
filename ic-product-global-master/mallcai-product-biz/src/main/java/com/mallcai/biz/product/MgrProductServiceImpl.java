package com.mallcai.biz.product;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.biz.product.converter.ServiceFeeTemplateConverter;
import com.mallcai.biz.product.manager.AuditBillManager;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.biz.product.manager.ServiceFeeDomainService;
import com.mallcai.biz.product.model.HqProductTax;
import com.mallcai.common.ICResponse;
import com.mallcai.common.PageDTO;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.dataobject.product.TblHqProduct;
import com.mallcai.domain.product.dto.AuditResultDTO;
import com.mallcai.domain.product.dto.HqProductExtraAttrDTO;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.product.dto.ServiceFeeTemplateDTO;
import com.mallcai.domain.product.dto.attr.HqProductDeliveryHomeAttr;
import com.mallcai.domain.product.request.*;
import com.mallcai.domain.enums.HqProductDeliveryModeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service("mgrProductService")
public class MgrProductServiceImpl implements MgrProductService {

    private static final Logger logger = LoggerFactory.getLogger(MgrProductServiceImpl.class);

    @Resource
    private ServiceFeeTemplateConverter serviceFeeTemplateConverter;

    @Resource
    private ServiceFeeDomainService serviceFeeDomainService;

    @Resource
    private MgrProductServiceHelper mgrProductServiceHelper;

    @Resource
    private AuditBillManager auditBillManager;

    @Override
    public ICResponse<Boolean> addProduct(AddProductRequest addProductRequest) {
        try {
            if (addProductRequest == null) {
                return ICResponse.fail("无效参数");
            }
            if (addProductRequest.getProductType() == null) {
                addProductRequest.setProductType(1);
            }
            if(Objects.equals(HqProductDeliveryModeEnum.DELIVERY_HOME.getCode(),addProductRequest.getDeliveryMode())
                && addProductRequest.getArrivalDay() == null){
                return ICResponse.fail("无效发货时间");
            }
            boolean isSuccess = mgrProductServiceHelper.addProduct(addProductRequest);
            if (!isSuccess) {
                return ICResponse.fail("保存到DB失败");
            }
        } catch (Exception ex) {
            logger.error("addProduct ex,param:{}", JSON.toJSONString(addProductRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
        return ICResponse.success(true);
    }

    @Override
    public ICResponse<List<ProductDTO>> getProductList(GetProductListRequest getProductListRequest) {
        try {
            if (getProductListRequest == null) {
                return ICResponse.fail("无效参数");
            }
            if (getProductListRequest.getProductType() == null) {
                getProductListRequest.setProductType(1);
            }
            List<TblHqProduct> getProductList = mgrProductServiceHelper.getProductList(getProductListRequest);
            if (CollectionUtils.isEmpty(getProductList)) {
                return ICResponse.success(Collections.emptyList());
            }
            // 获取商品状态
            Map<Integer, AuditResultDTO> auditResults = getAuditResult(getProductList);
            // 获取税率
            List<ProductDTO> productList = Lists.newArrayList();
            Set<Integer> catIds = Sets.newHashSet();
            List<Integer> productTaxIdList = new ArrayList<>();
            getProductList.forEach(p -> {
                productList.add(mgrProductServiceHelper.buildProductDTO(p));
                catIds.add(p.getClassifyId());
                if (p.getProductTaxId() != null) {
                    productTaxIdList.add(p.getProductTaxId());
                }
            });
            Map<Integer, HqProductTax> map = mgrProductServiceHelper.getProductTaxesMapByCodeList(productTaxIdList);
            List<CategoryDTO> catResult = mgrProductServiceHelper.getCategoryByIds(Lists.newArrayList(catIds));
            Map<Integer, CategoryDTO> catId2Dto = catResult.stream().collect(Collectors.toMap(CategoryDTO::getClassifyId, Function.identity()));
            productList.forEach(productDTO -> {
                productDTO.setCategoryDTO(catId2Dto.get(productDTO.getCategoryId()));
                //税率
                if (productDTO.getProductTaxId() != null) {
                    HqProductTax hqProductTax = map.get(productDTO.getProductTaxId());
                    if (hqProductTax != null) {
                        productDTO.setProductTaxId(hqProductTax.getId());
                        productDTO.setProductRate(hqProductTax.getTaxRate());
                        productDTO.setTaxClassifyCode(hqProductTax.getTaxClassifyCode());
                    }
                }
                // 审批结果
                productDTO.setAuditResult(auditResults.get(productDTO.getProductId()));
            });

            //商品属性赋值
            mgrProductServiceHelper.appendProductExtra(productList);

            log.warn("getProductList>>{}",JSON.toJSONString(productList));
            PageInfo<TblHqProduct> page = new PageInfo<>(getProductList);
            return ICResponse.success(productList, new PageDTO(getProductListRequest.getPageSize(), (int) page.getTotal(), getProductListRequest.getCurrentPage()));
        } catch (Exception ex) {
            logger.error("getProductList ex,param:{}", JSON.toJSONString(getProductListRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    private Map<Integer, AuditResultDTO> getAuditResult(List<TblHqProduct> getProductList) {
        Set<Long> billIds = Sets.newHashSet();
        Set<Integer> ids = Sets.newHashSet();
        for (TblHqProduct product : getProductList) {
            if (product.getAuditBillId() != null && product.getAuditBillId() != 0) {
                billIds.add(product.getAuditBillId());
                ids.add(product.getHqProductId());
            }
        }
        return auditBillManager.batchFindAuditResult(billIds, ids);
    }

    @Override
    public ICResponse<Boolean> existedProduct(ExistedProductRequest existedProductRequest) {
        try {
            if (existedProductRequest == null) {
                return ICResponse.fail("无效参数");
            }
            boolean isExisted = mgrProductServiceHelper.existedProduct(existedProductRequest);
            return ICResponse.success(isExisted);
        } catch (Exception ex) {
            logger.error("existedProduct ex,param:{}", JSON.toJSONString(existedProductRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> modifyProductName(ModifyProductNameRequest modifyProductNameRequest) {
        try {
            if (modifyProductNameRequest == null) {
                return ICResponse.fail("无效参数");
            }
            mgrProductServiceHelper.modifyProductName(modifyProductNameRequest);
            return ICResponse.success(true);
        } catch (Exception ex) {
            logger.error("modifyProductName ex,param:{}", JSON.toJSONString(modifyProductNameRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public ICResponse<Boolean> modifyProduct(ModifyProductRequest modifyProductRequest) {
        try {
            if (modifyProductRequest == null) {
                return ICResponse.fail("无效参数");
            }
            Integer productIds = modifyProductRequest.getProductId();
            ProductDTO product = mgrProductServiceHelper.getProduct(productIds);
            if (Objects.equals(product.getLocalizeFlag(), 1)
                    && modifyProductRequest.getDeliveryMode() != null
                    && !Objects.equals(product.getDeliveryMode(),modifyProductRequest.getDeliveryMode())) {
                return ICResponse.fail("商品已被本市化，无法修改配送方式");
            }
            if(Objects.equals(HqProductDeliveryModeEnum.DELIVERY_HOME.getCode(),modifyProductRequest.getDeliveryMode())
                    && modifyProductRequest.getArrivalDay() == null){
                return ICResponse.fail("无效配送方式设置");
            }
            //ProductDTO dto = mgrProductServiceHelper.getProduct(modifyProductRequest.getProductId());
            mgrProductServiceHelper.updateProduct(modifyProductRequest);
            //mgrProductServiceHelper.updateProductClassify(dto.getCategoryId());
            return ICResponse.success(true);
        } catch (Exception ex) {
            logger.error("modifyProductRequest ex,param:{}", JSON.toJSONString(modifyProductRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public ICResponse<Boolean> localizeProduct(LocalizeProductRequest localizeRequest) {
        try {
            if (localizeRequest == null || CollectionUtils.isEmpty(localizeRequest.getProductIds())) {
                return ICResponse.fail("无效参数");
            }
            mgrProductServiceHelper.localizeProduct(localizeRequest);
            return ICResponse.success(true);
        } catch (Exception ex) {
            logger.error("localizeProduct ex,param:{}", JSON.toJSONString(localizeRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }
    @Override
    public ICResponse<Boolean> modifyProductStatus(ModifyProductStatusRequest modifyProductStatusRequest) {
        try {
            if (modifyProductStatusRequest == null) {
                return ICResponse.fail("无效参数");
            }
            mgrProductServiceHelper.modifyProductStatus(modifyProductStatusRequest);
            return ICResponse.success(true);
        } catch (Exception ex) {
            logger.error("modifyProductStatus ex,param:{}", JSON.toJSONString(modifyProductStatusRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> delete(List<Integer> productIds) {
        try {
            if (CollectionUtils.isEmpty(productIds)) {
                return ICResponse.fail("无效参数");
            }
            List<ProductDTO> products = mgrProductServiceHelper.getProduct(productIds);
            Optional<ProductDTO> localizeTrue = products.stream().filter(e -> Objects.equals(1, e.getLocalizeFlag())).findAny();
            if (localizeTrue.isPresent()) {
                ProductDTO localizeProduct = localizeTrue.get();
                return ICResponse.fail("商品<" + localizeProduct.getProductName() +"> 已被本市化，无法删除");
            }
            mgrProductServiceHelper.delete(productIds);
            //mgrProductServiceHelper.updateProductClassify(0);
            return ICResponse.success(true);
        } catch (Exception ex) {
            logger.error("delete ex,param:{}", JSON.toJSONString(productIds), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<ProductDTO> getProduct(Integer hqProductId) {
        try {
            if (hqProductId == null) {
                return ICResponse.fail("无效参数");
            }
            List<ProductDTO> getProductList = mgrProductServiceHelper.getProduct(Lists.newArrayList(hqProductId));
            if (CollectionUtils.isEmpty(getProductList)) {
                return ICResponse.success();
            }
            return ICResponse.success(getProductList.get(0));
        } catch (Exception ex) {
            logger.error("getProduct ex,param:{}", hqProductId, ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<HqProductVo> addHqProduct(HqProductVo hqProductVo) {
        if(hqProductVo.getThirdPlatType() == null) {
            hqProductVo.setThirdPlatType(0);
        }

        HqProductVo vo = mgrProductServiceHelper.addHqProduct(hqProductVo);
        if (vo.getHqProductId() == null || vo.getHqProductId() <= 0) {
            return ICResponse.fail("创建总部商品失败，productNO:" + hqProductVo.getProductNo());
        } else {
            return ICResponse.success(vo);
        }

    }

    @Override
    public ICResponse<PddProductAttr> addPddProductAttr(PddProductAttr pddProductAttr) {
        PddProductAttr attr = mgrProductServiceHelper.addPddProductAttr(pddProductAttr);
        if(attr.getId() == null || attr.getId() <= 0) {
            return ICResponse.fail("创建拼多多商品附加属性失败，productNO:" + attr.getProductNo());
        }else{
            return ICResponse.success(attr);
        }
    }

    @Override
    public ICResponse<List<PddProductAttr>> getPddProductAttrByPddGoodIds(List<String> goodIds) {
        return ICResponse.success(mgrProductServiceHelper.getPddProductAttrByPddGoodIds(goodIds));
    }

    @Override
    public ICResponse<Boolean> updateServiceFeeTemplate(ModifyServiceFeeTemplateRequest request) {
        if (request.getId() == null) {
            logger.error("id cannot be null, {}", request);
        }
        ServiceFeeTemplateDTO update = serviceFeeTemplateConverter.request2dto(request);
        Boolean r = serviceFeeDomainService.updateServiceFeeTemplate(update);
        return ICResponse.success(r);
    }

    @Override
    public ICResponse<List<ProductDTO>> queryProductByProductNos(List<String> productNoList) {
        List<ProductDTO> hqProductByProductNoList = mgrProductServiceHelper.getHqProductByProductNoList(productNoList);
        return ICResponse.success(hqProductByProductNoList);
    }


    @Override
    public ICResponse<List<ProductDTO>> queryProductByKeyWord(PagingHqProductSearchQueryRequest request) {
        ICResponse<List<ProductDTO>> productDTOS = mgrProductServiceHelper.selectHqProductByKeyWord(request);
        return productDTOS;
    }

    @Override
    public ICResponse<Map<Integer, HqProductDeliveryHomeAttr>> getHqProductDeliverExtraAttrs(List<Integer> hqProductIds) {
        if (CollectionUtils.isEmpty(hqProductIds)) {
            return ICResponse.fail("商品id不能为空");
        }
        Map<Integer, List<HqProductExtraAttrDTO>> attrListMap = mgrProductServiceHelper.getProductExtraAttrs(hqProductIds);
        Map<Integer, HqProductDeliveryHomeAttr> retMap = new HashMap<>(hqProductIds.size());
        for (Integer hqProductId : hqProductIds) {
            List<HqProductExtraAttrDTO> list = attrListMap.get(hqProductId);
            for (HqProductExtraAttrDTO attr : list) {
                if (attr instanceof HqProductDeliveryHomeAttr) {
                    retMap.put(hqProductId, (HqProductDeliveryHomeAttr) attr);
                }
            }
        }
        return ICResponse.success(retMap);
    }
}
