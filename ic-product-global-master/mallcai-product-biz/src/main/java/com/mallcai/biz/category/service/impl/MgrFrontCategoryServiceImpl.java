package com.mallcai.biz.category.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mallcai.api.category.CategoryService;
import com.mallcai.api.category.front.MgrFrontCategoryAssociatedService;
import com.mallcai.api.category.front.MgrFrontCategoryService;
import com.mallcai.biz.category.dao.mapper.IcFrontCategoryMapper;
import com.mallcai.biz.category.dao.mapper.IcHqFcategoryRelationCustomMapper;
import com.mallcai.biz.category.manager.HqFcatCacheManager;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.category.front.dto.FrontCategoryDTO;
import com.mallcai.domain.category.front.request.*;
import com.mallcai.domain.category.request.CategoryQueryRequest;
import com.mallcai.domain.dataobject.category.FcatRelatedItemCntDO;
import com.mallcai.domain.dataobject.category.IcFrontCategory;
import com.mallcai.domain.dto.IcFrontCategoryExample;
import com.mallcai.domain.dto.TblProductClassifyExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class MgrFrontCategoryServiceImpl implements MgrFrontCategoryService {

    @Resource
    private IcFrontCategoryMapper icFrontCategoryMapper;
    @Resource
    private IcHqFcategoryRelationCustomMapper icHqFcategoryRelationCustomMapper;
    @Resource
    private HqFcatCacheManager hqFcatCacheManager;
    @Resource
    private CategoryService categoryService;
    @Resource
    private MgrFrontCategoryAssociatedService mgrFrontCategoryAssociatedService;

    public ICResponse manuallyRefresh() {
        try {
            hqFcatCacheManager.manuallyRefresh();
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("initCategory ex", ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    public ICResponse initCategory() {
        try {
            CategoryQueryRequest catReq = new CategoryQueryRequest();
            ICResponse<List<CategoryDTO>> catRes = categoryService.listCategory(catReq);
            if (catRes == null) {
                log.error("listCategory return null");
                return ICResponse.fail("listCategory return null");
            }
            if (!catRes.isSuccess()) {
                log.error("listCategory return {}", catRes.getMsg());
                return ICResponse.fail(catRes.getMsg());
            }
            List<CategoryDTO> backendCats = catRes.getData();
            if (CollectionUtils.isEmpty(backendCats)) {
                return ICResponse.fail("后台类目无数据");
            }
            List<CategoryDTO> pBackendLevel1Cats = Lists.newArrayList();
            List<CategoryDTO> cBackendLevel2Cats = Lists.newArrayList();
            backendCats.forEach(bc -> {
                Integer level = bc.getLevel();
                if (level == null) {
                    return;
                }
                if (level == 1) {
                    pBackendLevel1Cats.add(bc);
                }
                if (level == 2) {
                    cBackendLevel2Cats.add(bc);
                }
            });
            Map<Integer, Long> bCatId2FCatId = Maps.newHashMap();
            pBackendLevel1Cats.forEach(bc -> {
                FrontCategoryAddRequest addRequest = new FrontCategoryAddRequest();
                addRequest.setCategoryName(bc.getClassifyName());
                addRequest.setSort(bc.getShowOrder());
                addRequest.setOperatorId(8);
                ICResponse<Long> addCategoryResponse = addCategory(addRequest);
                if (addCategoryResponse != null && addCategoryResponse.isSuccess() && addCategoryResponse.getData() != null) {
                    bCatId2FCatId.put(bc.getClassifyId(), addCategoryResponse.getData());
                }
            });

            Map<Integer, Long> bCatId2FCatIdWithLevel2 = Maps.newHashMap();
            Map<Long, Integer> fCatId2BCatIdWithLevel2 = Maps.newHashMap();
            List<Long> fCatLevel2 = Lists.newArrayList();
            cBackendLevel2Cats.forEach(bc -> {
                FrontCategoryAddRequest addRequest = new FrontCategoryAddRequest();
                addRequest.setCategoryName(bc.getClassifyName());
                addRequest.setSort(bc.getShowOrder());
                addRequest.setOperatorId(8);
                addRequest.setParentCategoryId(bCatId2FCatId.get(bc.getFatherId()));
                ICResponse<Long> addCategoryResponse = addCategory(addRequest);
                if (addCategoryResponse != null && addCategoryResponse.isSuccess() && addCategoryResponse.getData() != null) {
                    bCatId2FCatIdWithLevel2.put(bc.getClassifyId(), addCategoryResponse.getData());
                    fCatId2BCatIdWithLevel2.put(addCategoryResponse.getData(), bc.getClassifyId());
                    fCatLevel2.add(addCategoryResponse.getData());
                }
            });
            fCatLevel2.forEach(fcId -> {
                FrontCategoryAssociatedSaveRequest saveAssociatedReq = new FrontCategoryAssociatedSaveRequest();
                saveAssociatedReq.setCategoryId(fcId);
                saveAssociatedReq.setOperatorId(8);
                saveAssociatedReq.setAssociatedType(1);
                List<FrontCategoryAssociatedItemRequest> associatedItemRequestList = Lists.newArrayList();
                FrontCategoryAssociatedItemRequest itemRequest = new FrontCategoryAssociatedItemRequest();
                itemRequest.setId(fCatId2BCatIdWithLevel2.get(fcId).toString());
                itemRequest.setSort(1);
                associatedItemRequestList.add(itemRequest);
                saveAssociatedReq.setAssociatedItemRequestList(associatedItemRequestList);

                mgrFrontCategoryAssociatedService.saveAssociated(saveAssociatedReq);

                FrontCategoryEditEffectiveStatusRequest editEffectiveStatusRequest = new FrontCategoryEditEffectiveStatusRequest();
                editEffectiveStatusRequest.setCategoryId(fcId);
                editEffectiveStatusRequest.setEffectiveStatus(2);
                editEffectiveStatusRequest.setOperatorId(8);
                editCategoryEffectiveStatus(editEffectiveStatusRequest);
            });
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("initCategory ex", ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<List<FrontCategoryDTO>> getCategoryList(FrontCategoryQueryRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        try {
            IcFrontCategoryExample query = new IcFrontCategoryExample();
            query.setOrderByClause("display_order");
            IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
            Long catId = request.getCategoryId();
            if (catId != null) {
                queryCriteria.andIdEqualTo(catId);
            }
            String catName = request.getCategoryName();
            if (StringUtils.isNotBlank(catName)) {
                queryCriteria.andNameLike("%" + catName + "%");
            }
            Integer effectiveStatus = request.getEffectiveStatus();
            if (effectiveStatus != null) {
                if (effectiveStatus == 1 || effectiveStatus == 2) {
                    queryCriteria.andStatusEqualTo(effectiveStatus);
                }
            }
            Integer associatedStatus = request.getAssociatedStatus();
            if (associatedStatus != null) {
                if (associatedStatus != 1 && associatedStatus != 2) {
                    return ICResponse.fail("关联参数无效1:未关联,2:已关联");
                }
            }
            Long parentCatId = request.getParentCategoryId();
            if (parentCatId != null) {
                queryCriteria.andParentIdEqualTo(parentCatId);
            }
            Integer level = request.getCategoryLevel();
            if (level != null) {
                queryCriteria.andLevelEqualTo(level);
            }
            queryCriteria.andIsDeletedEqualTo((byte) 0);
            List<IcFrontCategory> filterCategories = icFrontCategoryMapper.selectByExample(query);
            if (CollectionUtils.isEmpty(filterCategories)) {
                return ICResponse.success(Collections.EMPTY_LIST);
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
                return ICResponse.success(Collections.EMPTY_LIST);
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
            List<FrontCategoryDTO> frontCategoryDTOS = Lists.newArrayList();
            Map<Long, FrontCategoryDTO> frontCategoryMap = Maps.newHashMap();
            categories.forEach(c -> {
                Long id = c.getId();
                Boolean isBing = BooleanUtils.toBoolean(isAssociate.get(id));
                // 条件是已启用,但实际未绑定数据的类目过滤掉
                if (effectiveStatus != null && effectiveStatus == 2 && !isBing && c.getLevel() == 2) {
                    return;
                }
                //
                if (associatedStatus != null && c.getLevel() == 2 && ((associatedStatus == 1 && isBing) || (associatedStatus == 2 && !isBing))) {
                    return;
                }
                FrontCategoryDTO dto = new FrontCategoryDTO();
                dto.setCategoryId(c.getId());
                dto.setParentCategoryId(c.getParentId() != null ? c.getParentId().longValue() : null);
                dto.setCategoryName(c.getName());
                dto.setCategoryDesc(c.getDescription());
                dto.setSort(c.getDisplayOrder());
                dto.setEffectiveStatus(isBing && c.getStatus() != null && c.getStatus() == 2 ? 2 : 1);
                dto.setAssociatedStatus(isBing ? 2 : 1);
                dto.setCategoryLevel(c.getLevel());
                //已绑定的类目失效
                dto.setDisabled(c.getLevel() == 1 ? false : isBing);
                frontCategoryDTOS.add(dto);
                frontCategoryMap.put(id, dto);
            });
            //转换层级
            List<FrontCategoryDTO> result = Lists.newArrayList();
            frontCategoryDTOS.forEach(c -> {
                Long pid = c.getParentCategoryId();
                if (pid != null && pid != 0L) {
                    FrontCategoryDTO pDto = frontCategoryMap.get(pid);
                    if (pDto != null) {
                        pDto.getChildFrontCategorys().add(c);
                        if (c.getEffectiveStatus() == 2) {
                            pDto.setEffectiveStatus(2);
                        }
                        if (c.getAssociatedStatus() == 2) {
                            pDto.setAssociatedStatus(2);
                        }
                    }
                } else {
                    result.add(c);
                }
            });
            Iterator<FrontCategoryDTO> iter = result.iterator();
            while (iter.hasNext()) {
                FrontCategoryDTO frontCategoryDTO = iter.next();
                if (effectiveStatus != null || associatedStatus != null) {
                    if (CollectionUtils.isEmpty(frontCategoryDTO.getChildFrontCategorys())) {
                        iter.remove();
                    }
                }
            }
            return ICResponse.success(result);
        } catch (Exception ex) {
            log.error("getCategoryList ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<Long> addCategory(FrontCategoryAddRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long parentCategoryId = request.getParentCategoryId();
        String categoryName = request.getCategoryName();
        String categoryDesc = request.getCategoryDesc();
        Integer sort = request.getSort();
        Integer operatorId = request.getOperatorId();
/*        if (StringUtils.isBlank(categoryName) || categoryName.length() > 5) {
            return ICResponse.fail("分类名称必填且不能超过5个字");
        }
        if (StringUtils.isBlank(categoryDesc) || categoryDesc.length() > 20) {
            return ICResponse.fail("分类说明必填且不能超过20个字");
        }*/
        if (sort == null) {
            return ICResponse.fail("排序必填");
        }
        IcFrontCategory pcat = null;
        if (parentCategoryId != null) {
            pcat = getCategory(parentCategoryId);
            if (pcat == null || (pcat.getIsDeleted() != null && pcat.getIsDeleted() == 1)) {
                return ICResponse.fail("父类目不存在");
            }
        }
        if (operatorId == null) {
            return ICResponse.fail("操作人员编号不能为空");
        }
        int catLevel = 1;
        if (pcat != null && pcat.getLevel() != null) {
            catLevel = pcat.getLevel() + 1;
        }
        try {
            IcFrontCategory icFrontCategory = new IcFrontCategory();
            icFrontCategory.setName(categoryName);
            icFrontCategory.setLevel(catLevel);
            icFrontCategory.setParentId(pcat == null ? 0 : pcat.getId());
            icFrontCategory.setDisplayOrder(sort);
            icFrontCategory.setDescription(categoryDesc);
            icFrontCategory.setCreateUserId(operatorId);
            icFrontCategory.setUpdateUserId(operatorId);
            icFrontCategoryMapper.insertSelective(icFrontCategory);
            return ICResponse.success(icFrontCategory.getId());
        } catch (Exception ex) {
            log.error("addCategory ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse editCategory(FrontCategoryEditRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        String categoryName = request.getCategoryName();
        String categoryDesc = request.getCategoryDesc();
        Integer sort = request.getSort();
        Integer operatorId = request.getOperatorId();
        if (categoryId == null) {
            return ICResponse.fail("类目编号不能为空");
        }
/*        if (StringUtils.isBlank(categoryName) || categoryName.length() > 5) {
            return ICResponse.fail("分类名称必填且不能超过5个字");
        }
        if (StringUtils.isBlank(categoryDesc) || categoryDesc.length() > 20) {
            return ICResponse.fail("分类说明必填且不能超过20个字");
        }*/
        if (sort == null) {
            return ICResponse.fail("排序必填");
        }
        if (operatorId == null) {
            return ICResponse.fail("操作人员编号不能为空");
        }
        IcFrontCategoryExample query = new IcFrontCategoryExample();
        IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
        queryCriteria.andIdEqualTo(categoryId);
        try {
            IcFrontCategory icFrontCategory = new IcFrontCategory();
            icFrontCategory.setName(categoryName);
            icFrontCategory.setDisplayOrder(sort);
            icFrontCategory.setDescription(categoryDesc);
            icFrontCategory.setUpdateUserId(operatorId);
            icFrontCategoryMapper.updateByExampleSelective(icFrontCategory, query);
            hqFcatCacheManager.write2Cache(null, true, false);
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("editCategory ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse editCategoryEffectiveStatus(FrontCategoryEditEffectiveStatusRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        Integer effectiveStatus = request.getEffectiveStatus();
        Integer operatorId = request.getOperatorId();
        if (effectiveStatus == null) {
            return ICResponse.fail("生效状态不能为空");
        }
        if (effectiveStatus != 1 && effectiveStatus != 2) {
            return ICResponse.fail("生效状态数值无效,1:禁用,2:生效");
        }
        if (operatorId == null) {
            return ICResponse.fail("操作人员编号不能为空");
        }
        //类目是否有关联数据
        if (effectiveStatus == 2) {
            List<Long> isAssociateList = Lists.newArrayList(categoryId);
            List<FcatRelatedItemCntDO> isAssociateBCategory = icHqFcategoryRelationCustomMapper.isAssociateBCategory(isAssociateList);
            List<FcatRelatedItemCntDO> isAssociateProduct = icHqFcategoryRelationCustomMapper.isAssociateProduct(isAssociateList);
            Map<Long, Boolean> isAssociate = Maps.newHashMap();
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
            if (!BooleanUtils.toBoolean(isAssociate.get(categoryId))) {
                return ICResponse.fail("请先关联商品/类目再启用");
            }
        }
        IcFrontCategoryExample query = new IcFrontCategoryExample();
        IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
        queryCriteria.andIdEqualTo(categoryId);
        try {
            IcFrontCategory icFrontCategory = new IcFrontCategory();
            icFrontCategory.setStatus(effectiveStatus);
            icFrontCategory.setUpdateUserId(operatorId);
            icFrontCategoryMapper.updateByExampleSelective(icFrontCategory, query);
            hqFcatCacheManager.write2Cache(null, true, false);
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("editCategoryEffectiveStatus ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse deleteCategory(FrontCategoryDeleteRequest request) {
        if (request == null) {
            return ICResponse.fail("参数无效");
        }
        Long categoryId = request.getCategoryId();
        Integer operatorId = request.getOperatorId();
        if (operatorId == null) {
            return ICResponse.fail("操作人员编号不能为空");
        }
        IcFrontCategoryExample query = new IcFrontCategoryExample();
        IcFrontCategoryExample.Criteria queryCriteria = query.createCriteria();
        queryCriteria.andIdEqualTo(categoryId);
        try {
            IcFrontCategory icFrontCategory = new IcFrontCategory();
            icFrontCategory.setIsDeleted((byte) 1);
            icFrontCategory.setUpdateUserId(operatorId);
            icFrontCategoryMapper.updateByExampleSelective(icFrontCategory, query);
            hqFcatCacheManager.write2Cache(null, true, false);
            return ICResponse.success();
        } catch (Exception ex) {
            log.error("deleteCategory ex,req={}", JSON.toJSONString(request), ex);
            return ICResponse.fail(ex.getMessage());
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
}
