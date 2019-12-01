package com.dailuobo.biz.manager.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.ic.api.util.CacheKeyGenerator;
import com.dailuobo.ic.domain.dao.model.product.ProductGroupItemDO;
import com.dailuobo.ic.dto.spec.ProductAttributeDTO;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.cache.mongo.utils.BeanMapUtils;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.dao.ProductExtraAttrDao;
import com.mallcai.bs.domain.ProductExtraAttr;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import com.mallcai.service.request.product.ProductExtraAttrPutRequest;
import com.mallcai.service.vo.ic.common.SOAProductExtraAttr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductManager {
    @Autowired
    private CityProductDao cityProductDao;



    @Autowired
    private ProductExtraAttrDao productExtraAttrDao;

    public Response<Boolean> putProductExtraAttr(ProductExtraAttrPutRequest request) {
        Integer cityProductId = request.getCityProductId();
        SOAProductExtraAttr attr = request.getAttr();
        ProductExtraAttrTypeEnum attrType = request.getAttrType();
        Integer userId = request.getUserId();
        productExtraAttrDao.deleteByCityProductIdAndType(cityProductId, attrType);

        if(attr != null){
            Map<String, String> valueMap = BeanMapUtils.transBean2MapWithValueStr(attr);
            List<ProductExtraAttr> attrList = new ArrayList<>();
            for (Map.Entry<String, String> entry : valueMap.entrySet()) {
                ProductExtraAttr attrDO = new ProductExtraAttr();
                attrDO.setCityProductId(cityProductId);
                attrDO.setAttrType(attrType);
                attrDO.setAttrName(entry.getKey());
                attrDO.setAttrValue(entry.getValue());
                attrDO.setCreateUser(userId);
                attrList.add(attrDO);
            }
            productExtraAttrDao.insertBatch(attrList);
        }

        String productExtraAttr = CacheKeyGenerator.generateExtraAttr(cityProductId);
        JedisProxy.getInstance().delKey(productExtraAttr);
        return Response.success(true);
    }

    public void fillProductAttribute(Integer cityId, List<CityProduct> cityProducts) {
     /*   //多规格商品查询
        List<ProductGroupItemDO> productGroupItemDOS = cityProductDao.listAllGroupItems(cityId);

        Set<Integer> allMultiProductIdList = new HashSet<>();
        if (CollectionUtils.isNotEmpty(productGroupItemDOS)) {
            allMultiProductIdList = productGroupItemDOS.stream().map(ProductGroupItemDO::getCityProductId).collect(Collectors.toSet());
        }*/

        // 兼容货品关联数据表中没有关联数据情形
        //Set<Integer> finalAllMultiProductIdList = allMultiProductIdList;
        cityProducts.forEach(cityProduct -> {
            if (null == cityProduct.getIsGoodsRel()) {
                cityProduct.setIsGoodsRel(0);
            }
            //新老两种对规格展示
            // if (finalAllMultiProductIdList.contains(cityProduct.getCityProductId())) {
            cityProduct.setIsMultiProduct(cityProduct.multiProductFlag() ? 1 : 0);
            // } else {
            //}
        });
        List<Integer> multiProductIdList = cityProducts.stream().filter(t -> Objects.equals(t.getIsMultiProduct(), 1)).map(CityProduct::getCityProductId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(multiProductIdList)) {
            List<ProductAttributeDTO> productAttributeDTOS = productAttributeDTOS(cityId, multiProductIdList);
            cityProducts.forEach(t -> {
                productAttributeDTOS.forEach(q -> {
                    if (t.getCityProductId().equals(q.getCityProductId())) {
                        t.setSkuAttributes(q.getAttributes());
                    }
                });
            });
        }

    }

    private List<ProductAttributeDTO> productAttributeDTOS(Integer cityId, List<Integer> cityProductIdList) {
        try {
            return cityProductDao.listProductAttributes(cityId, cityProductIdList);
        } catch (Exception ex) {
            log.error(String.format("##productAttributeDTOS error,cityId:%s,cityProductIdList:%s", cityId, JSON.toJSON(cityProductIdList)), ex);
            return Lists.newArrayList();
        }

    }
}
