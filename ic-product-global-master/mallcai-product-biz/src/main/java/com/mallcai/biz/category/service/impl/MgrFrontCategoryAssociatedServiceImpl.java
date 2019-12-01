package com.mallcai.biz.category.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.api.category.front.MgrFrontCategoryAssociatedService;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.biz.category.dao.CategoryDAO;
import com.mallcai.biz.category.dao.mapper.IcFrontCategoryMapper;
import com.mallcai.biz.category.dao.mapper.IcHqFcategoryRelatedBcategoryMapper;
import com.mallcai.biz.category.dao.mapper.IcHqFcategoryRelatedHqProductMapper;
import com.mallcai.biz.category.dao.mapper.IcHqFcategoryRelationMapper;
import com.mallcai.biz.category.manager.HqFcatCacheManager;
import com.mallcai.biz.product.dao.mapper.TblHqProductCustomMapper;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedConfigurationDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedItemDTO;
import com.mallcai.domain.category.front.request.*;
import com.mallcai.domain.dataobject.category.*;
import com.mallcai.domain.dataobject.product.TblHqProduct;
import com.mallcai.domain.dto.IcFrontCategoryExample;
import com.mallcai.domain.dto.IcHqFcategoryRelatedBcategoryExample;
import com.mallcai.domain.dto.IcHqFcategoryRelatedHqProductExample;
import com.mallcai.domain.dto.IcHqFcategoryRelationExample;
import com.mallcai.domain.product.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MgrFrontCategoryAssociatedServiceImpl implements MgrFrontCategoryAssociatedService {

    @Resource
    private IcHqFcategoryRelationMapper icHqFcategoryRelationMapper;

    @Resource
    private IcFrontCategoryMapper icFrontCategoryMapper;
    @Resource
    private IcHqFcategoryRelatedBcategoryMapper icHqFcategoryRelatedBcategoryMapper;
    @Resource
    private IcHqFcategoryRelatedHqProductMapper icHqFcategoryRelatedHqProductMapper;

    @Resource
    private CategoryDAO categoryDAO;
    @Resource
    private TblHqProductCustomMapper tblHqProductCustomMapper;
    @Resource
    private HqFcatCacheManager hqFcatCacheManager;
    @Resource
    private MgrProductServiceHelper mgrProductServiceHelper;

    @Override
    public ICResponse<List<FrontCategoryAssociatedConfigurationDTO>> getAssociateConfigurationList(FrontCategoryAssociatedConfigurationQueryRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        if (categoryId == null) {
            return ICResponse.fail("类目编号不能为空");
        }
        try {
            List<IcFrontCategory> categories = Lists.newArrayList();
            IcFrontCategory category = getCategory(categoryId);
            if (category == null) {
                return ICResponse.fail("类目不存在");
            }
            List<Long> catIds = Lists.newArrayList();
            Long parentCateId = category.getParentId();
            // 一级类目,取底下所有二级
            if (parentCateId == null || parentCateId == 0) {
                List<IcFrontCategory> cats = getCategoryByParentId(category.getId());
                if (CollectionUtils.isNotEmpty(cats)) {
                    categories.addAll(cats);
                    cats.forEach(c -> {
                        catIds.add(c.getId());
                    });
                }
            } else {
                catIds.add(category.getId());
                categories.add(category);
            }
            if (CollectionUtils.isEmpty(catIds)) {
                return ICResponse.success();
            }
            List<IcHqFcategoryRelation> hqFcategoryRelations = getCategoryRelation(catIds);
            if (CollectionUtils.isEmpty(hqFcategoryRelations)) {
                return ICResponse.success();
            }
            Map<Long, IcFrontCategory> id2cat = Maps.newHashMap();
            categories.forEach(c -> {
                id2cat.put(c.getId(), c);
            });
            List<FrontCategoryAssociatedConfigurationDTO> result = Lists.newArrayList();
            hqFcategoryRelations.forEach(r -> {
                FrontCategoryAssociatedConfigurationDTO dto = new FrontCategoryAssociatedConfigurationDTO();
                dto.setId(r.getId());
                dto.setFrontCategoryId(id2cat.get(r.getFrontCategoryId()).getId());
                dto.setFrontCategoryName(id2cat.get(r.getFrontCategoryId()).getName());
                dto.setFrontParentCategoryId(id2cat.get(r.getFrontCategoryId()).getParentId().longValue());
                dto.setAssociatedType(r.getRelationType());
                dto.setCreatorId(r.getCreateUserId());
                dto.setLastOperatorId(r.getUpdateUserId());
                dto.setCreatedTime(DateFormatUtils.format(r.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                dto.setLastUpdateTime(DateFormatUtils.format(r.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
                result.add(dto);
            });
            return ICResponse.success(result);
        } catch (Exception ex) {
            log.error("getAssociateConfigurationList ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<List<FrontCategoryAssociatedItemDTO>> getAssociateItemList(FrontCategoryAssociatedItemQueryRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        if (categoryId == null) {
            return ICResponse.fail("类目编号不能为空");
        }
        try {
            List<IcFrontCategory> categories = Lists.newArrayList();
            IcFrontCategory category = getCategory(categoryId);
            if (category == null) {
                return ICResponse.fail("类目不存在");
            }
            IcHqFcategoryRelation hqFcategoryRelation = getCategoryRelation(categoryId);
            if (hqFcategoryRelation == null) {
                return ICResponse.success(Collections.EMPTY_LIST);
            }
            Integer relationType = hqFcategoryRelation.getRelationType();
            if (relationType == null || (relationType != 1 && relationType != 2)) {
                return ICResponse.fail("绑定类型无效");
            }
            Long associatedId = hqFcategoryRelation.getId();
            List<FrontCategoryAssociatedItemDTO> dtos = Lists.newArrayList();
            if (relationType == 1) {
                IcHqFcategoryRelatedBcategoryExample query = new IcHqFcategoryRelatedBcategoryExample();
                query.setOrderByClause("display_order");
                IcHqFcategoryRelatedBcategoryExample.Criteria queryCriteria = query.createCriteria();
                queryCriteria.andRelationIdEqualTo(associatedId);
                queryCriteria.andIsDeletedEqualTo((byte) 0);
                List<IcHqFcategoryRelatedBcategory> relatedBcategories = icHqFcategoryRelatedBcategoryMapper.selectByExample(query);
                if (CollectionUtils.isEmpty(relatedBcategories)) {
                    return ICResponse.success();
                }
                List<Integer> bcatIds = Lists.newArrayList();
                relatedBcategories.forEach(rb -> {
                    bcatIds.add(rb.getBackendCategoryId());
                });
                List<CategoryDO> stdCats = categoryDAO.findByCategories(bcatIds);
                if (CollectionUtils.isEmpty(stdCats)) {
                    return ICResponse.success();
                }
                Map<Integer, CategoryDO> id2StdCat = Maps.newHashMap();
                stdCats.forEach(std -> {
                    id2StdCat.put(std.getClassifyId(), std);
                });
                relatedBcategories.forEach(r -> {
                    FrontCategoryAssociatedItemDTO dto = new FrontCategoryAssociatedItemDTO();
                    Integer backendCategoryId = r.getBackendCategoryId();
                    dto.setId(backendCategoryId.toString());
                    dto.setName(id2StdCat.get(backendCategoryId).getFatherName() + ">" + id2StdCat.get(backendCategoryId).getClassifyName());
                    dto.setSort(r.getDisplayOrder());
                    dtos.add(dto);
                });
            } else {
                IcHqFcategoryRelatedHqProductExample query = new IcHqFcategoryRelatedHqProductExample();
                query.setOrderByClause("display_order");
                IcHqFcategoryRelatedHqProductExample.Criteria queryCriteria = query.createCriteria();
                queryCriteria.andRelationIdEqualTo(associatedId);
                queryCriteria.andIsDeletedEqualTo((byte) 0);
                List<IcHqFcategoryRelatedHqProduct> relatedHqProdcuts = icHqFcategoryRelatedHqProductMapper.selectByExample(query);
                if (CollectionUtils.isEmpty(relatedHqProdcuts)) {
                    return ICResponse.success();
                }
                List<Long> hqProductIds = Lists.newArrayList();
                relatedHqProdcuts.forEach(rb -> {
                    hqProductIds.add(rb.getHqProductId());
                });
                List<TblHqProduct> tblHqProducts = tblHqProductCustomMapper.selectHqProductByIds(hqProductIds);
                if (CollectionUtils.isEmpty(tblHqProducts)) {
                    return ICResponse.success();
                }
                List<Integer> bcatIds = Lists.newArrayList();
                Map<Long, TblHqProduct> id2Product = Maps.newHashMap();
                tblHqProducts.forEach(std -> {
                    id2Product.put(std.getHqProductId().longValue(), std);
                    bcatIds.add(std.getClassifyId());
                });
                List<CategoryDO> stdCats = categoryDAO.findByCategories(bcatIds);
                Map<Integer, CategoryDO> id2StdCat = Maps.newHashMap();
                stdCats.forEach(std -> {
                    id2StdCat.put(std.getClassifyId(), std);
                });
                relatedHqProdcuts.forEach(r -> {
                    Long hpid = r.getHqProductId();
                    TblHqProduct hq = id2Product.get(hpid);
                    if (hq == null) {
                        return;
                    }
                    FrontCategoryAssociatedItemDTO dto = new FrontCategoryAssociatedItemDTO();
                    dto.setId(hpid.toString());
                    dto.setProductNo(hq.getProductNo());
                    dto.setName(hq.getHqProductName());
                    dto.setSort(r.getDisplayOrder());
                    dto.setProductImage(hq.getHqProductIcon());
                    dto.setProductCategoryName(id2StdCat.get(hq.getClassifyId()).getFatherName() + ">" + id2StdCat.get(hq.getClassifyId()).getClassifyName());
                    dtos.add(dto);
                });
            }
            return ICResponse.success(dtos);
        } catch (Exception ex) {
            log.error("getAssociateItemList ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    public ICResponse deleteRelation(FrontCategoryAssociatedDeleteRequest request) {
        log.info("##deleteRelation ==>request:{}", JSON.toJSON(request));
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        if (categoryId == null) {
            return ICResponse.fail("类目编号不能为空");
        }
        Integer operatorId = request.getOperatorId();
        if (operatorId == null) {
            return ICResponse.fail("操作人编号不能为空");
        }
        try {
            IcHqFcategoryRelation dbRelation = getCategoryRelation(categoryId);
            if (dbRelation == null) {
                return ICResponse.fail("类目无关联记录,无需");
            }
            Integer associatedType = dbRelation.getRelationType();
            Long relationId = dbRelation.getId();
            delRelation2DB(relationId, associatedType, operatorId);
            hqFcatCacheManager.write2Cache(categoryId, true, true);
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("saveAssociated ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Transactional
    public void delRelation2DB(Long relationId, Integer associatedType, Integer operatorId) {
        log.info("##delRelation2DB ==>request:relationId:{},associatedType:{},operatorId:{}", relationId, associatedType, operatorId);
        IcHqFcategoryRelation updRelation = new IcHqFcategoryRelation();
        updRelation.setId(relationId);
        updRelation.setIsDeleted((byte) 1);
        updRelation.setUpdateUserId(operatorId);
        icHqFcategoryRelationMapper.updateByPrimaryKeySelective(updRelation);

        if (associatedType == 1) {
            IcHqFcategoryRelatedBcategory delRecord = new IcHqFcategoryRelatedBcategory();
            delRecord.setIsDeleted((byte) 1);
            delRecord.setUpdateUserId(operatorId);
            IcHqFcategoryRelatedBcategoryExample delExample = new IcHqFcategoryRelatedBcategoryExample();
            IcHqFcategoryRelatedBcategoryExample.Criteria delExampleCriteria = delExample.createCriteria();
            delExampleCriteria.andRelationIdEqualTo(relationId);
            icHqFcategoryRelatedBcategoryMapper.updateByExampleSelective(delRecord, delExample);
        } else {
            IcHqFcategoryRelatedHqProduct delRecord = new IcHqFcategoryRelatedHqProduct();
            delRecord.setIsDeleted((byte) 1);
            delRecord.setUpdateUserId(operatorId);
            IcHqFcategoryRelatedHqProductExample delExample = new IcHqFcategoryRelatedHqProductExample();
            IcHqFcategoryRelatedHqProductExample.Criteria delExampleCriteria = delExample.createCriteria();
            delExampleCriteria.andRelationIdEqualTo(relationId);
            icHqFcategoryRelatedHqProductMapper.updateByExampleSelective(delRecord, delExample);
        }
    }

    @Override
    public ICResponse saveAssociated(FrontCategoryAssociatedSaveRequest request) {
        log.info("##saveAssociated ==>request:{}", JSON.toJSON(request));
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        if (categoryId == null) {
            return ICResponse.fail("类目编号不能为空");
        }
        Integer operatorId = request.getOperatorId();
        if (operatorId == null) {
            return ICResponse.fail("操作人编号不能为空");
        }
        Integer associatedType = request.getAssociatedType();
        if (associatedType == null || (associatedType != 1 && associatedType != 2)) {
            return ICResponse.fail("绑定类型无效");
        }
        List<FrontCategoryAssociatedItemRequest> items = request.getAssociatedItemRequestList();
        if (CollectionUtils.isEmpty(items)) {
            return ICResponse.fail("无绑定的商品或类目数据");
        }
        try {
            saveRelation2DB(categoryId, associatedType, operatorId, items);
            hqFcatCacheManager.write2Cache(categoryId, true, true);
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("saveAssociated ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Transactional
    public void saveRelation2DB(Long categoryId, Integer associatedType, Integer operatorId, List<FrontCategoryAssociatedItemRequest> items) {
        IcHqFcategoryRelation dbRelation = getCategoryRelationIncludeDeleted(categoryId);
        Long relationId;
        IcHqFcategoryRelation fcategoryRelation = new IcHqFcategoryRelation();
        fcategoryRelation.setFrontCategoryId(categoryId);
        fcategoryRelation.setRelationType(associatedType);
        fcategoryRelation.setUpdateUserId(operatorId);
        if (dbRelation == null) {
            fcategoryRelation.setCreateUserId(operatorId);
            icHqFcategoryRelationMapper.insertSelective(fcategoryRelation);
            relationId = fcategoryRelation.getId();
        } else {
            /*if(!dbRelation.getRelationType().equals(associatedType)){
                throw new IllegalArgumentException("前台分类已存在不同关联关系，不可变更关联关系");
            }*/
            relationId = dbRelation.getId();
            IcHqFcategoryRelationExample icHqFcategoryRelationExample = new IcHqFcategoryRelationExample();
            IcHqFcategoryRelationExample.Criteria criteria = icHqFcategoryRelationExample.createCriteria();
            criteria.andIdEqualTo(relationId);
            //criteria.andIsDeletedEqualTo((byte) 0);
            fcategoryRelation.setFrontCategoryId(null);
            fcategoryRelation.setIsDeleted((byte) 0);
            icHqFcategoryRelationMapper.updateByExampleSelective(fcategoryRelation, icHqFcategoryRelationExample);
        }

        if (associatedType == 1) {
            IcHqFcategoryRelatedBcategory delRecord = new IcHqFcategoryRelatedBcategory();
            delRecord.setIsDeleted((byte) 1);
            delRecord.setUpdateUserId(operatorId);
            IcHqFcategoryRelatedBcategoryExample delExample = new IcHqFcategoryRelatedBcategoryExample();
            IcHqFcategoryRelatedBcategoryExample.Criteria delExampleCriteria = delExample.createCriteria();
            delExampleCriteria.andRelationIdEqualTo(relationId);
            icHqFcategoryRelatedBcategoryMapper.updateByExampleSelective(delRecord, delExample);
            items.forEach(t -> {
                IcHqFcategoryRelatedBcategory associateDate = new IcHqFcategoryRelatedBcategory();
                associateDate.setRelationId(relationId);
                associateDate.setBackendCategoryId(Integer.valueOf(t.getId()));
                associateDate.setCreateUserId(operatorId);
                associateDate.setUpdateUserId(operatorId);
                associateDate.setDisplayOrder(t.getSort());
                icHqFcategoryRelatedBcategoryMapper.insertSelective(associateDate);
            });
        } else {
            List<Integer> hqProductId = Lists.newArrayList();
            items.forEach(i -> {
                hqProductId.add(Integer.valueOf(i.getId()));
            });
            List<ProductDTO> hqProductDtos = mgrProductServiceHelper.getProduct(hqProductId);
            Map<String, String> hqId2Product = Maps.newHashMap();
            if (CollectionUtils.isEmpty(hqProductDtos)) {
                throw new RuntimeException("商品不存在");
            }
            hqProductDtos.forEach(dto -> {
                hqId2Product.put(dto.getProductId().toString(), dto.getProductNo());
            });
            IcHqFcategoryRelatedHqProduct delRecord = new IcHqFcategoryRelatedHqProduct();
            delRecord.setIsDeleted((byte) 1);
            delRecord.setUpdateUserId(operatorId);
            IcHqFcategoryRelatedHqProductExample delExample = new IcHqFcategoryRelatedHqProductExample();
            IcHqFcategoryRelatedHqProductExample.Criteria delExampleCriteria = delExample.createCriteria();
            delExampleCriteria.andRelationIdEqualTo(relationId);
            icHqFcategoryRelatedHqProductMapper.updateByExampleSelective(delRecord, delExample);
            items.forEach(t -> {
                IcHqFcategoryRelatedHqProduct associateDate = new IcHqFcategoryRelatedHqProduct();
                associateDate.setRelationId(relationId);
                associateDate.setHqProductId(Long.valueOf(t.getId()));
                associateDate.setProductNo(hqId2Product.get(t.getId()));
                associateDate.setCreateUserId(operatorId);
                associateDate.setUpdateUserId(operatorId);
                associateDate.setDisplayOrder(t.getSort());
                icHqFcategoryRelatedHqProductMapper.insertSelective(associateDate);
            });
        }
    }

    private IcFrontCategory getCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        IcFrontCategoryExample query = new IcFrontCategoryExample();
        IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
        queryCriteria.andIdEqualTo(categoryId);
        queryCriteria.andIsDeletedEqualTo((byte) 0);
        List<IcFrontCategory> cat = icFrontCategoryMapper.selectByExample(query);
        if (CollectionUtils.isEmpty(cat)) {
            return null;
        }
        return cat.get(0);
    }

    private List<IcFrontCategory> getCategoryByParentId(Long parentCategoryId) {
        if (parentCategoryId == null) {
            return null;
        }
        IcFrontCategoryExample query = new IcFrontCategoryExample();
        IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
        queryCriteria.andParentIdEqualTo(parentCategoryId);
        queryCriteria.andIsDeletedEqualTo((byte) 0);
        List<IcFrontCategory> cat = icFrontCategoryMapper.selectByExample(query);
        return cat;
    }

    private List<IcHqFcategoryRelation> getCategoryRelation(List<Long> catIds) {
        IcHqFcategoryRelationExample query = new IcHqFcategoryRelationExample();
        IcHqFcategoryRelationExample.Criteria createCriteria = query.createCriteria();
        createCriteria.andFrontCategoryIdIn(catIds);
        createCriteria.andIsDeletedEqualTo((byte) 0);
        List<IcHqFcategoryRelation> hqFcategoryRelations = icHqFcategoryRelationMapper.selectByExample(query);
        return hqFcategoryRelations;
    }

    private IcHqFcategoryRelation getCategoryRelation(Long catId) {
        IcHqFcategoryRelationExample query = new IcHqFcategoryRelationExample();
        IcHqFcategoryRelationExample.Criteria createCriteria = query.createCriteria();
        createCriteria.andFrontCategoryIdEqualTo(catId);
        createCriteria.andIsDeletedEqualTo((byte) 0);
        List<IcHqFcategoryRelation> hqFcategoryRelations = icHqFcategoryRelationMapper.selectByExample(query);
        if (CollectionUtils.isEmpty(hqFcategoryRelations)) {
            return null;
        }
        return hqFcategoryRelations.get(0);
    }

    private IcHqFcategoryRelation getCategoryRelationIncludeDeleted(Long catId) {
        IcHqFcategoryRelationExample query = new IcHqFcategoryRelationExample();
        IcHqFcategoryRelationExample.Criteria createCriteria = query.createCriteria();
        createCriteria.andFrontCategoryIdEqualTo(catId);
        List<IcHqFcategoryRelation> hqFcategoryRelations = icHqFcategoryRelationMapper.selectByExample(query);
        if (CollectionUtils.isEmpty(hqFcategoryRelations)) {
            return null;
        }
        return hqFcategoryRelations.get(0);
    }
}
