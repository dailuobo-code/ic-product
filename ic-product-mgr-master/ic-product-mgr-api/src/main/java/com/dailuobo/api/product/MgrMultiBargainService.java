package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.ActProductSign;
import com.dailuobo.api.domain.entity.MultiBargainTask;
import com.dailuobo.api.domain.soa.StandardResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MgrMultiBargainService {

    ICResponse<List<MultiBargainTask>> selectAll(Integer offset, Integer limit, Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName);

    ICResponse<MultiBargainTask> saveTask(MultiBargainTask task);

    ICResponse<StandardResult> signActProduct(Map<String, Object> param);

    ICResponse<Boolean> delete(Integer id);

    ICResponse<MultiBargainTask> getStatus(Integer id);

    ICResponse<Boolean> updateStatus(MultiBargainTask task);

    ICResponse<Boolean> runTask(MultiBargainTask task);

    ICResponse<Boolean> updateProductSignEffectiveTime(Map<String, Object> param);

    ICResponse<List<MultiBargainTask>> getEffectiveTask(Map<String, Object> param);

    ICResponse<List<MultiBargainTask>> getEffectiveTaskMgr(Map<String, Object> param);

    ICResponse<List<String>> getNoExecuteTaskStoreIdsById(Map<String, Object> param);

    ICResponse<List<String>> getNoExecuteTaskStoreIdsByIdMgr(Map<String, Object> param);

    ICResponse< List<MultiBargainTask>> selectByExecuteTime(Map<String, Object> param);


    ICResponse<List<ActProductSign>> selectByEffectTime(Map<String, Object> param);

    ICResponse deleteProductSignByTaskIds(List<Integer> ids);

}

