package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *标品可售库存
 * @author zhanghao
 * @date 2019-05-14 10:08:29
 */
@Data
public class ProductAvailableChange implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer cityProductId;
    private String putName;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date execDate;
    private Integer synchStatus;
    private Integer available;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    private Integer createUserId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
    private Integer updateUserId;
    private Integer delFlag;
    private Integer cityId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date synchTime;
}
