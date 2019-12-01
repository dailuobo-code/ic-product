package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.MultiBargainTask;
import com.dailuobo.api.domain.entity.ShelveTask;

import java.util.Date;
import java.util.List;

public interface MgrShelveService {

    ICResponse<List<ShelveTask>> selectAll(Integer offset, Integer limit, Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName);

    ICResponse<ShelveTask> saveTask(ShelveTask task);

    ICResponse<Boolean> delete(Integer id);

    ICResponse<ShelveTask> getStatus(Integer id);

    ICResponse<Boolean> updateStatus(ShelveTask task);

    ICResponse<Boolean> runTask(ShelveTask task);
}
