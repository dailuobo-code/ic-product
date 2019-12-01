package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.ShelveTask;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/20.
 */
public interface ShelveMapper {
    void saveTask(ShelveTask task);

    List<ShelveTask> selectAll(@Param("cityId") Integer cityId, @Param("from") Date from, @Param("to") Date to,
                               @Param("status") Integer status, @Param("order")String order, @Param("sort")String sort,
                               @Param("taskName")String taskName);

    int delete(@Param("id") Integer id);

    void updateStatus(ShelveTask task);

    void runTask(ShelveTask task);
    
    ShelveTask getStatus(@Param("taskId") Integer taskId);
}
