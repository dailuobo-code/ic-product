package com.mallcai.itemcenter.category.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.google.common.collect.Lists;
import com.mallcai.itemcenter.category.model.BackCategory;
import com.mallcai.itemcenter.category.repository.BackCategoryDAO;
import com.mallcai.itemcenter.common.Constant;
import com.mallcai.itemcenter.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BackCategoryDomainService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 15:38<br/>
 */
@Service
public class BackCategoryDomainReadService {
    private final BackCategoryDAO backCategoryDAO;

    public BackCategoryDomainReadService(BackCategoryDAO backCategoryDAO) {
        this.backCategoryDAO = backCategoryDAO;
    }

    public List<BackCategory> findChildrenByPid(Long pid) {
        return backCategoryDAO.findByPid(pid);
    }

    @CreateCache(name = "BackCategory:single", expire = Constant.EXPIRE_1M)
    private Cache<Long, BackCategory> singleCache;
    @CreateCache(name = "BackCategory:ancestor", expire = Constant.EXPIRE_1M)
    private Cache<Long, List<BackCategory>> ancestorCache;

    public BackCategory findById(Long id) {
        return singleCache.computeIfAbsent(id, cid -> {
            BackCategory origin = backCategoryDAO.findById(cid);
            if (origin == null || -1 == origin.getStatus()) {
                throw new ServiceException("backCategory.not.found");
            }
            return origin;
        });
    }

    public List<Long> findAncestorIdsOf(Long id) {
        List<BackCategory> ancestors = findAncestorsOfNotOrderly(id);
        List<Long> list = new ArrayList<>(4);
        for (int i = ancestors.size() - 1; i >= 0; --i) {
            list.add(ancestors.get(i).getId());
        }
        return list;
    }

    public List<BackCategory> findAncestorsOfNotOrderly(Long id) {
        return ancestorCache.computeIfAbsent(id, aLong -> {
            List<BackCategory> ancestors = Lists.newArrayListWithCapacity(8);
            Long currentId = aLong;
            while (currentId > 0) {
                BackCategory current = backCategoryDAO.findById(currentId);
                if (current != null && 1 == current.getStatus()) {
                    ancestors.add(current);
                    currentId = current.getPid();
                } else {
                    throw new ServiceException("backCategory.not.found");
                }
            }
            return ancestors;
        });
    }

    public List<BackCategory> findAncestorsOf(Long id) {
        List<BackCategory> ancestors = findAncestorsOfNotOrderly(id);
        List<BackCategory> list = new ArrayList<>(4);
        for (int i = ancestors.size() - 1; i >= 0; --i) {
            list.add(ancestors.get(i));
        }
        return list;
    }
}
