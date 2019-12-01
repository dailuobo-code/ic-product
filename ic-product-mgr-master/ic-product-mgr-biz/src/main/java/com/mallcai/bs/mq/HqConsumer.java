package com.mallcai.bs.mq;

import com.alibaba.fastjson.JSONObject;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.OpenCity;
import com.dailuobo.api.domain.util.Constant;
import com.dailuobo.biz.utils.AppEnv;
import com.mallcai.backend.common.utils.JSONUtil;
import com.mallcai.bs.mapper.CityProductMapper;
import com.mallcai.bs.mq.classify.dto.ClassifyMsgDTO;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Sjn
 * @description 分类MQ消费者
 * @date 2019-06-17
 */
@Service
@Slf4j
public class HqConsumer extends AbstractListener {

    @Autowired
    private CityProductService cityProductService;

    @Autowired
    private ProductInfoMsgUtils infoMsgUtils;
    @Autowired
    private CityProductMapper cityProductMapper;

    @Value("${Service.CityId:0}")
    private String cityFlg;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private CityService cityService;
    @Resource
    private AppEnv appEnv;

    public HqConsumer() {
        super(MqInfo.APP_GROUP, MqInfo.APP_PRODUCT_TOPIC, "");
    }

    @Override
    public void consume(String body, String tag) {
        if (MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME.equals(tag)) {
            log.info("MQ消息开始消费，topic:{},tag:{},msg:{}", MqInfo.APP_PRODUCT_TOPIC, MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME, body);
            ClassifyMsgDTO classifyMsgDTO = JSONUtil.parseObject(body, ClassifyMsgDTO.class);
            cityProductService.updateL1L2Names(classifyMsgDTO.getClassifyId(), classifyMsgDTO.getClassifyName());
            log.info("MQ消息消费成功，topic:{},tag:{},msg:{}", MqInfo.APP_PRODUCT_TOPIC, MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME, body);
        } else if (MqInfo.TAG_MODIFY_HQ_PRODUCT_BASE.equals(tag)) {

            ProductInfoMsgDTO msgDTO = JSONObject.parseObject(body, ProductInfoMsgDTO.class);
            //本城市发出的mq不用在消费
            log.info("总部商品下行至城市id:{}的商品库更新消息接收成功，msg:{}", cityFlg, msgDTO.toString());
            if (msgDTO != null && msgDTO.getInfoMsgEnum() != null) {
                if (ProductInfoMsgEnum.edit.getCode().equals(msgDTO.getInfoMsgEnum().getCode())) {
                    CityProduct cityProduct = new CityProduct();
                    BeanUtils.copyProperties(msgDTO, cityProduct);
                    if (cityProduct.getHqProductId() != null && StringUtils.isNotEmpty(cityProduct.getL1L2Names())) {
                        cityProductMapper.updateByhqProductId(cityProduct);
                        log.info("cityProduct.getCityProductId={}",cityProduct.getCityProductId());
                        infoMsgUtils.updateCityProductInfo(cityProduct.getHqProductId());
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

    }

    private boolean judgeIsNotSplitCity(Integer cityId, String splitCityIds) {
        return makeStringAppendComma(splitCityIds).contains(makeStringAppendComma(cityId.toString()));
    }

    private String makeStringAppendComma(String str) {
        return "," + str + ",";
    }


}
