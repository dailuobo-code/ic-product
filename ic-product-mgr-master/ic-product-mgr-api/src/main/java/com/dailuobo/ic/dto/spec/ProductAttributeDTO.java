package com.dailuobo.ic.dto.spec;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

import java.util.List;

/**
 * 商品规格属性
 */
public class ProductAttributeDTO {
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecAttributeJson() {
        return specAttributeJson;
    }

    public void setSpecAttributeJson(String specAttributeJson) {
        this.specAttributeJson = specAttributeJson;
    }

    /**
     * 城市Id
     */

    private Integer cityId;
    /**
     * 商品Id
     */
    private Integer cityProductId;
    /**
     * 规格Id
     */
    private Integer specId;
    /**
     * 规格属性Json
     */
    private String specAttributeJson;

    /**
     * 规格属性
     */
    private List<SkuAttributeDTO> attributes;

    public List<SkuAttributeDTO> getAttributes() {
        if (CollectionUtils.isNotEmpty(attributes)) {
            return attributes;
        }
        if (specAttributeJson == null) {
            return attributes;
        }
        return JSON.parseArray(specAttributeJson, SkuAttributeDTO.class);
    }
}
