package com.dailuobo.biz.service.specification;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.*;
import com.dailuobo.api.specification.SpecificationService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mapper.SpecMapper;
import com.mallcai.bs.service.SOACityGlobalService;
import com.mallcai.bs.service.SpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("specificationService")
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecService specService;
    @Autowired
    private SOACityGlobalService soaCityGlobalService;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private CityProductMapper cityProductMapper;

    @Transactional
    public ICResponse<Spec> getDefaultSpec(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            Spec defaultSpec = specService.getDefaultSpec(cityProductId);
            if (defaultSpec == null) {
                log.warn("##getDefaultSpec result is null,param=>cityProductId:{}", cityProductId);
            }
            return ICResponse.success(defaultSpec);
        }, "getDefaultSpec", cityProductId);


    }

    @Transactional
    public ICResponse<SpecAdjustment> getSpecAdjustment(Integer cityId, Integer storeId, Integer cityProductId) {
        Map param1 = Maps.newHashMap();
        param1.put("cityProductId", cityProductId);
        param1.put("cityId", cityId);
        param1.put("storeId", storeId);
        return ICResponseHandler.template(() -> {
            SpecAdjustment specAdjustment = specService.getSpecAdjustment(cityId, storeId, cityProductId);
            if (specAdjustment == null) {
                log.warn("##getSpecAdjustment result is null,param=>cityId:{},storeId:{},cityProductId:{}",cityId,storeId, cityProductId);
            }
            return ICResponse.success(specAdjustment);
        }, "getSpecAdjustment", param1);


    }

    public ICResponse<List<Spec>> getSpecs(Integer cityId, Integer cityProductId) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("cityProductId", cityProductId);
        return ICResponseHandler.template(() -> {
            List<Spec> specs = specService.getSpecs(cityId, cityProductId);
            return ICResponse.success(specs);
        }, "getSpecs", param1);


    }

    @Transactional
    public ICResponse createOrUpdate(CityProduct cityProduct, Spec spec) {
        Map param1 = Maps.newHashMap();
        param1.put("cityProduct", cityProduct);
        param1.put("spec", spec);

        return ICResponseHandler.template(() -> {
            specService.createOrUpdate(cityProduct, spec);
            return ICResponse.success();
        }, "createOrUpdate", param1);


    }

    @Transactional
    public ICResponse<List<StoreSpec>> getStoreSpecs(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.offsetPage(offset, limit);
            }
            List<StoreSpec> storeSpecs = specService.getStoreSpecs(param);
            //int pageSize, long totalNumber, int currentPage

            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) storeSpecs).getTotal(), offset / limit + 1);
                return ICResponse.success(storeSpecs, pageDTO);
            }


            return ICResponse.success(storeSpecs);
        }, "getStoreSpecs", param);


    }

    @Transactional
    public ICResponse updateDefaultSpec(CityProduct cityProduct, Spec spec) {
        Map param1 = Maps.newHashMap();
        param1.put("cityProduct", cityProduct);
        param1.put("spec", spec);

        return ICResponseHandler.template(() -> {
            specService.updateDefaultSpec(cityProduct, spec);
            return ICResponse.success();
        }, "updateDefaultSpec", param1);


    }

    @Transactional
    public ICResponse updateAdjustmentFactor(Integer cityId, Integer storeId, Integer cityProductId, Integer unify, Double delta, Integer upperLimit, Integer lowerLimit, Integer userId) {
        Map param = Maps.newHashMap();
        param.put("storeId", storeId);
        param.put("cityProductId", cityProductId);
        param.put("cityId", cityId);
        param.put("unify", unify);
        param.put("delta", delta);
        param.put("upperLimit", upperLimit);
        param.put("lowerLimit", lowerLimit);
        param.put("userId", userId);
        return ICResponseHandler.template(() -> {
            specService.updateAdjustmentFactor(cityId, storeId, cityProductId, unify, delta, upperLimit, lowerLimit, userId);
            return ICResponse.success();
        }, "updateAdjustmentFactor", param);


    }

    @Transactional
    public ICResponse updateDefaultVipCount(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            specService.updateDefaultVipCount(param);
            return ICResponse.success();
        }, "updateDefaultVipCount", param);

    }

    @Transactional
    public ICResponse<Spec> getDefaultSpecBySyncProduct(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            Spec defaultSpecBySyncProduct = specService.getDefaultSpecBySyncProduct(cityProductId);
            return ICResponse.success(defaultSpecBySyncProduct);
        }, "getDefaultSpecBySyncProduct", cityProductId);


    }

    public ICResponse syncProductSpecCreate(Spec spec) {
        return ICResponseHandler.template(() -> {
            specService.syncProductSpecCreate(spec);
            return ICResponse.success();
        }, "syncProductSpecCreate", spec);
    }


    public ICResponse syncProductSpecUpdate(Spec spec) {
        return ICResponseHandler.template(() -> {
            specService.syncProductSpecUpdate(spec);
            return ICResponse.success();
        }, "syncProductSpecUpdate", spec);


    }

    @Override
    public ICResponse updateSalesSpec(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            soaCityGlobalService.updateSalesSpec(param);
            return ICResponse.success();
        }, "updateSalesSpec", param);


    }

    @Transactional
    public ICResponse updateSaleTypeInfo(SaleTypeInfo saleTypeInfo) {
        return ICResponseHandler.template(() -> {
            Spec spec = specMapper.getDefaultSpec(saleTypeInfo.getCityProductId());
            final Integer packageMaxWeight = saleTypeInfo.getPackageMaxWeight();
            final Integer packageQuantity = saleTypeInfo.getPackageQuantity();
            spec.setPackageMaxWeight(packageMaxWeight);
            spec.setPackageQuantity(packageQuantity);
            if (spec.getChangeFlag() == 2) {
                //如果是标品 售价等于折合价*每份数量
                spec.setRealPrice(spec.getAvgPrice() * packageQuantity);
            } else {
                //如果是称重商品，售价等于折合价*称重量
                BigDecimal realPrice = new BigDecimal(spec.getAvgPrice() * 100)
                        .divide(new BigDecimal(500))
                        .multiply(new BigDecimal(packageMaxWeight))
                        .divide(new BigDecimal(100));
                spec.setRealPrice(realPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            specMapper.updateDefaultSpec(spec);
            return ICResponse.success();
        }, "updateSaleTypeInfo", saleTypeInfo);
    }
}
