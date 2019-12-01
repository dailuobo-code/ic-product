package com.mallcai.biz.product.model;

import lombok.Data;

import java.util.Date;

/**
 * AuditItem
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/8/28 16:30<br/>
 */
@Data
public class AuditItemDO {
    private Long id;
    private Long billId;
    private Long itemId;
    private String reply;
    private String type;
    private String status;
    private Boolean isDeleted;
    public Date updateTime;
    public Date createTime;
}
