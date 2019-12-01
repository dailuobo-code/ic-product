package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.ActProductSign;
import com.dailuobo.api.domain.entity.BargainTaskByMulti;
import com.dailuobo.api.domain.entity.MultiBargainTask;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/10/20.
 */
public interface MultiBargainMapper {
    void saveTask(MultiBargainTask task);

    List<MultiBargainTask> selectAll(@Param("cityId") Integer cityId, @Param("from") Date from, @Param("to") Date to,
                                     @Param("status") Integer status, @Param("order")String order, @Param("sort")String sort,
                                     @Param("taskName")String taskName);

    List<MultiBargainTask> selectByExecuteTime(Map<String, Object> param);

    List<ActProductSign> selectByEffectTime(Map<String, Object> param);

    List<String> getNoExecuteTaskStoreIdsById(Map<String, Object> param);

    List<String> getNoExecuteTaskStoreIdsByIdMgr(Map<String, Object> param);

    List<MultiBargainTask> getEffectiveTask(Map<String, Object> param);

    List<MultiBargainTask> getEffectiveTaskMgr(Map<String, Object> param);

    int delete(@Param("id") Integer id);

    void updateStatus(MultiBargainTask task);

    void runTask(BargainTaskByMulti task);
    
    MultiBargainTask getStatus(@Param("taskId") Integer taskId);

	Integer checkProductSignByDateAndSKU(Map<String, Object> param);

	void insertActProduct(Map<String, Object> param);

    void updateProductSignEffectiveTime(Map<String, Object> param);

    void deleteProductSignByTaskIds(@Param("ids") List<Integer> ids);

	void insertProductSignDetail(Map<String, Object> param);
}
