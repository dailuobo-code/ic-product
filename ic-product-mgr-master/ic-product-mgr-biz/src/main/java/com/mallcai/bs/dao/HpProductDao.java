package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.HpProduct;
import com.mallcai.bs.mapper.HpProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class HpProductDao {
    @Autowired
    private HpProductMapper hpProductMapper;

    public List<HpProduct> selectAll(Map<String, Object> param) {
        return this.hpProductMapper.selectAll(param);
    }

    public void add(List<HpProduct> hpProducts) {
        this.hpProductMapper.add(hpProducts);
    }

    public void delete(List<Integer> ids) {
        this.hpProductMapper.delete(ids);
    }

    public void update(HpProduct hpProduct) {
        this.hpProductMapper.update(hpProduct);
    }
}
