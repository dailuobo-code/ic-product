package com.dailuobo.ic.service.front;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.ic.api.category.front.ICityFrontCategoryReadService;
import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedContentQueryRequest;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationQueryRequest;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.dailuobo.ic.dto.category.front.FCategoryRelatedBCategoryDTO;
import com.dailuobo.ic.dto.category.front.FCategoryRelationDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedCityProductDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedItemDTO;
import com.dailuobo.ic.manager.category.front.CityFrontCategoryRelationReadManager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.mallcai.api.category.front.MgrFrontCategoryService;
import com.mallcai.domain.category.front.dto.FrontCategoryDTO;
import com.mallcai.domain.category.front.request.FrontCategoryQueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service("cityFrontCategoryReadService")
public class CityFrontCategoryReadServiceImpl implements ICityFrontCategoryReadService {

    @Autowired
    private CityFrontCategoryRelationReadManager cityFrontCategoryRelationReadManager;

    @Autowired
    private MgrFrontCategoryService mgrFrontCategoryService;

    @Override
    public ICResponse<List<com.dailuobo.ic.dto.category.front.FrontCategoryDTO>> listFrontCategory() {
        return null;
    }

    /**
     * @param relationQueryRequest 城市前台二级类目关联关系查询
     * @return
     */
    @Override
    public ICResponse<List<FCategoryRelationDTO>> listCityFrontCategoryRelations(FCategoryRelationQueryRequest relationQueryRequest) {
        if (relationQueryRequest == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        relationQueryRequest.checkParams();
        try {
            FrontCategoryQueryRequest frontCategoryQueryRequest = new FrontCategoryQueryRequest();
            frontCategoryQueryRequest.setCategoryId(relationQueryRequest.getLv1frontCategoryId().longValue());
            com.mallcai.common.ICResponse<List<FrontCategoryDTO>> categoryList = mgrFrontCategoryService.getCategoryList(frontCategoryQueryRequest);
            if (categoryList == null || !categoryList.isSuccess() || CollectionUtils.isEmpty(categoryList.getData())) {
                log.error("##mgrFrontCategoryService.getCategoryList exception,request param:{},response:{}", frontCategoryQueryRequest, categoryList);
                return ICResponse.fail("分类信息查询异常");
            }
            List<FrontCategoryDTO> data = Lists.newArrayList();
            if (categoryList.getData().get(0).getCategoryLevel().equals(1) && CollectionUtils.isEmpty(categoryList.getData().get(0).getChildFrontCategorys())) {
                FrontCategoryQueryRequest frontCategoryQueryRequest1 = new FrontCategoryQueryRequest();
                frontCategoryQueryRequest1.setParentCategoryId(categoryList.getData().get(0).getCategoryId());
                com.mallcai.common.ICResponse<List<FrontCategoryDTO>> categoryList1 = mgrFrontCategoryService.getCategoryList(frontCategoryQueryRequest1);
                data = categoryList1.getData();
            } else {
                data = categoryList.getData();
            }
            List<BigInteger> frontCategoryList = Lists.newArrayList();
            if (CollectionUtils.isEmpty(data)) {
                return ICResponse.success();
            } else {
                frontCategoryList = data.get(0).getChildFrontCategorys().stream().map(FrontCategoryDTO::getCategoryId).map(BigInteger::valueOf).collect(Collectors.toList());
            }

            Integer cityId = relationQueryRequest.getCityId();
            //PageHelper.startPage(relationQueryRequest.getCurrentPage(), relationQueryRequest.getPageSize());
            List<FCategoryRelationDTO> fCategoryRelationDTO = cityFrontCategoryRelationReadManager.listFrontCategoryRelationByFrontCategoryIdList(frontCategoryList, cityId);
            List<FrontCategoryDTO> finalData = data;
            fCategoryRelationDTO.forEach(t -> {
                finalData.get(0).getChildFrontCategorys().forEach(s -> {
                    if (s.getCategoryId().equals(t.getFrontCategoryId().longValue())) {
                        t.setRelatedFCategoryName(finalData.get(0).getCategoryName() + "->" + s.getCategoryName());
                    }
                });
            });
            //PageDTO pageDTO = new PageDTO(relationQueryRequest.getPageSize(), (int) ((Page) fCategoryRelationDTO).getTotal(), relationQueryRequest.getCurrentPage());
            return ICResponse.success(fCategoryRelationDTO);
        } catch (Exception ex) {
            log.error(String.format("##listCityFrontCategoryRelations query error,param:%sd", relationQueryRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }

    }

    @Override
    public ICResponse<List<FCategoryRelatedBCategoryDTO>> listCityFrontCategoryRelatedBackendCategory(FCategoryRelatedContentQueryRequest relatedBCategoryQueryRequest) {
        if (relatedBCategoryQueryRequest == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        relatedBCategoryQueryRequest.checkParams();
        try {
            BigInteger relationId = relatedBCategoryQueryRequest.getRelationId();
            Integer cityId = relatedBCategoryQueryRequest.getCityId();
            List<FCategoryRelatedBCategoryDTO> categoryRelatedBCategoryDTOList = cityFrontCategoryRelationReadManager.listFrontCategoryRelatedBackendCategory(relationId);

            return ICResponse.success(categoryRelatedBCategoryDTOList);
        } catch (Exception ex) {
            log.error(String.format("##listCityFrontCategoryRelatedBackendCategory query error,param:%sd", relatedBCategoryQueryRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }

    }

    @Override
    public ICResponse<List<FrontCategoryRelatedCityProductDTO>> listCityFrontCategoryRelatedCityProduct(FCategoryRelatedContentQueryRequest relatedCityProductQueryRequest) {
        if (relatedCityProductQueryRequest == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        relatedCityProductQueryRequest.checkParams();
        try {
            BigInteger relationId = relatedCityProductQueryRequest.getRelationId();
            Integer cityId = relatedCityProductQueryRequest.getCityId();

            List<FrontCategoryRelatedCityProductDTO> frontCategoryRelatedCityProductDTOS = cityFrontCategoryRelationReadManager.listFrontCategoryRelatedCityProduct(relationId);

            return ICResponse.success(frontCategoryRelatedCityProductDTOS);

        } catch (Exception ex) {

            log.error(String.format("##listCityFrontCategoryRelatedCityProduct query error,param:%sd", relatedCityProductQueryRequest), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<FrontCategoryRelatedItemDTO> listCityFrontCategoryRelatedItems(FCategoryRelatedContentQueryRequest relatedCityProductQueryRequest) {
        if (relatedCityProductQueryRequest == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        relatedCityProductQueryRequest.checkParams();
        BigInteger relationId = relatedCityProductQueryRequest.getRelationId();
        Integer relationType = relatedCityProductQueryRequest.getRelationType();

        FrontCategoryRelatedItemDTO relatedItemDTO = new FrontCategoryRelatedItemDTO();
        relatedItemDTO.setRelationId(relationId);
        relatedItemDTO.setRelationType(relationType);

        FrontCategoryRelationDO frontCategoryRelationDO = cityFrontCategoryRelationReadManager.loadRelation(relationId);
        if (frontCategoryRelationDO == null) {
            return ICResponse.success();
        }
        BigInteger frontCategoryId = frontCategoryRelationDO.getFrontCategoryId();
        relatedItemDTO.setFrontCategoryId(frontCategoryId);

        FrontCategoryQueryRequest request = new FrontCategoryQueryRequest();
        request.setCategoryId(frontCategoryId.longValue());
        com.mallcai.common.ICResponse<List<FrontCategoryDTO>> categoryList = mgrFrontCategoryService.getCategoryList(request);

        if (CollectionUtils.isNotEmpty(categoryList.getData())) {
            FrontCategoryDTO categoryDTO = categoryList.getData().get(0);
            if (CollectionUtils.isNotEmpty(categoryDTO.getChildFrontCategorys())) {
                if (categoryDTO.getCategoryId().equals(request.getCategoryId())) {
                    relatedItemDTO.setFrontCategoryName(categoryDTO.getCategoryName());
                } else {
                    relatedItemDTO.setFrontCategoryName(categoryDTO.getChildFrontCategorys().get(0).getCategoryName());
                    relatedItemDTO.setParentFrontCategoryName(categoryDTO.getCategoryName());
                    relatedItemDTO.setParentCategoryId(BigInteger.valueOf(categoryDTO.getCategoryId()));
                }

            } else {
                relatedItemDTO.setParentCategoryId(BigInteger.valueOf(categoryDTO.getParentCategoryId()));
                request.setCategoryId(categoryDTO.getParentCategoryId());
                com.mallcai.common.ICResponse<List<FrontCategoryDTO>> categoryListParent = mgrFrontCategoryService.getCategoryList(request);
                if (categoryListParent != null && CollectionUtils.isNotEmpty(categoryListParent.getData()))
                    relatedItemDTO.setParentFrontCategoryName(categoryListParent.getData().get(0).getCategoryName());
            }
        }

        relatedItemDTO.setEffectScope(frontCategoryRelationDO.getEffectScope());
        relatedItemDTO.setEffectScopeList(frontCategoryRelationDO.getEffectScopeIdList());
        if (Objects.equals(relatedCityProductQueryRequest.getRelationType(), CategoryRelationTypeEnum.BACKEND_CATEGORY.getCode())) {
            List<FCategoryRelatedBCategoryDTO> categoryRelatedBCategoryDTOList = cityFrontCategoryRelationReadManager.listFrontCategoryRelatedBackendCategory(relationId);
            relatedItemDTO.setRelatedBackendCategoryList(categoryRelatedBCategoryDTOList);
            return ICResponse.success(relatedItemDTO);
        }
        if (Objects.equals(relatedCityProductQueryRequest.getRelationType(), CategoryRelationTypeEnum.CITY_PRODUCT.getCode())) {
            List<FrontCategoryRelatedCityProductDTO> frontCategoryRelatedCityProductDTOS = cityFrontCategoryRelationReadManager.listFrontCategoryRelatedCityProduct(relationId);
            relatedItemDTO.setRelatedCityProductList(frontCategoryRelatedCityProductDTOS);
            return ICResponse.success(relatedItemDTO);
        }
        log.warn("##listCityFrontCategoryRelatedItems empty,request:{}", relatedCityProductQueryRequest);
        return ICResponse.success();
    }

    @Override
    public ICResponse<List<FrontCategoryRelatedItemDTO>> listAllCityFrontCategoryRelatedItems(Integer cityId) {
        List<FrontCategoryRelatedItemDTO> frontCategoryRelatedItemDTOS = cityFrontCategoryRelationReadManager.listAllFrontCategoryRelations(cityId);
        return ICResponse.success(frontCategoryRelatedItemDTOS);
    }
}
