package com.mallcai.itemcenter.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mallcai.itemcenter.base.model.BaseModel;
import com.mallcai.itemcenter.sku.model.GroupedSkuAttribute;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Item
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:00<br/>
 */
@Data
public class Item extends BaseModel {

    private static final long serialVersionUID = -7786299938876067260L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * SPU id
     */
    private Long spuId;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 商品编码
     */
    private String itemCode;

    /**
     * 店铺id
     */
    private Long sellerId;

    /**
     * 店铺名
     */
    private String sellerName;

    /**
     * 服务费模板id
     */
    private Long serviceFeeTempId;

    /**
     * 商品名
     */
    private String name;

    /**
     * 运营定义关键词
     */
    private String keywords;

    /**
     * 广告，副标题
     */
    private String advertise;

    /**
     * 主图地址
     */
    private String mainImage;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 商品状态：1(上架),-1(下架),-2(冻结),-3(删除)
     */
    private Integer status;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 存储类型
     */
    private Integer storeType;

    /**
     * 取货类型
     */
    private Integer pickType;

    /**
     * 最高价
     */
    private Long highPrice;

    /**
     * 最低价
     */
    private Long lowPrice;

    @JsonIgnore
    @ApiModelProperty("skuAttributes的json表示形式, 存数据库")
    private String skuAttributesJson;

    @ApiModelProperty("sku属性及属性值们, sku属性按照属性key值归组, 不存数据库")
    private List<GroupedSkuAttribute> skuAttributes;

    @JsonIgnore
    @ApiModelProperty("otherAttributes的json表示形式, 存数据库")
    private String otherAttributesJson;

    @ApiModelProperty("其他属性及属性值们, 其他属性则按照组名归组, 不存数据库")
    private List<GroupedOtherAttribute> otherAttributes;

    /**
     * ${item.comment}
     */
    private String hashCode;

    /**
     * 信息版本号
     */
    private Integer version;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Long updatedBy;

    /**
     * 创建者
     */
    private Long createdBy;

    public void setSkuAttributesJson(String skuAttributesJson) {
        this.skuAttributesJson = skuAttributesJson;
        this.skuAttributes = json2object(skuAttributesJson, GROUPED_SKU_ATTRIBUTE, Collections::emptyList, "");
    }

    public void setSkuAttributes(List<GroupedSkuAttribute> skuAttrs) {
        this.skuAttributes = skuAttrs;
        this.skuAttributesJson = object2json(skuAttrs, "");
    }

    public void setOtherAttributesJson(String otherAttributesJson) {
        this.otherAttributesJson = otherAttributesJson;
        this.otherAttributes = json2object(otherAttributesJson, GROUPED_OTHER_ATTRIBUTE, Collections::emptyList, "");
    }

    public void setOtherAttributes(List<GroupedOtherAttribute> otherAttrs) {
        this.otherAttributes = otherAttrs;
        this.otherAttributesJson = object2json(otherAttrs, "");
    }
}
