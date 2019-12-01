package com.mallcai.biz.product.model;

import lombok.Data;

@Data
public class ProductAsyDO {
    private Long id;
    private String requestId;
    private Long itemId;
    private Long requestParamJson;
    private String after_convert_request_param_json;
}
