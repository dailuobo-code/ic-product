package com.dailuobo.biz.service.product;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.newmgr.MultiBargainTask;
import com.dailuobo.api.product.MgrProductPriceTimerService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("mgrProductPriceTimerService")
public class MgrProductPriceTimerServiceImpl implements MgrProductPriceTimerService {

    @Autowired
    private MgrProductPriceTimerServiceHelp mgrProductPriceTimerServiceHelp;

    @Override
    public ICResponse<List<MultiBargainTask>> selectAll(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.startPage(offset / limit + 1, limit);
            }
            List<MultiBargainTask> list = mgrProductPriceTimerServiceHelp.selectAll(param);
            if (offset >= 0 && limit > 0) {
                PageInfo<MultiBargainTask> pageInfo = new PageInfo<>(list);
                return ICResponse.success(list, new PageDTO(limit, pageInfo.getTotal(), offset / limit + 1));
            }
            return ICResponse.success(list);
        }, "selectAll", param);

    }

    @Override
    public ICResponse<Boolean> addTask(List<MultiBargainTask> tasks) {
        return ICResponseHandler.template(() -> {
            mgrProductPriceTimerServiceHelp.addTask(tasks);
            return ICResponse.success(true);
        }, "addTask", tasks);


    }

    @Override
    public ICResponse<Boolean> delete(Integer taskId) {
        return ICResponseHandler.template(() -> {
            mgrProductPriceTimerServiceHelp.delete(taskId);
            return ICResponse.success(true);
        }, "delete", taskId);


    }
}
