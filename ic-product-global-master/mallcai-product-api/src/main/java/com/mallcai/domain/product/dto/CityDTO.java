package com.mallcai.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO implements Serializable {

    private Long itemId;
    /**
     * 城市Id
     */
    private Long cityId;
    /**
     * 城市名称
     */
    private String cityName;
}
