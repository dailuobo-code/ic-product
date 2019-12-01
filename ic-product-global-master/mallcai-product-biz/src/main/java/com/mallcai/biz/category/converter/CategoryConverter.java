package com.mallcai.biz.category.converter;

import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.dataobject.category.CategoryDO;
import org.mapstruct.Mapper;

/**
 * CategoryConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/22 21:00<br/>
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter {
    public CategoryDTO domain2dto(CategoryDO s);
}
