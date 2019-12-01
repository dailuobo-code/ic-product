package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.product.MgrMerchantCityProductService;
import com.dailuobo.biz.manager.product.ProductManager;
import com.dailuobo.biz.service.ICResponseHandler;
import com.dailuobo.ic.domain.dao.model.product.ProductGroupItemDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.api.model.dubbo.IMerchantService;
import com.mallcai.api.model.merchant.OpMerchantDetailInfo;
import com.mallcai.api.model.merchant.OpMerchantDetailInfoLists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.backend.common.api.Response;
import com.mallcai.bs.service.*;
import com.mallcai.service.openapi.IProductService;
import com.mallcai.service.request.product.ProductThirdPartyAttrQueryRequest;
import com.mallcai.service.vo.attr.ProductThirdPartyAttr;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service("mgrMerchantCityProductService")
public class MgrMerchantCityProductServiceImpl implements MgrMerchantCityProductService {

    @Autowired
    private CityProductService cityProductService;

    @Autowired
    private SpecService specService;

    @Autowired
    private SOACityGlobalService SOACityGlobalService;

    @Autowired
    private SOAHPService soaHPService;

    @Autowired
    private MultiBargainService multiBargainService;

    @Autowired
    private MarketingInventoryService marketingInventoryService;

    @Autowired
    private CityService cityService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IProductService productService;

    public ICResponse<List<CityProduct>> selectAll(Integer cityId, Map<String, Object> param, Integer offset, Integer limit) {
        Map param1 = Maps.newHashMap();
        param1.put("cityId", cityId);
        param1.put("param", param);
        param1.put("offset", offset);
        param1.put("limit", limit);

        return ICResponseHandler.template(() -> {
            List<Integer> merchantIdList = new ArrayList<>();
            OpMerchantDetailInfo info = new OpMerchantDetailInfo();
            info.setCityId(cityId);
            PlainResult<OpMerchantDetailInfoLists> opMerchantDetailInfoListsPlainResult = merchantService.selectMerchantInfoLists(info);
            if (opMerchantDetailInfoListsPlainResult == null || opMerchantDetailInfoListsPlainResult.getData() == null
                    || CollectionUtils.isEmpty(opMerchantDetailInfoListsPlainResult.getData().getMerchantStoreInfos())) {
                return ICResponse.fail("查找不到商户信息");
            }
            List<OpMerchantDetailInfo> list = opMerchantDetailInfoListsPlainResult.getData().getMerchantStoreInfos();
            list.forEach(detailInfo -> merchantIdList.add(detailInfo.getMerchantId()));
            param.put("merchantIdList", merchantIdList);

            if (offset != null) {
                PageHelper.startPage(offset / limit + 1, limit);
            }
            List<CityProduct> cityProducts = cityProductService.selectAll(param);
            PageInfo<CityProduct> pageInfo = new PageInfo<>(cityProducts);

            if (CollectionUtils.isNotEmpty(cityProducts)) {
                List<Integer> resultMerchantIdList = new ArrayList<>();
                List<Integer> cityProductIdList = new ArrayList<>();
                cityProducts.forEach(cityProduct -> {
                    resultMerchantIdList.add(cityProduct.getMerchantId());
                    cityProductIdList.add(cityProduct.getCityProductId());
                });
                PlainResult<OpMerchantDetailInfoLists> opMerchantDetailInfoListsPlainResult1 = merchantService.selectMerchantInfoListsByMerchantIds(resultMerchantIdList);
                Map<Integer, String> merchantMap = Maps.newHashMap();
                if (opMerchantDetailInfoListsPlainResult1 != null && opMerchantDetailInfoListsPlainResult1.getData() != null && !CollectionUtils.isEmpty(opMerchantDetailInfoListsPlainResult1.getData().getMerchantStoreInfos())) {
                    List<OpMerchantDetailInfo> merchantStoreInfos = opMerchantDetailInfoListsPlainResult1.getData().getMerchantStoreInfos();
                    merchantStoreInfos.forEach(detailInfo -> merchantMap.put(detailInfo.getMerchantId(), detailInfo.getMerchantName()));
                }
                ProductThirdPartyAttrQueryRequest queryRequest = new ProductThirdPartyAttrQueryRequest();
                queryRequest.setCityProductIds(cityProductIdList);
                Response<Map<Integer, ProductThirdPartyAttr>> partyResponse = productService.getThirdPartyAttrByCityProductIds(queryRequest);
                cityProducts.forEach(cityProduct -> {
                    cityProduct.setMerchantName(merchantMap.get(cityProduct.getMerchantId()));
                    if (partyResponse != null && partyResponse.getData() != null) {
                        Map<Integer, ProductThirdPartyAttr> parthMap = partyResponse.getData();
                        ProductThirdPartyAttr partyAttr = parthMap.get(cityProduct.getCityProductId());
                        if (partyAttr != null) {
                            cityProduct.setSaleTime(partyAttr.getStartHour() + "-" + partyAttr.getEndHour());
                            cityProduct.setProductDesc(partyAttr.getProductDesc());
                        }
                    }
                });
            }
            return ICResponse.success(cityProducts, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectAll", param1);

    }

}
