package com.mallcai.biz.category.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.api.category.CategoryService;
import com.mallcai.biz.category.dao.CategoryDAO;
import com.mallcai.biz.category.dao.redisdao.CategoryRedisDao;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.common.ICResponse;
import com.mallcai.common.PageDTO;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.category.request.CategoryEditRequest;
import com.mallcai.domain.category.request.CategoryQueryRequest;
import com.mallcai.domain.category.request.GetHierarchicalCategoryRequest;
import com.mallcai.domain.category.request.PageCategoryQeueryRequest;
import com.mallcai.domain.dataobject.category.CategoryDO;
import com.mallcai.domain.product.request.ExistedProductRequest;
import com.mallcai.domain.enums.CategoryLevelEnum;
import com.mallcai.domain.enums.OperationEnum;
import com.mallcai.domain.enums.ResponseCodeEnum;
import com.mallcai.mq.classify.ClassifyMsgDTO;
import com.mallcai.mq.classify.producer.ClassifyProducer;
import com.mallcai.utils.AppEnv;
import com.mallcai.utils.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service("categoryService")
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDAO categoryDAO;

    @Resource
    private CategoryRedisDao categoryRedisDao;

    @Autowired
    private ClassifyProducer classifyProducer;

    @Resource
    private AppEnv appEnv;

    @Autowired
    private MgrProductServiceHelper mgrProductServiceHelper;

    @Override
    public ICResponse<Boolean> editCategory(CategoryEditRequest category) {
        if (category == null) {
            log.warn("[editCategory]请求参数为空:{}", category);
            return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
        }
        ICResponse<Boolean> validResponse = validCategory(category);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        CategoryDO categoryDO = BeanCopyUtil.copyBean(category, CategoryDO.class);
        if (Objects.equals(category.getOperationEnum(), OperationEnum.ADD)) {
            try {
                Integer createResult = categoryDAO.createCategory(categoryDO);
                category.setClassifyId(categoryDO.getClassifyId());
                if (createResult != null && createResult > 0) {
                    return ICResponse.success(true);
                }
                return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_CREATE_FAIL);
            } catch (Exception ex) {
                log.error(String.format("[editCategory],创建分类失败，category=>%s", category), ex);
                return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_CREATE_FAIL);
            }
        }
        if (Objects.equals(category.getOperationEnum(), OperationEnum.EDIT)) {
            if (category.getClassifyId() == null) {
                log.warn("[editCategory]请求参数不包含分类Id");
                return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
            }
            try {
                Integer editResult = categoryDAO.editCategory(categoryDO);
                if (editResult > 0) {
                    CategoryDO editCategoryDo = categoryDAO.loadCategory(categoryDO.getClassifyId());
                    if (editCategoryDo != null) {
                        if (editCategoryDo.getLevel() != null && editCategoryDo.getLevel() == 1) {
                            //如果是一级类目，只刷新一级类目
                            categoryRedisDao.refreshFirstCategory();
                        } else if (editCategoryDo.getLevel() != null && editCategoryDo.getLevel() == 2) {
                            //如果是二级类目，刷新二级类目对应的一级类目的缓存
                            categoryRedisDao.refreshSecondCategory(editCategoryDo.getFatherId());
                        }
                        classifyProducer.sendClassifyUpdate(new ClassifyMsgDTO(editCategoryDo.getClassifyId(), editCategoryDo.getClassifyName()));
                        return ICResponse.success(true);
                    }
                }
                return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_UPATE_FAIL);
            } catch (Exception ex) {
                log.error(String.format("[editCategory],更新分类失败，category=>%s", category), ex);
                return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_UPATE_FAIL);
            }
        }
        return ICResponse.fail(ResponseCodeEnum.INVALID_OPERATION);
    }

    @Override
    public ICResponse<List<CategoryDTO>> listCategory(CategoryQueryRequest request) {
        if (request == null) {
            log.warn("[listCategory]requst is null");
            return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
        }
        try {
            CategoryDO categoryDO = new CategoryDO();
            BeanUtils.copyProperties(request, categoryDO);
            List<CategoryDO> categoryList = categoryDAO.listCategory(categoryDO);
            List<CategoryDTO> categoryDTOList = BeanCopyUtil.copyList(categoryList, CategoryDTO.class);
            return ICResponse.success(categoryDTOList);
        } catch (Exception ex) {
            log.error(String.format("[listCategory]query Exception,requestParam:%s", request), ex);
            return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_QUERY_FAIL);
        }
    }

    @Override
    public ICResponse<List<CategoryDTO>> categoryPageList(PageCategoryQeueryRequest request) {
        if (request == null) {
            log.warn("[listCategory]requst is null");
            return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
        }
        CategoryDO categoryDO = BeanCopyUtil.copyBean(request, CategoryDO.class);
        categoryDO.setFatherId(request.getParentId());
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        try {
            List<CategoryDO> categoryDOS = categoryDAO.listCategory(categoryDO);
            List<CategoryDTO> categoryDTO = BeanCopyUtil.copyList(categoryDOS, CategoryDTO.class);
            PageDTO pageDTO = new PageDTO(request.getPageSize(), (int) ((Page) categoryDOS).getTotal(), request.getCurrentPage());
            return ICResponse.success(categoryDTO, pageDTO);

        } catch (Exception ex) {
            log.error(String.format("categoryPageList error,request:%s", request), ex);
            return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_QUERY_FAIL);
        }
    }

    @Override
    @Transactional
    public ICResponse<Boolean> delCategoryByIdList(List<Integer> categoryIdList) {
        if (CollectionUtils.isEmpty(categoryIdList)) {
            return ICResponse.success();
        }

        boolean allow = true;
        for (Integer categoryId : categoryIdList) {
            if (!allow) {
                break;
            }
            CategoryDO categoryDO = categoryDAO.loadCategory(categoryId);
            if (categoryDO == null)
                continue;
            //如果是一级分类，检查一级分类下的所有二级分类是否有商品
            if (categoryDO.getLevel().equals(CategoryLevelEnum.LEVEL_ONE.getCode())) {
                CategoryDO categoryQuery = new CategoryDO();
                categoryQuery.setFatherId(categoryDO.getClassifyId());
                List<CategoryDO> categoryLv2List = categoryDAO.listCategory(categoryQuery);
                if (!CollectionUtils.isEmpty(categoryLv2List)) {
                    for (CategoryDO lv2Category : categoryLv2List) {
                        ExistedProductRequest request = new ExistedProductRequest();
                        request.setCategoryId(lv2Category.getClassifyId());
                        if (mgrProductServiceHelper.existedProduct(request)) {
                            allow = false;
                            break;
                        }
                    }
                }
                categoryRedisDao.refreshFirstCategory();
            } else if (Objects.equals(categoryDO.getLevel(), CategoryLevelEnum.LEVEL_TWO.getCode())) {
                ExistedProductRequest request = new ExistedProductRequest();
                request.setCategoryId(categoryId);
                if (mgrProductServiceHelper.existedProduct(request)) {
                    allow = false;
                    break;
                }
                categoryRedisDao.refreshSecondCategory(categoryDO.getFatherId());
            } else {
                log.error("存在异常分类信息:{}", categoryDO);
                return ICResponse.fail(ResponseCodeEnum.SYSTEM_ERROR);
            }

        }
        if (allow) {
            try {
                boolean b = categoryDAO.delByCategoryList(categoryIdList);

                return ICResponse.success(b);
            } catch (Exception ex) {
                log.error(String.format("删除分类异常，请检查,request:%s", JSON.toJSONString(categoryIdList)), ex);
                return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_DEL_FAIL);
            }

        }
        return ICResponse.fail("删除失败，请检查删除的分类是否包含商品");
    }

    @Override
    public ICResponse<List<CategoryDTO>> getHierarchicalCategory(GetHierarchicalCategoryRequest getHierarchicalCategoryRequest) {
        if (getHierarchicalCategoryRequest == null) {
            return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
        }
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setFatherId(getHierarchicalCategoryRequest.getParentId());
        categoryDO.setLevel(getHierarchicalCategoryRequest.getLevel());
        try {
            List<CategoryDO> categoryDOS = categoryDAO.listCategory(categoryDO);
            List<CategoryDTO> categoryDTO = BeanCopyUtil.copyList(categoryDOS, CategoryDTO.class);
            return ICResponse.success(categoryDTO);
        } catch (Exception ex) {
            log.error(String.format("[getHierarchicalCategory] error,request:%s", JSON.toJSONString(getHierarchicalCategoryRequest
            )), ex);
            return ICResponse.fail(ResponseCodeEnum.DB_OPERATION_QUERY_FAIL);
        }
    }

    @Override
    public ICResponse<CategoryDTO> loadCategoryById(Integer categoryId) {
        if (categoryId == null) {
            return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
        }
        CategoryDO categoryDO = categoryDAO.loadCategory(categoryId);
        CategoryDTO categoryDTO = BeanCopyUtil.copyBean(categoryDO, CategoryDTO.class);
        return ICResponse.success(categoryDTO);
    }

    @Override
    public ICResponse<List<CategoryDTO>> findByCategoryIdList(List<Integer> categoryIdList) {
        if (CollectionUtils.isEmpty(categoryIdList)) {
            return ICResponse.fail(ResponseCodeEnum.INVALID_PARAM);
        }
        try {
            List<CategoryDO> categoryList = categoryDAO.findByCategories(categoryIdList);
            List<CategoryDTO> categoryDTOList = BeanCopyUtil.copyList(categoryList, CategoryDTO.class);
            return ICResponse.success(categoryDTOList);
        } catch (Exception ex) {
            log.error(String.format("[分类信息批量查询失败],,param:%s", JSON.toJSONString(categoryIdList)), ex);
            return ICResponse.fail(ResponseCodeEnum.SYSTEM_ERROR);
        }

    }

    @Override
    public ICResponse<Boolean> refreshALlSecondCategory() {
        try {
            categoryRedisDao.refreshAllSecondCategory();
            return ICResponse.success(false);
        } catch (Exception ex) {
            log.error("refreshALlSecondCategory fail", ex);
            return ICResponse.fail(ex.getMessage());
        }
    }


    private ICResponse<Boolean> validCategory(CategoryEditRequest request) {
        if (request == null) {
            return ICResponse.fail("请求入参不能为空");
        }
        String msg = "请求参数缺失,请检查参数列表:参数：%s,请求入参：%s";
        if (request.getClassifyName() == null) {
            return ICResponse.fail(String.format(msg, "classifyName", request));
        }

        if (request.getUpdateUserId() == null) {
            return ICResponse.fail(String.format(msg, "updateUserId", request));
        }
        if (OperationEnum.ADD.equals(request.getOperationEnum())) {
            if (request.getCreateUserId() == null) {
                return ICResponse.fail(String.format(msg, "createUseId", request));
            }
            if (request.getLevel() == null) {
                return ICResponse.fail(String.format(msg, "level", request));
            }
            if (request.getClassifyNo() == null) {
                return ICResponse.fail(String.format(msg, "classifyNo", request));
            }

        }
        return ICResponse.success();
    }
}
