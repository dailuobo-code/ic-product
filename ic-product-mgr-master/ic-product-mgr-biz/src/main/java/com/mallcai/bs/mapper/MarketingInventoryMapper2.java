package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.MarketingInventory2;
import com.dailuobo.api.domain.entity.WhMarketingInventory;
import com.mallcai.bs.model.ExportInventoryLine;
import com.mallcai.bs.model.ivy.IvyMarketingStockPageQueryDO;
import com.mallcai.bs.model.ivy.MarketingInventoryBaseDO;
import com.mallcai.bs.model.ivy.MarketingInventoryDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Cowboy on 2016/4/13.
 */
public interface MarketingInventoryMapper2 {
    //TODO: 分城待确认(方案:sql拆分可解决)
    List<WhMarketingInventory> getWhMarketingInventories(Map<String, Object> param);

    List<MarketingInventory2> getMarketingInventories(@Param("cityId") Integer cityId, @Param("cityProductId") Integer cityProductId);

    Integer checkAndSetStock(@Param("cityProductId") Integer cityProductId, @Param("storeId") Integer storeId, @Param("afterAvailable") Integer afterAvailable, @Param("version") Integer version);

    void updateBaseInventory(@Param("cityProductId") Integer cityProductId, @Param("storeId") Integer storeId, @Param("delta") Integer delta);

    void batchWriteStock(List<MarketingInventoryDO> marketingInventoryDOS);

    List<MarketingInventoryDO> list(@Param("storeId") Integer storeId, @Param("cityProductIds") List<Integer> cityProductIds, @Param("delFlag") Integer delFlag);

    List<MarketingInventoryDO> listAll(IvyMarketingStockPageQueryDO ivyMarketingStockQueryDTO);

    Integer countInventoryByCityIdAndClassifyId(@Param("cityIds") List<Integer> cityIds, @Param("classifyIds") List<Integer> classifyIds);

    List<ExportInventoryLine> selectInventoryByCityIdAndClassifyId(@Param("cityIds") List<Integer> cityIds,
                                                                   @Param("classifyIds") List<Integer> classifyIds, @Param("start") Integer start,
                                                                   @Param("size") Integer size);


    List<MarketingInventoryBaseDO> listMarketingBaseDO(@Param("storeId") Integer storeId, @Param("cityProductIds") List<Integer> cityProductIds);


    Integer countAll(IvyMarketingStockPageQueryDO ivyMarketingStockQueryDTO);

    Integer updateTblMarketingInventoryByPrimaryKey(MarketingInventoryDO marketingInventoryDO);
}
