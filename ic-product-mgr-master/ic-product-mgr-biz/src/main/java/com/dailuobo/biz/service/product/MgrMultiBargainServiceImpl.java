package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.ActProductSign;
import com.dailuobo.api.domain.entity.MultiBargainTask;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.product.MgrMultiBargainService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.CityProductService;
import com.mallcai.bs.service.MultiBargainService;
import com.mallcai.bs.service.SOACityGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("mgrMultiBargainService")
public class MgrMultiBargainServiceImpl implements MgrMultiBargainService {

    @Autowired
    private MultiBargainService multiBargainService;

    @Autowired
    private SOACityGlobalService SOACityGlobalService;

    @Autowired
    private CityProductService cityProductService;

    @Override
    public ICResponse<List<MultiBargainTask>> selectAll(Integer offset, Integer limit, Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
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
            List<MultiBargainTask> list = multiBargainService.selectAll(cityId, from, to, status, order, sort, taskName);
            PageInfo<MultiBargainTask> pageInfo = new PageInfo<>(list);
            return ICResponse.success(list, new PageDTO(limit != null ? limit : 0, pageInfo.getTotal(), offset != null ? (offset / limit + 1) : 0));
        }, "selectAll", param);


    }

    @Override
    public ICResponse<MultiBargainTask> saveTask(MultiBargainTask task) {
        return ICResponseHandler.template(() -> {
            multiBargainService.saveTask(task);
            return ICResponse.success(task);
        }, "saveTask", task);


    }

    @Override
    public ICResponse<StandardResult> signActProduct(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            StandardResult result = multiBargainService.signActProduct(param);
            return ICResponse.success(result);
        }, "signActProduct", param);


    }

    @Override
    public ICResponse<Boolean> delete(Integer id) {
        return ICResponseHandler.template(() -> {
            return ICResponse.success(multiBargainService.delete(id) == 1);
        }, "delete", id);
    }

    @Override
    public ICResponse<MultiBargainTask> getStatus(Integer id) {
        return ICResponseHandler.template(() -> {
            MultiBargainTask multiBargainTask = multiBargainService.getStatus(id);
            return ICResponse.success(multiBargainTask);
        }, "getStatus", id);


    }

    @Override
    public ICResponse<Boolean> updateStatus(MultiBargainTask task) {
        return ICResponseHandler.template(() -> {
            multiBargainService.updateStatus(task);
            return ICResponse.success(true);
        }, "updateStatus", task);


    }

    public ICResponse<Boolean> runTask(MultiBargainTask task) {
        return ICResponseHandler.template(() -> {
            multiBargainService.runTask(task);
            return ICResponse.success(true);
        }, "runTask", task);
    }

    public ICResponse<Boolean> updateProductSignEffectiveTime(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            multiBargainService.updateProductSignEffectiveTime(param);
            return ICResponse.success(true);
        }, "updateProductSignEffectiveTime", param);
    }

    @Override
    public ICResponse<List<MultiBargainTask>> getEffectiveTask(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            List<MultiBargainTask> effectiveTask = multiBargainService.getEffectiveTask(param);
            return ICResponse.success(effectiveTask);
        }, "getEffectiveTask", param);


    }

    @Override
    public ICResponse<List<MultiBargainTask>> getEffectiveTaskMgr(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            List<MultiBargainTask> effectiveTask = multiBargainService.getEffectiveTaskMgr(param);
            return ICResponse.success(effectiveTask);
        }, "getEffectiveTaskMgr", param);


    }

    @Override
    public ICResponse<List<String>> getNoExecuteTaskStoreIdsById(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            List<String> noExecuteTaskStoreIdsById = multiBargainService.getNoExecuteTaskStoreIdsById(param);
            return ICResponse.success(noExecuteTaskStoreIdsById);
        }, "getNoExecuteTaskStoreIdsById", param);


    }

    @Override
    public ICResponse<List<String>> getNoExecuteTaskStoreIdsByIdMgr(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            List<String> noExecuteTaskStoreIdsByIdMgr = multiBargainService.getNoExecuteTaskStoreIdsByIdMgr(param);
            return ICResponse.success(noExecuteTaskStoreIdsByIdMgr);
        }, "getNoExecuteTaskStoreIdsByIdMgr", param);


    }

    @Override
    public ICResponse<List<MultiBargainTask>> selectByExecuteTime(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            List<MultiBargainTask> multiBargainTasks = multiBargainService.selectByExecuteTime(param);
            return ICResponse.success(multiBargainTasks);
        }, "selectByExecuteTime", param);


    }

    @Override
    public ICResponse<List<ActProductSign>> selectByEffectTime(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            List<ActProductSign> actProductSigns = multiBargainService.selectByEffectTime(param);
            return ICResponse.success(actProductSigns);
        }, "selectByEffectTime", param);
    }

    @Override
    public ICResponse deleteProductSignByTaskIds(List<Integer> ids) {
        return ICResponseHandler.template(() -> {
            multiBargainService.deleteProductSignByTaskIds(ids);
            return ICResponse.success();
        }, "deleteProductSignByTaskIds", ids);


    }
}
