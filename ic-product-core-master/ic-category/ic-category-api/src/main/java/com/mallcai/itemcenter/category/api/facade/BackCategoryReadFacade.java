package com.mallcai.itemcenter.category.api.facade;

import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.dto.ICResponse;

import java.util.List;

/**
 * BackCategoryReadFacade
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 17:37<br/>
 */
public interface BackCategoryReadFacade {
    /**
     * 下属类目
     *
     * @param request 类目pid
     * @return
     */
    ICResponse<List<BackCategoryInfo>> findChildrenByPid(BackCategoryQueryChildrenRequest request);

    /**
     * 查询后台类目
     *
     * @param request 类目id
     * @return
     */
    ICResponse<BackCategoryInfo> findById(BackCategoryQueryRequest request);

    /**
     * 查询从一级类目到本类目的路径上的所有后台类目列表(包括本级类目)
     *
     * @param request 本级类目id
     * @return
     */
    ICResponse<List<BackCategoryInfo>> findAncestorsOf(BackCategoryQueryRequest request);
}
