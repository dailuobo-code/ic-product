package com.mallcai.biz.product.model;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import com.mallcai.backend.search.common.utils.Splitters;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductTopic
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-08-22 11:31<br/>
 */
@Data
public class ProductTopicDO {
    private static final Joiner joiner = Joiner.on(",");

    private Integer id;
    private String name;
    private String classifies;
    private Integer order;
    private Integer operatorId;
    private String operator;
    private String status;
    private Boolean deleted;
    private Date updateTime;
    private Date createTime;

    private List<Integer> classifyIds;

    public void setClassifies(String classifies) {
        this.classifies = classifies;
        if (StringUtils.isNoneEmpty(classifies)) {
            this.classifyIds = Splitters.COMMA.splitToList(classifies)
                    .stream()
                    .map(Ints::tryParse)
                    .collect(Collectors.toList());
        } else {
            this.classifyIds = Collections.emptyList();
        }
    }

    public void setClassifyIds(List<Integer> classifyIds) {
        this.classifyIds = classifyIds;
        if (classifyIds == null) {
            this.classifies = null;
        } else if (classifyIds.isEmpty()) {
            this.classifies = "";
        } else {
            this.classifies = joiner.join(classifyIds);
        }
    }
}
