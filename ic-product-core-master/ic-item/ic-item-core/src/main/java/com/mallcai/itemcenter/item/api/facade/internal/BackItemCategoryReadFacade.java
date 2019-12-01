package com.mallcai.itemcenter.item.api.facade.internal;

import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.api.facade.BackCategoryReadFacade;
import com.mallcai.itemcenter.dto.ICResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * BackCategoryReadFacade
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 17:37<br/>
 */
@Slf4j
@Component
public class BackItemCategoryReadFacade {

    @Resource
    private BackCategoryReadFacade backCategoryReadFacade;

    /**
     * 下属类目
     *
     * @param pid 类目pid
     * @return
     */
    public List<BackCategoryInfo> findChildrenByPid(Long pid) {
        try {
            BackCategoryQueryChildrenRequest request1 = new BackCategoryQueryChildrenRequest();
            request1.setPid(pid);
            ICResponse<List<BackCategoryInfo>> icResponse = backCategoryReadFacade.findChildrenByPid(request1);
            if (!icResponse.isSuccess()) {
                log.error("item findChildrenByPid fail,pid={}", pid);
            } else {
                return icResponse.getResult();
            }
        } catch (Exception ex) {
            log.error("item findChildrenByPid ex,pid={}", pid, ex);
        }
        return Collections.EMPTY_LIST;
    }
}


