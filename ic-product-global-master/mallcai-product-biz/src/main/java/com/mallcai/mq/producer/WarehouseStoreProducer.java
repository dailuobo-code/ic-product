package com.mallcai.mq.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-05 14:22
 */
@Component
@Slf4j
public class WarehouseStoreProducer {

    private DefaultMQProducer mqProducerInstance;

    @Value("${rocketmq.producer.erp.namesrvAddr}")
    private String wsNamesrvAddr;
    @Value("${rocketmq.producer.erp.groupName}")
    private String wsGroupName;
    @Value("${rocketmq.producer.erp.topic}")
    private String wsTopic;


    @PostConstruct
    public void init() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(wsGroupName);
        producer.setInstanceName(System.currentTimeMillis() + RandomUtil.getRandomString(10));
        producer.setNamesrvAddr(wsNamesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.setMultiCity(ClientConfig.MultiCity.ENABLE);
        producer.start();
        log.info("WarehouseStoreProducer  started.");
        this.mqProducerInstance = producer;
    }

    public Boolean send(Object message) {
        String body = JSON.toJSONString(message);
        Message msg = new Message(wsTopic, body.getBytes());
        try {
            SendResult sendResult = mqProducerInstance.send(msg);
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                log.info("WarehouseStoreProducer send message success .{},{}", wsTopic, JSON.toJSONString(message));
                return true;
            }
            log.error("WarehouseStoreProducer return failed  . {},{}", wsTopic, JSON.toJSONString(message));
        } catch (Exception e) {
            log.error("WarehouseStoreProducer send error.data =" + JSON.toJSONString(message), e);
        }
        return false;

    }
}
