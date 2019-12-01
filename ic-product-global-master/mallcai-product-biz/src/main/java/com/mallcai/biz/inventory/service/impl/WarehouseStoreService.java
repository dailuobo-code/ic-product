package com.mallcai.biz.inventory.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.api.inventory.IWarehouseStoreService;
import com.mallcai.backend.common.redis.DefaultJedisProxy;
import com.mallcai.backend.common.redis.DefaultMasterJedisProxy;
import com.mallcai.biz.inventory.dao.StoreWarehouseDAO;
import com.mallcai.biz.inventory.model.StoreWarehouseDO;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.inventory.request.StoreWarehouseRequest;
import com.mallcai.domain.inventory.vo.WarehouseVo;
import com.mallcai.utils.AppEnv;
import com.mallcai.utils.NacosParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-01 16:12
 */
@Service("warehouseStoreService")
@Slf4j
public class WarehouseStoreService implements IWarehouseStoreService {

    @Autowired
    private StoreWarehouseDAO storeWarehouseDAO;

    @Autowired
    private NacosParser nacosParser;


    @Resource
    private AppEnv appEnv;

    @Override
    public ICResponse<List<WarehouseVo>> getWarehouseListByStoreId(Integer storeId) {

        DefaultMasterJedisProxy.getInstance().set("getWarehouseListByStoreId","ops");
        DefaultMasterJedisProxy.getInstance().delKey("getWarehouseListByStoreId");
        log.info("DefaultJedisProxy instance execute success...");

        ICResponse<Map<Integer, List<WarehouseVo>>> rs = getWarehouseListByStoreIds(Lists.newArrayList(storeId));

        if (!rs.isSuccess()) {
            return ICResponse.fail("加载仓库信息异常 storeid=" + storeId);
        }
        return ICResponse.success(rs.getData().get(storeId));

    }

    @Override
    public ICResponse<Map<Integer, List<WarehouseVo>>> getWarehouseListByStoreIds(List<Integer> storeIds) {
        List<StoreWarehouseDO> wareHouseStores;
        try {
            wareHouseStores = storeWarehouseDAO.getWareHouseByStoreIds(storeIds);
        } catch (Exception e) {
            log.error("getWarehouseListByStoreIds occur error . storeids:" + JSONObject.toJSONString(storeIds), e);
            return ICResponse.fail("加载仓库信息异常 storeids=" + JSONObject.toJSONString(storeIds));
        }

        Map<Integer, List<WarehouseVo>> listHashMap = Maps.newHashMap();

        wareHouseStores.forEach(ws -> {
            List<WarehouseVo> warehouseVos = listHashMap.get(ws.getStoreId());

            if (warehouseVos == null) {
                warehouseVos = Lists.newArrayList();
                listHashMap.put(ws.getStoreId(), warehouseVos);
            }
            WarehouseVo vo = new WarehouseVo();
            BeanUtils.copyProperties(ws, vo);
            warehouseVos.add(vo);
        });
        //storeid查无记录 map.value=empty
        Map<Integer, List<WarehouseVo>> data = storeIds.stream().distinct().collect(toMap(e -> e, e -> {
            List<WarehouseVo> warehouseVo = listHashMap.get(e);
            return warehouseVo == null ? Lists.newArrayList() : warehouseVo;
        }));
        return ICResponse.success(data);
    }

    @Override
    public ICResponse<String> bindStoreWarehouse(StoreWarehouseRequest request) {

        if (!nacosParser.isOpenCaicai2erp()) {
            return ICResponse.fail("开关尚未打开,请勿操作.");
        }
        if (request == null) {
            return ICResponse.fail("参数异常");
        }

        try {
            storeWarehouseDAO.batchBindWarehouse(request);
            log.info("bindStoreWarehouse execute success. param:{}", request);
            return ICResponse.success(null);
        } catch (Exception e) {
            log.error("bindStoreWarehouse invoke error, params:" + request, e);
            return ICResponse.fail("仓库门店关系绑定异常. params:" + request);
        }
    }

    @Override
    public ICResponse<List<WarehouseVo>> getWarehouseListByCityId(Integer cityId) {
        if (cityId == null) {
            return ICResponse.fail("城市id不为空");
        }
        try {
            List<StoreWarehouseDO> warehouseDos = storeWarehouseDAO.getWareHouseByCityId(cityId);
            List<WarehouseVo> warehouseVos = warehouseDos.stream().map(warehouseDo -> {
                WarehouseVo vo = new WarehouseVo();
                BeanUtils.copyProperties(warehouseDo, vo);
                return vo;
            }).collect(Collectors.toList());
            return ICResponse.success(warehouseVos);
        } catch (Exception e) {
            log.error("getWarehouseListByCityId occur error . cityid:" + cityId, e);
            return ICResponse.fail(" load getWarehouseListByCityId error. cityid=" + cityId);
        }
    }

}
