package com.mallcai.bs.service;

import com.mallcai.bs.dao.InventoryDao;
import com.dailuobo.api.domain.entity.InventoryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/18.
 */
@Service
public class InventoryService {
    @Autowired
    private InventoryDao inventoryDao;

    @Transactional
    public void saveTask(List<InventoryTask> tasks) {
        inventoryDao.saveTask(tasks);
    }

    @Transactional
    public List<InventoryTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return inventoryDao.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    @Transactional
    public int delete(Integer id) {
        return inventoryDao.delete(id);
    }

    @Transactional
    public void updateStatus(InventoryTask task) {
        inventoryDao.updateStatus(task);
    }
    
    @Transactional
    public InventoryTask getStatus(Integer taskId) {
       return inventoryDao.getStatus(taskId);
    }
}
