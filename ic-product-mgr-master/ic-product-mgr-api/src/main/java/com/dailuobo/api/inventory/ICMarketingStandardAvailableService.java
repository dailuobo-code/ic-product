package com.dailuobo.api.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.ProductAvailableChange;
import com.dailuobo.api.domain.vo.ProductAvailableChangeQueryVo;
import com.dailuobo.api.domain.vo.ProductAvailableChangeResponseVo;

import java.util.Date;
import java.util.List;

public interface ICMarketingStandardAvailableService {
    ICResponse addProductAvailableChange(ProductAvailableChange productAvailableChange);

    ICResponse updateProductAvailableChange(ProductAvailableChange productAvailableChange);

    ICResponse<List<ProductAvailableChange>> selectDataByProductIdAndDate(Integer cityProductId, Date execDate);

    ICResponse<ProductAvailableChange> selectDataById(Integer id);

    ICResponse<List<ProductAvailableChangeResponseVo>> selectProductAvailableChangeList(ProductAvailableChangeQueryVo changeQueryVo);
}
