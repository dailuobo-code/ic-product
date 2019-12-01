package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.domain.entity.SpecAdjustment;
import com.dailuobo.api.domain.entity.StoreSpec;
import com.dailuobo.api.domain.vo.DefaultSpecVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
public interface SpecMapper {
    Spec getDefaultSpec(@Param("cityProductId") Integer cityProductId);

    SpecAdjustment getSpecAdjustment(@Param("cityId") Integer cityId, @Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId);

    List<Spec> getSpecs(@Param("cityId") Integer cityId, @Param("cityProductId") Integer cityProductId);

    void create(Spec spec);

    void update(Spec spec);

    void syncProductSpecCreate(Spec spec);

    void syncProductSpecUpdate(Spec spec);

    void updateDefaultSpec(Spec spec);

    void createOrUpdate(Spec spec);

    List<StoreSpec> getStoreSpecs(Map<String, Object> param);

    void updateAdjustmentFactor(@Param("cityId") Integer cityId, @Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId, @Param("unify") Integer unify, @Param("delta") Double delta, @Param("upperLimit") Integer upperLimit, @Param("lowerLimit") Integer lowerLimit, @Param("userId") Integer userId);

	void updateDefaultVipCount(Map<String, Object> param);

	List<Integer> getNeedInsertStores(Spec spec);

	void insertDefaultSpec(DefaultSpecVo dsv);

    Spec getDefaultSpecBySyncProduct(@Param("cityProductId") Integer cityProductId);

/*	Map getLimitStore(Map param);
    Map getCityLimitStore(Map param);*/

    void addSpec(@Param("spec") Spec spec, @Param("userId") Integer userId);

    List<Spec> getDefaultSpecByProductIds(@Param("cityProductIds") List<Integer> cityProductIds);

    void batchAddSpec(@Param("list") List<Spec> specs);
}
