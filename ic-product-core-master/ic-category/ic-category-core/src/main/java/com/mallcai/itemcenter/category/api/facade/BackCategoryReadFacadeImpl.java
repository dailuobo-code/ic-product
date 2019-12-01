package com.mallcai.itemcenter.category.api.facade;

import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.api.converter.output.BackCategoryInfoConverter;
import com.mallcai.itemcenter.category.model.BackCategory;
import com.mallcai.itemcenter.category.service.BackCategoryDomainReadService;
import com.mallcai.itemcenter.dto.ICResponse;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.utils.GenericConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BackCategoryReadFacadeImpl
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 15:42<br/>
 */
@Slf4j
@Component
public class BackCategoryReadFacadeImpl implements BackCategoryReadFacade {
    private final BackCategoryInfoConverter backCategoryInfoConverter;
    private final BackCategoryDomainReadService backCategoryDomainReadService;

    public BackCategoryReadFacadeImpl(BackCategoryInfoConverter backCategoryInfoConverter,
                                      BackCategoryDomainReadService backCategoryDomainReadService) {
        this.backCategoryInfoConverter = backCategoryInfoConverter;
        this.backCategoryDomainReadService = backCategoryDomainReadService;
    }

    @Override
    public ICResponse<List<BackCategoryInfo>> findChildrenByPid(BackCategoryQueryChildrenRequest request) {
        try {
            List<BackCategory> list = backCategoryDomainReadService.findChildrenByPid(request.getPid());
            List<BackCategoryInfo> r = GenericConverter.batchConvert(list, backCategoryInfoConverter::domain2dto);
            return ICResponse.ok(r);
        } catch (ServiceException e) {
            log.warn(e.getMessage());
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ICResponse.fail("backCategory.find.fail");
        }
    }

    /**
     * 查询后台类目
     *
     * @param request 类目id
     * @return
     */
    @Override
    public ICResponse<BackCategoryInfo> findById(BackCategoryQueryRequest request) {
        try {
            BackCategory bc = backCategoryDomainReadService.findById(request.getId());
            return ICResponse.ok(backCategoryInfoConverter.domain2dto(bc));
        } catch (ServiceException e) {
            log.warn(e.getMessage());
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ICResponse.fail("backCategory.find.fail");
        }
    }

    /**
     * 查询从一级类目到本类目的路径上的所有后台类目列表(包括本级类目)
     *
     * @param request 本级类目id
     * @return
     */
    @Override
    public ICResponse<List<BackCategoryInfo>> findAncestorsOf(BackCategoryQueryRequest request) {
        try {
            List<BackCategory> list = backCategoryDomainReadService.findAncestorsOf(request.getId());
            List<BackCategoryInfo> r = list.stream().map(backCategoryInfoConverter::domain2dto).collect(Collectors.toList());
            return ICResponse.ok(r);
        } catch (ServiceException e) {
            log.warn(e.getMessage());
            return ICResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ICResponse.fail("backCategory.find.fail");
        }
    }
}
