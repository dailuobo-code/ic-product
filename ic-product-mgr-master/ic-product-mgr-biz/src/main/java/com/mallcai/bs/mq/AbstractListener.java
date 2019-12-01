package com.mallcai.bs.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

@Slf4j
public abstract  class AbstractListener extends DefaultConsumerConfig implements InitializingBean {

    private String groupName;

    private final String  topic;
    
    
    private final String  tag;

//    public AbstractListener(String topic){
//            this.topic  = topic;
//    }

    public AbstractListener(String topic,String tag){
        this.topic  = topic;
        this.tag=tag;
    }
    
    public AbstractListener(String groupName, String topic,String tag){
        this.groupName = groupName;
        this.topic  = topic;
        this.tag=tag;
    }



    @Override
    public ConsumeConcurrentlyStatus consumeMessageConcurrently(List<MessageExt> msgs) {
        for(MessageExt msg : msgs){
            String body = new String(msg.getBody());
            log.info(getTopic()+"----->:接受消息,"+body);
            try {
                consume(body,msg.getTags());
            } catch (Exception e) {
                log.error("消费者消费失败",e);
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public ConsumeOrderlyStatus consumeMessageOrderly(List<MessageExt> msgs, ConsumeOrderlyContext context) {
            return null;
    }

    public abstract void consume(String body, String tag);

    public String getTopic() {
        return topic;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            listener(groupName,topic,tag);
        } catch (MQClientException e) {
            log.error(topic+"消费者启动失败",e);
            throw new RuntimeException(e);
        }
    }
}
