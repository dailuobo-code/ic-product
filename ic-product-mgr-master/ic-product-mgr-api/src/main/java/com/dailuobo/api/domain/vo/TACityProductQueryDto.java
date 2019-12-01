package com.dailuobo.api.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * sku模糊查询查询实体
 * @author zhanghao
 */
@Data
public class TACityProductQueryDto implements Serializable {
    private static final long serialVersionUID = 1;

    /** 城市ID **/
    private Integer cityId;
    /** 关键字 **/
    private String keyword;
    /** 是否只查询标品 0:不限制 1:只查询标品 **/
    private Integer queryStandard;
    /** 是否只查询共享 0:不限制 1:只查询共享 **/
    private Integer queryShare;
    private Integer offset;
    private Integer limit;
}
