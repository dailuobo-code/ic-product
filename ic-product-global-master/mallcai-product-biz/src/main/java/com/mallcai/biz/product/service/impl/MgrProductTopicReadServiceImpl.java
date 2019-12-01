package com.mallcai.biz.product.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.mallcai.api.product.backend.MgrProductTopicReadService;
import com.mallcai.backend.common.api.PageInfo;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.exception.ServiceException;
import com.mallcai.backend.search.common.utils.GenericConverter;
import com.mallcai.biz.cacher.CategoryCacheManager;
import com.mallcai.biz.cacher.ProductTopicCacheManager;
import com.mallcai.biz.product.converter.ProductTopicConverter;
import com.mallcai.biz.product.dao.ProductTopicDao;
import com.mallcai.biz.product.model.ProductTopicDO;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.ProductTopicDTO;
import com.mallcai.domain.product.request.PagingProductTopicRequest;
import com.mallcai.domain.product.request.QueryActiveProductTopicRequest;
import com.mallcai.domain.product.request.QueryOneProductTopicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * MgrProductTopicReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-20 10:15<br/>
 */
@Slf4j
@Service
public class MgrProductTopicReadServiceImpl implements MgrProductTopicReadService {
    private final ProductTopicCacheManager productTopicCacheManager;
    private final CategoryCacheManager categoryCacheManager;
    private final ProductTopicDao productTopicDao;
    private final ProductTopicConverter productTopicConverter;

    public MgrProductTopicReadServiceImpl(ProductTopicCacheManager productTopicCacheManager,
                                          CategoryCacheManager categoryCacheManager,
                                          ProductTopicDao productTopicDao,
                                          ProductTopicConverter productTopicConverter) {
        this.productTopicCacheManager = productTopicCacheManager;
        this.categoryCacheManager = categoryCacheManager;
        this.productTopicDao = productTopicDao;
        this.productTopicConverter = productTopicConverter;
    }

    /**
     * 分页查询多规格商品组
     *
     * @param request 分页查询参数 {@link PagingProductTopicRequest}
     * @return 多规格商品 {@link ProductTopicDTO}  的分页消息
     */
    @Override
    public ICResponse<Paging<ProductTopicDTO>> pagingProductTopic(PagingProductTopicRequest request) {
        try {
            Map<String, Object> criteria = Maps.newHashMap();
            criteria.put("nameLike", request.getName());
            criteria.put("status", request.getStatus());
            PageInfo.of(request.getCurrentPage(), request.getPageSize()).into(criteria);

            Paging<ProductTopicDO> page = productTopicDao.paging(criteria);
            List<ProductTopicDTO> data = GenericConverter.batchConvert(page.getData(), productTopicConverter::domain2dto);
            for (ProductTopicDTO it : data) {
                if (!CollectionUtils.isEmpty(it.getClassifyIds())) {
                    it.setClassifies(categoryCacheManager.findByIds(it.getClassifyIds())
                            .stream()
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
                }
            }

            return ICResponse.success(new Paging<>(page.getTotal(), data));
        } catch (ServiceException e) {
            log.error("fail to pagingProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to pagingProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("查询商品推荐主题失败");
        }
    }

    /**
     * 展示所有生效推荐主题
     *
     * @param request 查询方法可以通过 {@link QueryActiveProductTopicRequest#instance()} 初始化
     * @return 生效的 {@link ProductTopicDTO} 列表
     */
    @Override
    public ICResponse<List<ProductTopicDTO>> queryActiveProductTopic(QueryActiveProductTopicRequest request) {
        try {
            List<ProductTopicDTO> found = productTopicCacheManager.listActive();
            found.forEach(it ->
                    it.setClassifies(
                            categoryCacheManager.findByIds(it.getClassifyIds())));
            return ICResponse.success(found);
        } catch (ServiceException e) {
            log.error("fail to queryActiveProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryActiveProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("查询商品推荐主题失败");
        }
    }

    /**
     * 查询一个生效推荐主题
     *
     * @param request 查询条件 {@link QueryOneProductTopicRequest}
     * @return 一个 {@link ProductTopicDTO} 或者 null
     */
    @Override
    public ICResponse<ProductTopicDTO> queryOneProductTopic(QueryOneProductTopicRequest request) {
        request.checkParam();
        try {
            ProductTopicDTO found = productTopicCacheManager.findById(request.getId());
            found.setClassifies(categoryCacheManager.findByIds(found.getClassifyIds()));
            return ICResponse.success(found);
        } catch (ServiceException e) {
            log.error("fail to queryOneProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryOneProductTopic by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            return ICResponse.fail("查询商品推荐主题失败");
        }
    }
}
