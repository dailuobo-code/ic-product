package com.mallcai.itemcenter.category.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * CategoryAttribute
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 16:43<br/>
 */
@Data
public class CategoryAttribute implements Serializable {
    private static final long serialVersionUID = -8252776830508998085L;

    /**
     * id
     */
    private Long id;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 属性名
     */
    private String attrKey;
    /**
     * 所属组名
     */
    private String group;
    /**
     * 顺序编号
     */
    private Integer index;
    /**
     * 状态,1启用,-1删除
     */
    private Integer status;
    /**
     * json 格式存储的属性元信息
     */
    private String attrMetasJson;
    /**
     * json 格式存储的属性值信息
     */
    private String attrValsJson;
    /**
     * 附加字段
     */
    private String extraJson;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    private Date updateTime;

    private Long updatedBy;
}
