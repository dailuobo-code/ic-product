package com.mallcai.bs.service;

import com.mallcai.bs.dao.HpProductDao;
import com.dailuobo.api.domain.entity.HpProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class HpProductService {
    @Autowired
    private HpProductDao hpProductDao;

    public List<HpProduct> selectAll(Map<String, Object> param) {
        return this.hpProductDao.selectAll(param);
    }

    @Transactional
    public void add(List<HpProduct> hpProducts) {
        this.hpProductDao.add(hpProducts);
    }

    @Transactional
    public void delete(List<Integer> ids) {
        this.hpProductDao.delete(ids);
    }

    @Transactional
    public void update(HpProduct hpProduct) {
        this.hpProductDao.update(hpProduct);
    }

}
