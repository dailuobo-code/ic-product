package com.dailuobo.api.domain.entity;

import com.dailuobo.ic.dto.spec.SkuAttributeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityProduct extends HqProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityProductId;
    private Integer cityId;
    private Integer hqStatus;
    private Integer cityStatus;
    private String showName;
    private Double advisePrice;
    private Double disablePrice;
    private String weightUnit;
    private String numUnit;
    private String cityProductIcon;
    private String cityProductPic;
    private String origin;
    private String detailUrl;
    private String subtitle;
    private String keyword;
    private String iconTip;
    private String l1L2Names;
    private Integer changeFlag;
    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    private int isShare;//是否共享
    private Integer newUserPro; //新人专享  0不是，1是
    private Integer isUnder;
    private String barCode;
    private Double vipCount;
    private Integer vipLimit;//'会员限购 0：限购，1：不限购'
    private Integer threshold;
    private String length;
    private String wides;
    private String high;
    private Integer goodsType;

    private Integer pointMallStatus;//积分商城 上架状态  0：下架，1：上架
    private Integer pointPrice; //积分价格
    private Integer delFlag;//删除标识
    private Integer merchantId;//商家ID
    private String merchantName;//商家名称
    private String saleTime;
    private String productDesc;

    /**
     * 是否走采销预测
     */
    private Integer isForecast;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    private Integer available;

    /**
     * 商家抽佣比例，百分比转化成小数格式
     */
    private BigDecimal merchantRate;

    /**
     * 是否关联货品
     * 0表示未关联 1表示已关联
     */
    private Integer isGoodsRel;

    /**
     * 发货时间
     */
    private Integer arrivalDay;

    /**
     * 主图视频
     */
    private String videoUrl;

    /**
     * 多规格销售属性
     */
    private List<SkuAttributeDTO> skuAttributes;


    /**
     * 0 非多规格  1 多规格
     * 是否是多规格商品
     */
    private Integer isMultiProduct;


    /**
     * 新商品 itemId 2019-10-21 09:48:004
     */
    private BigInteger itemId;
    /**
     * skuId  2019-10-21 09:47:49
     */
    private BigInteger skuId;



    public Integer getIsForecast() {
        return isForecast;
    }

    public void setIsForecast(Integer isForecast) {
        this.isForecast = isForecast;
    }

    public Integer getPointMallStatus() {
        return pointMallStatus;
    }

    public void setPointMallStatus(Integer pointMallStatus) {
        this.pointMallStatus = pointMallStatus;
    }

    public Integer getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(Integer pointPrice) {
        this.pointPrice = pointPrice;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getVipLimit() {
        return vipLimit;
    }

    public void setVipLimit(Integer vipLimit) {
        this.vipLimit = vipLimit;
    }

    public Double getVipCount() {
        return vipCount;
    }

    public void setVipCount(Double vipCount) {
        this.vipCount = vipCount;
    }

    public Integer getNewUserPro() {
        return newUserPro;
    }

    public void setNewUserPro(Integer newUserPro) {
        this.newUserPro = newUserPro;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public CityProduct() {
    }

    public CityProduct(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getHqStatus() {
        return hqStatus;
    }

    public void setHqStatus(Integer hqStatus) {
        this.hqStatus = hqStatus;
    }

    public Integer getCityStatus() {
        return cityStatus;
    }

    public void setCityStatus(Integer cityStatus) {
        this.cityStatus = cityStatus;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Double getAdvisePrice() {
        return advisePrice;
    }

    public void setAdvisePrice(Double advisePrice) {
        this.advisePrice = advisePrice;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getNumUnit() {
        return numUnit;
    }

    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit;
    }

    public String getCityProductIcon() {
        return cityProductIcon;
    }

    public void setCityProductIcon(String cityProductIcon) {
        this.cityProductIcon = cityProductIcon;
    }

    public String getCityProductPic() {
        return cityProductPic;
    }

    public void setCityProductPic(String cityProductPic) {
        this.cityProductPic = cityProductPic;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getIconTip() {
        return iconTip;
    }

    public void setIconTip(String iconTip) {
        this.iconTip = iconTip;
    }

    public Double getDisablePrice() {
        return disablePrice;
    }

    public void setDisablePrice(Double disablePrice) {
        this.disablePrice = disablePrice;
    }

    public String getL1L2Names() {
        return l1L2Names;
    }

    public void setL1L2Names(String l1L2Names) {
        this.l1L2Names = l1L2Names;
    }

    public Integer getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getIsUnder() {
        return isUnder;
    }

    public void setIsUnder(Integer isUnder) {
        this.isUnder = isUnder;
    }

    @Override
    public String getBarCode() {
        return barCode;
    }

    @Override
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String getLength() {
        return length;
    }

    @Override
    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String getWides() {
        return wides;
    }

    @Override
    public void setWides(String wides) {
        this.wides = wides;
    }

    @Override
    public String getHigh() {
        return high;
    }

    @Override
    public void setHigh(String high) {
        this.high = high;
    }

    @Override
    public Integer getGoodsType() {
        return goodsType;
    }

    @Override
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getNewArrivalType() {
        return newArrivalType;
    }

    public void setNewArrivalType(Integer newArrivalType) {
        this.newArrivalType = newArrivalType;
    }

    public Integer getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(Integer seasonal) {
        this.seasonal = seasonal;
    }

    public BigDecimal getMerchantRate() {
        return merchantRate;
    }

    public void setMerchantRate(BigDecimal merchantRate) {
        this.merchantRate = merchantRate;
    }

    public Integer getIsGoodsRel() {
        return isGoodsRel;
    }

    public void setIsGoodsRel(Integer isGoodsRel) {
        this.isGoodsRel = isGoodsRel;
    }

    public Integer getIsMultiProduct() {
        return isMultiProduct;
    }

    public void setIsMultiProduct(Integer isMultiProduct) {
        this.isMultiProduct = isMultiProduct;
    }


    public List<SkuAttributeDTO> getSkuAttributes() {
        return skuAttributes;
    }

    public void setSkuAttributes(List<SkuAttributeDTO> skuAttributes) {
        this.skuAttributes = skuAttributes;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public BigInteger getSkuId() {
        return skuId;
    }

    public void setSkuId(BigInteger skuId) {
        this.skuId = skuId;
    }

    public Boolean multiProductFlag(){
        if(itemId==null&&skuId==null){
            return false;
        }
        if(itemId!=null&&itemId.intValue()>0){
            return true;
        }
        if(skuId!=null&&skuId.intValue()>0){
            return true;
        }
        return false;
    }

    public Integer getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(Integer arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
