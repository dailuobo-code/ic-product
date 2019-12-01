package com.mallcai.itemcenter.category.api.bean.response;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * CategoryAttributeInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 13:57<br/>
 */
@Data
@ApiModel("类目属性")
public class CategoryAttributeInfo extends ApiExtraInfo {
    private static final long serialVersionUID = -1788571365143500406L;

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("类目id")
    private Long categoryId;
    @ApiModelProperty("属性名")
    private String attrKey;
    @ApiModelProperty("所属组名")
    private String group;
    @ApiModelProperty("顺序编号")
    private Long index;
    @ApiModelProperty("该属性是否继承自父级类目")
    private Boolean isExtended;
    @ApiModelProperty("属性呈现状态，1：启用，-1：未使用（继承自父类目且启用则呈现'使用中'，不是继承自父类则呈现'已关联'）")
    private Integer status;
    @ApiModelProperty("属性元信息，key = AttributeMetaKey，查询时直接从数据库反序列化json给出去")
    private Map<String, String> attrMetas;
    @ApiModelProperty("属性值信息")
    private List<String> attrVals;
}
