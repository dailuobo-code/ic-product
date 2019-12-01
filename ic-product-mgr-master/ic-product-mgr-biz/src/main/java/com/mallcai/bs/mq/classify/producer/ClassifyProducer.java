package com.mallcai.bs.mq.classify.producer;

import com.dailuobo.api.domain.util.JsonUtil;
import com.mallcai.bs.mq.MqInfo;
import com.mallcai.bs.mq.classify.dto.ClassifyMsgDTO;
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

import static com.mallcai.bs.mq.MqInfo.APP_PRODUCT_GROUP;

/**
 * @author Sjn
 * @description 分类MQ生产者
 * @date 2019-06-17
 */
@Component
@Slf4j
public class ClassifyProducer implements InitializingBean {

    private DefaultMQProducer defaultMQProducer;


    private String groupName = APP_PRODUCT_GROUP;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;


    public boolean sendClassifyUpdate(ClassifyMsgDTO classifyMsgDTO) {
        String body = JsonUtil.toJSONString(classifyMsgDTO);
        Message message = new Message(MqInfo.APP_PRODUCT_TOPIC, MqInfo.TAG_MODIFY_HQ_CATEGORY_NAME, body.getBytes());

        try {
            SendResult result = defaultMQProducer.send(message);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                log.info("商品分类更新通知各个city消息发送成功:{},topic:{}", body, MqInfo.APP_PRODUCT_TOPIC);
                return true;
            } else {
                log.error("商品分类更新通知各个city消息发送失败:{},topic:{}", body, MqInfo.APP_PRODUCT_TOPIC);
                return false;
            }

        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            log.error("商品分类更新通知各个city消息发送失败,ErrMsg:{},topic{}", e.getMessage(), MqInfo.APP_PRODUCT_TOPIC);
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

        log.info("商品分类更新提供者启用成功");
        this.defaultMQProducer = defaultMQProducer;*/
    }
}
