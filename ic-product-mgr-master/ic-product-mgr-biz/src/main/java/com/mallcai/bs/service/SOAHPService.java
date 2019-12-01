package com.mallcai.bs.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dailuobo.api.domain.entity.HqProduct;
import com.dailuobo.api.domain.entity.HqProductTax;
import com.dailuobo.api.domain.soa.SOAHqProduct;
import com.dailuobo.api.domain.soa.SOAStore;
import com.dailuobo.api.domain.soa.city.ProductClassifyRel;
import com.dailuobo.api.domain.util.BeanMapUtils;
import com.dailuobo.api.domain.util.LoggerUtils;
import com.dailuobo.ic.api.base.SingleCityProductQueryRequest;
import com.dailuobo.ic.api.enums.ModifyTypeEnum;
import com.dailuobo.ic.api.product.bean.request.RefreshClassifyProductRequest;
import com.mallcai.backend.common.dao.vo.StorageStore;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mapper.SOAClassifyMapper;
import com.mallcai.bs.mapper.SOAHPMapper;
import com.mallcai.bs.model.StorageHPBanner;
import com.mallcai.bs.model.StorageHPTile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SOAHPService extends BaseService {

    @Autowired
    private SOACityGlobalService SOACityGlobalService;

    @Autowired
    SOAHPMapper SOAHPMapper;

    @Autowired
    SOAClassifyMapper SOAClassifyMapper;

    @Autowired
    CityProductMapper cityProductMapper;


    /**
     * 刷新某个城市的首页banner.
     *
     * @param cityId 1.读取mongo中的首页banner的排序及id信息；
     *               2.根据id列表得到对应的Banner实体对象；
     *               3.将对象改为soaPhbanner对象；
     *               4.将转化过的组成map写入codis;
     */
    public void refreshCityHPBanners(Integer cityId) {
        try {
            LoggerUtils.getLogger().info("刷新城市{}首页banner数据", cityId);
            List<StorageHPBanner> cityHpBannerList = SOAHPMapper.getCityHPBannerList(cityId);
            delCityStoreData(cityId, "HPBanner");
            if (cityHpBannerList == null || cityHpBannerList.size() == 0) {
                return;
            }

            Map<String, List<StorageHPBanner>> map = new HashMap<>();
            for (StorageHPBanner mongoHPBanner : cityHpBannerList) {
                String redisKey = RedisKeyGenerator.generateHPBannerSetKey(cityId, mongoHPBanner.getStoreId());
                if (!map.containsKey(redisKey))
                    map.put(redisKey, new ArrayList<StorageHPBanner>());
                map.get(redisKey).add(mongoHPBanner);
            }

            Iterator<Map.Entry<String, List<StorageHPBanner>>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, List<StorageHPBanner>> entry = iter.next();
			/*	Map<Integer, Integer> sOAHPBannerOrderMap = new HashMap<Integer, Integer>();
				Map<String, Double> writeMembers = new HashMap<String, Double>();
				List<Integer> allBannerIdList = new ArrayList<Integer>();
				for(StorageHPBanner mongoHPBanner : entry.getValue()){
					sOAHPBannerOrderMap.put(mongoHPBanner.getBannerId(), mongoHPBanner.getBannerOrder());
					allBannerIdList.add(mongoHPBanner.getBannerId());
				}

				List<StorageBannerPool> mongoBannerPoolList = getCityHPBannerList(allBannerIdList);

				for(StorageBannerPool mongoBannerPool:mongoBannerPoolList){
					SOAHPBanner soaPhBanner = new SOAHPBanner();
					BeanMapUtils.bean2Bean(mongoBannerPool, soaPhBanner);
					soaPhBanner.setOrder(sOAHPBannerOrderMap.get(mongoBannerPool.getBannerId()));
					writeMembers.put(JSON.json(soaPhBanner), Double.valueOf(soaPhBanner.getOrder()));
				}*/
                JedisProxy.getInstance().delKey(entry.getKey());
                //JedisProxy.getInstance().setSortedSetMultiMember(entry.getKey(), writeMembers);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void delCityStoreData(Integer cityId, String type) {
        try {
            List<SOAStore> soaStores = getCityStore(cityId);
            if (soaStores == null || soaStores.isEmpty()) {
                refreshCityStore(cityId);
                soaStores = getCityStore(cityId);
            }
            if (soaStores != null && !soaStores.isEmpty()) {
                List<String> keys = new ArrayList<>();
                for (SOAStore soaStore : soaStores) {
                    if (soaStore.getStoreId() == null) continue;
                    switch (type) {
                        case "HPBanner":
                            keys.add(RedisKeyGenerator.generateHPBannerSetKey(cityId, soaStore.getStoreId()));
                            break;
                        case "HPTile":
                            keys.add(RedisKeyGenerator.generateHPTileSetKey(cityId, soaStore.getStoreId()));
                            break;
                        case "StoreTheme":
                            keys.add(RedisKeyGenerator.generateStoreThemeKey(cityId, soaStore.getStoreId()));
                            break;
                        case "StoreHeadline":
                            keys.add(RedisKeyGenerator.generateStoreHeadlineKey(cityId, soaStore.getStoreId()));
                            break;
                        case "HPProduct":
                            keys.add(RedisKeyGenerator.generateHPProductKey(cityId, soaStore.getStoreId()));
                        default:
                            break;
                    }
                }

                JedisProxy.getInstance().delMultiKey(keys.toArray(new String[]{}));
            } else {
                logger.warn("delCityStoreData:cityId=" + cityId + ",没有门店");
            }
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
    }

    public List<SOAStore> getCityStore(Integer cityId) throws Exception {
        List<SOAStore> result = new ArrayList<SOAStore>();
        String redisKey = RedisKeyGenerator.generateCityStoreKey(cityId);
        Set<String> mongoStoreStrList = JedisProxy.getInstance().getSortedSetAllMember(redisKey);
        for (String mongoStoreStr : mongoStoreStrList) {
            StorageStore mongoStore = JSON.parseObject(mongoStoreStr, new TypeReference<StorageStore>() {
            });
            SOAStore soaStore = new SOAStore();
            BeanMapUtils.bean2Bean(mongoStore, soaStore);
            result.add(soaStore);
        }
        return result;
    }

    public void refreshCityStore(Integer cityId) throws IOException {
        List<StorageStore> mongoStoreList = SOAHPMapper.getCityStoreList(cityId);
        writeCityStoreList(cityId, mongoStoreList);
    }

    public void writeCityStoreList(Integer cityId, List<StorageStore> mongoStoreList) throws IOException {
        String redisKey = RedisKeyGenerator.generateCityStoreKey(cityId);
        String cityStoreHashKey = RedisKeyGenerator.generateCityStoreHashKey(cityId);
        String cityStoreHashKeyNew = RedisKeyGenerator.generateCityStoreHashKeyNew(cityId);
        Map<String, Double> writeMembers = new HashMap<String, Double>();
        Map<String, String> hashStoreMembers = new HashMap<String, String>();
        for (StorageStore mongoStore : mongoStoreList) {
            String jsonMonogStore = JSON.toJSONString(mongoStore);
            writeMembers.put(jsonMonogStore, Double.valueOf(mongoStore.getStoreId()));
            hashStoreMembers.put(String.valueOf(mongoStore.getStoreId()), jsonMonogStore);
        }
        /** 双写到redis，一份是有序集合，用于排序得到，一份是hash结构，用于给设置提货点时调用*/
        JedisProxy.getInstance().delMultiKey(redisKey, cityStoreHashKey, cityStoreHashKeyNew);
        JedisProxy.getInstance().setSortedSetMultiMember(redisKey, writeMembers);
        JedisProxy.getInstance().setHashMultiField(cityStoreHashKey, hashStoreMembers);
    }

    /**
     * 删除城市首页的tile.
     *
     * @param cityId the city id
     */
    public void refreshCityHPTiles(Integer cityId) {
        try {
            LoggerUtils.getLogger().info("刷新城市{}首页tile数据", cityId);
            List<StorageHPTile> cityHpTileList = SOAHPMapper.getCityHPTileList(cityId);
            delCityStoreData(cityId, "HPTile");
            if (cityHpTileList == null || cityHpTileList.size() == 0) {
                return;
            }

            Map<String, List<StorageHPTile>> map = new HashMap<>();
            for (StorageHPTile mongoHPTile : cityHpTileList) {
                String redisKey = RedisKeyGenerator.generateHPTileSetKey(cityId, mongoHPTile.getStoreId());
                if (!map.containsKey(redisKey))
                    map.put(redisKey, new ArrayList<StorageHPTile>());
                map.get(redisKey).add(mongoHPTile);
            }

            Iterator<Map.Entry<String, List<StorageHPTile>>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, List<StorageHPTile>> entry = iter.next();
				/*	Map<Integer, Integer> sOAHPTileOrderMap = new HashMap<Integer, Integer>();
					Map<String, Double> writeMembers = new HashMap<String, Double>();
					List<Integer> allTileIdList = new ArrayList<Integer>();
					
					for(StorageHPTile mongoHPTile : entry.getValue()){
						sOAHPTileOrderMap.put(mongoHPTile.getTileId(), mongoHPTile.getTileOrder());
						allTileIdList.add(mongoHPTile.getTileId());
						//LoggerFactory.getLogger(RedisHpDAO.class).error("测试信息:" + mongoHPTile.getTileId());
					}
					
					List<StorageTilePool> mongoTilePoolList = getCityHPTileList(allTileIdList);
					//LoggerFactory.getLogger(RedisHpDAO.class).error("测试信息:" + cityHpTileList.toString());
					for(StorageTilePool mongoTilePool:mongoTilePoolList){
						SOAHPTile soaHPTile = new SOAHPTile();
						BeanMapUtils.bean2Bean(mongoTilePool, soaHPTile);
						soaHPTile.setTileOrder(sOAHPTileOrderMap.get(mongoTilePool.getTileId()));
						writeMembers.put(JSON.json(soaHPTile), Double.valueOf(soaHPTile.getTileOrder()));
						//LoggerFactory.getLogger(RedisHpDAO.class).error("测试信息:" + soaHPTile.getTileName());
					}*/

                JedisProxy.getInstance().delKey(entry.getKey());
                //JedisProxy.getInstance().setSortedSetMultiMember(entry.getKey(), writeMembers);
            }

        } catch (Exception e) {
            logger.error("刷新城市磁贴异常," + e.getMessage(), e);
        }
    }

    public void clearAdvertsProductList() {
        Map<String, String> delKeys = JedisProxy.getInstance().getHashAllField(RedisKeyGenerator.generateRefreshAdvertsProductListKey());
        if (delKeys == null || delKeys.isEmpty()) return;
        Iterator<String> iterator = delKeys.keySet().iterator();
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            String delKey = iterator.next();
            list.add(delKey);
            int startPos = delKey.lastIndexOf(":") + 1;
            list.add(RedisKeyGenerator.generateAdvertsKey(Integer.valueOf(delKey.substring(startPos))));
        }

        JedisProxy.getInstance().delMultiKey(list.toArray(new String[]{}));
    }

    // 更新二级分类与商品关系
    public void updateProductClassify(Integer oldSencondClassifyId) {
        List<ProductClassifyRel> productClassifyRels = SOAClassifyMapper.getProductClassifyRel();
        if (productClassifyRels == null || productClassifyRels.isEmpty()) return;
        Map<String, Map<String, Double>> data = new HashMap<>();
        List<String> delKeys = new ArrayList<>();
        for (ProductClassifyRel productClassifyRel : productClassifyRels) {
            String key = productClassifyRel.getCityId() + ":" + productClassifyRel.getClassifyId();
            if (!data.containsKey(key)) {
                data.put(key, new HashMap<String, Double>());
            }
            data.get(key).put(com.alibaba.fastjson.JSON.toJSONString(productClassifyRel), 1d);
            delKeys.add(RedisKeyGenerator.generateClassifyProdListKey(oldSencondClassifyId, productClassifyRel.getCityId()));
        }

        JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));

        Iterator<Map.Entry<String, Map<String, Double>>> iter = data.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Map<String, Double>> entry = iter.next();
            String[] keys = entry.getKey().split(":");
            Integer cityId = Integer.parseInt(keys[0]), classifyId = Integer.parseInt(keys[1]);
            String redisKey = RedisKeyGenerator.generateClassifyProdListKey(classifyId, cityId);
            JedisProxy.getInstance().delKey(redisKey);
            JedisProxy.getInstance().setSortedSetMultiMember(redisKey,
                    entry.getValue());
        }
    }


    public Boolean refreshClassifyProductCache(RefreshClassifyProductRequest request) {
        request.checkParams();
        //删除所有对应的二级类目
        if (request.getModifyTypeEnum().equals(ModifyTypeEnum.HqProduct)) {
            updateProductClassify(request.getBeforeClassifyId(), request.getAfterClassifyId());
        } else {
            //只更新对应的上商品的分类缓存
            updateProductClassifyRef(request.getCityId(), request.getCityProductId());
        }
        return true;
    }


    /**
     * @param beforeSecondClassifyId
     * @param afterClassifyId
     */
    public void updateProductClassify(Integer beforeSecondClassifyId, Integer afterClassifyId) {
        List<ProductClassifyRel> productClassifyRels = SOAClassifyMapper.getProductClassifyRelList(null,beforeSecondClassifyId, afterClassifyId);
        if (productClassifyRels == null || productClassifyRels.isEmpty()) return;
        Map<String, Map<String, Double>> data = new HashMap<>();
        Set<String> delKeys = new HashSet<>();
        for (ProductClassifyRel productClassifyRel : productClassifyRels) {
            String key = productClassifyRel.getCityId() + ":" + productClassifyRel.getClassifyId();
            if (!data.containsKey(key)) {
                data.put(key, new HashMap<String, Double>());
            }
            data.get(key).put(com.alibaba.fastjson.JSON.toJSONString(productClassifyRel), 1d);
            delKeys.add(RedisKeyGenerator.generateClassifyProdListKey(beforeSecondClassifyId, productClassifyRel.getCityId()));
            delKeys.add(RedisKeyGenerator.generateClassifyProdListKey(afterClassifyId, productClassifyRel.getCityId()));
        }

        JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));

        Iterator<Map.Entry<String, Map<String, Double>>> iter = data.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Map<String, Double>> entry = iter.next();
            String[] keys = entry.getKey().split(":");
            Integer cityId = Integer.parseInt(keys[0]), classifyId = Integer.parseInt(keys[1]);
            String redisKey = RedisKeyGenerator.generateClassifyProdListKey(classifyId, cityId);
            JedisProxy.getInstance().delKey(redisKey);
            JedisProxy.getInstance().setSortedSetMultiMember(redisKey,
                    entry.getValue());
        }
    }

    /**
     * 上下架更新分类下的商品
     *
     * @param cityId        城市Id
     * @param cityProductId 城市商品Id
     */
    public void updateProductClassifyRef(Integer cityId, Integer cityProductId) {
        SingleCityProductQueryRequest request = new SingleCityProductQueryRequest();
        request.setCityId(cityId);
        request.setCityProductId(cityProductId);
        //拿到城市商品对应的分类
        ProductClassifyRel productClassifyRel = SOAClassifyMapper.loadCityProductClassifyRel(cityProductId, cityId);
        String redisKey = RedisKeyGenerator.generateClassifyProdListKey(productClassifyRel.getClassifyId(), productClassifyRel.getCityId());
        //更新商品对应分类下的所有商品信息
        List<ProductClassifyRel> productClassifyRels = SOAClassifyMapper.getProductClassifyRelList(cityId,productClassifyRel.getClassifyId(), null);
        Map<String, Map<String, Double>> data = new HashMap<>();
        for (ProductClassifyRel productClassifyRelItem : productClassifyRels) {
            String key = productClassifyRelItem.getCityId() + ":" + productClassifyRelItem.getClassifyId();
            if (!data.containsKey(key)) {
                data.put(key, new HashMap<String, Double>());
            }
            data.get(key).put(JSON.toJSONString(productClassifyRelItem), 1d);
        }

        Iterator<Map.Entry<String, Map<String, Double>>> iter = data.entrySet().iterator();
        JedisProxy.getInstance().delKey(redisKey);
        while (iter.hasNext()) {
            Map.Entry<String, Map<String, Double>> entry = iter.next();
            JedisProxy.getInstance().setSortedSetMultiMember(redisKey,
                    entry.getValue());
        }
    }


    public void shelveProduct(Integer cityId, Integer cityProductId) {
        // 刷新商品列表  刷新门店价格
        SOACityGlobalService.updateProductV2(cityProductId, null);

        //刷新banner，tile，headline
        refreshCityHPBanners(cityId);
        refreshCityHPTiles(cityId);
        SOACityGlobalService.refreshHeadLine(cityId);

        // 刷新banner商品列表
			/*refreshHPBannerProductList(cityId);
			
			// 刷新磁贴商品列表
			refreshHPTitleProductList(cityId);
			
			// 刷新首页商品列表
			refreshHPProductListV2(cityId);
			
			// 刷新主题商品列表
			refreshThemeProductListCity(cityId);
			
			// 刷新头条商品列表
			refreshHeadlineProductListCity(cityId);*/

        // 刷新二级分类与商品关系
        SOAHqProduct hqProduct = SOAClassifyMapper.getClassifyByCityProductId(cityProductId);

        //updateProductClassify(hqProduct.getClassifyId());
        updateProductClassifyRef(cityId, cityProductId);

        // 更新城市未下架商品列表
        /*SOACityGlobalService.refreshProductListCity(cityId);*/

        clearAdvertsProductList();//清空广告的商品列表
    }


    public void refreshTopTitle(Integer cityId) {
        String redisKey = RedisKeyGenerator.generateHomePageTitle(cityId);
        JedisProxy.getInstance().delKey(redisKey);
    }

    public void delProductClassify(Integer cityId, List<Integer> cityProductIds) {
        List<SOAHqProduct> hqProducts = SOAClassifyMapper.getClassifyByCityProductIds(cityProductIds);
        if (hqProducts != null && hqProducts.size() > 0) {
            List<String> delKeys = hqProducts.stream().filter(e -> {
                return e != null;
            })
                    .map(e -> {
                        return RedisKeyGenerator.generateClassifyProdListKey(e.getClassifyId(), cityId);
                    }).collect(Collectors.toList());

            Long ret = JedisProxy.getInstance().delMultiKey(delKeys.toArray(new String[]{}));
            log.info("删除二级分类商品key {}个", delKeys.size());
        }
    }

    public List<HqProductTax> getProductTaxByCode(List<String> codes) {
        return SOAHPMapper.getProductTaxByCode(codes);
    }

    public List<HqProduct> getByProductNos(List<String> productNOs) {
        return SOAHPMapper.getByProductNos(productNOs);
    }

    public List<HqProduct> getByHqProductNames(List<String> hqProductNames) {
        return SOAHPMapper.getByHqProductNames(hqProductNames);
    }

}
