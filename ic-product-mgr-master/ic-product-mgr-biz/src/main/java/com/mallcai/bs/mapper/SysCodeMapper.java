package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.vo.SysCode;

public interface SysCodeMapper {

	@CustomDataSource(CustomDataSource.GLOBAL)
	List<SysCode> selectAll(Map<String, Object> param);

	@CustomDataSource(CustomDataSource.GLOBAL)
	void add(Map<String, Object> param);

	@CustomDataSource(CustomDataSource.GLOBAL)
	List<SysCode> getSysCodeType(Map<String, Object> param);

	@CustomDataSource(CustomDataSource.GLOBAL)
	void edit(Map<String, Object> param);

	@CustomDataSource(CustomDataSource.GLOBAL)
	void delete(Map<String, Object> param);

}
