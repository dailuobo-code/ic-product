package com.mallcai.itemcenter.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户上传图片信息, 主要包括图片名称, url, 其中url是必选的, 其他可选
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 16:24<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    /**
     * 图片名称, 用户自定义, 如侧面图, 正面图等, 非必选
     */
    private String name;

    /**
     * 图片对应的url, 必选
     */
    private String url;
}
