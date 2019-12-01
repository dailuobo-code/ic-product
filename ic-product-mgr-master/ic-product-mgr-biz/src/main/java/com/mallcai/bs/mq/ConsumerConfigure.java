package com.mallcai.bs.mq;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Davi
 * @Description:
 * @Date: Created in 2019/4/2
 */
@Data
@Configuration
@ToString
public class ConsumerConfigure {

    @Value("${rocketmq.consumer.mgr.groupName}_Consumer")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    private boolean isOrderly;
}
