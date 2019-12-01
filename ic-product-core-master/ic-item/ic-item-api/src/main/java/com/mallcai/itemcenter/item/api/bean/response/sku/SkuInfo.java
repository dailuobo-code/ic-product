package com.mallcai.itemcenter.item.api.bean.response.sku;

import com.mallcai.itemcenter.api.response.info.ApiExtraInfo;
import com.mallcai.itemcenter.sku.enums.SalesType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * SkuInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:16<br/>
 */
@Data
@ApiModel("sku信息-完整版")
@EqualsAndHashCode(callSuper = false)
public class SkuInfo extends ApiExtraInfo {

    private static final long serialVersionUID = 3125327485267194958L;

    @ApiModelProperty("主键")
    private Long id;

    // @ApiModelProperty(value = "关联库存SkuIds以及对象的数量映射", notes = "存储格式为库存skuId+对应数量")
    // private Map<Long, Integer> skuIds;

    @ApiModelProperty("编码")
    private String skuCode;

    @ApiModelProperty("条形码")
    private String barcode;

    @ApiModelProperty("外部id")
    private String outerId;

    @ApiModelProperty("店铺id")
    private Long sellerId;

    @ApiModelProperty("商品ID")
    private Long itemId;

    @ApiModelProperty("商品名")
    private String name;

    @ApiModelProperty(value = "商品类型", notes = "请使用ItemType进行解析")
    private Integer type;

    @ApiModelProperty("主图")
    private String image;

    @ApiModelProperty("价格")
    private Long price;

    @ApiModelProperty("销售属性集合")
    private List<SkuAttributeInfo> attributes;

    @ApiModelProperty(value = "特殊价格", notes = "特殊价格包括渠道价格等")
    private Map<String, Long> extraPrice;

    @ApiModelProperty(value = "状态", notes = "请使用ItemStatus进行解析")
    private Integer status;

    @ApiModelProperty(value = "是否启用", notes = "使用ItemStatus=DISABLE亦可")
    private Boolean enable;

    @ApiModelProperty("skuTempId")
    private Long skuTemplateId;

    // @ApiModelProperty("元属性值集合")
    // private Map<String, Boolean> metaAttributes;

    @ApiModelProperty("信息记录的版本信息")
    private Long version;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("更新人")
    private Long updatedBy;

    @ApiModelProperty("创建者id")
    private Long createdBy;

    // todo: 使用属性
    @ApiModelProperty("销售方式，按重量、按数量")
    private SalesType salesType;

    @ApiModelProperty("每份重量")
    private Integer packageWeight;

    @ApiModelProperty("每份数量")
    private Integer packageQuantity;
}

