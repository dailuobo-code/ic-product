package com.mallcai.biz.product.model;

import com.mallcai.domain.enums.AsyStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsyProductLogDO {
    /**
     * 请求Id
     */
    private String requestUUId;
    /**
     * 商品Id
     */
    private Long itemId;
    /**
     * 同步状态
     */
    private AsyStatusEnum asyStatusEnum;
    /**
     * 请求日志
     */
    private String requestParamJson;
    /**
     * 请求日志转换后数据
     */
    private String afterConvertRequestParamJson;


    private String asyncDetailString;
}
