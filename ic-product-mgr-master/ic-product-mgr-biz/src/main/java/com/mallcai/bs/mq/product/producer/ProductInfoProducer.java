package com.mallcai.bs.mq.product.producer;

import com.dailuobo.api.domain.util.JsonUtil;
import com.mallcai.bs.mq.product.dto.ProductInfoMsgDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.mallcai.bs.mq.MqInfo.*;

/**
 * @author Benson
 * @projectName app-admin
 * @packageName com.mallcai.bs.mq.product.producer
 * @description TODO
 * @date 2019-06-16 11:02
 */
@Component
@Slf4j
public class ProductInfoProducer implements InitializingBean {


    private DefaultMQProducer defaultMQProducer;


    private String groupName = APP_PRODUCT_GROUP;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;


    public boolean sendProductInfo(ProductInfoMsgDTO productInfoMsgDTO) {
        String body = JsonUtil.toJSONString(productInfoMsgDTO);

        Message message = new Message(APP_PRODUCT_TOPIC, TAG_MODIFY_HQ_PRODUCT_BASE, body.getBytes());

        try {
            SendResult result = defaultMQProducer.send(message);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                log.info("商品基础信息同步各个city消息发送成功:{}", body);
                return true;
            } else {
                log.error("商品基础信息同步各个city消息发送失败:{}", body);
                return false;
            }

        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            log.error("商品基础信息同步各个city消息发送失败，", e);
            return false;
        }

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        /*DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setProducerGroup(this.groupName);
        defaultMQProducer.setInstanceName(System.currentTimeMillis() + RandomUtil.getRandomString(10));
        defaultMQProducer.setNamesrvAddr(this.namesrvAddr);
        defaultMQProducer.setVipChannelEnabled(false);
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(10);

        defaultMQProducer.setMultiCity(ClientConfig.MultiCity.ENABLE);
        defaultMQProducer.start();

        log.info("商品基础信息更新提供者启用成功");
        this.defaultMQProducer = defaultMQProducer;*/
    }
}
