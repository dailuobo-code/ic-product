package com.mallcai.domain.product;

import com.mallcai.domain.enums.AsyStatusEnum;
import com.mallcai.domain.enums.AsyncLogTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 同步商品详情
 */
@Data
public class AsyncProductLogDetail implements Serializable {

    /**
     * 同步结果
     */
    private AsyStatusEnum asyStatusEnum;

    /**
     * 同步日志类型
     */
    private AsyncLogTypeEnum asyncLogTypeEnum;

    /**
     * 请求参数
     */
    private String requestString;
    /**
     * 相应参数
     */
    private String responseString;
}
