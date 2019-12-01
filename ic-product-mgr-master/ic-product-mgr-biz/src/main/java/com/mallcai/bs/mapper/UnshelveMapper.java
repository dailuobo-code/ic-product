package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.UnshelveTask;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/20.
 */
public interface UnshelveMapper {
    void saveTask(UnshelveTask task);

    List<UnshelveTask> selectAll(@Param("cityId") Integer cityId, @Param("from") Date from, @Param("to") Date to,
                                 @Param("status") Integer status, @Param("order")String order, @Param("sort")String sort,
                                 @Param("taskName")String taskName);

    int delete(@Param("id") Integer id);

    void updateStatus(UnshelveTask task);

    void runTask(UnshelveTask task);

    int getCityProductDeliveryMode(Integer cityProductId);
    
    UnshelveTask getStatus(@Param("taskId") Integer taskId);
}
