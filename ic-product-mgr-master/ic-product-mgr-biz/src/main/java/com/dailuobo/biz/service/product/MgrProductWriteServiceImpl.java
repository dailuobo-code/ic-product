package com.dailuobo.biz.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.api.product.MgrProductWriteService;
import com.dailuobo.biz.convert.product.ProductConvert;
import com.dailuobo.biz.manager.product.ProductManager;
import com.dailuobo.ic.api.request.ProductUnShelveRequest;
import com.dailuobo.ic.api.request.product.AsyncProductShelveRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.dailuobo.ic.dto.spec.ProductAttributeDTO;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.service.SOAHPService;
import com.mallcai.bs.service.SpecService;
import com.mallcai.service.api.ICityGlobalService;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import com.mallcai.service.request.product.ProductExtraAttrPutRequest;
import com.mallcai.service.vo.attr.ProductDeliveryHomeAttr;
import dailuobo.dao.mapper.ProductGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service("mgrProductWriteService")
public class MgrProductWriteServiceImpl implements MgrProductWriteService {

    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private SpecService specService;

    @Autowired
    private ProductGroupMapper productGroupMapper;

    @Autowired
    MgrCityProductService mgrCityProductService;


    @Autowired
    private ICityGlobalService iCityGlobalService;

    @Autowired
    private SOAHPService soahpService;


    @Autowired
    private ProductManager productManage;


    /**
     * 新商品同步到城市
     *
     * @param request 创建城市商品
     * @return
     */
    @Override
    public PlainResult<Boolean> createProduct(CityProductCreateRequest request) {
        try {
            log.info("asyncCreateProduct=>request=>{}", JSON.toJSONString(request));
            request.checkParams();
            CityProduct cityProduct = ProductConvert.INSTANCE.convertCityProductRequest2CityProduct(request);

            CityProduct findDBCityProduct = cityProductDao.loadCityProductByItemIdAndSkuId(request.getCityId(), request.getItemId(), request.getSkuId());

            if (findDBCityProduct != null) {
                cityProduct.setCityProductId(findDBCityProduct.getCityProductId());
                cityProductDao.update(cityProduct);
            } else {
                cityProductDao.creatCityProduct(cityProduct);
            }

            if (Objects.equals(request.getDeliveryMode(), 2)) {
                ProductExtraAttrPutRequest<ProductDeliveryHomeAttr> attrPutRequest = new ProductExtraAttrPutRequest<>();
                attrPutRequest.setAttrType(ProductExtraAttrTypeEnum.DELIVERY_HOME);
                attrPutRequest.setUserId(request.getUpdateUserId());
                attrPutRequest.setCityProductId(cityProduct.getCityProductId());
                ProductDeliveryHomeAttr deliveryHomeAttr = new ProductDeliveryHomeAttr();
                deliveryHomeAttr.setArrivalDay(request.getArrivalDay());
                deliveryHomeAttr.setMerchantName("test");

                attrPutRequest.setAttr(deliveryHomeAttr);
                productManage.putProductExtraAttr(attrPutRequest);
            }


            Spec spc = request.getSpec();
            spc.setCityId(request.getCityId());
            spc.setCityProductId(cityProduct.getCityProductId());
            Spec defaultSpec = specService.getDefaultSpec(cityProduct.getCityProductId());
            if (defaultSpec == null) {
                specService.syncProductSpecCreate(spc);
            } else {
                specService.updateDefaultSpec(cityProduct, spc);
            }

            //创建规格属性 todo 检查创建人
            ProductAttributeDTO productAttributeDTO = new ProductAttributeDTO();
            productAttributeDTO.setCityId(request.getCityId());
            productAttributeDTO.setCityProductId(cityProduct.getCityProductId());
            productAttributeDTO.setSpecAttributeJson(JSON.toJSONString(request.getAttributes()));
            //productAttributeDTO.
            productGroupMapper.insertProductAttr(productAttributeDTO);
            //刷新缓存
            iCityGlobalService.refreshProductAttribute(request.getCityId(), Lists.newArrayList(productAttributeDTO.getCityProductId()));

            refreshCategory(cityProduct.getCityId(), cityProduct.getCityProductId());
            return PlainResult.getDefaultSuccess(true);

        } catch (Exception ex) {
            log.error(String.format("##createCityProduct error,request:%s,exception msg:%s", JSON.toJSONString(request), ex.getMessage()), ex);
            return PlainResult.fail(ex.getMessage());

        }

    }


    @Async
    public void refreshCategory(Integer cityId, Integer cityProductId) {
        soahpService.updateProductClassifyRef(cityId, cityProductId);
    }

    @Override
    public PlainResult<Boolean> asyncProductShelve(AsyncProductShelveRequest request) {
        try {
            log.info("AsyncProductShelveRequest=>{}", request);
            for (AsyncProductShelveRequest.SkuSpec t : request.getSkuSpecList()) {
                CityProduct findDBCityProduct = cityProductDao.loadCityProductByItemIdAndSkuId(request.getCityId(), request.getItemId(), t.getSkuId());
                if (findDBCityProduct == null) {
                    continue;
                }
                //下架操作
                if (Objects.equals(request.getStatus(), 2)) {
                    ProductUnShelveRequest unShelveRequest = new ProductUnShelveRequest();
                    unShelveRequest.setCityProductId(findDBCityProduct.getCityProductId());
                    unShelveRequest.setForce(false);
                    unShelveRequest.setCityId(request.getCityId());
                    unShelveRequest.setUserId(request.getUpdateBy());
                    ICResponse down = mgrCityProductService.down(unShelveRequest);

                }
                //上架处理
                else if (Objects.equals(request.getStatus(), 1)) {
                    Spec defaultSpec = specService.getDefaultSpec(findDBCityProduct.getCityProductId());
                    defaultSpec.setAvgPrice(t.getAvgPrice());
                    defaultSpec.setRealPrice(t.getPrice());
                    if (t.getSaleType() != null) {

                        defaultSpec.setUnitType(t.getSaleType().byteValue());
                    }
                    defaultSpec.setThreshold(t.getThreshold());
                    if (t.getBarcode() != null) {
                        findDBCityProduct.setBarCode(t.getBarcode());
                    }
                    findDBCityProduct.setDisablePrice(t.getDisablePrice());
                    findDBCityProduct.setThreshold(t.getThreshold());
                    mgrCityProductService.up(findDBCityProduct, defaultSpec);
                    refreshCategory(findDBCityProduct.getCityId(), findDBCityProduct.getCityProductId());
                }
            }

            return PlainResult.ok(true);
        } catch (Exception ex) {
            log.error(String.format("asyncProductShelve error,%s", JSON.toJSONString(request)), ex);
            return PlainResult.fail(ex.getMessage());
        }

    }

}
