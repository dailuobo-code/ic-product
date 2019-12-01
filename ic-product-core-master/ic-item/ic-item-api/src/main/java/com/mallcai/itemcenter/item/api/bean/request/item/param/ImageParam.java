package com.mallcai.itemcenter.item.api.bean.request.item.param;

import com.mallcai.itemcenter.api.response.param.ApiExtraParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ImageParam
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 11:39<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageParam extends ApiExtraParam {
    private static final long serialVersionUID = 85294712758811904L;

    @ApiModelProperty(value = "图片名称", notes = "用户自定义, 如侧面图, 正面图等, 非必选")
    private String name;

    @ApiModelProperty(value = "图片地址", notes = "不要携带协议", required = true)
    private String url;
}
