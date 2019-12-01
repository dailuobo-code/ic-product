package com.mallcai.bs.manager.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.ivy.IVYStockDTO;
import com.dailuobo.api.domain.vo.DDLWarehouse;
import com.dailuobo.api.domain.vo.InventoryExportVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.enums.DelFlagEnum;
import com.mallcai.bs.enums.InventoryExportEnum;
import com.mallcai.bs.enums.WarehouseTypeEnum;
import com.mallcai.bs.mapper.CityWarehouseMapper;
import com.mallcai.bs.mapper.InventoryExportMapper;
import com.mallcai.bs.mapper.MarketingInventoryMapper;
import com.mallcai.bs.mapper.MarketingInventoryMapper2;
import com.mallcai.bs.model.ExportParameter;
import com.mallcai.bs.model.ivy.MarketingInventoryBaseDO;
import com.mallcai.bs.model.ivy.MarketingInventoryDO;
import com.mallcai.bs.service.ExecuteInventoryExportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IVYMarketingInventoryReadWriteManager {

    @Autowired
    private InventoryExportMapper inventoryExportMapper;

    @Autowired
    private CityWarehouseMapper cityWarehouseMapper;

    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private MarketingInventoryMapper marketingInventoryMapper;

    @Autowired
    private MarketingInventoryMapper2 marketingInventoryMapper2;


    private Integer specialCityId = 30;

    private static final DateTimeFormatter exportDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public InventoryExportVo getInventoryExportById(Integer id){
        return inventoryExportMapper.selectById(id);
    }

    /**
     * 批量写库存
     *
     * @param cityId
     * @param request
     * @return
     */
    public Integer batchWriteStock(Integer cityId, List<IVYStockDTO> request) throws Exception{

        //TODO 通过缓存key 查看是否有批量刷新库存操作

        boolean isLock = false;
        if(isLock){
            throw new Exception("库存刷新的定时任务正在执行,请5-10分钟后操作,谢谢");
        }

        //查看是否有多过规格商品存在，不支持
        Map<String, Object> mapParam = new HashMap<>(1);

        List<Integer> productIds = request.stream().map( e->e.getCityProductId()).collect(Collectors.toList());

        mapParam.put("list",productIds);
        int multiSpec = marketingInventoryMapper.countMultiSpec(mapParam);
        if(multiSpec > 0){
            throw new Exception("暂时不支持多规格商品");
        }
        List<CityProduct> productListFromDB = cityProductDao.listProductForStock(cityId,productIds,null,null);

        List<Integer> productInDBList = productListFromDB.stream().map(n -> n.getCityProductId()).collect(Collectors.toList());
        Map<Integer, CityProduct> productBasicMap = productListFromDB.stream().collect(Collectors.toMap(CityProduct::getCityProductId, cityProduct -> cityProduct));

        //异常商品ID
        List<Integer> exceptionCityProductIdList = Lists.newLinkedList();
        List<Integer> exceptionNotShareCityProductId = Lists.newArrayList();
        List<Integer> exceptionHasWarehouseId = Lists.newArrayList();

        Set<Integer> cityProductIdsInParam = Sets.newHashSet();
        request.forEach(stock -> cityProductIdsInParam.add(stock.getCityProductId()));
        //1。商品不在对应城市
        if (cityProductIdsInParam.size() != productListFromDB.size()) {
            cityProductIdsInParam.forEach(cityProductId -> {
                if (!productInDBList.contains(cityProductId)) {
                    exceptionCityProductIdList.add(cityProductId);
                }
            });
            throw  new Exception("商品数据异常:" + exceptionCityProductIdList + ";请核对后重试");
        }

        List<DDLWarehouse> storeWarehouseList = cityWarehouseMapper.getWareHouse(Arrays.asList(cityId),null,null,null);

        Map<Integer, Set<Integer>> warehouseId2TypeMap = storeWarehouseList.stream().collect(Collectors.groupingBy(DDLWarehouse::getType, Collectors.mapping(DDLWarehouse::getWarehouseId, Collectors.toSet())));

        request.forEach(n -> {
            Integer cityProductId = n.getCityProductId();
            Integer wareHouseId = n.getWarehouseId();
            Integer storeId = n.getStoreId();
            CityProduct cityProduct = productBasicMap.get(cityProductId);
            if (true) {
                if (cityProduct.getIsShare() == 1) {
                    if (storeId != 0 || wareHouseId == -1) {
                        exceptionCityProductIdList.add(cityProductId);
                        return;
                    }
                    if (cityProduct.getIsStandard() == 1) {
                        if (!warehouseId2TypeMap.get(WarehouseTypeEnum.STANDARD.getType()).contains(wareHouseId)) {
                            log.error(" 标品对应的仓类型异常 cityId:{},cityProductId:{},wareHouseId:{}", cityId, cityProductId, wareHouseId);
                            exceptionCityProductIdList.add(cityProductId);
                        }
                    } else if (cityProduct.getIsStandard() == 2 && cityId.equals(specialCityId)) {
                        if (!warehouseId2TypeMap.get(WarehouseTypeEnum.FRESH.getType()).contains(wareHouseId)) {
                            log.error(" 生鲜对应的仓类型异常 cityId:{},cityProductId:{},wareHouseId:{}", cityId, cityProductId, wareHouseId);
                            exceptionCityProductIdList.add(cityProductId);
                        }
                    }
                } else {
                    if (storeId == 0 || wareHouseId != -1) {
                        exceptionNotShareCityProductId.add(cityProductId);
                    }
                }
            } else {
                if (wareHouseId != -1) {
                    log.error("不是合肥，但是有仓, cityId:{},cityProductId:{}", cityId, cityProductId);
                    exceptionHasWarehouseId.add(cityProductId);
                }
            }
        });

        if (CollectionUtils.isNotEmpty(exceptionCityProductIdList)) {
            throw new Exception("共享仓对应仓有问题商品Ids:" + exceptionCityProductIdList);
        }

        if (CollectionUtils.isNotEmpty(exceptionNotShareCityProductId)) {
            throw new Exception("非共享对应仓有问题商品Ids:" + exceptionCityProductIdList);
        }

        if (CollectionUtils.isNotEmpty(exceptionHasWarehouseId)) {
            throw new Exception("没仓的城市：" + cityId + ", 却对应有仓；请检查商品列表：" + exceptionHasWarehouseId);
        }

        List<MarketingInventoryDO> list = OrikaUtil.convertList(request, MarketingInventoryDO.class);

        marketingInventoryMapper2.batchWriteStock(list);

        // TODO 远程调用进行库存重算

        return request.size();
    }

    /**
     * 调整库存,提供给mgr设置共享标品单存
     * 前置条件 ：
     * 1、标品
     * 2、共享
     * 3、与可用库存作对比 取相对的小值
     * 4、返回失败message
     *
     * @param cityId
     * @param cityProductId
     * @param afterAvailable
     * @return
     */
    @Transactional
    public Integer adjustingStock(Integer cityId, Integer cityProductId, Integer afterAvailable) throws Exception {


        Boolean result = cityProductDao.checkIfShareAndStandard(cityId, cityProductId,1,1);
        if(!result){
            throw  new Exception("不是共享商品或标品");
        }
        Integer storeId = 0;
        List<MarketingInventory> marketingInventories = marketingInventoryMapper.getMarketingShares(storeId,cityProductId);
        if(CollectionUtils.isEmpty(marketingInventories) || marketingInventories.size() > 1){
            throw new Exception("商品库存不存在或存在多个商品");
        }
        MarketingInventory marketingInventory = marketingInventories.get(0);
        if(afterAvailable.compareTo(marketingInventory.getAvailable()) > 0){
            log.error("afterAvailable:{},inTimeInventory:{}", afterAvailable, marketingInventory.getAvailable());
            throw new Exception("可售库存必须小于或等于仓库实际库存" + marketingInventory.getAvailable());
        }

        Integer affectRows = marketingInventoryMapper2.checkAndSetStock(cityProductId,storeId,afterAvailable,marketingInventory.getVersion());
        if(affectRows < 1){
            throw new Exception("请校验库存量,重新设值");
        }

        //TODO 调用重新计算

        int delta = marketingInventory.getAvailable() - afterAvailable;
        marketingInventoryMapper2.updateBaseInventory(cityProductId, storeId, delta);

        return affectRows;
    }

    /**
     * 导出库存
     *
     * @param cityIds
     * @param classifyIds
     * @param userId
     * @param username
     * @return
     */
    public Integer startExportInventoryOfCities(List<Integer> cityIds, List<Integer> classifyIds, Integer userId, String username) throws Exception {

        InventoryExportVo toAdd = new InventoryExportVo();
        toAdd.setProgress((byte) 0);
        toAdd.setStatus(InventoryExportEnum.RUNNING.getStatus());
        toAdd.setUserId(userId);
        toAdd.setCreateTime(new Date());
        toAdd.setUpdateTime(new Date());

        StringBuilder sb = new StringBuilder(username);
        sb.append("_").append(exportDateTimeFormatter.format(LocalDateTime.now())).append(".xls");
        String filename = sb.toString();
        toAdd.setFilename(filename);

        Integer count = inventoryExportMapper.insertOne(toAdd);

        if(count > 0){
            ExportParameter parameter = new ExportParameter();
            parameter.setExportId(toAdd.getId());
            parameter.setCityIds(cityIds);
            parameter.setFilename(filename);
            parameter.setClassifyIds(classifyIds);
            ExecuteInventoryExportService.addToExportList(parameter);
            return toAdd.getId();
        }else {
            throw new Exception("生成库存导出记录失败");
        }
    }

    /**
     * 查找库存可用量
     *
     * @param cityId
     * @param cityProductList
     * @return
     */
    public Map<Integer, Integer> availableList(Integer cityId, List<Integer> cityProductList) {
        int storeId = 0;
        List<MarketingInventoryDO> marketingInventoryDOS = marketingInventoryMapper2.list(storeId,cityProductList, DelFlagEnum.NORMAL.getValue());
        return marketingInventoryDOS.stream().collect(Collectors.toMap(n -> n.getCityProductId(), n -> n.getAvailable()));
    }


    public Map<Integer, Boolean> checkSyncTime(Integer cityId, List<Integer> cityProductList) throws Exception{

        List<MarketingInventoryBaseDO> marketingInventoryBaseDOS = marketingInventoryMapper2.listMarketingBaseDO(cityId,cityProductList);
        if (CollectionUtils.isEmpty(marketingInventoryBaseDOS)) {
            log.error("cityId:{},cityProductList,不存在{}", cityId, cityProductList);
            throw new Exception("不存在该商品");
        }
        Map<Integer, Boolean> result = marketingInventoryBaseDOS.stream().collect(Collectors.toMap(n -> n.getCityProductId(), n -> n.getSyncCompleteTime() == null ? false : dateToLocalDate(n.getSyncCompleteTime()).equals(LocalDate.now())));

        cityProductList.forEach(n -> {
            Boolean flag = result.get(n);
            if (flag == null) {
                log.error(" cityProductId:{}是脏数据，对应的库存表没数据", n);
                result.put(n, false);
            }
        });
        return result;
    }

    public LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }
}
