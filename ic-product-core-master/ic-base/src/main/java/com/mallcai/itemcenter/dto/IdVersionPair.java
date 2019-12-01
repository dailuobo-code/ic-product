package com.mallcai.itemcenter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * IdVersionPair
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 15:28<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdVersionPair implements Serializable {
    private static final long serialVersionUID = 1828133234030729219L;

    @ApiModelProperty("对象id")
    private Long id;
    @ApiModelProperty("当前版本号")
    private Integer version;
}
