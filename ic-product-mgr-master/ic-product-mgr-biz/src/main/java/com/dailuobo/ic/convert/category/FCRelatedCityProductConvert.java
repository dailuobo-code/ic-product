package com.dailuobo.ic.convert.category;

import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedCityProductDO;
import com.dailuobo.ic.dto.category.front.FCategoryRelatedBCategoryDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedCityProductDTO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FCRelatedCityProductConvert {
    public static FrontCategoryRelatedCityProductDTO convertDo2Dto(FCategoryRelatedCityProductDO fCategoryRelatedBackendCategoryDO) {

        FrontCategoryRelatedCityProductDTO dto = new FrontCategoryRelatedCityProductDTO();
        if (fCategoryRelatedBackendCategoryDO == null) {
            return dto;
        }
        dto.setCityProductId(fCategoryRelatedBackendCategoryDO.getCityProductId());
        dto.setDisplayOrder(fCategoryRelatedBackendCategoryDO.getDisplayOrder());
        dto.setProductNo(fCategoryRelatedBackendCategoryDO.getProductNo());
        dto.setRelationId(fCategoryRelatedBackendCategoryDO.getRelationId());
        dto.setCityId(fCategoryRelatedBackendCategoryDO.getCityId());
        dto.setProductNo(fCategoryRelatedBackendCategoryDO.getProductNo());

        return dto;

    }


    public static FCategoryRelatedCityProductDO convertDto2Do(FrontCategoryRelatedCityProductDTO frontCategoryRelatedCityProductDTO) {

        FCategoryRelatedCityProductDO categoryDO = new FCategoryRelatedCityProductDO();
        if (frontCategoryRelatedCityProductDTO == null) {
            return categoryDO;
        }
        categoryDO.setDisplayOrder(frontCategoryRelatedCityProductDTO.getDisplayOrder());
        categoryDO.setId(frontCategoryRelatedCityProductDTO.getId());
        categoryDO.setRelationId(frontCategoryRelatedCityProductDTO.getRelationId());
        categoryDO.setProductNo(frontCategoryRelatedCityProductDTO.getProductNo());
        categoryDO.setCityProductId(frontCategoryRelatedCityProductDTO.getCityProductId());
        categoryDO.setCityId(frontCategoryRelatedCityProductDTO.getCityId());
        return categoryDO;

    }


    public static List<FrontCategoryRelatedCityProductDTO> convertDoList2DtoList(List<FCategoryRelatedCityProductDO> doList) {
        List<FrontCategoryRelatedCityProductDTO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(doList)) {
            return result;
        }
        result = doList.stream().map(FCRelatedCityProductConvert::convertDo2Dto).collect(Collectors.toList());
        return result;

    }


    public static List<FCategoryRelatedCityProductDO> convertDtoList2DoList(List<FrontCategoryRelatedCityProductDTO> dtoList) {
        List<FCategoryRelatedCityProductDO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dtoList)) {
            return result;
        }
        result = dtoList.stream().map(FCRelatedCityProductConvert::convertDto2Do).collect(Collectors.toList());
        return result;
    }
}
