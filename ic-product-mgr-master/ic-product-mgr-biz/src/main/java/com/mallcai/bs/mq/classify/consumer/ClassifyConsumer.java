package com.mallcai.bs.mq.classify.consumer;

import com.mallcai.backend.common.utils.JSONUtil;
import com.mallcai.bs.mq.AbstractListener;
import com.mallcai.bs.mq.MqInfo;
import com.mallcai.bs.mq.classify.dto.ClassifyMsgDTO;
import com.mallcai.bs.service.CityProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sjn
 * @description 分类MQ消费者
 * @date 2019-06-17
 */
//@Service
@Slf4j
public class ClassifyConsumer extends AbstractListener {

    @Autowired
    private CityProductService cityProductService;

    public ClassifyConsumer() {
        super(MqInfo.APP_GROUP, MqInfo.APP_PRODUCT_TOPIC, MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME);
    }

    @Override
    public void consume(String body,String tag) {
        log.info("MQ消息开始消费，topic:{},tag:{},msg:{}", MqInfo.APP_PRODUCT_TOPIC, MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME, body);
        ClassifyMsgDTO classifyMsgDTO = JSONUtil.parseObject(body, ClassifyMsgDTO.class);
        cityProductService.updateL1L2Names(classifyMsgDTO.getClassifyId(), classifyMsgDTO.getClassifyName());
        log.info("MQ消息消费成功，topic:{},tag:{},msg:{}", MqInfo.APP_PRODUCT_TOPIC, MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME, body);
    }


}
