package com.mallcai.biz.product.merge.mapper;

import com.mallcai.domain.dataobject.product.FrozenProductDO;

import java.util.List;
import java.util.Map;

public interface FrozenProductMapper {

    List<FrozenProductDO> getFrozenProduct(Map<String, Object> params);

}
