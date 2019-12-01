package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.ShelveTask;
import com.mallcai.bs.mapper.ShelveMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/20.
 */
@Repository
public class ShelveDao {
    @Autowired
    private ShelveMapper shelveMapper;

    public void saveTask(ShelveTask task) {
        shelveMapper.saveTask(task);
    }

    public List<ShelveTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return shelveMapper.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    public int delete(Integer id) {
        return shelveMapper.delete(id);
    }

    public void updateStatus(ShelveTask task) {
        shelveMapper.updateStatus(task);
    }

    public void runTask(ShelveTask task) {
        shelveMapper.runTask(task);
    }
    
    public ShelveTask getStatus(Integer taskId) {
        return shelveMapper.getStatus(taskId);
    }
}
