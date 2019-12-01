package com.mallcai.domain.enums;

public enum OperationEnum  {
    ADD(1,"新增"),
    EDIT(2,"修改"),
    DELETE(3,"删除");
    OperationEnum(Integer code,String description){
        this.code=code;
        this.description=description;
    }

    /**
     * 操作code
     */
    private Integer code;
    /**
     * 描述
     */
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
