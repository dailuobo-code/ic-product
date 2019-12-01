package com.com.dailuobo.biz.service.product;

import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.goods.IProductGoodsRelService;
import com.dailuobo.ic.api.request.goods.AddProductGoodsRelRequest;
import com.dailuobo.ic.api.request.goods.DelProductGoodsRelRequest;
import com.dailuobo.ic.api.request.goods.QueryProductGoodsRelRequest;
import com.dailuobo.ic.api.vo.ProductGoodsRelVO;
import com.dailuobo.ic.dto.GoodsRelDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lgh
 * @date 2019/10/11
 */
@Slf4j
public class ProductGoodsRelServiceTest extends BaseTest {

    @Autowired
    private IProductGoodsRelService productGoodsRelService;

    @Test
    public void testProductGoodsRel() {

        Integer cityId = 30;

        AddProductGoodsRelRequest relRequest = new AddProductGoodsRelRequest();
        relRequest.setCityId(cityId);
        relRequest.setOperatorId(1);
        relRequest.setCityProductId(11);

        GoodsRelDTO goodsRelDTO = new GoodsRelDTO();
        goodsRelDTO.setGoodsId("001");
        goodsRelDTO.setGoodsName("goods");
        goodsRelDTO.setGoodsUnit("g");
        goodsRelDTO.setIsStandard(1);
        goodsRelDTO.setKeepType(1);
        goodsRelDTO.setRelNum(new BigDecimal("500"));
        List<GoodsRelDTO> goodsRelDTOS  = new ArrayList<>();
        goodsRelDTOS.add(goodsRelDTO);
        relRequest.setGoodsRelDTOS(goodsRelDTOS);

        try{
            // 查询
            QueryProductGoodsRelRequest query = new QueryProductGoodsRelRequest();
            query.setCityId(cityId);
            query.setCityProductId(11);
            ICResponse<List<ProductGoodsRelVO>> response = productGoodsRelService.queryByCityProductId(query);
            Assert.assertTrue(response.isSuccess());
            List<ProductGoodsRelVO> productGoodsRelVOS = response.getData();
            ProductGoodsRelVO relVO = productGoodsRelVOS.get(0);
            Assert.assertTrue(null != relVO);

            // 新增
            ICResponse addRe = productGoodsRelService.addProductGoodsRel(relRequest);
            Assert.assertTrue(addRe.isSuccess());

            // 按货品查询
            query.setGoodsNo("001");
            ICResponse<List<ProductGoodsRelVO>> relResult = productGoodsRelService.queryByGoodsNo(query);
            Assert.assertTrue(relResult.isSuccess());
            Assert.assertTrue(null != relResult.getData().get(0));

        }catch (Throwable e){
            log.error("err",e);
        }

    }

}
