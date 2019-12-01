package com.mallcai.bs.mapper;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.vo.SysCode;

import java.util.List;

public interface SystemMapper {
    @CustomDataSource(CustomDataSource.GLOBAL)
    List<SysCode> getSysCode(String type);

	 
}
