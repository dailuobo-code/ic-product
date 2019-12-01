package com.mallcai.biz.product.dao.mapper;


import com.mallcai.domain.dataobject.product.ProductCategoryRelDO;
import com.mallcai.domain.dataobject.product.TblCityProduct;

import java.util.List;

public interface TblCityProductCustomMapper {

    List<TblCityProduct> selectCityProductByHqProduct(Integer productId);

    List<ProductCategoryRelDO> selectAllCityProductOfLevel2Category();

    Integer selectPointProductCnt(Integer productId);
}
