package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dailuobo.api.domain.entity.NoSaleDto;
import com.dailuobo.api.domain.entity.StoreNoSale;
import com.dailuobo.api.domain.vo.NoSaleVo;

public interface NoSaleMapper {

	List<NoSaleVo> selectAll(Map<String, Object> param);

	void add(@Param("storeNoSales") List<StoreNoSale> storeNoSales);

	void delete(@Param("ids")List<Integer> ids, @Param("userId")Integer userId);

	List<NoSaleDto> getCityStoreNoSaleProduct(Map<String, Object> map);


}
