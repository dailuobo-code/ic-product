package com.mallcai.itemcenter.category.api.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.itemcenter.category.model.BackCategory;
import com.mallcai.itemcenter.category.model.CategoryAttribute;
import com.mallcai.itemcenter.category.repository.InitCategoryAndPropertyDataDAO;
import com.mallcai.itemcenter.dto.ICResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class InitCategoryAndPropertyDataImpl implements InitCategoryAndPropertyData {

    @Resource
    private InitCategoryAndPropertyDataDAO initCategoryAndPropertyDataDAO;

    /**
     * json格式:
     * [{"pname":"家居家纺","cnames":["家居内衣","床上用品"]},{"pname":"家居家纺","cnames":["家居内衣","床上用品"]}]
     *
     * @param json
     * @return
     */
    public ICResponse<Boolean> initCategoryData(String json) {
        try {

            JSONArray jsonArray = JSON.parseArray(json);
            jsonArray.forEach(jsonObj -> {
                JSONObject node = (JSONObject) jsonObj;
                String pname = node.getString("pname");
                List<BackCategory> icCategory = initCategoryAndPropertyDataDAO.selectIcCategoryByName(pname);
                if (CollectionUtils.isNotEmpty(icCategory) && icCategory.size() > 1) {
                    return;
                }
                Long hqClassifyId = 0L;
                if (CollectionUtils.isEmpty(icCategory)) {
                    hqClassifyId = initCategoryAndPropertyDataDAO.selectHqClassifyIdByName(pname);
                    if (hqClassifyId == null) {
                        return;
                    }
                    BackCategory insertIcCategory = new BackCategory();
                    insertIcCategory.setId(hqClassifyId);
                    insertIcCategory.setPid(0L);
                    insertIcCategory.setName(pname);
                    insertIcCategory.setLevel(1);
                    insertIcCategory.setStatus(1);
                    insertIcCategory.setHasChildren(true);
                    insertIcCategory.setHasSpu(false);
                    initCategoryAndPropertyDataDAO.insertIcCategory(insertIcCategory);
                }else{
                    hqClassifyId = icCategory.get(0).getId();
                }
                final Long pid = hqClassifyId;
                JSONArray cnamesArry = node.getJSONArray("cnames");
                cnamesArry.forEach(cname -> {
                    String cnameStr = (String) cname;
                    List<BackCategory> icCategory1 = initCategoryAndPropertyDataDAO.selectIcCategoryByName(cnameStr);
                    if (CollectionUtils.isNotEmpty(icCategory1)) {
                        return;
                    }
                    Long hqClassifyId1 = initCategoryAndPropertyDataDAO.selectHqClassifyIdByName(cnameStr);
                    if (hqClassifyId1 == null) {
                        return;
                    }
                    BackCategory insertIcCategory1 = new BackCategory();
                    insertIcCategory1.setId(hqClassifyId1);
                    insertIcCategory1.setPid(pid);
                    insertIcCategory1.setName(cnameStr);
                    insertIcCategory1.setLevel(2);
                    insertIcCategory1.setStatus(1);
                    insertIcCategory1.setHasChildren(false);
                    insertIcCategory1.setHasSpu(false);
                    initCategoryAndPropertyDataDAO.insertIcCategory(insertIcCategory1);
                });
            });

        } catch (Exception ex) {
            log.error("initCategoryData ex,json={}", json, ex);
            return ICResponse.fail(ex.getMessage());
        }
        return ICResponse.ok();
    }

    /**
     * json格式:
     * [{"categoryId":1,"attrKey":"颜色","group":"DEFAULT","status":1,"attrMetasJson":{"IMPORTANT":"true","REQUIRED":"true","VALUE_TYPE":"STRING","USER_DEFINED":"true","SKU_CANDIDATE":"true"},"attrValsJson":["10","20"],"index":1,"extraJson":"{}"}]
     * attrMetaJson类型参数<code>AttributeMetaKey.java</code>
     *
     * @param json
     * @return
     */
    public ICResponse<Boolean> initCategoryPropertyData(String json) {
        try {
            List<CategoryAttribute> attributes = JSON.parseArray(json, CategoryAttribute.class);
/*            attributes.forEach(a->{
                a.setAttrMetasJson("");
                a.setAttrValsJson("");
                a.setExtraJson("");
            });*/
            initCategoryAndPropertyDataDAO.insertCategoryAttr(attributes);
        } catch (Exception ex) {
            log.error("initCategoryData ex,json={}", json, ex);
            return ICResponse.fail(ex.getMessage());
        }
        return ICResponse.ok();
    }
}
