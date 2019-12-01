package com.mallcai.mq.classify;

import lombok.Data;

import java.io.Serializable;

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