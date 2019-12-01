package com.mallcai.biz.category.manager;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mallcai.backend.common.redis.DefaultMasterJedisProxy;
import com.mallcai.biz.category.dao.mapper.*;
import com.mallcai.biz.product.dao.mapper.TblHqProductCustomMapper;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.front.dto.FrontCategoryAssociatedItemDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryDTO;
import com.mallcai.domain.common.ICRedisKey;
import com.mallcai.domain.dataobject.category.*;
import com.mallcai.domain.dataobject.product.TblHqProduct;
import com.mallcai.domain.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 总部类目缓存管理类
 */
@Service
@Slf4j
public class HqFcatCacheManager {

    @Resource
    private IcFrontCategoryMapper icFrontCategoryMapper;
    @Resource
    private IcHqFcategoryRelationCustomMapper icHqFcategoryRelationCustomMapper;
    @Resource
    private IcHqFcategoryRelationMapper icHqFcategoryRelationMapper;
    @Resource
    private IcHqFcategoryRelatedBcategoryMapper icHqFcategoryRelatedBcategoryMapper;
    @Resource
    private IcHqFcategoryRelatedHqProductMapper icHqFcategoryRelatedHqProductMapper;
    @Resource
    private TblHqProductCustomMapper tblHqProductCustomMapper;

    @Async
    public void write2Cache(Long leafCategoryIds, boolean refreshTree, boolean refreshAssociateItem) {
        if (refreshAssociateItem) {
            writeHqFcatAssociateItemToCache(leafCategoryIds);
        }
        if (refreshTree) {
            writeHqFcatTreeToCache();
        }
    }

    public void manuallyRefresh() {
        List<IcFrontCategory> cats = getAllEnableLeafCategory();
        if (CollectionUtils.isNotEmpty(cats)) {
            cats.forEach(c -> {
                writeHqFcatAssociateItemToCache(c.getId());
            });
        }
        writeHqFcatTreeToCache();
    }

    /**
     * 前台类目树写入缓存
     *
     * @return
     */
    public void writeHqFcatTreeToCache() {

        try {
            IcFrontCategoryExample query = new IcFrontCategoryExample();
            query.setOrderByClause("display_order");
            IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
            Integer effectiveStatus = 2;
            queryCriteria.andStatusEqualTo(effectiveStatus);
            queryCriteria.andIsDeletedEqualTo((byte) 0);
            List<IcFrontCategory> filterCategories = icFrontCategoryMapper.selectByExample(query);
            if (CollectionUtils.isEmpty(filterCategories)) {
                return;
            }
            Set<Long> leafCategoryList = Sets.newHashSet();
            Set<Long> leafCategoryParentIdList = Sets.newHashSet();
            Set<Long> parentCategoryList = Sets.newHashSet();
            Set<Long> leafHasNotParentId = Sets.newHashSet();
            filterCategories.forEach(c -> {
                Integer levelForMatch = c.getLevel();
                if (levelForMatch != null && levelForMatch == 2) {
                    leafCategoryList.add(c.getId());
                    leafCategoryParentIdList.add(c.getParentId());
                } else {
                    parentCategoryList.add(c.getId());
                }
            });
            leafCategoryParentIdList.forEach(lpid -> {
                if (!parentCategoryList.contains(lpid)) {
                    leafHasNotParentId.add(lpid);
                }
            });
            log.error("leafCategoryList:{}", JSON.toJSONString(leafCategoryList));
            // 根据ID再查一遍
            List<Long> queryIds = Lists.newArrayList(leafCategoryList);
            queryIds.addAll(parentCategoryList);
            queryIds.addAll(leafHasNotParentId);
            IcFrontCategoryExample queById = new IcFrontCategoryExample();
            queById.setOrderByClause("display_order");
            IcFrontCategoryExample.Criteria queryCriteriaById = queById.createCriteria();
            queryCriteriaById.andIsDeletedEqualTo((byte) 0);
            queryCriteriaById.andIdIn(queryIds);
            List<IcFrontCategory> categories = icFrontCategoryMapper.selectByExample(queById);
            if (CollectionUtils.isEmpty(categories)) {
                return;
            }

            Map<Long, Boolean> isAssociate = Maps.newHashMap();
            if (CollectionUtils.isNotEmpty(leafCategoryList)) {
                List<Long> isAssociateList = Lists.newArrayList(leafCategoryList);
                List<FcatRelatedItemCntDO> isAssociateBCategory = icHqFcategoryRelationCustomMapper.isAssociateBCategory(isAssociateList);
                List<FcatRelatedItemCntDO> isAssociateProduct = icHqFcategoryRelationCustomMapper.isAssociateProduct(isAssociateList);

                if (CollectionUtils.isNotEmpty(isAssociateBCategory)) {
                    isAssociateBCategory.forEach(data -> {
                        Long c = data.getFrontCategoryId();
                        Integer cnt = data.getCnt();
                        Boolean isHas = isAssociate.get(c);
                        if (isHas == null || (!isHas && cnt != null && cnt > 0)) {
                            isAssociate.put(c, cnt > 0);
                        }
                    });
                }
                if (CollectionUtils.isNotEmpty(isAssociateProduct)) {
                    isAssociateProduct.forEach(data -> {
                        Long c = data.getFrontCategoryId();
                        Integer cnt = data.getCnt();
                        Boolean isHas = isAssociate.get(c);
                        if (isHas == null || (!isHas && cnt != null && cnt > 0)) {
                            isAssociate.put(c, cnt > 0);
                        }
                    });
                }
            }
            List<HqFcatTreeCacheDto> frontCategoryDTOS = Lists.newArrayList();
            Map<Long, HqFcatTreeCacheDto> frontCategoryMap = Maps.newHashMap();
            categories.forEach(c -> {
                Long id = c.getId();
                Boolean isBing = BooleanUtils.toBoolean(isAssociate.get(id));
                // 条件是已启用,但实际未绑定数据的类目过滤掉
                if (effectiveStatus != null && effectiveStatus == 2 && !isBing && c.getLevel() == 2) {
                    return;
                }
                HqFcatTreeCacheDto dto = new HqFcatTreeCacheDto();
                dto.setCid(c.getId());
                dto.setPcid(c.getParentId() != null ? c.getParentId() : null);
                dto.setCname(c.getName());
                dto.setOrder(c.getDisplayOrder());
                frontCategoryDTOS.add(dto);
                frontCategoryMap.put(id, dto);
            });
            log.error("frontCategoryDTOS:{}", JSON.toJSONString(frontCategoryDTOS));
            //转换层级
            List<HqFcatTreeCacheDto> result = Lists.newArrayList();
            frontCategoryDTOS.forEach(c -> {
                Long pid = c.getPcid();
                if (pid != null && pid != 0L) {
                    HqFcatTreeCacheDto pDto = frontCategoryMap.get(pid);
                    if (pDto != null) {
                        pDto.getChild().add(c);
                    }
                } else {
                    result.add(c);
                }
            });
            log.error("result:{}", JSON.toJSONString(result));
            Iterator<HqFcatTreeCacheDto> iter = result.iterator();
            while (iter.hasNext()) {
                HqFcatTreeCacheDto frontCategoryDTO = iter.next();
                if (effectiveStatus != null) {
                    if (CollectionUtils.isEmpty(frontCategoryDTO.getChild())) {
                        iter.remove();
                    }
                }
            }
            DefaultMasterJedisProxy.getInstance().set(ICRedisKey.buildHqFcatTree(), JSON.toJSONString(result));
        } catch (Exception ex) {
            log.error("writeHqFcatTreeToCache ex", ex);
        }
    }

    public void writeHqFcatAssociateItemToCache(Long leafCategoryIds) {
        if (leafCategoryIds == null) {
            return;
        }
        try {
            IcFrontCategory category = getCategory(leafCategoryIds);
            if (category == null) {
                return;
            }
            IcHqFcategoryRelation hqFcategoryRelation = getCategoryRelation(leafCategoryIds);
            if (hqFcategoryRelation == null) {
                return;
            }
            Integer relationType = hqFcategoryRelation.getRelationType();
            if (relationType == null || (relationType != 1 && relationType != 2)) {
                return;
            }
            Long associatedId = hqFcategoryRelation.getId();
            HqFcatAssociatedCacheDto dto = new HqFcatAssociatedCacheDto();
            dto.setRelationType(relationType);
            if (relationType == 1) {
                IcHqFcategoryRelatedBcategoryExample query = new IcHqFcategoryRelatedBcategoryExample();
                query.setOrderByClause("display_order");
                IcHqFcategoryRelatedBcategoryExample.Criteria queryCriteria = query.createCriteria();
                queryCriteria.andRelationIdEqualTo(associatedId);
                queryCriteria.andIsDeletedEqualTo((byte) 0);
                List<IcHqFcategoryRelatedBcategory> relatedBcategories = icHqFcategoryRelatedBcategoryMapper.selectByExample(query);
                if (CollectionUtils.isEmpty(relatedBcategories)) {
                    return;
                }
                relatedBcategories.forEach(r -> {
                    HqFcatAssociatedCacheDto.HqFcatAssociatedItemCacheDto item = new HqFcatAssociatedCacheDto.HqFcatAssociatedItemCacheDto(r.getBackendCategoryId().toString(), r.getDisplayOrder());
                    dto.getItems().add(item);
                });
            } else {
                IcHqFcategoryRelatedHqProductExample query = new IcHqFcategoryRelatedHqProductExample();
                query.setOrderByClause("display_order");
                IcHqFcategoryRelatedHqProductExample.Criteria queryCriteria = query.createCriteria();
                queryCriteria.andRelationIdEqualTo(associatedId);
                queryCriteria.andIsDeletedEqualTo((byte) 0);
                List<IcHqFcategoryRelatedHqProduct> relatedHqProdcuts = icHqFcategoryRelatedHqProductMapper.selectByExample(query);
                if (CollectionUtils.isEmpty(relatedHqProdcuts)) {
                    return;
                }
                List<Long> hqProductIds = Lists.newArrayList();
                relatedHqProdcuts.forEach(rb -> {
                    if (StringUtils.isBlank(rb.getProductNo())) {
                        hqProductIds.add(rb.getHqProductId());
                    }
                });
                Map<Long, TblHqProduct> id2Product = Maps.newHashMap();
                if (CollectionUtils.isNotEmpty(hqProductIds)) {
                    List<TblHqProduct> tblHqProducts = tblHqProductCustomMapper.selectHqProductByIds(hqProductIds);
                    if (CollectionUtils.isNotEmpty(tblHqProducts)) {
                        tblHqProducts.forEach(std -> {
                            id2Product.put(std.getHqProductId().longValue(), std);
                        });
                    }
                }

                relatedHqProdcuts.forEach(r -> {
                    Long productId = r.getHqProductId();
                    TblHqProduct tblHqProduct = id2Product.get(productId);
                    String tempProductNo = tblHqProduct != null ? tblHqProduct.getProductNo() : "";
                    String id = StringUtils.isNotBlank(r.getProductNo()) ? r.getProductNo() : tempProductNo;
                    HqFcatAssociatedCacheDto.HqFcatAssociatedItemCacheDto item = new HqFcatAssociatedCacheDto.HqFcatAssociatedItemCacheDto(id, r.getDisplayOrder());
                    dto.getItems().add(item);
                });
            }
            if (CollectionUtils.isEmpty(dto.getItems())) {
                log.warn("有前台类目但均未关联数据");
                return;
            }
            DefaultMasterJedisProxy.getInstance().set(ICRedisKey.buildHqFcatAssociateItem(category.getId(), category.getParentId()), JSON.toJSONString(dto));
        } catch (Exception ex) {
            log.error("writeHqFcatAssociateItemToCache ex,leafCategoryIds={}", leafCategoryIds, ex);
        }
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

    /**
     * 所有已启用的二级类目
     *
     * @return
     */
    private List<IcFrontCategory> getAllEnableLeafCategory() {
        IcFrontCategoryExample query = new IcFrontCategoryExample();
        IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
        queryCriteria.andStatusEqualTo(2);
        queryCriteria.andIsDeletedEqualTo((byte) 0);
        queryCriteria.andLevelEqualTo(2);
        List<IcFrontCategory> cat = icFrontCategoryMapper.selectByExample(query);
        return cat;
    }
}
