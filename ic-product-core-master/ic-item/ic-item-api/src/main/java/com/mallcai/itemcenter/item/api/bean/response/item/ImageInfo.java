package com.mallcai.itemcenter.item.api.bean.response.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ImageInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/9 20:16<br/>
 */
@Data
@ApiModel("图片信息")
public class ImageInfo implements Serializable {
    private static final long serialVersionUID = 3056316709305317326L;

    @ApiModelProperty(value = "图片名称", notes = "用户自定义, 如主图，轮播图等")
    private String name;

    @ApiModelProperty(value = "图片地址", notes = "无协议图片地址")
    private String url;
}
