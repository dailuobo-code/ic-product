package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.ShelveTask;
import com.dailuobo.api.product.MgrShelveService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.SOAHPService;
import com.mallcai.bs.service.ShelveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("mgrShelveService")
public class MgrShelveServiceImpl implements MgrShelveService {

    @Autowired
    private ShelveService shelveService;

    @Autowired
    private SOAHPService soaHPService;

    @Override
    public ICResponse<List<ShelveTask>> selectAll(Integer offset, Integer limit, Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        Map param = Maps.newHashMap();
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("cityId", cityId);
        param.put("from", from);
        param.put("to", to);
        param.put("status", status);
        param.put("order", order);
        param.put("sort", sort);
        param.put("taskName", taskName);

        return ICResponseHandler.template(() -> {
            if (offset != null) {
                PageHelper.startPage(offset / limit + 1, limit);
            }
            List<ShelveTask> list = shelveService.selectAll(cityId, from, to, status, order, sort, taskName);
            PageInfo<ShelveTask> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectAll", param);


    }

    @Override
    public ICResponse<ShelveTask> saveTask(ShelveTask task) {
        return ICResponseHandler.template(() -> {
            shelveService.saveTask(task);
            return ICResponse.success(task);
        }, "saveTask", task);


    }

    @Override
    public ICResponse<Boolean> delete(Integer id) {
        return ICResponseHandler.template(() -> {
            return ICResponse.success(shelveService.delete(id) == 1);
        }, "delete", id);


    }

    @Override
    public ICResponse<ShelveTask> getStatus(Integer id) {
        return ICResponseHandler.template(() -> {
            ShelveTask shelveTask = shelveService.getStatus(id);
            return ICResponse.success(shelveTask);
        }, "getStatus", id);


    }

    @Override
    public ICResponse<Boolean> updateStatus(ShelveTask task) {
        return ICResponseHandler.template(() -> {
            shelveService.updateStatus(task);
            return ICResponse.success(true);
        }, "updateStatus", task);


    }

    @Override
    public ICResponse<Boolean> runTask(ShelveTask task) {
        return ICResponseHandler.template(() -> {
            shelveService.runTask(task);
            return ICResponse.success(true);
        }, "runTask", task);

    }
}
