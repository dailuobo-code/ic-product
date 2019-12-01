package com.mallcai.biz.product.model;

import lombok.Data;

import java.util.Date;

/**
 * AuditBillDO
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 16:28<br/>
 */
@Data
public class AuditBillDO {
    private Long id;
    private String type;
    private String status;
    private String comment;
    private String reply;
    private String flowId;
    private Integer operatorId;
    private String operator;
    private Boolean isDeleted;
    private Date updateTime;
    private Date createTime;
}
