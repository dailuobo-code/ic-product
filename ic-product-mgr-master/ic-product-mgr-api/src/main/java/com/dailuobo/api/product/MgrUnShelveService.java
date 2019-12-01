package com.dailuobo.api.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.ShelveTask;
import com.dailuobo.api.domain.entity.UnshelveTask;
import com.dailuobo.api.domain.soa.StandardResult;

import java.util.Date;
import java.util.List;

public interface MgrUnShelveService {

    ICResponse<List<UnshelveTask>> selectAll(Integer offset, Integer limit, Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName);

    ICResponse<UnshelveTask> saveTask(UnshelveTask task);

    ICResponse<Boolean> delete(Integer id);

    ICResponse<UnshelveTask> getStatus(Integer id);

    ICResponse<Boolean> updateStatus(UnshelveTask task);

    ICResponse<Boolean> runTask(UnshelveTask task);

    ICResponse<Integer> getCityProductDeliveryMode(Integer cityProductId);

    ICResponse<StandardResult> deleteMarketingInventory(List<Integer> cityProductIds);
}
