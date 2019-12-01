package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.vo.InventoryExportVo;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: InventoryExportMapper
 * @Description: 库存导出mapper
 * @Author: zhangxuefeng
 * @Date: 2019/6/24 下午1:57
 * @Version: 1.0
 **/
public interface InventoryExportMapper {
    Integer insertOne(InventoryExportVo vo);

    InventoryExportVo selectById(@Param("id") Integer id);

    Integer updateById(@Param("export") InventoryExportVo export, @Param("exportId") Integer exportId);
}
