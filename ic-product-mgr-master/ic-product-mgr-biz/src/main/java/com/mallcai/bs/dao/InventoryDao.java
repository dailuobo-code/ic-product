package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.InventoryTask;
import com.mallcai.bs.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Cowboy on 2016/10/20.
 */
@Repository
public class InventoryDao {
    @Autowired
    private InventoryMapper inventoryMapper;

    public void saveTask(List<InventoryTask> tasks) {
        inventoryMapper.saveTask(tasks);
    }

    public List<InventoryTask> selectAll(Integer cityId, Date from, Date to, Integer status, String order, String sort, String taskName) {
        return inventoryMapper.selectAll(cityId, from, to, status,order,sort,taskName);
    }

    public int delete(Integer id) {
        return inventoryMapper.delete(id);
    }

    public void updateStatus(InventoryTask task) {
        inventoryMapper.updateStatus(task);
    }
    
    public InventoryTask getStatus(Integer taskId) {
        return inventoryMapper.getStatus(taskId);
    }
    
}
