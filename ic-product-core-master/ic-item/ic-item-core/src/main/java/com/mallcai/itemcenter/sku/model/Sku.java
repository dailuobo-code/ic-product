package com.mallcai.itemcenter.sku.model;

import com.mallcai.itemcenter.base.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Sku
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:00<br/>
 */
@Data
public class Sku extends BaseModel {

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * sku模板id
     */
    private Long skuTemplateId;

    /**
     * SKU 编码 (标准库存单位编码)
     */
    private String skuCode;

    /**
     * 商品条码
     */
    private String barcode;

    /**
     * 外部id
     */
    private String outerId;

    /**
     * 卖家 ID
     */
    private Long sellerId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片url
     */
    private String image;

    /**
     * sku状态, 1: 上架, -1:下架, -2:冻结, -3:删除
     */
    private Integer status;

    /**
     * sku 类型
     */
    private Integer type;

    @ApiModelProperty(value = "sku的销售属性, 不存数据库")
    private List<SkuAttribute> attributes;

    /**
     * json存储的sku属性键值对
     */
    private String attrsJson;

    /**
     * 实际售卖价格
     */
    private Integer price;

    /**
     * sku其他各种价格的json表示形式
     */
    private String priceJson;

    @ApiModelProperty("其他各种价格, 如市场价, 阶梯价等, 不存数据库")
    private Map<String, Long> extraPrice;

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

    public void setAttributes(List<SkuAttribute> attributes) {
        this.attributes = attributes;
        attrsJson = object2json(attributes, "序列化SKU销售属性失败");
    }

    public void setAttrsJson(String attrsJson) {
        this.attrsJson = attrsJson;
        this.attributes = json2object(attrsJson, LIST_OF_SKU_ATTRIBUTE, Collections::emptyList, "反序列化SKU销售属性失败");
    }

    public void setPriceJson(String priceJson) {
        this.priceJson = priceJson;
        this.extraPrice = json2object(priceJson, MAP_OF_LONG, Collections::emptyMap, "");
    }
}
