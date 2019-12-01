package com.mallcai.itemcenter.category.api.converter.output;

import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.model.BackCategory;
import org.mapstruct.Mapper;

/**
 * BacategoryConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 15:50<br/>
 */
@Mapper(componentModel = "spring")
public interface BackCategoryInfoConverter {
    BackCategoryInfo domain2dto(BackCategory s);
}
