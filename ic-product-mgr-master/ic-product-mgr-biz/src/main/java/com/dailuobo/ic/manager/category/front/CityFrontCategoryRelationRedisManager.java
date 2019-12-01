package com.dailuobo.ic.manager.category.front;

import com.alibaba.fastjson.JSON;
import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedCityProductDO;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.dailuobo.ic.domain.dao.model.redis.CategoryRelatedEntity;
import com.dailuobo.ic.domain.dao.model.redis.CityCategoryModel;
import com.google.common.collect.Lists;
import com.mallcai.api.category.front.MgrFrontCategoryService;
import com.mallcai.backend.common.redis.JedisProxy;
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

/**
 * 城市前台类目缓存管理
 */
@Repository
@Slf4j
public class CityFrontCategoryRelationRedisManager {


    @Autowired
    private CityFrontCategoryRelationReadManager cityFrontCategoryRelationReadManager;

    @Autowired
    private FrontCategoryRelationMapper frontCategoryRelationMapper;

    /**
     * 关联后台类目映射
     */
    @Autowired
    private FrontCategoryRelatedBackendCategoryMapper frontCategoryRelatedBackendCategoryMapper;

    /**
     * 关联城市商品映射
     */
    @Autowired
    private FrontCategoryRelatedCityProductMapper frontCategoryRelatedCityProductMapper;


    /**
     * @param categoryId 二级前台类目
     * @param cityId     城市Id
     */
    public void refreshLv2FrontCategoryRelationCache(BigInteger categoryId, Integer cityId) {
        String lv2FrontCategoryRelationCacheKey = generationCategoryRedisKey(categoryId, cityId);
        Long del = JedisProxy.getInstance().delKey(lv2FrontCategoryRelationCacheKey);
        log.info("refreshLv2FrontCategoryRelationCache =>delKey:{},reuslt:{}",lv2FrontCategoryRelationCacheKey,del);

        List<CityCategoryModel> categoryModel = loadLv2FrontCategoryRelatedEntityList(categoryId, cityId);
        if (CollectionUtils.isEmpty(categoryModel)) {
            return;
        }
        JedisProxy.getInstance().set(lv2FrontCategoryRelationCacheKey, JSON.toJSONString(categoryModel));
        log.info("refreshLv2FrontCategoryRelationCache =>setKey:{},reuslt:{}",lv2FrontCategoryRelationCacheKey,del);
    }


    /**
     * 刷新所有城市关联的数据
     *
     * @param cityId 城市Id
     */
    public void refreshAllFrontCategoryRelation(Integer cityId) {
        List<FrontCategoryRelationDO> frontCategoryRelationDOS = cityFrontCategoryRelationReadManager.loadFrontCategoryAllRelation(cityId);
        for (FrontCategoryRelationDO frontCategoryRelationDO : frontCategoryRelationDOS) {
            refreshLv2FrontCategoryRelationCache(frontCategoryRelationDO.getFrontCategoryId(), cityId);
        }

    }


    /**
     * 查询当前修改项对应的前台二级类目所有的关联对象
     * 组装成redis缓存数据
     *
     * @param categoryId 前台二级类目Id
     * @param cityId     城市Id
     */
    private List<CityCategoryModel> loadLv2FrontCategoryRelatedEntityList(BigInteger categoryId, Integer cityId) {
        try {
            List<FrontCategoryRelationDO> frontCategoryRelationDO = frontCategoryRelationMapper.listFCategoryRelation(categoryId, null, cityId);
            if (CollectionUtils.isEmpty(frontCategoryRelationDO)) {
                return null;
            }
            List<CityCategoryModel> result = Lists.newArrayList();
            for (FrontCategoryRelationDO relationDO : frontCategoryRelationDO) {
                CityCategoryModel categoryModel = new CityCategoryModel();
                categoryModel.setRelationId(relationDO.getId());
                categoryModel.setFrontCategoryLv2Id(relationDO.getFrontCategoryId());
                categoryModel.setEffectScope(relationDO.getEffectScope());
                categoryModel.setRelationType(relationDO.getRelationType());
                categoryModel.setEffectScopeList(relationDO.getEffectScopeIdList());

                if (Objects.equals(relationDO.getRelationType(), CategoryRelationTypeEnum.BACKEND_CATEGORY.getCode())) {
                    List<FCategoryRelatedBackendCategoryDO> relatedBackendCategoryList = frontCategoryRelatedBackendCategoryMapper.listFCategoryRelatedBackendCategory(categoryModel.getRelationId());
                    List<CategoryRelatedEntity> item = relatedBackendCategoryList.stream().map(t -> new CategoryRelatedEntity(t.getBackendCategoryId(), t.getDisplayOrder())).
                            sorted(Comparator.comparing(CategoryRelatedEntity::getOrder)).collect(Collectors.toList());
                    categoryModel.setItems(item);
                    result.add(categoryModel);

                }
                if (Objects.equals(relationDO.getRelationType(), CategoryRelationTypeEnum.CITY_PRODUCT.getCode())) {
                    List<FCategoryRelatedCityProductDO> relatedCityProductDOS = frontCategoryRelatedCityProductMapper.listFCategoryRelatedCityProduct(relationDO.getId());
                    List<CategoryRelatedEntity> item = relatedCityProductDOS.stream().map(t -> new CategoryRelatedEntity(t.getCityProductId(), t.getDisplayOrder())).
                            sorted(Comparator.comparing(CategoryRelatedEntity::getOrder)).collect(Collectors.toList());
                    categoryModel.setItems(item);
                    result.add(categoryModel);
                }

            }
            return result;
        } catch (Exception ex) {
            log.error(String.format("##loadLv2FrontCategoryRelatedEntityList error,param=> categoryId:%s,cityId:%s", categoryId, categoryId), ex);
            return null;
        }
    }

    /**
     * 删除缓存中前台类目关联关系
     *
     * @param frontCategoryId
     * @param cityId
     */
    public void delLv2FrontCategoryRelationCache(BigInteger frontCategoryId, Integer cityId) {
        String categoryRelationRedisKey = generationCategoryRedisKey(frontCategoryId, cityId);
        JedisProxy.getInstance().delKey(categoryRelationRedisKey);
    }


    private static String generationCategoryRedisKey(BigInteger frontCategoryId, Integer cityId) {
        return "front_category:" + frontCategoryId + ":cityId:" + cityId;
    }
}
