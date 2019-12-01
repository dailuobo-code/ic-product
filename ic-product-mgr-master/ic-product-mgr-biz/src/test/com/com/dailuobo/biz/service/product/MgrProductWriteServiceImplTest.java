package com.dailuobo.biz.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.product.MgrProductWriteService;
import com.dailuobo.ic.api.request.product.AsyncProductShelveRequest;
import com.dailuobo.ic.api.request.product.CityProductCreateRequest;
import com.mallcai.backend.common.api.PlainResult;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MgrProductWriteServiceImplTest extends BaseTest {

    @Autowired
    private MgrProductWriteService mgrProductWriteService;

    @Test
    public void createProduct() {
        CityProductCreateRequest request = new CityProductCreateRequest();
        request.setMerchantId(0);
        request.setCityProductPic("test.kkkk.com");
        request.setDisablePrice(100D);
        request.setL1L2Names("时令水果_蔬菜");
        request.setCityId(123);
        request.setNumUnit("克");
        request.setShowName("这个是个测试商品数据");
        request.setSkuId(1001L);
        request.setItemId(10002L);
        request.setHqProductId(10771);
        request.setHqStatus(1);
        request.setCityStatus(1);
        request.setDeliveryMode(1);
        request.setSeasonal(1);
        request.setNewArrivalType(1);
        request.setChangeFlag(1);
        request.setKeepType(1);
        request.setCreateUserId(1);
        request.setUpdateUserId(1);
        request.setGoodsType(1);
        request.setIsGoodsRel(1);
        Spec spec = new Spec();
        spec.setCityId(request.getCityId());
        spec.setAvgPrice(100D);
        request.setSpec(spec);


        String str= "{\"advisePrice\":140.0,\"attributes\":[{\"attrKey\":\"内存\",\"attrVal\":\"128G\"},{\"attrKey\":\"颜色\",\"attrVal\":\"绿色\"}],\"barCode\":\"11234\",\"changeFlag\":1,\"cityId\":32,\"cityProductIcon\":\"http://img1.dailuobo.com/test/2019/11/9/1573285350978.png\",\"cityProductPic\":\"http://img1.dailuobo.com/test/2019/11/9/1573285295827.png\",\"cityStatus\":2,\"createTime\":1573338707000,\"createUserId\":3713434,\"deliveryMode\":2,\"detailUrl\":\"http://img1.dailuobo.com/test/2019/11/9/1573285299422.png\",\"disablePrice\":400.0,\"goodsType\":1,\"homeFlag\":0,\"hqProductId\":24742,\"hqStatus\":1,\"isForecast\":0,\"isGoodsRel\":0,\"isMultiProduct\":1,\"isShare\":1,\"itemId\":53,\"keepType\":2,\"keyword\":\"上下架测试\",\"l1L2Names\":\"二级类目叶子类目\",\"length\":\"10\",\"merchantId\":3713434,\"newArrivalType\":0,\"numUnit\":\"克\",\"pointMallStatus\":0,\"productNo\":\"11551\",\"qualityTime\":1,\"seasonal\":0,\"showName\":\"上下架测试\",\"skuId\":134,\"spec\":{\"avgPrice\":700.0,\"avgUnit\":\"元/斤\",\"cityId\":32,\"cityProductId\":293792,\"createUserId\":3713434,\"packageMaxWeight\":100,\"packageQuantity\":1,\"realPrice\":140.0,\"standardFlag\":1,\"storeId\":0,\"threshold\":0,\"unitType\":1,\"vipCount\":1.0,\"vipProductPrice\":140.0},\"subtitle\":\"上下架测试\",\"threshold\":0,\"updateTime\":1573338707000,\"updateUserId\":3713434,\"vipCount\":1.0,\"vipLimit\":1,\"weightUnit\":\"g\",\"wides\":\"10\"}";

        CityProductCreateRequest request1 = JSON.parseObject(str,CityProductCreateRequest.class);

        PlainResult<Boolean> product = mgrProductWriteService.createProduct(request1);

        Assert.assertTrue(product.isSuccess());
    }

    @Test
    public void asyncProductShelve() {

        String str = "(barcode=9991313, price=700.0, disablePrice=3.0, saleType=2, threshold=0, avgPrice=7.0, skuId=112)," +
                "(barcode=9991314, price=800.0, disablePrice=4.0, saleType=2, threshold=0, avgPrice=8.0, skuId=113), " +
                "(barcode=9991311, price=500.0, disablePrice=1.0, saleType=2, threshold=0, avgPrice=5.0, skuId=110), " +
                "(barcode=9991312, price=600.0, disablePrice=2.0, saleType=2, threshold=0, avgPrice=6.0, skuId=111)])";
        AsyncProductShelveRequest request = new AsyncProductShelveRequest();
        request.setItemId(53L);
        request.setCityId(30);
        request.setStatus(1);
        request.setUpdateBy(0);
        List<AsyncProductShelveRequest.SkuSpec> list = new ArrayList<>();
        AsyncProductShelveRequest.SkuSpec skuSpec = new AsyncProductShelveRequest.SkuSpec();
        skuSpec.setPrice(700D);
        skuSpec.setDisablePrice(3D);
        skuSpec.setSaleType(2);
        skuSpec.setThreshold(0);
        skuSpec.setAvgPrice(7D);
        skuSpec.setSkuId(131L);
        skuSpec.setBarcode("11234");
        list.add(skuSpec);

        AsyncProductShelveRequest.SkuSpec skuSpec1 = new AsyncProductShelveRequest.SkuSpec();
        skuSpec1.setPrice(700D);
        skuSpec1.setDisablePrice(3D);
        skuSpec1.setSaleType(2);
        skuSpec1.setThreshold(0);
        skuSpec1.setAvgPrice(7D);
        skuSpec1.setSkuId(132L);
        skuSpec1.setBarcode("11232");
        list.add(skuSpec1);


        AsyncProductShelveRequest.SkuSpec skuSpec2 = new AsyncProductShelveRequest.SkuSpec();
        skuSpec2.setPrice(700D);
        skuSpec2.setDisablePrice(3D);
        skuSpec2.setSaleType(2);
        skuSpec2.setThreshold(0);
        skuSpec2.setAvgPrice(7D);
        skuSpec2.setSkuId(133L);
        skuSpec2.setBarcode("11233");
        list.add(skuSpec2);


        AsyncProductShelveRequest.SkuSpec skuSpec3 = new AsyncProductShelveRequest.SkuSpec();
        skuSpec3.setPrice(700D);
        skuSpec3.setDisablePrice(3D);
        skuSpec3.setSaleType(2);
        skuSpec3.setThreshold(0);
        skuSpec3.setAvgPrice(7D);
        skuSpec3.setSkuId(134L);
        skuSpec3.setBarcode("11235");
        list.add(skuSpec3);

        request.setSkuSpecList(list);

        mgrProductWriteService.asyncProductShelve(request);




    }
}