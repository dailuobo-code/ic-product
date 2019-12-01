package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.HqProduct;
import com.dailuobo.api.domain.entity.HqProductTax;
import com.dailuobo.api.domain.vo.HqProductCheckVo;
import com.dailuobo.api.product.MgrHqProductForCityService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.dailuobo.ic.api.product.bean.request.RefreshClassifyProductRequest;
import com.dailuobo.ic.api.request.product.HqProductLocalizeRequest;
import com.dailuobo.ic.api.request.product.HqProductSyncExtraAttrRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.bs.service.CityProductService;
import com.mallcai.bs.service.SOAHPService;
import com.mallcai.domain.product.dto.attr.HqProductDeliveryHomeAttr;
import com.mallcai.service.enums.ProductDeliveryModeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("mgrHqProductForCityService")
public class MgrHqProductForCityServiceImpl implements MgrHqProductForCityService {

    @Autowired
    private CityProductService cityProductService;

    @Autowired
    private MgrProductService mgrProductService;

    @Autowired
    private SOAHPService soaHPService;

    @Override
    public ICResponse<List<CityProduct>> selectHqAll(Integer cityId, Integer offset, Integer limit, Integer classifyId, Integer parentClassifyId, String productNo,
                                                     String hqProductName, String status,
                                                     Integer goodsTypeSearch) {
        Map<String, Object> param = new HashMap<>();
        param.put("cityId", cityId);
        if (classifyId != null) {
            param.put("classifyId", classifyId);
        }
        if (parentClassifyId != null) {
            param.put("parentClassifyId", parentClassifyId);
        }
        if (!StringUtils.isEmpty(productNo)) {
            param.put("productNo", productNo);
        }
        if (!StringUtils.isEmpty(hqProductName)) {
            param.put("hqProductName", hqProductName);
        }
        if (!StringUtils.isEmpty(status)) {
            param.put("status", status);
        }
        if (offset != null) {
            PageHelper.startPage(offset / limit + 1, limit);
        }
        if (goodsTypeSearch != null) {
            param.put("goodsType", goodsTypeSearch);
        }
        return ICResponseHandler.template(() -> {
            List<CityProduct> cityProducts = cityProductService.selectHqAll(param);
            appendProductExtraAttr(cityProducts);
            PageInfo<CityProduct> pageInfo = new PageInfo<>(cityProducts);
            return ICResponse.success(cityProducts, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectHqAll", param);

    }

    private void appendProductExtraAttr(List<CityProduct> cityProducts){

        List<Integer> ids = cityProducts.stream().filter(it -> Objects.equals(Integer.valueOf(it.getDeliveryMode()), ProductDeliveryModeEnum.DELIVERY_HOME.getCode())).map(CityProduct::getHqProductId).collect(Collectors.toList());
        com.mallcai.common.ICResponse<Map<Integer, HqProductDeliveryHomeAttr>> extraAttrs = mgrProductService.getHqProductDeliverExtraAttrs(ids);

        if(extraAttrs.isSuccess()){
            Map<Integer, HqProductDeliveryHomeAttr> attrDatas = extraAttrs.getData();

            cityProducts.forEach(it ->{
                if(Objects.equals(Integer.valueOf(it.getDeliveryMode()), ProductDeliveryModeEnum.DELIVERY_HOME.getCode())){
                    HqProductDeliveryHomeAttr deliveryHomeAttr = attrDatas.get(it.getHqProductId());
                    if(deliveryHomeAttr != null){
                        it.setArrivalDay(deliveryHomeAttr.getArrivalDay());
                    }
                }
            });
        }
    }

    @Override
    public ICResponse<Boolean> localize(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            cityProductService.localize(param);
            return ICResponse.success(true);
        }, "localize", param);

    }

    @Override
    public ICResponse<Boolean> localizeV2(HqProductLocalizeRequest localizeRequest) {
        return ICResponseHandler.template(() -> {
            cityProductService.localizeV2(localizeRequest);
            return ICResponse.success(true);
        }, "localizeV2", localizeRequest);
    }

    @Override
    public ICResponse<List<CityProduct>> getCityProductNosList(Map<String, Object> param) {

        return ICResponseHandler.template(() -> {
            List<CityProduct> list = cityProductService.selectCityProductNosList(param);
            return ICResponse.success(list);
        }, "getCityProductNosList", param);
    }

    @Override
    public ICResponse<Boolean> updateProductClassify(Integer oldSencondClassifyId) {
        return ICResponseHandler.template(() -> {
            soaHPService.updateProductClassify(0);
            return ICResponse.success(true);
        }, "updateProductClassify", oldSencondClassifyId);
    }

    @Override
    public ICResponse<Boolean> refreshClassifyProductCache(RefreshClassifyProductRequest request){
        return ICResponseHandler.template(() -> {
            soaHPService.refreshClassifyProductCache(request);
            return ICResponse.success(true);
        }, "RefreshClassifyProductRequest", request);
    }

    @Override
    public ICResponse<List<HqProductTax>> getProductTaxByCode(List<String> taxCode) {
        if(taxCode == null || taxCode.size() <= 0) {
            return ICResponse.fail("入参为空");
        }

        List<HqProductTax> list = soaHPService.getProductTaxByCode(taxCode);
        if(list == null || list.size() == 0) {
            return ICResponse.fail("税率编码不存在");
        }else{
            return ICResponse.success(list);
        }
    }


    // 返回类型改成List
    @Override
    public ICResponse<HqProductCheckVo> checkHqProductNotExist(List<String> productNos, List<String>
            hqProductNames) {
        HqProductCheckVo vo = new HqProductCheckVo();
        boolean haveError = false;

        if(productNos != null && productNos.size() > 0) {
            List<HqProduct> ret = soaHPService.getByProductNos(productNos);
            if(ret != null && ret.size() > 0) {
                List<String> duplicateNOs = ret.stream()
                        .map(HqProduct::getProductNo).distinct()
                        .collect(Collectors.toList());
                vo.setDuplicateHqProductNOs(duplicateNOs);
                haveError = true;
            }
        }

        if(hqProductNames != null && hqProductNames.size() > 0) {
            List<HqProduct> ret = soaHPService.getByHqProductNames(hqProductNames);
            if(ret != null && ret.size() > 0) {
                List<String> duplicateNames = ret.stream().map(HqProduct::getHqProductName)
                        .distinct().collect(Collectors.toList());
                vo.setDuplicateHqProductNames(duplicateNames);
                haveError = true;
            }
        }

        if(haveError) {
            ICResponse icResponse = ICResponse.fail("查询到已存在的总部商品");
            icResponse.setData(vo);
            return icResponse;
        }else{
            return ICResponse.success(vo);
        }
    }

    @Override
    public ICResponse<Boolean> syncHqProductExtraAttr(HqProductSyncExtraAttrRequest attrRequest) {

        return ICResponseHandler.template(() -> {
            cityProductService.syncHqProductExtraAttr(attrRequest);
            return ICResponse.success(true);
        }, "syncHqProductExtraAttr", attrRequest);
    }
}
