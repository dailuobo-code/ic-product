package com.mallcai.domain.product;

import com.alibaba.fastjson.JSON;
import com.mallcai.domain.enums.AsyStatusEnum;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncProductLogDTO implements Serializable {
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
    /**
     * 同步日志记录
     */
    List<AsyncProductLogDetail> asyncProductLogDetailList;

    private String asyncDetailString;


    public AsyStatusEnum getAsyStatusEnum() {
        if (this.asyStatusEnum != null) {
            return this.asyStatusEnum;
        }
        if (CollectionUtils.isNotEmpty(asyncProductLogDetailList)) {
            List<AsyncProductLogDetail> collect = asyncProductLogDetailList.stream().filter(t -> Objects.equals(t.getAsyStatusEnum(), AsyStatusEnum.Fail)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)) {
                return AsyStatusEnum.SUCCESS;
            }
            if (collect.size() < asyncProductLogDetailList.size()) {
                return AsyStatusEnum.PART_SUCCESS;
            }
            return AsyStatusEnum.Fail;
        }
        return null;
    }

    public String getAsyncDetailString() {
        if (asyncDetailString != null) {
            return asyncDetailString;
        }
        if (CollectionUtils.isNotEmpty(asyncProductLogDetailList)) {
            return JSON.toJSONString(asyncProductLogDetailList);
        }
        return null;
    }

    public List<AsyncProductLogDetail> getAsyncProductLogDetailList() {
        if (CollectionUtils.isNotEmpty(asyncProductLogDetailList)) {
            return asyncProductLogDetailList;
        }
        if (asyncDetailString != null) {
            return JSON.parseArray(asyncDetailString, AsyncProductLogDetail.class);
        }
        return null;
    }

    public void setAsyncDetailString(String asyncDetailString) {
        if (asyncDetailString != null) {
            this.asyncDetailString = asyncDetailString;
            return;
        }
        if (CollectionUtils.isNotEmpty(asyncProductLogDetailList)) {
            this.asyncDetailString = JSON.toJSONString(asyncProductLogDetailList);
        }

    }
}
