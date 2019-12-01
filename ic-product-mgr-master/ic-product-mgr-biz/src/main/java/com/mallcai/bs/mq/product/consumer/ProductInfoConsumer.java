package com.mallcai.bs.mq.product.consumer;

import com.alibaba.fastjson.JSONObject;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.OpenCity;
import com.dailuobo.api.domain.util.Constant;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mq.AbstractListener;
import com.mallcai.bs.mq.product.dto.ProductInfoMsgDTO;
import com.mallcai.bs.mq.product.enums.ProductInfoMsgEnum;
import com.mallcai.bs.mq.product.util.ProductInfoMsgUtils;
import com.mallcai.bs.service.CityProductService;
import com.mallcai.bs.service.CityService;
import com.mallcai.bs.service.SysParamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static com.mallcai.bs.mq.MqInfo.*;

/**
 * @author Benson
 * @projectName app-admin
 * @packageName com.mallcai.bs.mq.product.consumer
 * @description TODO
 * @date 2019-06-16 11:02
 */
//@Service
@Slf4j
public class ProductInfoConsumer extends AbstractListener {


    public ProductInfoConsumer() {
        super(APP_GROUP, APP_PRODUCT_TOPIC, TAG_MODIFY_HQ_PRODUCT_BASE);
    }


    @Autowired
    ProductInfoMsgUtils infoMsgUtils;
    @Autowired
    CityProductMapper cityProductMapper;

    @Value("${Service.CityId:0}")
    private String cityFlg;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CityProductService cityProductService;

    @Override
    public void consume(String body, String tag) {
        ProductInfoMsgDTO msgDTO = JSONObject.parseObject(body, ProductInfoMsgDTO.class);
        //本城市发出的mq不用在消费
        if (StringUtils.isNotEmpty(msgDTO.getCityFlg()) && !msgDTO.getCityFlg().equals(cityFlg)) {
            log.info("总部商品下行至城市id:{}的商品库更新消息接收成功，msg:{}", cityFlg, msgDTO.toString());
            if (msgDTO != null && msgDTO.getInfoMsgEnum() != null) {
                if (ProductInfoMsgEnum.edit.getCode().equals(msgDTO.getInfoMsgEnum().getCode())) {
                    CityProduct cityProduct = new CityProduct();
                    BeanUtils.copyProperties(msgDTO, cityProduct);
                    if (cityProduct.getHqProductId() != null && StringUtils.isNotEmpty(cityProduct.getL1L2Names())) {
                        cityProductMapper.updateByhqProductId(cityProduct);

                        infoMsgUtils.updateCityProductInfo(cityProduct.getCityProductId());
                        infoMsgUtils.updateProductClassify(msgDTO.getC2Old());
                        log.info("总部商品ID:{},下行至城市id:{}的商品库同步完成", msgDTO.getHqProductId(), cityFlg);
                    }
                } else if (ProductInfoMsgEnum.add.getCode().equals(msgDTO.getInfoMsgEnum().getCode())) {
                    infoMsgUtils.updateProductClassify(0);
                    log.info("总部商品新增操作,下行至城市id:{}的商品库同步完成", cityFlg);
                }
            } else {
                log.error("走消息队列下行各个城市更新商品信息时，请传入业务标识 ");
            }
        }
        //生成电子秤
        if ("0".equals(cityFlg)) {
            // 已经拆出去的城市ids
            String paramType = sysParamService.getGroupUserIsAduit(Constant.SYS_PARAM_TYPE_SPLIT_CITYIDS);
            //首先从tbl_city获取已经开通的城市
            List<OpenCity> openCities = cityProductService.getOpenCity();
            for (OpenCity openCity : openCities) {
                if (!judgeIsNotSplitCity(openCity.getCityId(), paramType)) {
                    cityService.establishExcelNewToOneCity(openCity.getCityId());
                }
            }
        } else {
            cityService.establishExcelNewToOneCity(Integer.parseInt(cityFlg));
        }


    }

    private boolean judgeIsNotSplitCity(Integer cityId, String splitCityIds) {
        return makeStringAppendComma(splitCityIds).contains(makeStringAppendComma(cityId.toString()));
    }

    private String makeStringAppendComma(String str) {
        return "," + str + ",";
    }
}
