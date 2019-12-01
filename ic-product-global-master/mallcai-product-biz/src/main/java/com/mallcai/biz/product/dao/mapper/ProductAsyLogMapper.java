package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.AsyProductLogDO;
import com.mallcai.domain.product.AsyncProductLogDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductAsyLogMapper {
    Integer insert(AsyProductLogDO productAsyLogDTO);
}
