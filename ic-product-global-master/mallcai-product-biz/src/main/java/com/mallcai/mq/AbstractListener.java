package com.mallcai.mq;

import com.mallcai.backend.common.utils.DefaultConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
public abstract class AbstractListener extends DefaultConsumerConfig implements InitializingBean {

    @Override
    public ConsumeConcurrentlyStatus consumeMessageConcurrently(List<MessageExt> msgs) {
        for (MessageExt msg : msgs) {

            String body = new String(msg.getBody());
            String tags = new String(msg.getTags());
            log.info("receieve MQ {},{}" ,body , tags);
            try {
                consume(body, tags);
            } catch (Exception e) {
                log.error("consumer failed  body:"+body, e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public ConsumeOrderlyStatus consumeMessageOrderly(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        return null;
    }

    public abstract void consume(String body, String tags) throws InterruptedException, ExecutionException;

}
