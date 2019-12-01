package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.UnshelveTask;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.product.MgrUnShelveService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.SOAHPService;
import com.mallcai.bs.service.SOAMarketingInventoryService;
import com.mallcai.bs.service.UnshelveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("mgrUnShelveService")
public class MgrUnShelveServiceImpl implements MgrUnShelveService {

    @Autowired
    private UnshelveService unshelveService;

    @Autowired
    private SOAHPService soaHPService;

    @Autowired
    private SOAMarketingInventoryService soaMarketingInventoryService;

    @Override
    public ICResponse<List<UnshelveTask>> selectAll(Integer offset, Integer limit, Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {

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
            List<UnshelveTask> list = unshelveService.selectAll(cityId, from, to, status, order, sort, taskName);
            PageInfo<UnshelveTask> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectAll", param);


    }

    @Override
    public ICResponse<UnshelveTask> saveTask(UnshelveTask task) {
        return ICResponseHandler.template(() -> {
            unshelveService.saveTask(task);
            return ICResponse.success(task);
        }, "saveTask", task);


    }

    @Override
    public ICResponse<Boolean> delete(Integer id) {
        return ICResponseHandler.template(() -> {
            return ICResponse.success(unshelveService.delete(id) == 1);
        }, "delete", id);


    }

    @Override
    public ICResponse<UnshelveTask> getStatus(Integer id) {
        return ICResponseHandler.template(() -> {
            UnshelveTask unshelveTask = unshelveService.getStatus(id);
            return ICResponse.success(unshelveTask);
        }, "getStatus", id);
    }

    @Override
    public ICResponse<Boolean> updateStatus(UnshelveTask task) {
        return ICResponseHandler.template(() -> {
            unshelveService.updateStatus(task);
            return ICResponse.success(true);
        }, "updateStatus", task);


    }

    @Override
    public ICResponse<Boolean> runTask(UnshelveTask task) {
        return ICResponseHandler.template(() -> {
            unshelveService.runTask(task);
            return ICResponse.success(true);
        }, "runTask", task);

    }

    @Override
    public ICResponse<Integer> getCityProductDeliveryMode(Integer cityProductId) {
        return ICResponseHandler.template(() -> {
            Integer i = unshelveService.getCityProductDeliveryMode(cityProductId);
            return ICResponse.success(i);
        }, "getCityProductDeliveryMode", cityProductId);


    }

    @Override
    public ICResponse<StandardResult> deleteMarketingInventory(List<Integer> cityProductIds) {
        return ICResponseHandler.template(() -> {
            StandardResult standardResult = soaMarketingInventoryService.deleteMarketingInventory(cityProductIds);
            return ICResponse.success(standardResult);
        }, "getCityProductDeliveryMode", cityProductIds);


    }
}
