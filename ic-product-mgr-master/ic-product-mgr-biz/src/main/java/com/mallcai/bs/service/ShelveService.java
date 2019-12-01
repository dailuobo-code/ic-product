package com.mallcai.bs.service;

import com.mallcai.bs.dao.ShelveDao;
import com.dailuobo.api.domain.entity.ShelveTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/18.
 */
@Service
public class ShelveService {
    @Autowired
    private ShelveDao shelveDao;

    @Transactional
    public void saveTask(ShelveTask task) {
        shelveDao.saveTask(task);
    }

    @Transactional
    public List<ShelveTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return shelveDao.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    @Transactional
    public int delete(Integer id) {
        return shelveDao.delete(id);
    }

    @Transactional
    public void updateStatus(ShelveTask task) {
        shelveDao.updateStatus(task);
    }

    @Transactional
    public void runTask(ShelveTask task) {
        shelveDao.runTask(task);
    }
    
    @Transactional
    public ShelveTask getStatus(Integer taskId) {
       return shelveDao.getStatus(taskId);
    }
}
