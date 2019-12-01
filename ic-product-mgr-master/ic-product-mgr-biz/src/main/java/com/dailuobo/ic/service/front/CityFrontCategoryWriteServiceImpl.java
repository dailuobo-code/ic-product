package com.dailuobo.ic.service.front;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.category.front.ICityFrontCategoryWriteService;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationDeleteRequest;
import com.dailuobo.ic.api.request.category.front.FrontCategoryRelationsCreateRequest;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.dailuobo.ic.manager.category.front.CityFrontCategoryRelationRedisManager;
import com.dailuobo.ic.manager.category.front.CityFrontCategoryRelationWriteManager;
import dailuobo.dao.mapper.FrontCategoryRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("cityFrontCategoryWriteService")
@Slf4j
public class CityFrontCategoryWriteServiceImpl implements ICityFrontCategoryWriteService {

    @Autowired
    private CityFrontCategoryRelationWriteManager cityFrontCategoryRelationWriteManager;

    @Autowired
    private CityFrontCategoryRelationRedisManager cityFrontCategoryRelationRedisManager;

    @Autowired
    private FrontCategoryRelationMapper frontCategoryRelationMapper;


    @Value("secret.key")
    private String secretKey;

    @Override
    public ICResponse<Boolean> saveFCategoryRelation(FrontCategoryRelationsCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        log.info("##saveFCategoryRelation ===> params:{}", JSON.toJSONString(request));
        request.checkParams();
        try {

            cityFrontCategoryRelationWriteManager.saveFrontCategoryRelation(request);
            cityFrontCategoryRelationRedisManager.refreshLv2FrontCategoryRelationCache(request.getFrontCategoryId(), request.getCityId());
            return ICResponse.success();
        } catch (Exception ex) {
            log.error(String.format("##saveFCategoryRelation query error,param:%sd", request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }

    @Override
    public ICResponse<Boolean> delFCategoryRelation(FCategoryRelationDeleteRequest request) {
        log.info("##delFCategoryRelation ===> params:{}", JSON.toJSONString(request));
        if (request == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        request.checkParams();
        try {
            FrontCategoryRelationDO frontCategoryRelationDO = frontCategoryRelationMapper.loadFCategoryRelationById(request.getRelationId());
            if (frontCategoryRelationDO == null) {
                return ICResponse.success();
            }
            cityFrontCategoryRelationWriteManager.deletedFrontRelation(request);
            cityFrontCategoryRelationRedisManager.refreshLv2FrontCategoryRelationCache(frontCategoryRelationDO.getFrontCategoryId(), frontCategoryRelationDO.getCityId());
            return ICResponse.success();
        } catch (Exception ex) {
            log.error(String.format("##delFCategoryRelation query error,param:%sd", request), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }


    public ICResponse refreshAllCategoryRelations(Integer cityId, String key) {
        if (key == null || !Objects.equals(key, "00xb7258"))
            return ICResponse.success();
        cityFrontCategoryRelationRedisManager.refreshAllFrontCategoryRelation(cityId);
        return ICResponse.success();
    }
}
