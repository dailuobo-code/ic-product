package com.mallcai.itemcenter.item.api.bean.response.item;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ItemInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:16<br/>
 */
@Data
@ApiModel("商品")
public class CityItemInfo extends ApiExtraInfo implements Serializable {
    private static final long serialVersionUID = 2881364365262252390L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("item id")
    private Long itemId;

    @ApiModelProperty("城市id")
    private Long cityId;

    @ApiModelProperty(value = "SPU ID", notes = "如果是通过spu发布商品")
    private Long spuId;

    @ApiModelProperty(value = "skuId集合", notes = "附加SkuId集合")
    private List<Long> skuIdList;

    @ApiModelProperty("编码")
    private String itemCode;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("广告语")
    private String advertise;

    @ApiModelProperty("运营定义关键词")
    private String keywords;

    @ApiModelProperty("商家id")
    private Long sellerId;

    @ApiModelProperty("商家名称")
    private String sellerName;

    @ApiModelProperty("后台类目id")
    private Long categoryId;

    @ApiModelProperty("运费模板id")
    private Long deliveryFeeTempId;

    @ApiModelProperty("主图url")
    private String mainImage;

    @ApiModelProperty("最低价")
    private Long lowPrice;

    @ApiModelProperty("最高价")
    private Long highPrice;

    @ApiModelProperty("标签信息")
    private Map<String, String> tags;

    @ApiModelProperty("销售属性集合")
    private List<GroupedSkuAttributeInfo> skuAttributes;

    @ApiModelProperty("规格属性集合")
    private List<GroupedOtherAttributeInfo> otherAttributes;

    @ApiModelProperty("信息的hash值")
    private String hashCode;

    @ApiModelProperty(value = "商品状态", notes = "请使用ItemStatus进行解析")
    private Integer status;

    @ApiModelProperty("信息的版本号")
    private Long version;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("更新者id")
    private Long updatedBy;

    @ApiModelProperty("创建者id")
    private Long createdBy;

    // **************** //
    // todo: 使用特性替代 //
    // **************** //

    @ApiModelProperty(value = "商品类型")
    private Integer type;

    @ApiModelProperty(value = "存储类型")
    private Integer storeType;

    @ApiModelProperty(value = "取货类型")
    private Integer pickType;

    @ApiModelProperty(value = "是否标品")
    private Integer isStandard;

    @ApiModelProperty(value = "上新类型")
    private Integer newArrivalType;

    @ApiModelProperty(value = "售卖类型")
    private Integer seasonal;

    @ApiModelProperty(value = "配送方式")
    private Integer deliveryMode;

    @ApiModelProperty(value = "保质期")
    private Integer qualityTime;

    // todo: 税率放入类目中
    @ApiModelProperty("税率编码")
    private String taxCode;
    @ApiModelProperty("税率")
    private String tax;
    @ApiModelProperty("发货时间")
    private Integer arrivalDay;
}
