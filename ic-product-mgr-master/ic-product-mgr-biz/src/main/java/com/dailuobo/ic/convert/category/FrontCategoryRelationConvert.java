package com.dailuobo.ic.convert.category;

import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import com.dailuobo.ic.api.enums.EffectScopeEnum;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.dailuobo.ic.dto.category.front.FCategoryRelationDTO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FrontCategoryRelationConvert {
    /**
     * DO转DTO对象
     *
     * @param frontCategoryRelationDO
     * @return
     */
    public static FCategoryRelationDTO convertDo2Dto(FrontCategoryRelationDO frontCategoryRelationDO) {

        FCategoryRelationDTO fCategoryRelationDTO = new FCategoryRelationDTO();
        if (frontCategoryRelationDO == null) {
            return fCategoryRelationDTO;
        }
        fCategoryRelationDTO.setRelationId(frontCategoryRelationDO.getId());
        fCategoryRelationDTO.setFrontCategoryId(frontCategoryRelationDO.getFrontCategoryId());
        fCategoryRelationDTO.setRelationType(frontCategoryRelationDO.getRelationType());

        fCategoryRelationDTO.setCreateTime(frontCategoryRelationDO.getCreateTime());
        fCategoryRelationDTO.setCreateUserId(frontCategoryRelationDO.getCreateUserId());
        fCategoryRelationDTO.setUpdateTime(frontCategoryRelationDO.getUpdateTime());
        fCategoryRelationDTO.setUpdateUserId(frontCategoryRelationDO.getUpdateUserId());
        fCategoryRelationDTO.setFrontCategoryId(frontCategoryRelationDO.getFrontCategoryId());
        fCategoryRelationDTO.setEffectScopeType(frontCategoryRelationDO.getEffectScope());

        if (frontCategoryRelationDO.getRelationType() != null && CategoryRelationTypeEnum.getByStatus(frontCategoryRelationDO.getRelationType()) != null) {
            fCategoryRelationDTO.setRelationTypeShowName(CategoryRelationTypeEnum.getByStatus(frontCategoryRelationDO.getRelationType()).getDescription());
        }

        if (frontCategoryRelationDO.getEffectScope() != null) {
            fCategoryRelationDTO.setEffectScopeTypeShowName(EffectScopeEnum.getByStatus(frontCategoryRelationDO.getEffectScope()).getDescription());
        }
        return fCategoryRelationDTO;
    }

    /**
     * DTO转Do对象
     *
     * @param fCategoryRelationDTO
     * @return
     */
    public static FrontCategoryRelationDO convertDto2Do(FCategoryRelationDTO fCategoryRelationDTO) {

        FrontCategoryRelationDO frontCategoryRelationDO = new FrontCategoryRelationDO();
        if (fCategoryRelationDTO == null) {
            return frontCategoryRelationDO;
        }
        frontCategoryRelationDO.setId(fCategoryRelationDTO.getRelationId());
        frontCategoryRelationDO.setFrontCategoryId(fCategoryRelationDTO.getFrontCategoryId());
        frontCategoryRelationDO.setRelationType(fCategoryRelationDTO.getRelationType());
        frontCategoryRelationDO.setCreateTime(fCategoryRelationDTO.getCreateTime());
        frontCategoryRelationDO.setCreateUserId(fCategoryRelationDTO.getCreateUserId());
        frontCategoryRelationDO.setUpdateTime(fCategoryRelationDTO.getUpdateTime());
        frontCategoryRelationDO.setUpdateUserId(fCategoryRelationDTO.getUpdateUserId());
        frontCategoryRelationDO.setFrontCategoryId(fCategoryRelationDTO.getFrontCategoryId());
        frontCategoryRelationDO.setEffectScope(fCategoryRelationDTO.getEffectScopeType());

        return frontCategoryRelationDO;
    }

    /**
     * DO列表转DTO列表
     *
     * @param frontCategoryRelationDOList
     * @return
     */
    public static List<FCategoryRelationDTO> convertCategoryDo2DtoList(List<FrontCategoryRelationDO> frontCategoryRelationDOList) {
        List<FCategoryRelationDTO> relationDTOList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(frontCategoryRelationDOList)) {
            return relationDTOList;
        }
        relationDTOList = frontCategoryRelationDOList.stream().map(FrontCategoryRelationConvert::convertDo2Dto).collect(Collectors.toList());
        return relationDTOList;
    }


    /**
     * DTO列表转DO列表
     *
     * @param frontCategoryRelationDOList
     * @return
     */
    public static List<FrontCategoryRelationDO> convertCategoryDto2DoList(List<FCategoryRelationDTO> frontCategoryRelationDOList) {
        List<FrontCategoryRelationDO> relationDTOList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(frontCategoryRelationDOList)) {
            return relationDTOList;
        }
        relationDTOList = frontCategoryRelationDOList.stream().map(FrontCategoryRelationConvert::convertDto2Do).collect(Collectors.toList());
        return relationDTOList;
    }

}
