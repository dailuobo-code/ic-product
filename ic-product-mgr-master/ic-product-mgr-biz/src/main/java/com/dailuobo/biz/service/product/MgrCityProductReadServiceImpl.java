package com.dailuobo.biz.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ResponseCodeEnum;
import com.dailuobo.api.domain.dto.merchant.MerchantProductMarketDTO;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.MerchantCityProduct;
import com.dailuobo.api.domain.entity.ProductClassify;
import com.dailuobo.ic.api.product.service.MgrCityProductReadService;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.ic.api.request.product.CityProductSearchRequest;
import com.dailuobo.ic.api.request.product.MerchantProductMarketRequest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.api.SOAStandardPage;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.LoggerUtils;
import com.mallcai.backend.common.utils.PageUtil;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.dao.ClassifyDao;
import com.mallcai.bs.dao.HqProductDao;
import com.mallcai.bs.model.TblHqProductPO;
import com.mallcai.service.inventory.marketing.api.IMarketingInventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("mgrCityProductReadService")
@Slf4j
public class MgrCityProductReadServiceImpl implements MgrCityProductReadService {

    @Autowired
    CityProductDao cityProductDao;

    @Autowired
    ClassifyDao classifyDao;

    @Autowired
    HqProductDao hqProductDao;

    @Autowired
    IMarketingInventory marketingInvertory;


    @Override
    public PlainResult<List<CityProduct>> findCityProductByCityId(CityProductSearchRequest request) {

        try {
            List<CityProduct> find = cityProductDao.findCityProductByCityId(request);

            return PlainResult.getDefaultSuccess(find);
        } catch (Exception ex) {
            log.error(String.format("##findCityProductByCityId error,params:%s", request), ex);
            return PlainResult.getDefaultFailed(ResponseCodeEnum.DB_OPERATION_QUERY_FAIL.getCode(), ex.getMessage());
        }
    }

    @Override
    public PlainResult<Paging<CityProduct>> productListByCity(CityProductQueryRequest request) {
        try {
            PageHelper.offsetPage(request.getOffset(), request.getLimit());
            List<CityProduct> cityProductByCityId = cityProductDao.productListByCity(request);
            long total = ((Page) cityProductByCityId).getTotal();
            Paging page = new Paging(total, cityProductByCityId);
            return PlainResult.getDefaultSuccess(page);
        } catch (Exception ex) {
            log.error(String.format("##productListByCity error,params:%s", request), ex);
            return PlainResult.getDefaultFailed(ResponseCodeEnum.DB_OPERATION_QUERY_FAIL.getCode(), ex.getMessage());
        }
    }

    @Override
    public PlainResult<SOAStandardPage<MerchantProductMarketDTO>> getMerchantProductMarketPage(MerchantProductMarketRequest productMarketRequest) {
        if (productMarketRequest.getCityId() == null || productMarketRequest.getMerchantId() == null
                || productMarketRequest.getPage() == null || productMarketRequest.getPageSize() == null) {
            LoggerUtils.getLogger().warn("无效的入参:{}", JSON.toJSONString(productMarketRequest));
            return PlainResult.getDefaultFailed(ResponseCodeEnum.INVALID_PARAM.getCode(),ResponseCodeEnum.INVALID_PARAM.getMsg());
        }

        try{
            int start = PageUtil.getStart(productMarketRequest.getPage(), productMarketRequest.getPageSize());
            PageHelper.offsetPage(start,productMarketRequest.getPageSize());
            SOAStandardPage<MerchantProductMarketDTO> standardPageProduct = getStandardPageProduct(productMarketRequest);

            return PlainResult.ok(standardPageProduct);
        } catch (Exception e){
            log.error(String.format("##getMerchantProductMarketPage error,params:%s", productMarketRequest), e);
            return PlainResult.getDefaultFailed(ResponseCodeEnum.INVALID_OPERATION.getCode(), ResponseCodeEnum.INVALID_OPERATION.getMsg());
        }
    }

    @Override
    public PlainResult<List<MerchantProductMarketDTO>> getMerchantProductMarketList(MerchantProductMarketRequest productMarketRequest) {
        if (productMarketRequest.getCityId() == null || productMarketRequest.getMerchantId() == null
                ||productMarketRequest.getCityProductIds() == null) {
            LoggerUtils.getLogger().warn("无效的入参:{}", JSON.toJSONString(productMarketRequest));
            return PlainResult.getDefaultFailed(ResponseCodeEnum.INVALID_PARAM.getCode(),ResponseCodeEnum.INVALID_PARAM.getMsg());
        }

        try{
            productMarketRequest.setPage(null);
            SOAStandardPage<MerchantProductMarketDTO> standardPageProduct = getStandardPageProduct(productMarketRequest);

            return PlainResult.ok(standardPageProduct.getList());
        } catch (Exception e){
            log.error(String.format("##getMerchantProductMarketList error,params:%s", productMarketRequest), e);
            return PlainResult.getDefaultFailed(ResponseCodeEnum.INVALID_OPERATION.getCode(), ResponseCodeEnum.INVALID_OPERATION.getMsg());
        }
    }

    private SOAStandardPage<MerchantProductMarketDTO> getStandardPageProduct(MerchantProductMarketRequest productMarketRequest){

        // 查询城市商品
        List<MerchantCityProduct> merchantCityProducts = cityProductDao.selectMerchantCityProduct(productMarketRequest);

        SOAStandardPage<MerchantProductMarketDTO> standardPageProduct = new SOAStandardPage<>();

        if(productMarketRequest.getPage() != null){
            // 分页查询时设置分页信息返回
            int total = (int) ((Page) merchantCityProducts).getTotal();

            int pageCount = PageUtil.getPageCount(total, productMarketRequest.getPageSize());
            standardPageProduct.setPageCount(pageCount);
            standardPageProduct.setTotalCount(total);
            standardPageProduct.setCurrentPage(productMarketRequest.getPage());
        }

        if(CollectionUtils.isEmpty(merchantCityProducts)){
            standardPageProduct.setList(Lists.newArrayList());
            return standardPageProduct;
        }

        List<Integer> hqProductIDs = merchantCityProducts.stream().map(MerchantCityProduct::getHqProductId).distinct().collect(Collectors.toList());

        List<TblHqProductPO> tblCityProductPOS = hqProductDao.selectHqProductBySelective(hqProductIDs);
        Map<Integer, TblHqProductPO> tblHqProductPOMap = tblCityProductPOS.stream().collect(Collectors.toMap(TblHqProductPO::getHqProductId, Function.identity()));

        // 查询一级 二级 类目
        List<Integer> classifyIds = tblCityProductPOS.stream().map(TblHqProductPO::getClassifyId).distinct().collect(Collectors.toList());

        List<ProductClassify> secClassify = classifyDao.getByClassifyIds(classifyIds);
        List<Integer> fatherClassIds = secClassify.stream().map(ProductClassify::getFatherId).distinct().collect(Collectors.toList());
        List<ProductClassify> fatherClassify = classifyDao.getByClassifyIds(fatherClassIds);

        Map<Integer, ProductClassify> secClassifyMap = secClassify.stream().collect(Collectors.toMap(ProductClassify::getClassifyId, Function.identity()));
        Map<Integer, ProductClassify> fatherClassifyMap = fatherClassify.stream().collect(Collectors.toMap(ProductClassify::getClassifyId, Function.identity()));

        List<Integer> cityProductIds = merchantCityProducts.stream().map(MerchantCityProduct::getCityProductId).collect(Collectors.toList());
        // 查询库存余量
        Response<Map<Integer, Integer>> availableResp = marketingInvertory.availableList(productMarketRequest.getCityId(), cityProductIds);
        Map<Integer, Integer> availableMap = new HashMap<>(cityProductIds.size());
        if(availableResp.isSuccess()){
            availableMap.putAll(availableResp.getData());
        }
        List<MerchantProductMarketDTO> marketDTOS = new ArrayList<>();

        MerchantProductMarketDTO marketDTO = null;
        for(MerchantCityProduct merchantCityProduct : merchantCityProducts){
            marketDTO = new MerchantProductMarketDTO();
            String classifyName = Optional.ofNullable(tblHqProductPOMap.get(merchantCityProduct.getHqProductId()))
                    .map(it ->{
                        return secClassifyMap.get(it.getClassifyId());
                    })
                    .map(it -> {
                Integer fatherId = it.getFatherId();
                return fatherClassifyMap.get(fatherId).getClassifyName()+"/"+it.getClassifyName();
            }).orElse("--");

            marketDTO.setCityProductId(merchantCityProduct.getCityProductId());
            marketDTO.setCategory(classifyName);
            marketDTO.setCityProductIcon(merchantCityProduct.getCityProductIcon());
            marketDTO.setRealPrice(merchantCityProduct.getRealPrice());
            marketDTO.setProductName(merchantCityProduct.getShowName());
            marketDTO.setStoreNum(availableMap.getOrDefault(merchantCityProduct.getCityProductId(),-1));
            marketDTOS.add(marketDTO);
        }

        standardPageProduct.setList(marketDTOS);

        return standardPageProduct;
    }

}
