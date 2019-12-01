package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.ActProductSign;
import com.dailuobo.api.domain.entity.BargainTaskByMulti;
import com.dailuobo.api.domain.entity.MultiBargainTask;
import com.mallcai.bs.mapper.MultiBargainMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/10/20.
 */
@Repository
public class MultiBargainDao {
    @Autowired
    private MultiBargainMapper multiBargainMapper;

    public void saveTask(MultiBargainTask task) {
        multiBargainMapper.saveTask(task);
    }

    public List<MultiBargainTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return multiBargainMapper.selectAll(cityId, from, to, status,order,sort,taskName);
    }


    public List<MultiBargainTask> selectByExecuteTime(Map<String, Object> param) {
        return multiBargainMapper.selectByExecuteTime(param);
    }

    public List<ActProductSign> selectByEffectTime(Map<String, Object> param) {
        return multiBargainMapper.selectByEffectTime(param);
    }

    public List<String> getNoExecuteTaskStoreIdsById(Map<String, Object> param) {
        return multiBargainMapper.getNoExecuteTaskStoreIdsById(param);
    }
    public List<String> getNoExecuteTaskStoreIdsByIdMgr(Map<String, Object> param) {
        return multiBargainMapper.getNoExecuteTaskStoreIdsByIdMgr(param);
    }

    public List<MultiBargainTask> getEffectiveTask(Map<String, Object> param) {
        return multiBargainMapper.getEffectiveTask(param);
    }

    public List<MultiBargainTask> getEffectiveTaskMgr(Map<String, Object> param) {
        return multiBargainMapper.getEffectiveTaskMgr(param);
    }

    public int delete(Integer id) {
        return multiBargainMapper.delete(id);
    }

    public void updateStatus(MultiBargainTask task) {
        multiBargainMapper.updateStatus(task);
    }

    public void runTask(BargainTaskByMulti task) {
        multiBargainMapper.runTask(task);
    }
    
    public MultiBargainTask getStatus(Integer taskId) {
        return multiBargainMapper.getStatus(taskId);
    }

	public Integer checkProductSignByDateAndSKU(Map<String, Object> param) {
		return multiBargainMapper.checkProductSignByDateAndSKU(param);
	}

	public void insertActProduct(Map<String, Object> param) {
		multiBargainMapper.insertActProduct(param);
	}

    public void updateProductSignEffectiveTime(Map<String, Object> param) {
        multiBargainMapper.updateProductSignEffectiveTime(param);
    }

    public void deleteProductSignByTaskIds(List<Integer> ids){
        multiBargainMapper.deleteProductSignByTaskIds(ids);
    }

	public void insertProductSignDetail(Map<String, Object> param) {
		multiBargainMapper.insertProductSignDetail(param);
	}
}
