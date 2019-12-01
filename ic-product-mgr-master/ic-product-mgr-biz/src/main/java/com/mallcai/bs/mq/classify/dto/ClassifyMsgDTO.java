package com.mallcai.bs.mq.classify.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Sjn
 * @description 分类MQ消息DTO
 * @date 2019-06-17
 */
@Data
public class ClassifyMsgDTO implements Serializable {

    private static final long serialVersionUID = 6328956962628564655L;

    private Integer classifyId;

    private String classifyName;

    public ClassifyMsgDTO(){

    }

    public ClassifyMsgDTO(Integer classifyId, String classifyName){
        this.classifyId = classifyId;
        this.classifyName = classifyName;
    }
}
