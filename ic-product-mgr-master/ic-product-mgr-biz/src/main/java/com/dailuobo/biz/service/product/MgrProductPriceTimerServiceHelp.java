package com.dailuobo.biz.service.product;

import com.dailuobo.api.domain.newmgr.MultiBargainTask;
import com.dailuobo.biz.mapper.product.ProductPriceTimerMapper;
import com.dailuobo.api.domain.entity.BargainTaskByMulti;
import com.mallcai.bs.service.SOACityGlobalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MgrProductPriceTimerServiceHelp {

    @Resource
    private ProductPriceTimerMapper productPriceTimerMapper;

    @Autowired
    private SOACityGlobalService soaCityGlobalService;

    @Transactional
    public void addTask(List<MultiBargainTask> tasks) {
        for (MultiBargainTask task : tasks) {
            productPriceTimerMapper.addTask(task);
        }
    }

    public List<MultiBargainTask> selectAll(Map<String, Object> param) {
        List<MultiBargainTask> list = productPriceTimerMapper.selectAll(param);
        if (list != null && !list.isEmpty()) {
            List<String> productNoList = new ArrayList<>();
            List<Integer> userIdList = new ArrayList<>();
            list.forEach(multiBargainTask -> {
                productNoList.add(multiBargainTask.getProductNo());
                userIdList.add(multiBargainTask.getCreateUserId());
            });
            Map<Integer, String> userMap = getUserNameToMap(userIdList);
            Map<String, String> productNoMap = getHqProductIconToMap(productNoList);
            list.forEach(multiBargainTask -> {
                if (userMap != null) {
                    multiBargainTask.setCreateRealName(userMap.get(multiBargainTask.getCreateUserId()));
                }
                if (productNoMap != null) {
                    multiBargainTask.setHqProductIcon(productNoMap.get(multiBargainTask.getProductNo()));
                }
            });
        }
        return list;
    }

    public int delete(Integer id) {
        return productPriceTimerMapper.delete(id);
    }

    @Transactional
    public void runTask(MultiBargainTask multiBargainTask) {
        String[] storeIds = multiBargainTask.getStoreIds().split(",");
        for (String storeId : storeIds) {
            BargainTaskByMulti task = new BargainTaskByMulti();
            task.setCityId(multiBargainTask.getCityId());
            task.setStoreId(Integer.parseInt(storeId));
            task.setCityProductId(multiBargainTask.getCityProductId());
            task.setIconTip(multiBargainTask.getIconTip());
            task.setKeyword(multiBargainTask.getKeyword());
            task.setAvgPrice(multiBargainTask.getAvgPrice());
            task.setRealPrice(multiBargainTask.getRealPrice());
            task.setThresholdForPurchase(multiBargainTask.getThresholdForPurchase());
            task.setCreateUserId(multiBargainTask.getCreateUserId());
            productPriceTimerMapper.runTask(task);
        }
    }

    public void updateTask() {

        // 查询出所有待执行任务
        List<MultiBargainTask> nonExecutionTask = productPriceTimerMapper.getNonExecutionTask();
        for (MultiBargainTask multiBargainTask : nonExecutionTask) {

            Map<String, Object> param = new HashMap<>();
            param.put("id", multiBargainTask.getId());
            try {
                param.put("status", 1);
                // 更改任务状态
                productPriceTimerMapper.updateTaskStatus(param);
                log.info("=====>定时调价[" + multiBargainTask.getId() + "]正在执行<=====");


                // 执行任务
                runTask(multiBargainTask);


                // 更改任务状态
                param.put("status", 2);
                productPriceTimerMapper.updateTaskStatus(param);

                //
                soaCityGlobalService.updateProductV2(multiBargainTask.getCityProductId(),null);

            } catch (Exception e) {
                param.put("status", 3);
                param.put("exception", e.getMessage());
                productPriceTimerMapper.updateTaskStatus(param);
                log.error("=====>定时调价[" + multiBargainTask.getId() + "]执行失败<=====", e);
            }
            log.info("=====>定时调价[" + multiBargainTask.getId() + "]任务结束<=====");
        }

    }

    private Map<String, String> getHqProductIconToMap(List<String> productNos) {
        Map<String, String> resultMap = new HashMap<>();
        if (productNos == null || productNos.isEmpty()) {
            return null;
        }
        List<MultiBargainTask> list = productPriceTimerMapper.selectProductIconByProductNos(productNos);
        if (list != null && !list.isEmpty()) {
            list.forEach(multiBargainTask -> resultMap.put(multiBargainTask.getProductNo(), multiBargainTask.getHqProductIcon()));
        }
        return resultMap;
    }

    private Map<Integer, String> getUserNameToMap(List<Integer> userIds) {
        Map<Integer, String> resultMap = new HashMap<>();
        if (userIds == null || userIds.isEmpty()) {
            return null;
        }
        List<MultiBargainTask> list = productPriceTimerMapper.selectUserNameByUserIds(userIds);
        if (list != null && !list.isEmpty()) {
            list.forEach(multiBargainTask -> resultMap.put(multiBargainTask.getCreateUserId(), multiBargainTask.getCreateRealName()));
        }
        return resultMap;
    }
}
