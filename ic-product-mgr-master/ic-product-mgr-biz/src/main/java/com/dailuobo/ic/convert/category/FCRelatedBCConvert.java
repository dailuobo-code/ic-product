package com.dailuobo.ic.convert.category;

import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import com.dailuobo.ic.dto.category.front.FCategoryRelatedBCategoryDTO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台类目关联的后台类目对象转换
 */
public class FCRelatedBCConvert {

    public static FCategoryRelatedBCategoryDTO convertDo2Dto(FCategoryRelatedBackendCategoryDO fCategoryRelatedBackendCategoryDO) {

        FCategoryRelatedBCategoryDTO dto = new FCategoryRelatedBCategoryDTO();
        if (fCategoryRelatedBackendCategoryDO == null) {
            return dto;
        }
        dto.setCategoryId(fCategoryRelatedBackendCategoryDO.getBackendCategoryId());
        dto.setDisplayOrder(fCategoryRelatedBackendCategoryDO.getDisplayOrder());
        dto.setId(fCategoryRelatedBackendCategoryDO.getId());
        dto.setCityId(fCategoryRelatedBackendCategoryDO.getCityId());
        return dto;

    }


    public static FCategoryRelatedBackendCategoryDO convertDto2Do(FCategoryRelatedBCategoryDTO fCategoryRelatedBCategoryDTO) {

        FCategoryRelatedBackendCategoryDO categoryDO = new FCategoryRelatedBackendCategoryDO();
        if (fCategoryRelatedBCategoryDTO == null) {
            return categoryDO;
        }
        categoryDO.setBackendCategoryId(fCategoryRelatedBCategoryDTO.getCategoryId());
        categoryDO.setDisplayOrder(fCategoryRelatedBCategoryDTO.getDisplayOrder());
        categoryDO.setId(fCategoryRelatedBCategoryDTO.getId());
        categoryDO.setCityId(fCategoryRelatedBCategoryDTO.getCityId());
        categoryDO.setRelationId(fCategoryRelatedBCategoryDTO.getRelationId());
        return categoryDO;

    }


    public static List<FCategoryRelatedBCategoryDTO> convertDoList2DtoList(List<FCategoryRelatedBackendCategoryDO> doList) {
        List<FCategoryRelatedBCategoryDTO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(doList)) {
            return result;
        }
        result = doList.stream().map(FCRelatedBCConvert::convertDo2Dto).collect(Collectors.toList());
        return result;

    }


    public static List<FCategoryRelatedBackendCategoryDO> convertDtoList2DoList(List<FCategoryRelatedBCategoryDTO> dtoList) {
        List<FCategoryRelatedBackendCategoryDO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dtoList)) {
            return result;
        }
        result = dtoList.stream().map(FCRelatedBCConvert::convertDto2Do).collect(Collectors.toList());
        return result;
    }


}
