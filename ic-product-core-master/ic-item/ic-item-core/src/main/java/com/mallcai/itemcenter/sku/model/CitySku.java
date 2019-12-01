package com.mallcai.itemcenter.sku.model;

import com.mallcai.itemcenter.base.model.BaseModel;
import com.mallcai.itemcenter.sku.enums.SalesType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 15:36<br/>
 */
@Data
public class CitySku extends BaseModel {

    private static final long serialVersionUID = 4257410061265342753L;

    @ApiModelProperty("自增主键")
    private Long id;

    @ApiModelProperty("城市id")
    private Long cityId;

    @ApiModelProperty("商品id")
    private Long itemId;

    @ApiModelProperty("sku id")
    private Long skuId;
    @ApiModelProperty("seller id")
    private Long sellerId;

    @ApiModelProperty("条形码")
    private String barcode;

    @ApiModelProperty("sku状态, 1: 上架, -1:下架, -2:冻结, -3:删除")
    private Integer status;

    @ApiModelProperty("实际售卖价格")
    private Integer price;

    @ApiModelProperty("sku其他各种价格的json表示形式")
    private String priceJson;

    @ApiModelProperty("其他各种价格, 如市场价, 阶梯价等, 不存数据库")
    private Map<String, Long> extraPrice;

    @ApiModelProperty("限购份数：0，表示不限购")
    private Integer threshold;

    @ApiModelProperty("信息版本号")
    private Integer version;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新者")
    private Long updatedBy;

    @ApiModelProperty("创建者")
    private Long createdBy;

    public void setPriceJson(String priceJson) {
        this.priceJson = priceJson;
        this.extraPrice = json2object(priceJson, MAP_OF_LONG, Collections::emptyMap, "");
    }
}
