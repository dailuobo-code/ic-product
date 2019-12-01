package com.dailuobo.api.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: lekaijun
 * @Date: 2019-10-24 11
 * @Description:
 */
public class MerchantCityProduct implements Serializable {

    private static final long serialVersionUID = -6341629021310544681L;

    private String cityProductIcon;
    private String hqProductName;
    private Integer cityProductId;
    private Integer hqProductId;
    private Integer classifyId;
    private Integer specId;
    private BigDecimal realPrice;
    private String showName;

    public String getCityProductIcon() {
        return cityProductIcon;
    }

    public void setCityProductIcon(String cityProductIcon) {
        this.cityProductIcon = cityProductIcon;
    }

    public String getHqProductName() {
        return hqProductName;
    }

    public void setHqProductName(String hqProductName) {
        this.hqProductName = hqProductName;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getHqProductId() {
        return hqProductId;
    }

    public void setHqProductId(Integer hqProductId) {
        this.hqProductId = hqProductId;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
