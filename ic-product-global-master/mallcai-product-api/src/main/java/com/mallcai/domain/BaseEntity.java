package com.mallcai.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {
    protected Integer createUserId;
    protected String createUsername;
    protected Date createTime;
    protected Integer updateUserId;
    protected String updateUsername;
    protected Date updateTime;

}
