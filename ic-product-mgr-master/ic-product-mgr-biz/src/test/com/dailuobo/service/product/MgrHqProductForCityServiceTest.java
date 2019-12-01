package dailuobo.service.product;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.Classify;
import com.dailuobo.api.domain.entity.HqProductTax;
import com.dailuobo.api.domain.entity.Spec;
import com.dailuobo.api.domain.vo.ProductCreateVo;
import com.dailuobo.api.enums.HqProductExtraAttrAction;
import com.dailuobo.api.product.MgrCityProductService;
import com.dailuobo.api.product.MgrHqProductForCityService;
import com.dailuobo.ic.api.request.product.HqProductLocalizeRequest;
import com.dailuobo.ic.api.request.product.HqProductSyncExtraAttrRequest;
import com.mallcai.api.product.backend.MgrProductService;
import com.mallcai.bs.dao.HqProductDao;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mapper.SOAHPMapper;
import com.mallcai.bs.mapper.SpecMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: MgrHqProductForCityServiceTest
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/8/25 下午4:33
 * @Version: 1.0
 **/
public class MgrHqProductForCityServiceTest extends BaseTest {
    @Autowired
    private MgrHqProductForCityService mgrHqProductForCityService;

    @Autowired
    private MgrCityProductService mgrCityProductService;

    @Autowired
    private CityProductMapper cityProductMapper;

    @Autowired
    private SOAHPMapper soaHpMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private MgrProductService mgrProductService;

    @Autowired
    private HqProductDao hqProductDao;


    @Test
    public void testGetProductTax() {
        List<String> codes = Arrays.asList("10901330200", "10604020105");
        ICResponse<List<HqProductTax>> ret = mgrHqProductForCityService.getProductTaxByCode(codes);
        Assert.assertTrue(ret.isSuccess());
        for(HqProductTax tax : ret.getData()) {
            System.out.println("id: " + tax.getId() + ", code: " + tax.getTaxClassifyCode() + ", rate: " + tax
                    .getTaxRate());
        }

    }


    @Test
    public void testLocalizeV2() {
        HqProductLocalizeRequest localizeRequest = HqProductLocalizeRequest.builder().userId(12123).build();
        List<HqProductLocalizeRequest.HqProductDTO> hqProductDTOS = new ArrayList<>();
        HqProductLocalizeRequest.HqProductDeliverHomeAttrDTO homeAttrDTO = HqProductLocalizeRequest.HqProductDeliverHomeAttrDTO.builder().arrivalDay(3).build();
        HqProductLocalizeRequest.HqProductDTO hqProductDTO = HqProductLocalizeRequest.HqProductDTO.builder().hqProductId(24442).cityId(220).merchantId(1223).isShare(1).attrDTO(homeAttrDTO).build();
        hqProductDTOS.add(hqProductDTO);
        HqProductLocalizeRequest.HqProductDTO hqProductDTO1 = HqProductLocalizeRequest.HqProductDTO.builder().hqProductId(24442).cityId(221).merchantId(1223).isShare(1).attrDTO(homeAttrDTO).build();
        hqProductDTOS.add(hqProductDTO1);

        homeAttrDTO = HqProductLocalizeRequest.HqProductDeliverHomeAttrDTO.builder().arrivalDay(8).build();
        hqProductDTO = HqProductLocalizeRequest.HqProductDTO.builder().hqProductId(24441).cityId(221).merchantId(1223).isShare(1).attrDTO(homeAttrDTO).build();
        hqProductDTOS.add(hqProductDTO);
        localizeRequest.setHqProducts(hqProductDTOS);
        ICResponse<Boolean> booleanICResponse = mgrHqProductForCityService.localizeV2(localizeRequest);
        System.out.println(JSON.toJSONString(booleanICResponse));
    }

    @Test
    public void testSyncHqProductExtraAttr() {
        HqProductSyncExtraAttrRequest.HqProductDeliverHomeAttrDTO homeAttrDTO = HqProductSyncExtraAttrRequest.HqProductDeliverHomeAttrDTO.builder().arrivalDay(2).build();
        HqProductSyncExtraAttrRequest.HqProductDTO hqProductDTO = HqProductSyncExtraAttrRequest.HqProductDTO.builder().hqProductId(3613).attrDTO(homeAttrDTO).build();
        HqProductSyncExtraAttrRequest syncExtraAttrRequest = HqProductSyncExtraAttrRequest.builder().attrAction(HqProductExtraAttrAction.MODIFY).hqProduct(hqProductDTO).build();
        ICResponse<Boolean> booleanICResponse = mgrHqProductForCityService.syncHqProductExtraAttr(syncExtraAttrRequest);
        System.out.println(JSON.toJSONString(booleanICResponse));
    }

    @Test
    public void testcheckProductNoNotExist() {
//        List<String> productNos = Arrays.asList("11108", "12203", "41002");
//        ICResponse<List<String>> ret = mgrHqProductForCityService.checkProductNoNotExist(productNos);
//        Assert.assertTrue(!ret.isSuccess());
//        for(String elm : productNos) {
//            System.out.println("productNO: " + elm + ", exits: ");
//        }
    }

    @Test
    public void testHqProdcutInsert2() {
        CityProduct cityProduct = new CityProduct();

        Classify classify = new Classify();
        cityProduct.setClassify(classify);

        cityProduct.getClassify().setClassifyId(36);
        cityProduct.setProductNo("41003");
        cityProduct.setHqProductName("测试商品插入");
        cityProduct.setRemark("插入商品测试");
        cityProduct.setStatus(1);
        cityProduct.setDeliveryMode((byte)1);
        cityProduct.setHqProductIcon("http://www.baidu.com");
        cityProduct.setHqProductPic("http://www.baidu.com");
        cityProduct.setLocalizeFlag(1);
        cityProduct.setCreateUserId(2);
        cityProduct.setUpdateUserId(2);
        cityProduct.setBarCode("code");
        cityProduct.setKeepType(4);
        cityProduct.setQualityTime(2);
        cityProduct.setIsStandard(2);
        cityProduct.setPickClassify(2);
        cityProduct.setGoodsType(1);
        cityProduct.setProductTaxId(123);


    }

    @Test
    public void testHqProdcutInsert() {
        CityProduct cityProduct = new CityProduct();

        Classify classify = new Classify();
        cityProduct.setClassify(classify);

        cityProduct.getClassify().setClassifyId(431);
        cityProduct.setProductNo("41006");
        cityProduct.setHqProductName("测试商品插入第二个");
        cityProduct.setRemark("插入商品测试");
        cityProduct.setStatus(1);
        cityProduct.setDeliveryMode((byte)1);
        cityProduct.setHqProductIcon("http://www.baidu.com");
        cityProduct.setHqProductPic("http://www.baidu.com");
        cityProduct.setLocalizeFlag(1);
        cityProduct.setCreateUserId(2);
        cityProduct.setUpdateUserId(2);
        cityProduct.setBarCode("code");
        cityProduct.setKeepType(4);
        cityProduct.setQualityTime(2);
        cityProduct.setIsStandard(2);
        cityProduct.setPickClassify(2);
        cityProduct.setGoodsType(1);
        cityProduct.setProductTaxId(123);

//        soaHpMapper.addHqProduct(cityProduct);
        System.out.println("===================hqProductId:" + cityProduct.getHqProductId());

        cityProduct.setCityId(30);
        cityProduct.setHqStatus(1);
        cityProduct.setCityStatus(0);
        cityProduct.setShowName("显示名");
        cityProduct.setDisablePrice(10.10);
        cityProduct.setWeightUnit("克");
        cityProduct.setNumUnit("份");
        cityProduct.setOrigin("来源");
        cityProduct.setAdvisePrice(12.12);
        cityProduct.setSubtitle("子标题");
        cityProduct.setKeyword("关键字");
        cityProduct.setL1L2Names("类目");
        cityProduct.setDeliveryMode((byte)1);
        cityProduct.setCityProductIcon("icon");
        cityProduct.setCityProductPic("pic");
        cityProduct.setDetailUrl("DetailUrl");
        cityProduct.setCreateUserId(2);
        cityProduct.setUpdateUserId(2);
        cityProduct.setIsShare(1);
        cityProduct.setNewUserPro(0);
        cityProduct.setIsUnder(0);
        cityProduct.setVipLimit(1);
        cityProduct.setPointPrice(null);
        cityProduct.setPointMallStatus(0);
        cityProduct.setMerchantId(12345);
        cityProduct.setMerchantRate(new BigDecimal(0.1234));


//        cityProductMapper.addCityProduct(cityProduct);

        Spec spec = new Spec();
        spec.setCityId(30);
        spec.setStoreId(0);
        spec.setCityProductId(cityProduct.getCityProductId());
        spec.setRealPrice(13.13);
        spec.setAvgPrice(14.14);
        spec.setAvgUnit("斤");
        spec.setUnitType((byte)1);
        spec.setStandardFlag(1);
        spec.setThreshold(0);
        spec.setPackageMaxWeight(400);
        spec.setChangeFlag((byte)1);
        spec.setPackageQuantity(1);
        spec.setVipCount(0.98);
        spec.setVipProductPrice(9.9);
        spec.setAvgPriceCeiling(25.0);
        spec.setAvgPriceFloor(5.0);

//        specMapper.addSpec(spec, 2);

        ProductCreateVo vo = new ProductCreateVo();
        vo.setCityProduct(cityProduct);
        vo.setSpec(spec);
        List<ProductCreateVo> list = Arrays.asList(vo);

        mgrCityProductService.createProductAllInOnce(list);

    }


    @Test
    public void testLocalizeProductToAllCity() {
        // dev环境测试
        List<ProductCreateVo> list = Arrays.asList(
                getProductCreateVo("40005"),
                getProductCreateVo("40006"));

        mgrCityProductService.localizeProductToAllCity(list);
    }

    @Test
    public void test() {
        // dev环境测试
        List<Integer> ret = hqProductDao.getClassifyIdsByProductNos(Arrays.asList("14101", "11103"));
        System.out.println("=================");
        System.out.println(ret);
    }


    private ProductCreateVo getProductCreateVo(String productNO) {
        CityProduct cityProduct = new CityProduct();

        Classify classify = new Classify();
        cityProduct.setClassify(classify);

        cityProduct.setHqProductId(1455);
        cityProduct.setDelFlag(1);

        cityProduct.getClassify().setClassifyId(431);
        cityProduct.setProductNo(productNO);
        cityProduct.setHqProductName("测试商品插入第二个");
        cityProduct.setRemark("插入商品测试");
        cityProduct.setStatus(1);
        cityProduct.setDeliveryMode((byte)1);
        cityProduct.setHqProductIcon("http://www.baidu.com");
        cityProduct.setHqProductPic("http://www.baidu.com");
        cityProduct.setLocalizeFlag(1);
        cityProduct.setCreateUserId(2);
        cityProduct.setUpdateUserId(2);
        cityProduct.setBarCode("code");
        cityProduct.setKeepType(4);
        cityProduct.setQualityTime(2);
        cityProduct.setIsStandard(2);
        cityProduct.setPickClassify(2);
        cityProduct.setGoodsType(1);
        cityProduct.setProductTaxId(123);

//        soaHpMapper.addHqProduct(cityProduct);
        System.out.println("===================hqProductId:" + cityProduct.getHqProductId());

        cityProduct.setCityId(5);
        cityProduct.setHqStatus(1);
        cityProduct.setCityStatus(0);
        cityProduct.setShowName(productNO + "显示名");
        cityProduct.setDisablePrice(10.10);
        cityProduct.setWeightUnit("克");
        cityProduct.setNumUnit("份");
        cityProduct.setOrigin("来源");
        cityProduct.setAdvisePrice(12.12);
        cityProduct.setSubtitle("子标题");
        cityProduct.setKeyword("关键字");
        cityProduct.setL1L2Names("类目");
        cityProduct.setDeliveryMode((byte)1);
        cityProduct.setCityProductIcon("icon");
        cityProduct.setCityProductPic("pic");
        cityProduct.setDetailUrl("DetailUrl");
        cityProduct.setCreateUserId(2);
        cityProduct.setUpdateUserId(2);
        cityProduct.setIsShare(1);
        cityProduct.setNewUserPro(0);
        cityProduct.setIsUnder(0);
        cityProduct.setVipLimit(1);
        cityProduct.setPointPrice(null);
        cityProduct.setPointMallStatus(0);
        cityProduct.setMerchantId(12345);
        cityProduct.setMerchantRate(new BigDecimal(0.1234));



//        cityProductMapper.addCityProduct(cityProduct);

        Spec spec = new Spec();
        spec.setCityId(5);
        spec.setStoreId(0);
        spec.setCityProductId(cityProduct.getCityProductId());
        spec.setRealPrice(13.13);
        spec.setAvgPrice(14.14);
        spec.setAvgUnit("斤");
        spec.setUnitType((byte)1);
        spec.setStandardFlag(1);
        spec.setThreshold(0);
        spec.setPackageMaxWeight(400);
        spec.setChangeFlag((byte)1);
        spec.setPackageQuantity(1);
        spec.setVipCount(0.98);
        spec.setVipProductPrice(9.9);
        spec.setAvgPriceCeiling(25.0);
        spec.setAvgPriceFloor(5.0);

        spec.setCreateUserId(1234);
        spec.setUpdateUserId(1234);

//        specMapper.addSpec(spec, 2);

        ProductCreateVo vo = new ProductCreateVo();
        vo.setCityProduct(cityProduct);
        vo.setSpec(spec);

        return vo;
    }
}
