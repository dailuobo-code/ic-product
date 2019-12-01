package com.mallcai.bs.service;

import com.mallcai.bs.dao.MultiBargainDao;
import com.dailuobo.api.domain.entity.ActProductSign;
import com.dailuobo.api.domain.entity.BargainTaskByMulti;
import com.dailuobo.api.domain.entity.MultiBargainTask;
import com.dailuobo.api.domain.soa.StandardResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/10/18.
 */
@Service
public class MultiBargainService {
    @Autowired
    private MultiBargainDao multiBargainDao;

    @Transactional
    public void saveTask(MultiBargainTask task) {
        multiBargainDao.saveTask(task);
    }

    @Transactional
    public List<MultiBargainTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return multiBargainDao.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    public List<MultiBargainTask> selectByExecuteTime(Map<String, Object> param) {
        return multiBargainDao.selectByExecuteTime(param);
    }

    public List<ActProductSign> selectByEffectTime(Map<String, Object> param) {
        return multiBargainDao.selectByEffectTime(param);
    }

    public List<String> getNoExecuteTaskStoreIdsById(Map<String, Object> param){
        return multiBargainDao.getNoExecuteTaskStoreIdsById(param);
    }

    public List<String> getNoExecuteTaskStoreIdsByIdMgr(Map<String, Object> param){
        return multiBargainDao.getNoExecuteTaskStoreIdsByIdMgr(param);
    }

    public List<MultiBargainTask> getEffectiveTask(Map<String, Object> param){
        return multiBargainDao.getEffectiveTask(param);
    }

    public List<MultiBargainTask> getEffectiveTaskMgr(Map<String, Object> param){
        return multiBargainDao.getEffectiveTaskMgr(param);
    }

    @Transactional
    public int delete(Integer id) {
        return multiBargainDao.delete(id);
    }

    @Transactional
    public void updateStatus(MultiBargainTask task) {
        multiBargainDao.updateStatus(task);
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
            multiBargainDao.runTask(task);
        }
    }
    
    @Transactional
    public MultiBargainTask getStatus(Integer taskId) {
        return multiBargainDao.getStatus(taskId);
    }

	public StandardResult signActProduct(Map<String, Object> param) {
		try {

		    // 同一门店在一天不能配置两个一分购商品的定时任务
			Integer count = multiBargainDao.checkProductSignByDateAndSKU(param);
			if(count!=null&&count.intValue()>0){
				return StandardResult.getDefaultFailedMsg("该标记日期该商品已被标记！");
			}
			multiBargainDao.insertActProduct(param);
			Integer signId = Integer.parseInt(param.get("id").toString());
			param.put("signId", signId);
			multiBargainDao.insertProductSignDetail(param);
			
		} catch (Exception e) {
			return StandardResult.getDefaultFailedMsg(e.getMessage());
		}
		return StandardResult.getDefaultSuccess();
	}

	public void deleteProductSignByTaskIds(List<Integer> ids){
        multiBargainDao.deleteProductSignByTaskIds(ids);
    }

	public void updateProductSignEffectiveTime(Map<String,Object> param){
        multiBargainDao.updateProductSignEffectiveTime(param);
    }

}
