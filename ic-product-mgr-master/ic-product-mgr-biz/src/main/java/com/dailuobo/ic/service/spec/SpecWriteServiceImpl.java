package com.dailuobo.ic.service.spec;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.specification.SpecWriteService;
import com.dailuobo.ic.api.spec.SpecUpdateAsyncQueryRequest;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.service.CityProductService;
import com.mallcai.bs.service.SpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("specWriteService")
@Slf4j
public class SpecWriteServiceImpl implements SpecWriteService {


    @Autowired
    private CityProductMapper cityProductMapper;
    @Autowired
    private SpecService specService;

    @Autowired
    private CityProductService cityProductService;


    /**
     * 2019-11-04 17:05:30
     * 一期同步只会修改 throshold，DisablePrice，avgPrice,barCode
     * 其中 disablePrice， barCode 在商品模型上
     * threshold ,avgPrice 在spec上
     *
     * @param request
     * @return
     */
    @Override
    public PlainResult<Boolean> specUpdateAsy(SpecUpdateAsyncQueryRequest request) {
        try {
            log.info("##specUpdateAsy,request:{}", JSON.toJSONString(request));
            request.checkParams();
            CityProduct cityProduct = cityProductMapper.loadCityProductByItemIdAndSkuId(request.getCityId(), request.getItemId(), request.getSkuId());
            if (cityProduct == null) {
                return PlainResult.getDefaultFailed(500, "spec relation product not found");
            }
            cityProduct.setDisablePrice(request.getDisablePrice());
            cityProduct.setBarCode(request.getBarCode());
            cityProduct.setUpdateUserId(request.getSpec().getUpdateUserId());

            Spec spec = request.getSpec();
            spec.setCityProductId(cityProduct.getCityProductId());
            spec.setCityId(request.getCityId());

            //如果需要上架
            Spec defaultSpec = specService.getDefaultSpec(cityProduct.getCityProductId());
            defaultSpec.setAvgPrice(spec.getAvgPrice());
            defaultSpec.setThreshold(spec.getThreshold());
            defaultSpec.setPackageQuantity(spec.getPackageQuantity());
            defaultSpec.setPackageMaxWeight(spec.getPackageMaxWeight());
            defaultSpec.setUnitType(spec.getUnitType());
            defaultSpec.setRealPrice(spec.getRealPrice());
            defaultSpec.setUpdateUserId(spec.getUpdateUserId());
            if (Objects.equals(request.getCityStatus(), 1)) {
                cityProductService.up(cityProduct, defaultSpec);
            } else {
                cityProductService.down(request.getCityId(), cityProduct.getCityProductId(), 0, false);
                specService.updateDefaultSpec(cityProduct,defaultSpec);
            }

            return PlainResult.getDefaultSuccess(true);
        } catch (Exception ex) {
            log.error("specUpdateAsy fail", ex);
            return PlainResult.fail(ex.getMessage());
        }

    }
}
