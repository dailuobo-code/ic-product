package com.dailuobo.ic.api.request.product;

import com.dailuobo.ic.api.base.CityPagedBaseRequest;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CityProductQueryRequest extends CityPagedBaseRequest {

    private Integer parentClassifyId;
    private Integer classifyId;

    private String productNo;
    private Integer isUnd;
    private Integer isNew;
    private String hqProductName;
    private String showName;
    private Integer currentStatus;
    private List<Integer> cityProductIds;
    private Integer deliveryMode;
    private String isShare;
    private Integer cityStatus;
    private String groupSearch;
}
