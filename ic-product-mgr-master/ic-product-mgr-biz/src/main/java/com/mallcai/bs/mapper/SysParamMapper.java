package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.entity.SysParam;
import org.apache.ibatis.annotations.Param;

public interface SysParamMapper {

	@CustomDataSource(CustomDataSource.GLOBAL)
	List<SysParam> selectAll(Map<String, Object> param);

	@CustomDataSource(CustomDataSource.GLOBAL)
	void edit(SysParam sysParam);

	@CustomDataSource(CustomDataSource.GLOBAL)
	void add(SysParam sysParam);

	@CustomDataSource(CustomDataSource.GLOBAL)
	String getGroupUserIsAduit(@Param("paramType") String groupUserIsaudit);

}
