package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.domain.entity.SpecAdjustment;
import com.dailuobo.api.domain.entity.StoreSpec;
import com.mallcai.bs.mapper.SpecMapper;
import com.dailuobo.api.domain.vo.DefaultSpecVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
@Repository
public class SpecDao {
    @Autowired
    private SpecMapper specMapper;

    public Spec getDefaultSpec(Integer cityProductId) {
        return specMapper.getDefaultSpec(cityProductId);
    }

    public SpecAdjustment getSpecAdjustment(Integer cityId, Integer storeId, Integer cityProductId) {
        return specMapper.getSpecAdjustment(cityId, storeId, cityProductId);
    }

    public List<Spec> getSpecs(Integer cityId, Integer cityProductId) {
        return specMapper.getSpecs(cityId, cityProductId);
    }

    public void create(Spec spec) {
        specMapper.create(spec);
    }

    public void syncProductSpecCreate(Spec spec) {
        specMapper.syncProductSpecCreate(spec);
    }


    public void update(Spec spec) {
        specMapper.update(spec);
    }

    public void syncProductSpecUpdate(Spec spec) {
        specMapper.syncProductSpecUpdate(spec);
    }

    public void updateDefaultSpec(Spec spec) {
        specMapper.updateDefaultSpec(spec);
    }

    public void createOrUpdate(Spec spec) {
        specMapper.createOrUpdate(spec);
    }

    public List<StoreSpec> getStoreSpecs(Map<String, Object> param) {
        return specMapper.getStoreSpecs(param);
    }

    public void updateAdjustmentFactor(Integer cityId, Integer storeId, Integer cityProductId, Integer unify, Double delta, Integer upperLimit, Integer lowerLimit, Integer userId) {
        specMapper.updateAdjustmentFactor(cityId, storeId, cityProductId, unify, delta, upperLimit, lowerLimit, userId);
    }

	public void updateDefaultVipCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		specMapper.updateDefaultVipCount(param);
	}

	public List<Integer> getNeedInsertStores(Spec spec) {
		// TODO Auto-generated method stub
		return specMapper.getNeedInsertStores(spec);
	}

	public void insertDefaultSpec(DefaultSpecVo dsv) {
		// TODO Auto-generated method stub
		specMapper.insertDefaultSpec(dsv);
	}

    public Spec getDefaultSpecBySyncProduct(Integer cityProductId) {
        return specMapper.getDefaultSpecBySyncProduct(cityProductId);
    }

	/*public Map getLimitStore(Map param){
        return specMapper.getLimitStore(param);
    }
    public  Map getCityLimitStore(Map param){
        return specMapper.getCityLimitStore(param);
    }*/

	public List<Spec> getDefaultSpecByProductIds(List<Integer> cityProductIds) {
	    return specMapper.getDefaultSpecByProductIds(cityProductIds);
    }
}
