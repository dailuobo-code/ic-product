package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.HisSales;

import java.util.List;

public interface MgrHisSalesService {

    ICResponse<List<HisSales>> selectCity(Integer cityId, String hdate, Integer storeId, Integer cityProductId, Integer offset, Integer limit, String isCity);

    ICResponse<List<HisSales>> selectAll(Integer cityId, String hdate, Integer storeId, Integer cityProductId, Integer offset, Integer limit, String isCity);
}
