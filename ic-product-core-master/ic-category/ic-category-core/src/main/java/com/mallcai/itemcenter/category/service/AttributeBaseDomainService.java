package com.mallcai.itemcenter.category.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.google.common.collect.Maps;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.category.repository.CategoryAttributeDAO;
import com.mallcai.itemcenter.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AttributeBaseDomainService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 16:42<br/>
 */
@Slf4j
@Service
public class AttributeBaseDomainService {

    private final CategoryAttributeDAO categoryAttributeDAO;
    private final BackCategoryDomainReadService backCategoryDomainReadService;

    public AttributeBaseDomainService(CategoryAttributeDAO categoryAttributeDAO,
                                      BackCategoryDomainReadService backCategoryDomainReadService) {
        this.categoryAttributeDAO = categoryAttributeDAO;
        this.backCategoryDomainReadService = backCategoryDomainReadService;
    }

    @CreateCache(name = "AttributeBase:categoryAttributes", expire = Constant.EXPIRE_30M, cacheType = CacheType.REMOTE)
    private Cache<Long, List<CategoryAttribute>> listCache;

    /**
     * 根据后台类目id查询挂在该类目下的属性列表(包括从父类目继承的属性,去重复)
     *
     * @param categoryId
     * @return
     */
    public List<CategoryAttribute> findAllByCategoryId(Long categoryId) {
        List<Long> ancestorIds = backCategoryDomainReadService.findAncestorIdsOf(categoryId);
        Map<String, CategoryAttribute> leafCtgAttrMap = getAttrModelMap(categoryId);

        ancestorIds.remove(categoryId);
        ancestorIds.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(cid -> {
                    List<CategoryAttribute> attributes = findByCategoryId(cid);
                    for (CategoryAttribute attr : attributes) {
                        // 1. 继承父类属性
                        // 2. 就近原则
                        leafCtgAttrMap.putIfAbsent(attrIdentity(attr), attr);
                    }
                });

        return leafCtgAttrMap.values().stream().
                sorted(Comparator.comparingLong(CategoryAttribute::getIndex)).
                collect(Collectors.toList());
    }

    public Map<String, CategoryAttribute> getAttrModelMap(Long categoryId) {
        List<CategoryAttribute> modelList = findByCategoryId(categoryId);

        Map<String, CategoryAttribute> result = Maps.newLinkedHashMap();
        for (CategoryAttribute attr : modelList) {
            result.put(attrIdentity(attr), attr);
        }
        return result;
    }

    /**
     * 查询当前类目直接绑定的属性
     * 当前类目直接绑定的属性，直接认为不是继承的，绑定状态也均以当前绑定状态为准
     *
     * @param categoryId
     * @return
     */
    public List<CategoryAttribute> findByCategoryId(Long categoryId) {
        return listCache.computeIfAbsent(categoryId, categoryAttributeDAO::listByCategoryId);
    }

    private String attrIdentity(CategoryAttribute attribute) {
        return attribute.getGroup() + ":" + attribute.getAttrKey();
    }
}
