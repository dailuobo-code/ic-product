package com.mallcai.itemcenter.category.api.facade;

import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryChildrenRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.dto.ICResponse;

import java.util.List;

public interface InitCategoryAndPropertyData {

    /**
     * json格式:
     * [{"pname":"家居家纺","cnames":["家居内衣","床上用品"]},{"pname":"家居家纺","cnames":["家居内衣","床上用品"]}]
     * @param json
     * @return
     */
    ICResponse<Boolean> initCategoryData(String json);

    /**
     * json格式:
     *[{"cid":1,"attrKey":"颜色","attrMetaJson":{"IMPORTANT":"true","REQUIRED":"true","VALUE_TYPE":"STRING","USER_DEFINED":"true","SKU_CANDIDATE":"true"},"attrValsJson":["10","20"],"index":1}]
     * attrMetaJson类型参数<code>AttributeMetaKey.java</code>
     * @param json
     * @return
     */
    ICResponse<Boolean> initCategoryPropertyData(String json);
}
