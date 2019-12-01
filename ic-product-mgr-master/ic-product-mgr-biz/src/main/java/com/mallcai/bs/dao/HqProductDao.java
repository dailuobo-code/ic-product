package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.HqProduct;
import com.dailuobo.api.domain.vo.CityProductIdAndMode;
import com.mallcai.bs.mapper.HqProductMapper;
import com.mallcai.bs.model.TblHqProductPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Deprecated
public class HqProductDao {
    @Autowired
    private HqProductMapper hqProductMapper;

    public List<HqProduct> selectAll(Map<String, Object> param) {
        return hqProductMapper.selectAll(param);
    }

    public void status(Map<String, Object> param) {
        this.hqProductMapper.status(param);
    }

    public void add(HqProduct hqproduct) {
        this.hqProductMapper.add(hqproduct);
    }

    public void duplicate(HqProduct hqproduct) {
        this.hqProductMapper.duplicate(hqproduct);
    }

    public void delete(List<Integer> ids) {
        this.hqProductMapper.delete(ids);
    }

    public void update(HqProduct hq) {
        this.hqProductMapper.update(hq);
    }

    public void updateProductName(Integer hqProductId, String newName) {
        this.hqProductMapper.updateProductName(hqProductId, newName);
    }

    public List<CityProductIdAndMode> getCityProductIds(List<Integer> hqProductIds) {
        return hqProductMapper.getCityProductIds(hqProductIds);
    }

    public int exist(String productNo) {
        return hqProductMapper.exist(productNo);
    }

    public int exist2(Map<String, Object> param) {
        return hqProductMapper.exist2(param);
    }

    public Integer getPointProduct(HqProduct hqProduct) {
        return hqProductMapper.getPointProduct(hqProduct);
    }

    public HqProduct selectHqProductById(Integer hqProductId) {
        return hqProductMapper.selectHqProductById(hqProductId);
    }

    public List<HqProduct> selectHqProductByIds(List<Integer> hqProductIds) {
        return hqProductMapper.selectHqProductByIds(hqProductIds);
    }

    public void updateCityProductStatus(Map<String, Object> param) {
        this.hqProductMapper.updateCityProductStatus(param);
    }

    public List<Integer> getClassifyIdsByProductNos(List<String> productNOs) {
        return hqProductMapper.getClassifyIdsByProductNos(productNOs);
    }

    public List<TblHqProductPO> selectHqProductBySelective(List<Integer> hqProductIds){
        return hqProductMapper.selectHqProductBySelective(hqProductIds);
    }
}
