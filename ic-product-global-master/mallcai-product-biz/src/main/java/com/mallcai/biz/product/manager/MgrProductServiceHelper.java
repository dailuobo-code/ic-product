package com.mallcai.biz.product.manager;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.enums.HqProductExtraAttrAction;
import com.dailuobo.ic.api.request.product.HqProductSyncExtraAttrRequest;
import com.dailuobo.itemcenter.api.service.product.ItemCenterRouterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.api.category.CategoryService;
import com.mallcai.backend.common.cache.mongo.utils.BeanMapUtils;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.biz.product.dao.mapper.*;
import com.mallcai.biz.product.merge.mapper.FrozenProductMapper;
import com.mallcai.biz.product.model.HqProductCheckDO;
import com.mallcai.biz.product.model.HqProductTax;
import com.mallcai.common.ICResponse;
import com.mallcai.common.PageDTO;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.dataobject.product.*;
import com.mallcai.domain.dto.IcHqProductExtraAttrExample;
import com.mallcai.domain.dto.ProductCategoryRel;
import com.mallcai.domain.dto.TblHqProductExample;
import com.mallcai.domain.dto.TblProductClassifyExample;
import com.mallcai.domain.enums.AuditStatus;
import com.mallcai.domain.enums.HqProductDeliveryModeEnum;
import com.mallcai.domain.enums.HqProductExtraAttrTypeEnum;
import com.mallcai.domain.product.dto.HqProductExtraAttrDTO;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.product.dto.ProductDeliveryHomeAttrDTO;
import com.mallcai.domain.product.dto.attr.HqProductDeliveryHomeAttr;
import com.mallcai.domain.product.request.*;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.mq.product.dto.ProductInfoMsgDTO;
import com.mallcai.mq.product.enums.ProductInfoMsgEnum;
import com.mallcai.mq.product.producer.ProductInfoProducer;
import com.mallcai.router.client.RouterReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MgrProductServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(MgrProductServiceHelper.class);

    @Resource
    private TblHqProductMapper tblHqProductMapper;

    @Resource
    private TblProductClassifyMapper tblProductClassifyMapper;

    @Resource
    private TblCityProductCustomMapper tblCityProductCustomMapper;

    @Resource
    private TblHqProductCustomMapper tblHqProductCustomMapper;

    @Resource
    private TblProductTaxMapper tblProductTaxMapper;

    @Resource
    private CategoryService categoryService;

    @Autowired
    private ProductInfoProducer productInfoProducer;

    @Resource
    private FrozenProductMapper partnerProductMapper;

    @Resource
    private IcHqProductExtraAttrMapper icHqProductExtraAttrMapper;

    @RouterReference
    private ItemCenterRouterService itemCenterRouterService;

    @ConfigValue(key = "/app-ic/app-product/gatewayRouterKey")
    private String gatewayRouterKey;

    public boolean addProduct(AddProductRequest addProductRequest) {
        String barcode = addProductRequest.getBarCode();
        // 商品条码不空,判断是否已经存在
        if (StringUtils.isNotBlank(barcode)) {
            TblHqProductExample example = new TblHqProductExample();
            example.createCriteria().andBarCodeEqualTo(barcode);
            List<TblHqProduct> existHqProducts = tblHqProductMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(existHqProducts)) {
                Optional<String> productNoOptional = existHqProducts.stream().map(TblHqProduct::getProductNo).findAny();
                if (productNoOptional.isPresent()) {
                    throw new RuntimeException(String.format("当前条形码在%s中已存在，无法继续添加", productNoOptional.get()));
                }
            }
        }
        TblHqProduct product = buildTblHqProduct(addProductRequest);

        if (1 == tblHqProductMapper.insertSelective(product)) {
            // 配送方式为 送货上门 需要保存发货时间属性
            if (Objects.equals(HqProductDeliveryModeEnum.DELIVERY_HOME.getCode(), addProductRequest.getDeliveryMode())) {
                IcHqProductExtraAttr productExtraAttr = buildIcHqProductExtraAttr(product, addProductRequest.getArrivalDay());
                icHqProductExtraAttrMapper.insertSelective(productExtraAttr);

            }
            addProductRequest.setHqProductId(product.getHqProductId());
            return true;
        }

        return false;
    }

    /**
     * 构建tblHqProduct
     *
     * @param addProductRequest
     * @return
     */
    protected static TblHqProduct buildTblHqProduct(AddProductRequest addProductRequest) {
        TblHqProduct hqProduct = new TblHqProduct();

        hqProduct.setProductNo(addProductRequest.getProductNo());
        hqProduct.setHqProductName(addProductRequest.getProductName());
        hqProduct.setClassifyId(addProductRequest.getCategoryId());
        hqProduct.setRemark(addProductRequest.getRemark());
        hqProduct.setHqProductIcon(addProductRequest.getProductIcon());
        List<String> productPicList = addProductRequest.getProductPics();
        String hqProductPic = StringUtils.EMPTY;
        if (CollectionUtils.isNotEmpty(productPicList)) {
            hqProductPic = StringUtils.join(productPicList, "^");
        }
        hqProduct.setHqProductPic(hqProductPic);
        hqProduct.setDeliveryMode(addProductRequest.getDeliveryMode() != null ? addProductRequest.getDeliveryMode().byteValue() : null);
        hqProduct.setBarCode(addProductRequest.getBarCode());
        hqProduct.setKeepType(addProductRequest.getKeepType());
        hqProduct.setQualityTime(addProductRequest.getQualityTime());
        hqProduct.setIsStandard(addProductRequest.getIsStandard());
        hqProduct.setPickClassify(addProductRequest.getPickClassify());
        hqProduct.setProductRate(addProductRequest.getProductRate());
        hqProduct.setHigh(addProductRequest.getHigh());
        hqProduct.setWide(addProductRequest.getWidth());
        hqProduct.setLength(addProductRequest.getLength());
        hqProduct.setGoodsType(addProductRequest.getProductType() != null ? addProductRequest.getProductType().byteValue() : null);
        hqProduct.setCreateUserId(addProductRequest.getUserId());
        hqProduct.setProductTaxId(addProductRequest.getProductTaxId());
        if (addProductRequest.getAuditStatus() == null) {
            hqProduct.setAuditStatus(AuditStatus.NORMAL.name());
        } else {
            hqProduct.setAuditStatus(addProductRequest.getAuditStatus().name());
        }


        // add 是否采销预测
        hqProduct.setIsForecast(addProductRequest.getIsForecast().byteValue());

        // 上新类型 && 售卖季节
        if (null != addProductRequest.getNewArrivalType()) {
            hqProduct.setNewArrivalType(String.valueOf(addProductRequest.getNewArrivalType()));
        }
        if (null != addProductRequest.getSeasonal()) {
            hqProduct.setSeasonal(String.valueOf(addProductRequest.getSeasonal()));
        }

        if (addProductRequest.getSkuId() != null) {
            hqProduct.setSkuId(addProductRequest.getSkuId());
        }
        if (addProductRequest.getItemId() != null) {
            hqProduct.setItemId(addProductRequest.getItemId());
        }
        return hqProduct;
    }

    private IcHqProductExtraAttr buildIcHqProductExtraAttr(TblHqProduct product, Integer deliverVal) {
        IcHqProductExtraAttr productExtraAttr = new IcHqProductExtraAttr();
        productExtraAttr.setHqProductId(product.getHqProductId());
        productExtraAttr.setAttrType(HqProductExtraAttrTypeEnum.DELIVERY_HOME.name());
        productExtraAttr.setAttrName("arrivalDay");
        productExtraAttr.setAttrValue(String.valueOf(deliverVal));

        if (product.getCreateUserId() != null) {
            productExtraAttr.setCreateUser(product.getCreateUserId());
            productExtraAttr.setCreateTime(new Date());
        }

        if (product.getUpdateUserId() != null) {
            productExtraAttr.setUpdateUserId(product.getUpdateUserId());
            productExtraAttr.setUpdateTime(new Date());
        }
        return productExtraAttr;
    }


    /**
     * 增量更新(城市+二级类目)与城市商品的映射关系
     *
     * @param productId     总部商品ID
     * @param newCategoryId 新二级类目ID
     * @param oldCategoryId 旧二级类目ID
     */
    public void incrUpdateProductClassify(Integer productId, Integer newCategoryId, Integer oldCategoryId) throws CloneNotSupportedException {
        //相同的类目无需刷新
        if (newCategoryId.intValue() == oldCategoryId) {
            return;
        }
        //获取总部商品ID对应的城市商品
        List<TblCityProduct> tblCityProductList = tblCityProductCustomMapper.selectCityProductByHqProduct(productId);
        //无城市商品则return
        if (CollectionUtils.isEmpty(tblCityProductList)) {
            return;
        }
        TblProductClassify newCategoryDO = tblProductClassifyMapper.selectByPrimaryKey(newCategoryId);
        //类目不存在不刷缓存
        if (newCategoryDO == null || newCategoryDO.getLevel() != 2) {
            return;
        }
        TblProductClassify oldCategoryDO = tblProductClassifyMapper.selectByPrimaryKey(oldCategoryId);

        //按城市和类目分组
        Map<String, Map<String, Double>> incrData = new HashMap<>();
        Map<String, Map<String, Double>> delData = new HashMap<>();
        for (TblCityProduct cityProduct : tblCityProductList) {
            String incrKey = cityProduct.getCityId() + ":" + newCategoryId;
            if (!incrData.containsKey(incrKey)) {
                incrData.put(incrKey, new HashMap<String, Double>());
            }
            String delKey = cityProduct.getCityId() + ":" + oldCategoryId;
            if (!delData.containsKey(delKey)) {
                delData.put(delKey, new HashMap<String, Double>());
            }
            //要缓存的对象
            ProductCategoryRel productCategoryRel = new ProductCategoryRel();
            productCategoryRel.setClassifyId(newCategoryId);
            productCategoryRel.setClassifyNo(newCategoryDO.getClassifyNo());
            productCategoryRel.setCityId(cityProduct.getCityId());
            productCategoryRel.setCityProductId(cityProduct.getCityProductId());

            incrData.get(incrKey).put(JSON.toJSONString(productCategoryRel), 1d);

            //要删除缓存的商品
            if (oldCategoryDO != null || oldCategoryDO.getLevel() != 2) {
                ProductCategoryRel delProductCategoryRel = (ProductCategoryRel) productCategoryRel.clone();
                delProductCategoryRel.setClassifyNo(oldCategoryDO.getClassifyNo());
                delProductCategoryRel.setClassifyId(oldCategoryDO.getClassifyId());
                delData.get(delKey).put(JSON.toJSONString(delProductCategoryRel), 1d);

            }
        }
        if (MapUtils.isNotEmpty(incrData)) {
            Iterator<Map.Entry<String, Map<String, Double>>> iter = incrData.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Map<String, Double>> entry = iter.next();
                String[] keys = entry.getKey().split(":");
                Integer cityId = Integer.parseInt(keys[0]), classifyId = Integer.parseInt(keys[1]);
                String redisKey = RedisKeyGenerator.generateClassifyProdListKey(classifyId, cityId);
                JedisProxy.getInstance().setSortedSetMultiMember(redisKey,
                        entry.getValue());
            }
        }
        //删除旧类目对应的商品缓存
        if (MapUtils.isNotEmpty(delData)) {
            Iterator<Map.Entry<String, Map<String, Double>>> iter = delData.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Map<String, Double>> entry = iter.next();
                String[] keys = entry.getKey().split(":");
                Integer cityId = Integer.parseInt(keys[0]), classifyId = Integer.parseInt(keys[1]);
                String redisKey = RedisKeyGenerator.generateClassifyProdListKey(classifyId, cityId);
                JedisProxy.getInstance().delSortedSetMember(redisKey,
                        entry.getValue().keySet().toArray(new String[]{}));
            }
        }

    }

    /**
     * 全量更新(城市+二级类目)与城市商品的映射关系
     *
     * @param oldSencondClassifyId 旧二级类目ID
     */
    public void updateProductClassify(Integer oldSencondClassifyId) throws CloneNotSupportedException {
        List<ProductCategoryRelDO> productClassifyRels = tblCityProductCustomMapper.selectAllCityProductOfLevel2Category();
        if (productClassifyRels == null || productClassifyRels.isEmpty()) return;
        Map<String, Map<String, Double>> data = new HashMap<>();
        List<String> delKeys = new ArrayList<>();
        for (ProductCategoryRelDO productClassifyRel : productClassifyRels) {
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

    public List<TblHqProduct> getProductList(GetProductListRequest getProductListRequest) {
        Integer rootCategoryId = getProductListRequest.getParentCategoryId();
        List<Integer> catIds = null;
        if (rootCategoryId != null) {
            TblProductClassifyExample catQuery = new TblProductClassifyExample();
            TblProductClassifyExample.Criteria catCriteria = catQuery.createCriteria();
            catCriteria.andFatherIdEqualTo(rootCategoryId).andDelFlagEqualTo((byte) 1).andLevelEqualTo((byte) 2);
            List<TblProductClassify> catList = tblProductClassifyMapper.selectByExample(catQuery);
            if (CollectionUtils.isNotEmpty(catList)) {
                catIds = catList.stream().map(TblProductClassify::getClassifyId).collect(Collectors.toList());
            }
        }
        TblHqProductExample query = new TblHqProductExample();
        TblHqProductExample.Criteria criteria = query.createCriteria();
        criteria.andDelFlagEqualTo((byte) 1);
        if (getProductListRequest.getCategoryId() != null) {
            if (CollectionUtils.isEmpty(catIds)) {
                catIds = Lists.newArrayList();
            }
            catIds.add(getProductListRequest.getCategoryId());
        }
        if (CollectionUtils.isNotEmpty(catIds)) {
            criteria.andClassifyIdIn(catIds);
        }
        if (StringUtils.isNotBlank(getProductListRequest.getProductNo())) {
            criteria.andProductNoLike("%" + getProductListRequest.getProductNo() + "%");
        }
        if (StringUtils.isNotBlank(getProductListRequest.getProductName())) {
            criteria.andHqProductNameLike("%" + getProductListRequest.getProductName() + "%");
        }
        if (CollectionUtils.isNotEmpty(getProductListRequest.getStatus())) {
            criteria.andStatusIn(getProductListRequest.getStatus());
        }
        if (getProductListRequest.getIsStandard() != null) {
            criteria.andIsStandardEqualTo(getProductListRequest.getIsStandard());
        }
        if (getProductListRequest.getPickClassify() != null) {
            criteria.andPickClassifyEqualTo(getProductListRequest.getPickClassify());
        }
        if (getProductListRequest.getProductType() != null) {
            criteria.andGoodsTypeEqualTo(getProductListRequest.getProductType().byteValue());
        }
        if (getProductListRequest.getDeliveryMode() != null) {
            criteria.andDeliveryModeEqualTo(getProductListRequest.getDeliveryMode().byteValue());
        }
        if (getProductListRequest.getAuditStatus() != null) {
            criteria.andAuditStatusEqualTo(getProductListRequest.getAuditStatus().name());
        }
        if (getProductListRequest.getNewArrivalType() != null) {
            criteria.andNewArrivalTypeEqualTo(String.valueOf(getProductListRequest.getNewArrivalType()));
        }
        if (getProductListRequest.getSeasonal() != null) {
            criteria.andSeasonalEqualTo(String.valueOf(getProductListRequest.getSeasonal()));
        }
        query.setOrderByClause("product_no");
        PageHelper.startPage(getProductListRequest.getCurrentPage(), getProductListRequest.getPageSize());
        return tblHqProductMapper.selectByExample(query);
    }

    public ProductDTO buildProductDTO(TblHqProduct p) {
        if (p == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setProductId(p.getHqProductId());
        dto.setProductNo(p.getProductNo());
        dto.setProductName(p.getHqProductName());
        dto.setRemark(p.getRemark());
        dto.setStatus((int) p.getStatus());
        String pic = p.getHqProductPic();
        if (StringUtils.isNotBlank(pic)) {
            String[] picArr = StringUtils.split(pic, "^");
            dto.setProductPics(Arrays.asList(picArr));
        }
        dto.setProductIcon(p.getHqProductIcon());
        dto.setCategoryId(p.getClassifyId());
        dto.setLocalizeFlag((int) p.getLocalizeFlag());
        dto.setDeliveryMode((int) p.getDeliveryMode());
        dto.setBarCode(p.getBarCode());
        dto.setKeepType(p.getKeepType());
        dto.setQualityTime(p.getQualityTime());
        dto.setIsStandard(p.getIsStandard());
        dto.setPickClassify(p.getPickClassify());
        dto.setProductRate(p.getProductRate());
        dto.setLength(p.getLength());
        dto.setWidth(p.getWide());
        dto.setHigh(p.getHigh());
        dto.setProductType((int) p.getGoodsType());
        dto.setDelFlag((int) p.getDelFlag());
        dto.setProductTaxId(p.getProductTaxId());
        dto.setAuditStatus(AuditStatus.fromName(p.getAuditStatus(), AuditStatus.NORMAL));

        // 设置是否采销预测
        dto.setIsForecast((int) p.getIsForecast());
        dto.setSeasonal(Integer.valueOf(p.getSeasonal()));
        dto.setNewArrivalType(Integer.valueOf(p.getNewArrivalType()));
        dto.setSkuId(p.getSkuId());
        dto.setItemId(p.getItemId());
        return dto;
    }

    public boolean getPointProduct(Integer productId) {
        return tblCityProductCustomMapper.selectPointProductCnt(productId) > 0;
    }

    public void updateProduct(ModifyProductRequest modifyProductRequest) {

        TblHqProduct oldHqProduct = tblHqProductMapper.selectByPrimaryKey(modifyProductRequest.getProductId());

        TblHqProduct hqProduct = new TblHqProduct();
        hqProduct.setClassifyId(modifyProductRequest.getCategoryId());
        hqProduct.setRemark(modifyProductRequest.getRemark());
        hqProduct.setBarCode(modifyProductRequest.getBarCode());
        hqProduct.setHqProductPic(StringUtils.join(modifyProductRequest.getProductPics(), "^"));
        hqProduct.setUpdateUserId(modifyProductRequest.getUserId());
        hqProduct.setUpdateTime(new Date());
        hqProduct.setHqProductIcon(modifyProductRequest.getProductIcon());
        hqProduct.setKeepType(modifyProductRequest.getKeepType());
        hqProduct.setQualityTime(modifyProductRequest.getQualityTime());
        hqProduct.setIsStandard(modifyProductRequest.getIsStandard());
        hqProduct.setPickClassify(modifyProductRequest.getPickClassify());
        hqProduct.setProductRate(modifyProductRequest.getProductRate());
        if (hqProduct.getIsStandard() != null && hqProduct.getIsStandard() == 1) {
            hqProduct.setLength(modifyProductRequest.getLength());
            hqProduct.setWide(modifyProductRequest.getWidth());
            hqProduct.setHigh(modifyProductRequest.getHigh());
        }
        hqProduct.setProductTaxId(modifyProductRequest.getProductTaxId());

        if (modifyProductRequest.getIsForecast() != null) {
            hqProduct.setIsForecast(modifyProductRequest.getIsForecast().byteValue());
        }

        if (null != modifyProductRequest.getSeasonal()) {
            hqProduct.setSeasonal(String.valueOf(modifyProductRequest.getSeasonal()));
        }
        if (null != modifyProductRequest.getNewArrivalType()) {
            hqProduct.setNewArrivalType(String.valueOf(modifyProductRequest.getNewArrivalType()));
        }

        if (modifyProductRequest.getDeliveryMode() != null) {
            hqProduct.setDeliveryMode(modifyProductRequest.getDeliveryMode().byteValue());
        }

        if (modifyProductRequest.getGoodsType() != null) {
            hqProduct.setGoodsType(modifyProductRequest.getGoodsType().byteValue());
        }

        TblHqProductExample example = new TblHqProductExample();
        TblHqProductExample.Criteria criteria = example.createCriteria();
        criteria.andHqProductIdEqualTo(modifyProductRequest.getProductId());
        tblHqProductMapper.updateByExampleSelective(hqProduct, example);

        // 配送方式为 送货上门 需要保存发货时间属性
        hqProduct.setHqProductId(modifyProductRequest.getProductId());
        modifyProductExtraAttr(hqProduct, Integer.valueOf(oldHqProduct.getDeliveryMode()), modifyProductRequest.getArrivalDay(), modifyProductRequest.getUserId());

        // 发送MQ
        log.info("tag_modify_hq_product_bae总部商品基础信息同步至各个城市商品的基础信息START");
        String l1l2Names = null;
        if (modifyProductRequest.getC1Name() != null && modifyProductRequest.getC2Name() != null) {
            l1l2Names = modifyProductRequest.getC1Name() + "_" + modifyProductRequest.getC2Name();
        }
        ProductInfoMsgDTO productInfoMsgDTO = new ProductInfoMsgDTO(modifyProductRequest.getProductId(), l1l2Names, modifyProductRequest.getC2Old(), ProductInfoMsgEnum.edit, "-1");
        productInfoMsgDTO.setBarCode(hqProduct.getBarCode());
        productInfoMsgDTO.setKeepType(hqProduct.getKeepType());
        productInfoMsgDTO.setQualityTime(hqProduct.getQualityTime());
        productInfoProducer.sendProductInfo(productInfoMsgDTO);
        log.info("tag_modify_hq_product_base总部商品基础信息同步至各个城市商品的基础信息END");
    }

    private void modifyProductExtraAttr(TblHqProduct hqProduct, Integer oldDeliverMode, Integer arrivalDay, Integer userId) {
        if (hqProduct.getDeliveryMode() == null) return;

        HqProductExtraAttrAction attrAction = null;
        if (Objects.equals(HqProductDeliveryModeEnum.STORE_PICK.getCode(), oldDeliverMode)) {
            // 新增
            if (Objects.equals(HqProductDeliveryModeEnum.DELIVERY_HOME.getCode(), Integer.valueOf(hqProduct.getDeliveryMode()))) {
                hqProduct.setCreateUserId(userId);
                IcHqProductExtraAttr productExtraAttr = buildIcHqProductExtraAttr(hqProduct, arrivalDay);
                icHqProductExtraAttrMapper.insertSelective(productExtraAttr);
                attrAction = HqProductExtraAttrAction.ADD;
            }
        } else {
            if (Objects.equals(HqProductDeliveryModeEnum.DELIVERY_HOME.getCode(), Integer.valueOf(hqProduct.getDeliveryMode()))) {
                // 修改 并和旧值比较
                IcHqProductExtraAttrExample extraAttrExample = new IcHqProductExtraAttrExample();
                IcHqProductExtraAttrExample.Criteria criteria1 = extraAttrExample.createCriteria();
                criteria1.andHqProductIdEqualTo(hqProduct.getHqProductId());
                criteria1.andAttrTypeEqualTo(HqProductExtraAttrTypeEnum.DELIVERY_HOME.name());
                List<IcHqProductExtraAttr> oldExtralAttr = icHqProductExtraAttrMapper.selectByExample(extraAttrExample);
                if (CollectionUtils.isEmpty(oldExtralAttr)) {
                    hqProduct.setUpdateUserId(userId);
                    hqProduct.setCreateUserId(userId);
                    IcHqProductExtraAttr productExtraAttr = buildIcHqProductExtraAttr(hqProduct, arrivalDay);
                    icHqProductExtraAttrMapper.insertSelective(productExtraAttr);
                    attrAction = HqProductExtraAttrAction.MODIFY;
                } else if (!Objects.equals(arrivalDay + "", oldExtralAttr.get(0).getAttrValue())) {
                    hqProduct.setUpdateUserId(userId);
                    IcHqProductExtraAttr productExtraAttr = buildIcHqProductExtraAttr(hqProduct, arrivalDay);
                    icHqProductExtraAttrMapper.updateByHqProductId(productExtraAttr);
                    attrAction = HqProductExtraAttrAction.MODIFY;
                }
            } else {
                // 删除
                icHqProductExtraAttrMapper.deleteByHqProductId(hqProduct.getHqProductId(), HqProductExtraAttrTypeEnum.DELIVERY_HOME);
                attrAction = HqProductExtraAttrAction.DELETE;
            }
        }

        try {
            if (attrAction != null) {
                Map<Integer, HqProductSyncExtraAttrRequest> syncExtraAttrRequestMap = new HashMap<>();

                String routerKey = Optional.ofNullable(gatewayRouterKey).orElse("0,27,30,32");
                String[] split = routerKey.split(",");
                HqProductSyncExtraAttrRequest syncExtraAttrRequest = null;
                HqProductSyncExtraAttrRequest.HqProductDeliverHomeAttrDTO deliverHomeAttrDTO = null;
                HqProductSyncExtraAttrRequest.HqProductDTO hqProductDTO = null;
                for (String key : split) {
                    deliverHomeAttrDTO = HqProductSyncExtraAttrRequest.HqProductDeliverHomeAttrDTO.builder().arrivalDay(arrivalDay).build();
                    hqProductDTO = HqProductSyncExtraAttrRequest.HqProductDTO.builder().hqProductId(hqProduct.getHqProductId()).attrDTO(deliverHomeAttrDTO).build();

                    syncExtraAttrRequest = HqProductSyncExtraAttrRequest.builder().attrAction(attrAction).hqProduct(hqProductDTO).userId(userId).build();
                    syncExtraAttrRequestMap.put(Integer.valueOf(key), syncExtraAttrRequest);
                }

                log.info("同步分城商品属性req>>{}", JSON.toJSONString(syncExtraAttrRequestMap));
                Map<Integer, com.dailuobo.api.common.ICResponse<Boolean>> integerICResponseMap = itemCenterRouterService.syncHqProductExtraAttr(syncExtraAttrRequestMap);
                log.info("同步分城商品属性resp>>{}", JSON.toJSONString(integerICResponseMap));
            }

        } catch (Exception e) {
            log.error("同步分城商品属性失败", e);
        }
    }

    public ProductDTO getProduct(Integer productId) {
        List<ProductDTO> list = getProduct(Lists.newArrayList(productId));
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public List<ProductDTO> getProduct(List<Integer> productId) {
        TblHqProductExample hqProductExample = new TblHqProductExample();
        TblHqProductExample.Criteria hqProductExampleCriteria = hqProductExample.createCriteria();
        hqProductExampleCriteria.andHqProductIdIn(productId);
        List<TblHqProduct> tblHqProducts = tblHqProductMapper.selectByExample(hqProductExample);
        if (CollectionUtils.isEmpty(tblHqProducts)) {
            return null;
        }
        List<ProductDTO> dtos = Lists.newArrayList();
        List<Integer> catIds = Lists.newArrayList();
        tblHqProducts.forEach(p -> {
            ProductDTO product = buildProductDTO(p);
            dtos.add(product);
            catIds.add(product.getCategoryId());
        });
        List<CategoryDTO> catResult = getCategoryByIds(catIds);
        Map<Integer, CategoryDTO> catMap = Maps.newHashMap();
        catResult.forEach(c -> {
            catMap.put(c.getClassifyId(), c);
        });

        dtos.forEach(dto -> {
            dto.setCategoryDTO(catMap.get(dto.getCategoryId()));
        });

        //补充商品属性
        appendProductExtra(dtos);
        return dtos;
    }

    public void appendProductExtra(List<ProductDTO> dtos) {
        List<Integer> ids = dtos.stream().filter(it -> Objects.equals(it.getDeliveryMode(), HqProductDeliveryModeEnum.DELIVERY_HOME.getCode())).map(ProductDTO::getProductId).collect(Collectors.toList());

        Map<Integer, HqProductDeliveryHomeAttr> extraMap = getProductExtraAttrs(ids, HqProductDeliveryHomeAttr.class);

        dtos.forEach(dto -> {
            if (Objects.equals(dto.getDeliveryMode(), HqProductDeliveryModeEnum.DELIVERY_HOME.getCode())) {
                HqProductDeliveryHomeAttr homeAttr = extraMap.get(dto.getProductId());
                if (homeAttr != null) {
                    dto.setDeliveryHomeAttrDTO(ProductDeliveryHomeAttrDTO.builder().arrivalDay(homeAttr.getArrivalDay()).build());
                }
            }
        });
    }

    public void modifyProductStatus(ModifyProductStatusRequest modifyProductStatusRequest) {
        TblHqProduct hqProduct = new TblHqProduct();
        hqProduct.setStatus(modifyProductStatusRequest.getStatus().byteValue());
        hqProduct.setUpdateUserId(modifyProductStatusRequest.getUserId());

        TblHqProductExample example = new TblHqProductExample();
        TblHqProductExample.Criteria criteria = example.createCriteria();
        criteria.andHqProductIdIn(modifyProductStatusRequest.getProductIds());
        tblHqProductMapper.updateByExampleSelective(hqProduct, example);
    }

    public void modifyProductName(ModifyProductNameRequest modifyProductNameRequest) {
        TblHqProduct hqProduct = new TblHqProduct();
        hqProduct.setHqProductName(modifyProductNameRequest.getProductName());

        TblHqProductExample example = new TblHqProductExample();
        TblHqProductExample.Criteria criteria = example.createCriteria();
        criteria.andHqProductIdEqualTo(modifyProductNameRequest.getProductId());
        tblHqProductMapper.updateByExampleSelective(hqProduct, example);
    }

    public void delete(List<Integer> includeProductIds) {
        tblHqProductCustomMapper.delete(includeProductIds);
    }

    public boolean existedProduct(ExistedProductRequest existedProductRequest) {
        TblHqProductExample example = new TblHqProductExample();
        TblHqProductExample.Criteria criteria = example.createCriteria();
        if (existedProductRequest.getCategoryId() != null) {
            criteria.andClassifyIdEqualTo(existedProductRequest.getCategoryId());
        }
        if (StringUtils.isNotBlank(existedProductRequest.getProductName())) {
            criteria.andHqProductNameEqualTo(existedProductRequest.getProductName());
        }
        if (StringUtils.isNotBlank(existedProductRequest.getProductNo())) {
            criteria.andProductNoEqualTo(existedProductRequest.getProductNo());
        }
        criteria.andDelFlagEqualTo((byte) 1);
        return tblHqProductMapper.countByExample(example) > 0;
    }

    public List<CategoryDTO> getCategoryByIds(List<Integer> catIds) {
        if (CollectionUtils.isEmpty(catIds)) {
            return Collections.EMPTY_LIST;
        }
        ICResponse<List<CategoryDTO>> catResult = null;
        try {
            catResult = categoryService.findByCategoryIdList(catIds);
        } catch (Exception ex) {
            logger.error("categoryService.findByCategoryIdList ex,cateIds={}", JSON.toJSONString(catIds), ex);
            throw new RuntimeException("查询类目数据失败");
        }
        if (catResult == null) {
            return Collections.EMPTY_LIST;
        }
        if (!catResult.isSuccess()) {
            throw new RuntimeException(catResult.getMsg());
        }
        return catResult.getData();
    }


    public Map<Integer, List<HqProductExtraAttrDTO>> getProductExtraAttrs(List<Integer> hqProductIds) {

        IcHqProductExtraAttrExample extraAttrExample = new IcHqProductExtraAttrExample();
        IcHqProductExtraAttrExample.Criteria criteria1 = extraAttrExample.createCriteria();
        criteria1.andHqProductIdIn(hqProductIds);
        List<IcHqProductExtraAttr> productExtraAttrs = icHqProductExtraAttrMapper.selectByExample(extraAttrExample);

        Map<Integer, List<IcHqProductExtraAttr>> groupMap = productExtraAttrs.stream()
                .collect(Collectors.groupingBy(IcHqProductExtraAttr::getHqProductId, Collectors.toList()));


        Map<Integer, List<HqProductExtraAttrDTO>> extraMap = new HashMap<>(hqProductIds.size());
        hqProductIds.forEach(it -> {
            List<IcHqProductExtraAttr> productExtraAttrs1 = groupMap.get(it);
            if (CollectionUtils.isNotEmpty(productExtraAttrs1)) {
                Map<String, List<IcHqProductExtraAttr>> collect = productExtraAttrs1.stream().collect(Collectors.groupingBy(IcHqProductExtraAttr::getAttrType, Collectors.toList()));

                List<HqProductExtraAttrDTO> hqProductExtraAttrDTOS = Lists.newArrayList();
                collect.forEach((key, value) -> {
                    HqProductExtraAttrDTO extraAttrDTO = null;
                    Map<String, Object> productExtraAttrMap = value.stream().collect(Collectors.toMap(IcHqProductExtraAttr::getAttrName, IcHqProductExtraAttr::getAttrValue));
                    HqProductExtraAttrTypeEnum attrTypeEnum = HqProductExtraAttrTypeEnum.fromName(key);
                    try {
                        Class<?> aClass = Class.forName(attrTypeEnum.getCode());
                        extraAttrDTO = (HqProductExtraAttrDTO) aClass.newInstance();
                    } catch (Exception e) {

                    }

                    BeanMapUtils.transMap2Bean(productExtraAttrMap, extraAttrDTO);
                    hqProductExtraAttrDTOS.add(extraAttrDTO);
                });

                extraMap.put(it, hqProductExtraAttrDTOS);
            } else {
                extraMap.put(it, Lists.newArrayList());
            }
        });

        return extraMap;
    }

    public <T extends HqProductExtraAttrDTO> Map<Integer, T> getProductExtraAttrs(List<Integer> cityProductIds, Class<T> clazz) {
        if (CollectionUtils.isEmpty(cityProductIds)) {
            return Collections.emptyMap();
        }

        Map<Integer, List<HqProductExtraAttrDTO>> extraMap = getProductExtraAttrs(cityProductIds);

        Map<Integer, T> retMap = new HashMap<>(cityProductIds.size());

        for (Map.Entry<Integer, List<HqProductExtraAttrDTO>> entry : extraMap.entrySet()) {
            Integer cityProductId = entry.getKey();
            List<HqProductExtraAttrDTO> extraList = entry.getValue();
            if (CollectionUtils.isNotEmpty(extraList)) {
                for (HqProductExtraAttrDTO extra : extraList) {
                    if (Objects.equals(extra.getClass(), clazz)) {
                        retMap.put(cityProductId, (T) extra);
                    }
                }
            }
        }
        return retMap;
    }


    public Map<Integer, HqProductTax> getProductTaxesMapByCodeList(List<Integer> productTaxIdList) {
        if (CollectionUtils.isEmpty(productTaxIdList)) {
            return Collections.EMPTY_MAP;
        }
        List<HqProductTax> list = tblProductTaxMapper.getProductTaxesByIds(productTaxIdList);
        if (list == null || list.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        Map<Integer, HqProductTax> map = new HashMap<>();
        list.forEach(hqProductTax -> map.put(hqProductTax.getId(), hqProductTax));
        return map;
    }

    public HqProductVo addHqProduct(HqProductVo hqProductVo) {
        tblHqProductCustomMapper.addHqProduct(hqProductVo);
        return hqProductVo;
    }

    public PddProductAttr addPddProductAttr(PddProductAttr pddProductAttr) {
        tblHqProductCustomMapper.addPddProductAttr(pddProductAttr);
        return pddProductAttr;
    }

    public List<PddProductAttr> getPddProductAttrByPddGoodIds(List<String> goodIds) {
        return tblHqProductCustomMapper.getPddProductAttrByPddGoodIds(goodIds);
    }

    /**
     * 获取冻品数据
     *
     * @return
     */
    public List<FrozenProductDO> getFrozenProduct(GetFrozenProductRequest request) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("keepType", request.getKeepType());
        Page<FrozenProductDO> page = PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        return partnerProductMapper.getFrozenProduct(params);
    }


    public List<ProductDTO> getHqProductByProductNoList(List<String> productNoList) {
        TblHqProductExample productExample = new TblHqProductExample();
        TblHqProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andProductNoIn(productNoList);
        List<TblHqProduct> tblHqProducts = tblHqProductMapper.selectByExample(productExample);
        List<ProductDTO> result = tblHqProducts.stream().map(this::buildProductDTO).collect(Collectors.toList());
        List<Integer> classifyIdList = result.stream().map(ProductDTO::getCategoryId).collect(Collectors.toList());
        ICResponse<List<CategoryDTO>> byCategoryIdList = categoryService.findByCategoryIdList(classifyIdList);
        result.forEach(t -> byCategoryIdList.getData().forEach(s -> {
            if (t.getCategoryId().equals(s.getClassifyId())) {
                t.setCategoryDTO(s);
            }
        }));
        return result;

    }


    public ICResponse<List<ProductDTO>> selectHqProductByKeyWord(PagingHqProductSearchQueryRequest request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<TblHqProduct> tblHqProducts = tblHqProductMapper.selectByKeyWord(request.getKeyword());
        List<ProductDTO> result = tblHqProducts.stream().map(this::buildProductDTO).collect(Collectors.toList());
        List<Integer> collect = result.stream().map(ProductDTO::getCategoryId).collect(Collectors.toList());
        ICResponse<List<CategoryDTO>> byCategoryIdList = categoryService.findByCategoryIdList(collect);
        result.forEach(t -> byCategoryIdList.getData().forEach(s -> {
            if (t.getCategoryId().equals(s.getClassifyId())) {
                t.setCategoryDTO(s);
            }
        }));
        PageDTO pageDTO = new PageDTO(request.getPageSize(), (int) ((Page) tblHqProducts).getTotal(), request.getCurrentPage());
        return ICResponse.success(result, pageDTO);
    }

    public void localizeProduct(LocalizeProductRequest localizeRequest) {

        List<Integer> productIds = localizeRequest.getProductIds();

        if (CollectionUtils.isEmpty(productIds)) {
            return;
        }

        for (Integer productId : productIds) {
            TblHqProduct hqProduct = new TblHqProduct();
            hqProduct.setLocalizeFlag(new Byte("1"));

            TblHqProductExample example = new TblHqProductExample();
            TblHqProductExample.Criteria criteria = example.createCriteria();
            criteria.andHqProductIdEqualTo(productId);
            tblHqProductMapper.updateByExampleSelective(hqProduct, example);

            log.info("商品{}本市化标记更新成功", productId);
        }


    }


    public ProductDTO loadProductsByItemId(Long itemId, String productNo) {
        TblHqProduct tblHqProduct = tblHqProductMapper.selectByItemId(itemId, productNo);
        ProductDTO result = buildProductDTO(tblHqProduct);
        return result;
    }

    public List<ProductDTO> checkDumpHqProduct(List<HqProductCheckDO> list) {
        List<TblHqProduct> tblHqProducts = tblHqProductMapper.checkDumpHqProduct(list);
        if (CollectionUtils.isNotEmpty(tblHqProducts)) {
            return tblHqProducts.stream().map(this::buildProductDTO).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
