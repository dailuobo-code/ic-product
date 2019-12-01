package com.mallcai.biz.product.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.mallcai.api.product.backend.MgrProductTopicWriteService;
import com.mallcai.backend.common.exception.ServiceException;
import com.mallcai.biz.category.dao.CategoryDAO;
import com.mallcai.biz.product.converter.ProductTopicConverter;
import com.mallcai.biz.product.dao.mapper.ProductTopicMapper;
import com.mallcai.biz.product.manager.ProductTopicManager;
import com.mallcai.biz.product.model.ProductTopicDO;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.dataobject.category.CategoryDO;
import com.mallcai.domain.product.request.CreateProductTopicRequest;
import com.mallcai.domain.product.request.DeleteOneProductTopicRequest;
import com.mallcai.domain.product.request.UpdateOneProductTopicRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * MgrProductTopicWriteServiceImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 19:47<br/>
 */
@Slf4j
@Service
public class MgrProductTopicWriteServiceImpl implements MgrProductTopicWriteService {
    private final CategoryDAO categoryDAO;

    private final ProductTopicConverter productTopicConverter;
    private final ProductTopicManager productTopicManager;
    private final ProductTopicMapper productTopicMapper;

    public MgrProductTopicWriteServiceImpl(CategoryDAO categoryDAO,
                                           ProductTopicConverter productTopicConverter,
                                           ProductTopicManager productTopicManager,
                                           ProductTopicMapper productTopicMapper) {
        this.categoryDAO = categoryDAO;
        this.productTopicConverter = productTopicConverter;
        this.productTopicManager = productTopicManager;
        this.productTopicMapper = productTopicMapper;
    }

    /**
     * 创建一个首页推荐分类
     *
     * @param request 创建单个首页推荐分类请求 {@link CreateProductTopicRequest}
     * @return 商品组的 ID
     */
    @Override
    public ICResponse<Integer> createProductTopic(CreateProductTopicRequest request) {
        request.checkParam();
        try {
            // 校验二级类目、且必修存在
            List<Integer> cids = checkCategory(request.getClassifies(), request.getClassifyNos());

            // 入库
            ProductTopicDO create = productTopicConverter.request2domain(request);
            create.setClassifyIds(cids);
            productTopicManager.create(create);
            return ICResponse.success(create.getId());
        } catch (ServiceException e) {
            log.error("fail to createProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to createProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("创建首页推荐主题失败");
        }
    }

    /**
     * 更新首页推荐分类名称、关联商品、状态
     *
     * @param request 更新单个首页推荐分类名称、关联类目、状态的请求 {@link UpdateOneProductTopicRequest}
     * @return 更新结果
     */
    @Override
    public ICResponse<Boolean> updateOneProductTopic(UpdateOneProductTopicRequest request) {
        request.checkParam();
        try {
            ProductTopicDO exist = productTopicMapper.findById(request.getId());
            if (exist == null) {
                log.warn("product topic not found {}", request);
                return ICResponse.success(Boolean.FALSE);
            }
            // 校验二级类目、且必修存在
            List<Integer> cids = checkCategory(request.getClassifies(), request.getClassifyNos());

            ProductTopicDO update = productTopicConverter.request2domain(request);
            update.setClassifyIds(cids);

            if (exist.getName().equals(update.getName())) {
                update.setName(null);
            }
            if (Objects.equals(exist.getStatus(), update.getStatus())) {
                update.setStatus(null);
            }
            productTopicManager.update(update);
            return ICResponse.success(Boolean.TRUE);
        } catch (ServiceException e) {
            log.error("fail to updateOneProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to updateOneProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("更新首页推荐主题失败");
        }
    }

    private List<Integer> checkCategory(List<Integer> classifies, List<String> classifyNos) {
        if (CollectionUtils.isNotEmpty(classifies)) {
            List<CategoryDO> categories = categoryDAO.findByCategories(classifies);
            Map<Integer, CategoryDO> indexed = Maps.newHashMap();
            for (CategoryDO category : categories) {
                indexed.put(category.getClassifyId(), category);
            }
            for (Integer it : classifies) {
                if (!indexed.containsKey(it) || indexed.get(it).getLevel() != 2) {
                    throw new ServiceException(String.format("类目(id=%d)不存在或该类目不是二级类目", it));
                }
            }
            return new ArrayList<>(indexed.keySet());
        } else if (CollectionUtils.isNotEmpty(classifyNos)) {
            List<CategoryDO> categories = categoryDAO.findByCategoryNos(classifyNos);
            Map<String, CategoryDO> indexed = Maps.newHashMap();
            for (CategoryDO category : categories) {
                indexed.put(category.getClassifyNo(), category);
            }
            for (String it : classifyNos) {
                if (!indexed.containsKey(it) || indexed.get(it).getLevel() != 2) {
                    throw new ServiceException(String.format("类目(id=%s)不存在或该类目不是二级类目", it));
                }
            }
            return indexed.values().stream().map(CategoryDO::getClassifyId).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 删除一个首页推荐分类
     *
     * @param request 删除单个首页推荐分类请求 {@link DeleteOneProductTopicRequest}
     * @return 操作结果
     */
    @Override
    public ICResponse<Boolean> deleteOneProductTopic(DeleteOneProductTopicRequest request) {
        request.checkParam();
        try {
            ProductTopicDO exist = productTopicMapper.findById(request.getId());
            if (exist == null) {
                log.warn("product topic not found {}", request);
                return ICResponse.success(Boolean.FALSE);
            }

            ProductTopicDO delete = new ProductTopicDO();
            delete.setId(exist.getId());
            delete.setOperatorId(request.getOperatorId());
            delete.setOperator(request.getOperator());
            productTopicManager.delete(delete);
            return ICResponse.success(Boolean.TRUE);
        } catch (ServiceException e) {
            log.error("fail to deleteOneProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to deleteOneProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("删除首页推荐主题失败");
        }
    }
}
