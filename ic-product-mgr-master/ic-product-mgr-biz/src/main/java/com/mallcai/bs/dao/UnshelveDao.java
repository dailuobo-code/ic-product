package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.UnshelveTask;
import com.mallcai.bs.mapper.UnshelveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/20.
 */
@Repository
public class UnshelveDao {
    @Autowired
    private UnshelveMapper unshelveMapper;

    public void saveTask(UnshelveTask task) {
        unshelveMapper.saveTask(task);
    }

    public List<UnshelveTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return unshelveMapper.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    public int delete(Integer id) {
        return unshelveMapper.delete(id);
    }

    public void updateStatus(UnshelveTask task) {
        unshelveMapper.updateStatus(task);
    }

    public void runTask(UnshelveTask task) {
        unshelveMapper.runTask(task);
    }

    public int getCityProductDeliveryMode(Integer cityProductId) {
        return unshelveMapper.getCityProductDeliveryMode(cityProductId);
    }
    
    public UnshelveTask getStatus(Integer taskId) {
        return unshelveMapper.getStatus(taskId);
    }
}
