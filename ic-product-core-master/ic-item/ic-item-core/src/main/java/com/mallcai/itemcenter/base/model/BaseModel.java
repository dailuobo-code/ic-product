package com.mallcai.itemcenter.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mallcai.itemcenter.common.JsonSupport;
import com.mallcai.itemcenter.item.model.GroupedOtherAttribute;
import com.mallcai.itemcenter.item.model.Image;
import com.mallcai.itemcenter.sku.model.GroupedSkuAttribute;
import com.mallcai.itemcenter.sku.model.SkuAttribute;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseModel
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:04<br/>
 */
public class BaseModel implements JsonSupport, Serializable {
    protected static final TypeReference<List<Image>> LIST_OF_IMAGE_INFO = new TypeReference<List<Image>>() {};

    protected static final TypeReference<List<SkuAttribute>> LIST_OF_SKU_ATTRIBUTE = new TypeReference<List<SkuAttribute>>() {};

    protected static final TypeReference<List<GroupedSkuAttribute>> GROUPED_SKU_ATTRIBUTE = new TypeReference<List<GroupedSkuAttribute>>() {};

    protected static final TypeReference<List<GroupedOtherAttribute>> GROUPED_OTHER_ATTRIBUTE = new TypeReference<List<GroupedOtherAttribute>>() {};

    @JsonIgnore
    @ApiModelProperty("extra的json表示形式, 存数据库")
    @Getter
    @Setter
    private String extraJson;

    @ApiModelProperty("放商品扩展信息，不存数据库")
    @Getter
    @Setter
    private Map<String, String> extra;

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
        this.extra = json2object(extraJson, MAP_OF_STRING, HashMap::new, "");
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
        this.extraJson = object2json(extra, "");
    }
}
