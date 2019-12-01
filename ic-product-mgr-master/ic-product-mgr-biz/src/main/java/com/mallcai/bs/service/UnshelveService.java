package com.mallcai.bs.service;

import com.mallcai.bs.dao.UnshelveDao;
import com.dailuobo.api.domain.entity.UnshelveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/18.
 */
@Service
public class UnshelveService {
    @Autowired
    private UnshelveDao unshelveDao;

    @Transactional
    public void saveTask(UnshelveTask task) {
        unshelveDao.saveTask(task);
    }

    @Transactional
    public List<UnshelveTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return unshelveDao.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    @Transactional
    public int delete(Integer id) {
        return unshelveDao.delete(id);
    }

    @Transactional
    public void updateStatus(UnshelveTask task) {
        unshelveDao.updateStatus(task);
    }

    @Transactional
    public void runTask(UnshelveTask task) {
        unshelveDao.runTask(task);
    }

    public int getCityProductDeliveryMode(Integer cityProductId) {
        return unshelveDao.getCityProductDeliveryMode(cityProductId);
    }
    
    public UnshelveTask getStatus(Integer taskId) {
        return unshelveDao.getStatus(taskId);
    }
}
