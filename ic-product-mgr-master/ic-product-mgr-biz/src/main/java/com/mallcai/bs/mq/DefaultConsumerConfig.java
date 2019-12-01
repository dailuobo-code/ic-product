package com.mallcai.bs.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * @Author : Davi
 * @Description:
 * @Date: Created in 2019/4/2
 */
@Slf4j
public abstract class DefaultConsumerConfig {

    @Autowired
    private ConsumerConfigure consumerConfigure;

    /**
     * 开启消费者监听服务
     */
    public void listener(String groupName, String topic, String tag) throws MQClientException {
        log.info("开启 consumer {}", consumerConfigure.toString());
        log.info(consumerConfigure.toString());
    
        DefaultMQPushConsumer consumer;
        if (StringUtils.isNotBlank(groupName)) {
            consumer = new DefaultMQPushConsumer(groupName);
        } else {
            //区别默认的消息者组，后面组名称拼接topic
            if(topic.equals("Qm_QimenOrderPakage_N")){
                consumer = new DefaultMQPushConsumer(consumerConfigure.getGroupName());
            }else{
                consumer = new DefaultMQPushConsumer(consumerConfigure.getGroupName()+"_"+topic);
            }
        }

        consumer.setNamesrvAddr(consumerConfigure.getNamesrvAddr());
        
        consumer.setMultiCity(ClientConfig.MultiCity.ENABLE);

        consumer.subscribe(topic,"*");

        // 如果是第一次启动，从队列头部开始消费
        // 如果不是第一次启动，从上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //consumer.setInstanceName(topic+"-"+tag+"-"+System.currentTimeMillis()+ RandomUtil.getRandomString(10));

        if (true) {
            // 开启内部类实现监听
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    return DefaultConsumerConfig.this.consumeMessageConcurrently(msgs);
                }
            });
        } else {

            // 顺序消息消费，带事务方式（应用可控制Offset什么时候提交）
            consumer.registerMessageListener(new MessageListenerOrderly() {
                private int num = 1;
                private Random random = new Random();

                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

                    return DefaultConsumerConfig.this.consumeMessageOrderly(msgs, context);
                }
            });
        }

        consumer.start();
        log.info("rocketmq启动成功---------------------------------------");
    }

    /**
     * 并行处理message
     *
     * @param msgs
     * @return
     */
    public abstract ConsumeConcurrentlyStatus consumeMessageConcurrently(List<MessageExt> msgs);

    /**
     * 顺序处理message
     *
     * @param msgs
     * @param context
     * @return
     */
    public abstract ConsumeOrderlyStatus consumeMessageOrderly(List<MessageExt> msgs, ConsumeOrderlyContext context);


}
