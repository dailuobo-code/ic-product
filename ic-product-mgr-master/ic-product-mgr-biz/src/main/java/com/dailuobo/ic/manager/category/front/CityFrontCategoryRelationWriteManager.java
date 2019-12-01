package com.dailuobo.ic.manager.category.front;

import com.alibaba.fastjson.JSON;
import com.dailuobo.ic.api.enums.CategoryRelationTypeEnum;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedBCategory;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedCityProduct;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationDeleteRequest;
import com.dailuobo.ic.api.request.category.front.FrontCategoryRelationsCreateRequest;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedCityProductDO;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.google.common.collect.Lists;
import dailuobo.dao.mapper.FrontCategoryRelatedBackendCategoryMapper;
import dailuobo.dao.mapper.FrontCategoryRelatedCityProductMapper;
import dailuobo.dao.mapper.FrontCategoryRelationMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Repository
public class CityFrontCategoryRelationWriteManager {

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


    @Transactional
    public void saveFrontCategoryRelation(FrontCategoryRelationsCreateRequest request) {
        BigInteger frontCategoryId = request.getFrontCategoryId();
        Integer cityId = request.getCityId();
        Integer scopeType = request.getEffectScope();
        BigInteger relationId = request.getId();
        FrontCategoryRelationDO frontCategoryRelationEntity = null;
        //同一个前台类目同一种关联类型同一个作用范围只有一条记录
        List<FrontCategoryRelationDO> frontCategoryRelationEntityList = frontCategoryRelationMapper.loadFCategoryRelation(frontCategoryId, scopeType, cityId);
        if (CollectionUtils.isNotEmpty(frontCategoryRelationEntityList)) {
            for (FrontCategoryRelationDO entity : frontCategoryRelationEntityList) {
                if (relationId != null && entity.getId().equals(request.getId())) {
                    continue;
                }
                if (Objects.equals(request.getEffectScope(), entity.getEffectScope())) {
                    List<Integer>  effectEntityIdList= entity.getEffectScopeIdList();
                    List<Integer>  effectScopeIdList= request.getEffectEntityIdList();
                    boolean b = CollectionUtils.containsAny(effectScopeIdList, effectEntityIdList);
                    if (b) {
                        effectScopeIdList.retainAll(effectEntityIdList);
                        throw new IllegalArgumentException(String.format("所选择门店已存在关联的记录%s)", JSON.toJSONString(effectScopeIdList)));
                    }
                }
            }


        }
        if (relationId != null) {
            frontCategoryRelationEntity = frontCategoryRelationMapper.loadFCategoryRelationById(relationId);
        }

        //关联关系的保存
        FrontCategoryRelationDO frontCategoryRelationDO = new FrontCategoryRelationDO();
        frontCategoryRelationDO.setFrontCategoryId(frontCategoryId);
        frontCategoryRelationDO.setEffectScope(request.getEffectScope());
        frontCategoryRelationDO.setCityId(request.getCityId());
        frontCategoryRelationDO.setEffectScopeIds(request.getEffectEntityIdListStr());
        frontCategoryRelationDO.setUpdateUserId(request.getOperatorId());
        frontCategoryRelationDO.setRelationType(request.getRelationType());
        frontCategoryRelationDO.setCreateUserId(request.getOperatorId());
        frontCategoryRelationDO.setUpdateUserId(request.getOperatorId());

        if (frontCategoryRelationEntity != null) {
            frontCategoryRelationDO.setId(relationId);
            frontCategoryRelationMapper.update(frontCategoryRelationDO);
            relationId = frontCategoryRelationEntity.getId();
        } else {
            frontCategoryRelationMapper.create(frontCategoryRelationDO);
            relationId = frontCategoryRelationDO.getId();
        }
        frontCategoryRelatedBackendCategoryMapper.deleteRelationByRelationId(relationId, cityId, request.getOperatorId());
        frontCategoryRelatedCityProductMapper.deleteRelationByRelationId(relationId, cityId, request.getOperatorId());
        //保存关联类目信息
        //todo 先删后加
        if (Objects.equals(request.getRelationType(), CategoryRelationTypeEnum.BACKEND_CATEGORY.getCode())) {
            List<FCategoryRelatedBackendCategoryDO> batchRelatedBCategory = Lists.newArrayList();
            for (FCategoryRelatedBCategory relatedBCategory : request.getRelatedBCategoryList()) {


                FCategoryRelatedBackendCategoryDO relatedBackendCategoryDO = new FCategoryRelatedBackendCategoryDO();
                relatedBackendCategoryDO.setRelationId(relationId);
                relatedBackendCategoryDO.setBackendCategoryId(relatedBCategory.getCategoryId());
                relatedBackendCategoryDO.setDisplayOrder(relatedBCategory.getDisplayOrder());
                relatedBackendCategoryDO.setCityId(request.getCityId());
                relatedBackendCategoryDO.setUpdateUserId(request.getOperatorId());
                relatedBackendCategoryDO.setCreateUserId(request.getOperatorId());
                batchRelatedBCategory.add(relatedBackendCategoryDO);
            }

            frontCategoryRelatedBackendCategoryMapper.batchCreate(batchRelatedBCategory);
        }
        //关联城市商品
        else if (Objects.equals(request.getRelationType(), CategoryRelationTypeEnum.CITY_PRODUCT.getCode())) {
            List<FCategoryRelatedCityProductDO> relatedCityProductList = Lists.newArrayList();
            for (FCategoryRelatedCityProduct relatedCityProduct : request.getRelatedCityProductList()) {
                FCategoryRelatedCityProductDO relatedCityProductDO = new FCategoryRelatedCityProductDO();
                relatedCityProductDO.setCityId(request.getCityId());
                relatedCityProductDO.setCityProductId(relatedCityProduct.getCityProductId());
                relatedCityProductDO.setProductNo(relatedCityProduct.getProductNo());
                relatedCityProductDO.setDisplayOrder(relatedCityProduct.getDisPlayOrder());
                relatedCityProductDO.setCreateUserId(request.getOperatorId());
                relatedCityProductDO.setUpdateUserId(request.getOperatorId());
                relatedCityProductDO.setRelationId(relationId);
                relatedCityProductList.add(relatedCityProductDO);
            }
            frontCategoryRelatedCityProductMapper.batchCreate(relatedCityProductList);
        } else {
            return;
        }
    }


    @Transactional
    public void deletedFrontRelation(FCategoryRelationDeleteRequest request) {
        //1.先删子集
        //2.再删关联关系集合
        BigInteger relationId = request.getRelationId();
        BigInteger frontCategoryId = request.getFCategoryId();
        Integer cityId = request.getCityId();
        Integer operationId = request.getOperatorId();
        frontCategoryRelationMapper.deleteRelations(relationId, frontCategoryId, cityId, operationId);
        if (Objects.equals(request.getRelationType(), CategoryRelationTypeEnum.BACKEND_CATEGORY.getCode())) {
            frontCategoryRelatedBackendCategoryMapper.deleteRelationByRelationId(relationId, cityId, operationId);
        } else if (Objects.equals(request.getRelationType(), CategoryRelationTypeEnum.CITY_PRODUCT.getCode())) {
            frontCategoryRelatedCityProductMapper.deleteRelationByRelationId(relationId, cityId, operationId);
        }
    }
}
