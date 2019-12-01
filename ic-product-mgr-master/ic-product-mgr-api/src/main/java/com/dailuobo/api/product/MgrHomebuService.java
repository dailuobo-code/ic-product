package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.Homebu;

import java.util.List;

public interface MgrHomebuService {

    ICResponse<List<Homebu>> selectAll(String productNo, Integer cityProductId, Integer cityId, Integer offset, Integer limit);

    ICResponse<Boolean> add(Integer cityProductId);

    ICResponse<Boolean> del(Integer[] ids);
}
