package com.mallcai.itemcenter.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mallcai.itemcenter.base.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * ItemDetail
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:00<br/>
 */
@Data
public class ItemDetail extends BaseModel {
    private static final long serialVersionUID = 643199417597415118L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * should use {@link #detail}
     */
    @Deprecated
    private String remark;

    @JsonIgnore
    @ApiModelProperty("商品辅图, 不存数据库")
    private List<Image> images;

    @ApiModelProperty("商品辅图列表的json表示形式, 存数据库")
    private String imageJson;

    /**
     * 富文本详情
     */
    private String detail;

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

    public void setImages(List<Image> images) {
        this.images = images;
        this.imageJson = object2json(images, "");
    }

    public void setImageJson(String imageJson) {
        this.imageJson = imageJson;
        this.images = json2object(imageJson, LIST_OF_IMAGE_INFO, Collections::emptyList, "");
    }
}
