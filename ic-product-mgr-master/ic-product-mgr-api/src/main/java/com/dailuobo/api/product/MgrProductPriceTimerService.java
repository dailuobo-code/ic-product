package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.newmgr.MultiBargainTask;

import java.util.List;
import java.util.Map;

/**
 * 参考新mgr<code>ProductPriceTimerController.java</code>
 */
public interface MgrProductPriceTimerService {

    ICResponse<List<MultiBargainTask>> selectAll(Map<String, Object> param);

    ICResponse<Boolean> addTask(List<MultiBargainTask> tasks);

    ICResponse<Boolean> delete(Integer taskId);
}
