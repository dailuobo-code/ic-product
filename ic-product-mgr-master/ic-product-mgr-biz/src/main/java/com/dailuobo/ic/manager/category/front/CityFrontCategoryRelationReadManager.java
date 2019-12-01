package com.dailuobo.ic.manager.category.front;

import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import com.dailuobo.ic.convert.category.FCRelatedBCConvert;
import com.dailuobo.ic.convert.category.FCRelatedCityProductConvert;
import com.dailuobo.ic.convert.category.FrontCategoryRelationConvert;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedCityProductDO;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.dailuobo.ic.dto.category.front.FCategoryRelatedBCategoryDTO;
import com.dailuobo.ic.dto.category.front.FCategoryRelationDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedCityProductDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedItemDTO;
import com.google.common.collect.Lists;
import com.mallcai.api.category.CategoryService;
import com.mallcai.api.category.front.MgrFrontCategoryService;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryDTO;
import com.mallcai.domain.category.front.request.FrontCategoryQueryRequest;
import dailuobo.dao.mapper.FrontCategoryRelatedBackendCategoryMapper;
import dailuobo.dao.mapper.FrontCategoryRelatedCityProductMapper;
import dailuobo.dao.mapper.FrontCategoryRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class CityFrontCategoryRelationReadManager {
    /**
     * 前台类目关联关系
     */
    @Autowired
    private FrontCategoryRelationMapper frontCategoryRelationMapper;

    /**
     * 前台类目关联的额后台类目列表
     */
    @Autowired
    private FrontCategoryRelatedBackendCategoryMapper frontCategoryRelatedBackendCategoryMapper;

    @Autowired
    private FrontCategoryRelatedCityProductMapper frontCategoryRelatedCityProductMapper;

    @Autowired
    private CityProductMapper cityProductMapper;

    @Autowired
    private MgrFrontCategoryService mgrFrontCategoryService;

    /**
     * 后台类目服务
     */
    @Autowired
    private CategoryService categoryService;


    /**
     * 二级前台类目关联关系
     *
     * @param frontCategoryId 二级前台类目Id
     * @param cityId          城市Id
     * @return
     */
    public List<FCategoryRelationDTO> listFrontCategoryRelation(BigInteger frontCategoryId, Integer relationType, Integer cityId) {
        List<FrontCategoryRelationDO> relationDOList = frontCategoryRelationMapper.listFCategoryRelation(frontCategoryId, relationType, cityId);
        List<FCategoryRelationDTO> fCategoryRelationDTO = FrontCategoryRelationConvert.convertCategoryDo2DtoList(relationDOList);
        return fCategoryRelationDTO;
    }



    public List<FCategoryRelationDTO> listFrontCategoryRelationByFrontCategoryIdList(List<BigInteger> frontCategoryId, Integer cityId) {
        List<FrontCategoryRelationDO> relationDO = frontCategoryRelationMapper.listFrontCategoryRelationsByFrontCategoryIdList(frontCategoryId, cityId);
        List<FCategoryRelationDTO> fCategoryRelationDTO = FrontCategoryRelationConvert.convertCategoryDo2DtoList(relationDO);
        return fCategoryRelationDTO;
    }

    /**
     * @param relationId 关联关系Id
     * @return 前台类目关联的后台类目列表
     */
    public List<FCategoryRelatedBCategoryDTO> listFrontCategoryRelatedBackendCategory(BigInteger relationId) {
        List<FCategoryRelatedBackendCategoryDO> fCategoryRelatedBackendCategoryDOS = frontCategoryRelatedBackendCategoryMapper.listFCategoryRelatedBackendCategory(relationId);
        List<FCategoryRelatedBCategoryDTO> categoryRelatedBCategoryDTOList = FCRelatedBCConvert.convertDoList2DtoList(fCategoryRelatedBackendCategoryDOS);
        //填充类目信息
        fillBackendCategoryShowName(categoryRelatedBCategoryDTOList);
        return categoryRelatedBCategoryDTOList;
    }


    /**
     * @param relationId 关联关系
     * @return 前台类目关联的城市商品列表
     */
    public List<FrontCategoryRelatedCityProductDTO> listFrontCategoryRelatedCityProduct(BigInteger relationId) {
        List<FCategoryRelatedCityProductDO> fCategoryRelatedCityProductDOS = frontCategoryRelatedCityProductMapper.listFCategoryRelatedCityProduct(relationId);
        List<FrontCategoryRelatedCityProductDTO> frontCategoryRelatedCityProductDTOS = FCRelatedCityProductConvert.convertDoList2DtoList(fCategoryRelatedCityProductDOS);

        //填充城市商品信息
        fillCityProductInfo(frontCategoryRelatedCityProductDTOS);
        List<FrontCategoryRelatedCityProductDTO> result = frontCategoryRelatedCityProductDTOS.stream().sorted(Comparator.comparing(FrontCategoryRelatedCityProductDTO::getDisplayOrder)).collect(Collectors.toList());
        return result;
    }


    /**
     * 加载城市中配置的所有的前台类目关联的后台类目/城市商品
     *
     * @param cityId 城市id
     * @return
     */
    public List<FrontCategoryRelatedItemDTO> listAllFrontCategoryRelations(Integer cityId) {
        List<FrontCategoryRelationDO> frontCategoryRelationDOS = loadFrontCategoryAllRelation(cityId);
        List<FrontCategoryRelatedItemDTO> result = Lists.newArrayList();
        for (FrontCategoryRelationDO relationDO : frontCategoryRelationDOS) {
            FrontCategoryRelatedItemDTO relatedItemDTO = new FrontCategoryRelatedItemDTO();
            relatedItemDTO.setRelationType(relationDO.getRelationType());
            relatedItemDTO.setRelationId(relationDO.getId());
            relatedItemDTO.setCityId(relationDO.getCityId());
            if (Objects.equals(relationDO.getRelationType(), CategoryRelationTypeEnum.BACKEND_CATEGORY.getCode())) {
                List<FCategoryRelatedBackendCategoryDO> relatedBackendCategoryDOS = frontCategoryRelatedBackendCategoryMapper.listFCategoryRelatedBackendCategory(relationDO.getId());
                relatedItemDTO.setRelatedBackendCategoryList(FCRelatedBCConvert.convertDoList2DtoList(relatedBackendCategoryDOS));
            }
            if (Objects.equals(relationDO.getRelationType(), CategoryRelationTypeEnum.CITY_PRODUCT.getCode())) {
                List<FCategoryRelatedCityProductDO> relatedCityProductDOS = frontCategoryRelatedCityProductMapper.listFCategoryRelatedCityProduct(relationDO.getId());
                relatedItemDTO.setRelatedCityProductList(FCRelatedCityProductConvert.convertDoList2DtoList(relatedCityProductDOS));
            }
        }

        return result;
    }

    List<FrontCategoryRelationDO> loadFrontCategoryAllRelation(Integer cityId) {
        FrontCategoryQueryRequest request = new FrontCategoryQueryRequest();
        request.setEffectiveStatus(2);

        ICResponse<List<FrontCategoryDTO>> categoryList = mgrFrontCategoryService.getCategoryList(request);
        if (categoryList == null || !categoryList.isSuccess() || CollectionUtils.isEmpty(categoryList.getData())) {
            log.error("##listFrontCategoryRelatedCityProduct=>mgrFrontCategoryService.getCategoryList result:{},param:{}", categoryList, request);
            return Lists.newArrayList();
        }
        List<FrontCategoryDTO> data = categoryList.getData();
        List<BigInteger> lv2FrontCategoryIdList = Lists.newArrayList();

        for (FrontCategoryDTO categoryDTO : data) {
            if (CollectionUtils.isNotEmpty(categoryDTO.getChildFrontCategorys())) {
                List<Long> frontLv2CategoryIdList = categoryDTO.getChildFrontCategorys().stream().map(FrontCategoryDTO::getCategoryId).collect(Collectors.toList());
                lv2FrontCategoryIdList.addAll(frontLv2CategoryIdList.stream().map(BigInteger::valueOf).collect(Collectors.toList()));
            }
        }
        List<FrontCategoryRelationDO> frontCategoryRelationDOS = frontCategoryRelationMapper.listFrontCategoryRelationsByFrontCategoryIdList(lv2FrontCategoryIdList,  cityId);
        return frontCategoryRelationDOS;
    }

    public FrontCategoryRelationDO loadRelation(BigInteger relationId){

        FrontCategoryRelationDO frontCategoryRelationDO = frontCategoryRelationMapper.loadFCategoryRelationById(relationId);
        return frontCategoryRelationDO;
    }
    /**
     * 填充类目信息
     *
     * @param categoryRelatedBCategoryDTOList
     */
    private void fillBackendCategoryShowName(List<FCategoryRelatedBCategoryDTO> categoryRelatedBCategoryDTOList) {
        List<Integer> backendCategoryIdList = categoryRelatedBCategoryDTOList.stream().map(FCategoryRelatedBCategoryDTO::getCategoryId).collect(Collectors.toList());
        ICResponse<List<CategoryDTO>> byCategoryIdList = categoryService.findByCategoryIdList(backendCategoryIdList);
        if (byCategoryIdList != null && byCategoryIdList.isSuccess() && CollectionUtils.isNotEmpty(byCategoryIdList.getData())) {
            List<CategoryDTO> backendCategoryList = byCategoryIdList.getData();
            categoryRelatedBCategoryDTOList.forEach(t -> {
                backendCategoryList.stream().filter(categoryDTO -> categoryDTO.getClassifyId().equals(t.getCategoryId())).forEachOrdered(categoryDTO -> {
                    if (categoryDTO.getFatherName() == null) {
                        t.setCategoryShowName(categoryDTO.getClassifyName());
                    } else {
                        t.setCategoryShowName(categoryDTO.getFatherName().concat("->").concat(categoryDTO.getClassifyName()));
                    }
                });
            });
        }
    }


    private void fillCityProductInfo(List<FrontCategoryRelatedCityProductDTO> frontCategoryRelatedCityProductDTOS) {
        List<Integer> cityProductIdList = frontCategoryRelatedCityProductDTOS.stream().map(FrontCategoryRelatedCityProductDTO::getCityProductId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(cityProductIdList)) {
            return;
        }
        List<CityProduct> cityProductList = cityProductMapper.selectCityProductByIdList(cityProductIdList);
        if (CollectionUtils.isEmpty(cityProductList)) {
            return;
        }
        for (FrontCategoryRelatedCityProductDTO t : frontCategoryRelatedCityProductDTOS) {
            for (CityProduct cityProduct : cityProductList) {
                if (cityProduct.getCityProductId().equals(t.getCityProductId())
                        && t.getCityId().equals(cityProduct.getCityId())) {
                    t.setProductShowName(cityProduct.getShowName());
                    t.setProductIcon(cityProduct.getCityProductIcon());
                    t.setProductName(cityProduct.getHqProductName());
                    t.setL1L2Names(cityProduct.getL1L2Names());
                }
            }
        }
    }

}
