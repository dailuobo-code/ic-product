package com.mallcai.domain.product.dto;

import com.mallcai.itemcenter.item.api.bean.response.item.CityItemInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemInfo;
import lombok.Data;

import java.util.List;

@Data
public class CityItemInfoDTO extends CityItemInfo {
    /**
     * 商品已发布的城市
     */
    List<CityDTO> distributeCityList;

    /**
     * 父级分类Id
     */
    private String pCategoryName;

    /**
     * 父级分类名称
     */
    private String categoryName;


    /**
     * 创建人名称
     */
    private String creatorName;


    /**
     * 修改人名称
     */
    private String updaterName;
}
