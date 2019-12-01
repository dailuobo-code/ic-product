package com.mallcai.domain.product.request;

import com.mallcai.domain.BaseEntity;
import com.mallcai.domain.RequestEntity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PagingHqProductSearchQueryRequest extends RequestEntity {
    /**
     * 关键词
     */
    private String keyword;

}
