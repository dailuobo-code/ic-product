package com.mallcai.bs.mapper.ivy;

import com.mallcai.bs.model.ivy.IvyWhProductRatioDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IvyWhProductRatioMapper {


    List<IvyWhProductRatioDO>  getWhProductRatio(@Param("cityId") Integer cityId, @Param("warehouseInventoryatioId") Integer warehouseInventoryatioId );


}
