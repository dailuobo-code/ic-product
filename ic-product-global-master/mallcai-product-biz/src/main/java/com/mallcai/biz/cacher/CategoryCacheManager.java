package com.mallcai.biz.cacher;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.mallcai.backend.search.common.utils.GenericConverter;
import com.mallcai.biz.category.converter.CategoryConverter;
import com.mallcai.biz.category.dao.mapper.CategoryMapper;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.dataobject.category.CategoryDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CategoryCacher
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 20:58<br/>
 */
@Component
public class CategoryCacheManager {

    private final CategoryMapper categoryMapper;
    private final CategoryConverter categoryConverter;

    public CategoryCacheManager(CategoryMapper categoryMapper,
                                CategoryConverter categoryConverter) {
        this.categoryMapper = categoryMapper;
        this.categoryConverter = categoryConverter;
    }

    @CachePenetrationProtect
    @Cached(cacheType = CacheType.BOTH)
    public CategoryDTO findById(Integer id) {
        CategoryDO found = categoryMapper.loadCategory(id);
        return categoryConverter.domain2dto(found);
    }

    @CachePenetrationProtect
    @Cached(cacheType = CacheType.BOTH)
    public List<CategoryDTO> findByIds(List<Integer> ids) {
        List<CategoryDO> found = categoryMapper.findByCategoryIdList(ids);
        Map<Integer, CategoryDO> idToCategory = found.stream().collect(Collectors.toMap(CategoryDO::getClassifyId, Function.identity()));
        List<CategoryDO> sorted = ids.stream().map(idToCategory::get).filter(Objects::nonNull).collect(Collectors.toList());
        return GenericConverter.batchConvert(sorted, categoryConverter::domain2dto);
    }
}
