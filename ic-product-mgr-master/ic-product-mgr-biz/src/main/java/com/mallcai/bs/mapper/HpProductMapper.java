package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.HpProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HpProductMapper {
    List<HpProduct> selectAll(Map<String, Object> param);

    void add(@Param("hpProducts") List<HpProduct> hpProducts);

    void delete(@Param("ids") List<Integer> ids);

    void update(HpProduct hpProduct);
}
