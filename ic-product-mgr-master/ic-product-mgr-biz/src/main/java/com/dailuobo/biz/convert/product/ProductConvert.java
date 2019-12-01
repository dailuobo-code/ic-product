package com.dailuobo.biz.convert.product;

import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    @Mapping(source = "hqProductId",target = "hqProductId")
    CityProductCreateRequest convertCityProduct2Request(CityProduct cityProduct);


    @Mapping(source = "hqProductId",target = "hqProductId")
    CityProduct convertCityProductRequest2CityProduct(CityProductCreateRequest cityProductCreateRequest);
}
