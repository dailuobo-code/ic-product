package com.dailuobo.biz.service.inventory.ivy;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.ivy.IVYMarketingWhInventoryDTO;
import com.dailuobo.api.inventory.ivy.IVYMarketingWhInventoryService;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.manager.ivy.IVYMarketingInventoryManager;
import com.mallcai.bs.manager.ivy.IVYStoreSkuInventoryManager;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mapper.ivy.IvyStoreSkuInventoryMapper;
import com.mallcai.bs.model.ivy.IvyMarketingInventoryDO;
import com.mallcai.service.inventory.marketing.api.IStockReadService;
import com.mallcai.service.inventory.marketing.api.IStockWriteService;
import com.mallcai.service.inventory.marketing.enums.InventoryAdjustEnum;
import com.mallcai.service.inventory.marketing.request.AdjustInventoryRequest;
import com.mallcai.service.inventory.marketing.request.DisCalculateInventoryRequest;
import com.mallcai.service.inventory.marketing.request.IvyMarketingInventoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianjunwei
 */

@Slf4j
@Service("iVYMarketingWhInventoryService")
public class IVYMarketingWhInventoryServiceImpl implements IVYMarketingWhInventoryService {

    @Resource
    private IStockReadService iStockReadService;

    @Resource
    private IStockWriteService iStockWriteService;

    @Resource
    private IVYMarketingInventoryManager ivyMarketingInventoryManager;

    @Autowired
    private IVYStoreSkuInventoryManager ivyStoreSkuInventoryManager;

    @Autowired
    private CityProductMapper cityProductMapper;

    @Resource
    private IvyStoreSkuInventoryMapper ivyStoreSkuInventoryMapper;

    /**
     * 根据城市id 和商品id 查找商品所在仓 库存信息
     *
     * @param cityId
     * @param cityProductId;
     * @return
     */
    @Override
    public ICResponse<List<IVYMarketingWhInventoryDTO>> getIVYWarehouseInventory(Integer cityId, Integer cityProductId) {

        try {
            log.info("getIVYWarehouseInventory 请求参数:{}",JSON.toJSONString(cityId+","+cityProductId));
            if(cityId == null || cityProductId == null){
                return ICResponse.fail("cityId 或 cityProductId 为空");
            }
            Page<IvyMarketingInventoryDO> page = ivyMarketingInventoryManager.getIVYWarehouseInventory(cityId,cityProductId);
            if(page == null){
                return ICResponse.success(new ArrayList<>());
            }else {
                List<IVYMarketingWhInventoryDTO> ivyMarketingWhInventoryDTOS = OrikaUtil.convertList(page.getResult(), IVYMarketingWhInventoryDTO.class);
                ivyMarketingWhInventoryDTOS.forEach(ivyMarketingWhInventoryDTO -> {
                    ivyMarketingWhInventoryDTO.setRemainderAvailable(0);
                });
                return ICResponse.success(ivyMarketingWhInventoryDTOS);
            }
        }catch (Exception e){
            log.error("getIVYWarehouseInventory error:{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 修改销售总库存
     *
     * @param ivyWarehouseInventory
     * @return
     */
    @Override
    public ICResponse<Boolean> updateIVYWarehouseInventory(IVYMarketingWhInventoryDTO ivyWarehouseInventory) {
        try {
            log.info("updateIVYWarehouseInventory 请求参数:{}",JSON.toJSONString(ivyWarehouseInventory));
            Integer marketingNum = ivyWarehouseInventory.getMarketingNum();

            if(marketingNum == null){
                return ICResponse.fail("参数为空");
            }
            IvyMarketingInventoryDO oldIvyMarketingInventoryDO = ivyMarketingInventoryManager.getSingleIVYMarketingInventory(ivyWarehouseInventory.getId());

            //需要调的营销总库存差值
            Integer updateNum = marketingNum - oldIvyMarketingInventoryDO.getMarketingNum();

            //标品要去库存中心查询
            Response<Integer> response = iStockReadService.queryWarehouseRemainderNum(ivyWarehouseInventory.getCityId(),ivyWarehouseInventory.getCityProductId(),ivyWarehouseInventory.getWarehouseId());
            if(!response.isSuccess()){
                return ICResponse.fail(response.getMessage());
            }
            // 实物总库存无法再分配
            if(response.getData() < updateNum){
                return ICResponse.fail("无法重新设置营销总库存");
            }

            DisCalculateInventoryRequest disCalculateInventoryRequest = new DisCalculateInventoryRequest();
            IvyMarketingInventoryDTO ivyMarketingInventoryDTO = new IvyMarketingInventoryDTO();
            ivyMarketingInventoryDTO.setCityProductId(ivyWarehouseInventory.getCityProductId());
            ivyMarketingInventoryDTO.setCityId(ivyWarehouseInventory.getCityId());
            ivyMarketingInventoryDTO.setWarehouseId(ivyWarehouseInventory.getWarehouseId());
            disCalculateInventoryRequest.setIvyMarketingInventoryDTO(ivyMarketingInventoryDTO);

            Response result = iStockWriteService.disAbleCalculate(disCalculateInventoryRequest);
            if(!result.isSuccess()){
                return ICResponse.fail(response.getMessage());
            }

            //修改营销总库存
            IvyMarketingInventoryDO ivyMarketingInventoryDO = OrikaUtil.convert(ivyWarehouseInventory, IvyMarketingInventoryDO.class);
            ivyMarketingInventoryManager.updateIVYWarehouseInventory(ivyMarketingInventoryDO);
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("updateIVYWarehouseInventory error:{}",e.toString());
            return ICResponse.fail(e.toString());
        }

    }

    /**
     * 修改可售库存
     *
     * @param ivyWarehouseInventory
     * @return
     */
    @Override
    public ICResponse<Boolean> updateIVYWarehouseInventoryAvailableNum(IVYMarketingWhInventoryDTO ivyWarehouseInventory) {

        try {
            log.info("updateIVYWarehouseInventoryAvailableNum mgr请求参数:{}", JSON.toJSONString(ivyWarehouseInventory));
            if(ivyWarehouseInventory.getAvailableNum() == null){
                return ICResponse.fail("请重新设置可售库存");
            }
            IvyMarketingInventoryDO oldIvyMarketingInventoryDO = ivyMarketingInventoryManager.getSingleIVYMarketingInventory(ivyWarehouseInventory.getId());
            if(oldIvyMarketingInventoryDO.getMarketingNum() < ivyWarehouseInventory.getAvailableNum()){
                return ICResponse.fail("请重新设置可售库存");
            }
            AdjustInventoryRequest adjustInventoryRequest = new AdjustInventoryRequest();
            adjustInventoryRequest.setInventoryAdjustEnum(InventoryAdjustEnum.MGR);
            IvyMarketingInventoryDTO ivyMarketingInventoryDTO = new IvyMarketingInventoryDTO();

            List<CityProduct> cityProductList = cityProductMapper.listCityProductIdsByCityId(null,null,null,Lists.newArrayList(oldIvyMarketingInventoryDO.getCityProductId()));
            if(CollectionUtils.isEmpty(cityProductList)){
                return ICResponse.fail("商品不存在");
            }
            ivyMarketingInventoryDTO.setCityId(cityProductList.get(0).getCityId());
            ivyMarketingInventoryDTO.setCityProductId(oldIvyMarketingInventoryDO.getCityProductId());
            ivyMarketingInventoryDTO.setWarehouseId(ivyWarehouseInventory.getWarehouseId());
            ivyMarketingInventoryDTO.setActivityNum(0);
            ivyMarketingInventoryDTO.setAlarmNum(0);
            ivyMarketingInventoryDTO.setLockNum(0);
            ivyMarketingInventoryDTO.setTotalWeight(new Double(0));
            ivyMarketingInventoryDTO.setAvailableNum(ivyWarehouseInventory.getAvailableNum());
            ivyMarketingInventoryDTO.setMarketingNum(oldIvyMarketingInventoryDO.getMarketingNum());
            adjustInventoryRequest.setIvyMarketingInventoryDTOList(Lists.newArrayList(ivyMarketingInventoryDTO));
            log.error("请求参数:{}", JSON.toJSONString(adjustInventoryRequest));
            Response response = iStockWriteService.batchAdjustInventory(adjustInventoryRequest);
            if(response.isSuccess()){
                return ICResponse.success(Boolean.TRUE);
            }else {
                return ICResponse.fail(response.getMessage());
            }
            /*IvyMarketingInventoryDO ivyMarketingInventoryDO = OrikaUtil.convert(ivyWarehouseInventory, IvyMarketingInventoryDO.class);
            Integer result = ivyMarketingInventoryManager.updateIVYWarehouseInventoryAvailable(ivyMarketingInventoryDO);
            if(result == 1) {
                WriteStockRequest writeStockRequest = new WriteStockRequest();
                writeStockRequest.setAvailable(ivyWarehouseInventory.getAvailableNum());
                writeStockRequest.setCityProductId(ivyWarehouseInventory.getCityProductId());
                writeStockRequest.setWarehouseId(ivyWarehouseInventory.getWarehouseId());
                writeStockRequest.setStoreId(0);
                //调用库存接口清理缓存
                iStockWriteService.batchClearCache(Lists.newArrayList(writeStockRequest));
                return ICResponse.success(Boolean.TRUE);
            }else {
                return ICResponse.fail("请重新设置可售库存");
            }*/
        }catch (Exception e){
            log.error("updateIVYWarehouseInventoryAvailableNum error:{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 修改告警库存
     *
     * @param ivyWarehouseInventory
     * @return
     */
    @Override
    public ICResponse<Boolean> updateIVYWarehouseInventoryAlarmNum(IVYMarketingWhInventoryDTO ivyWarehouseInventory) {
        try {
            log.info("updateIVYWarehouseInventoryAlarmNum:{}",JSON.toJSONString(ivyWarehouseInventory));
            if(ivyWarehouseInventory.getAlarmNum() == null){
                return ICResponse.fail("请重新设置告警库存");
            }
            if(ivyWarehouseInventory.getId() == null){
                return ICResponse.fail("ID 为空");
            }
            IvyMarketingInventoryDO ivyMarketingInventoryDO = OrikaUtil.convert(ivyWarehouseInventory, IvyMarketingInventoryDO.class);
            Integer result = ivyMarketingInventoryManager.updateIVYWarehouseInventoryAlarm(ivyMarketingInventoryDO);
            if(result == 1) {
                return ICResponse.success(Boolean.TRUE);
            }else {
                return ICResponse.fail("请重新设置告警库存");
            }
        }catch (Exception e){
            log.error("updateIVYWarehouseInventoryAlarmNum error:{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }
}
