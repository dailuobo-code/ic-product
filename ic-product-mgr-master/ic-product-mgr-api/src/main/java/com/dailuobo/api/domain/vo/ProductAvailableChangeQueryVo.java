package com.dailuobo.api.domain.vo;

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
public class ProductAvailableChangeQueryVo implements Serializable {
    private static final long serialVersionUID = 1;

    private Integer cityProductId;
    private String showName;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date execDate;
    private Integer status;
    private Integer available;
    private String productNo;
    private String hqProductIcon;
    private String hqProductName;
    private Integer offset;
    private Integer limit;
    private Integer cityId;
}
