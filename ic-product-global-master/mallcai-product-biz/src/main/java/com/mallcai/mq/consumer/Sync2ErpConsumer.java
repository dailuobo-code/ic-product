package com.mallcai.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.biz.erp.dao.PurchaseOrderDAO;
import com.mallcai.biz.erp.enums.ErpSyncTableEnum;
import com.mallcai.biz.erp.manager.AbstractErpSynHandle;
import com.mallcai.biz.erp.model.ErpPurchaseOrderDO;
import com.mallcai.mq.AbstractListener;

import com.mallcai.utils.AppEnv;
import com.mallcai.utils.NacosParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Component
public class Sync2ErpConsumer extends AbstractListener {

    @Value("${rocketmq.consumer.erp.topic}")
    private String topic;
    @Value("${rocketmq.consumer.erp.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.erp.namesrvAddr}")
    private String namesrvAddr;

    @Autowired
    private List<AbstractErpSynHandle> handles;

    @Value("${Service.CityId}")
    private String currCityId;
    @Autowired
    private NacosParser nacosParser;
    @Autowired
    private AppEnv appEnv;

    @Override
    public void consume(String body, String tags) {
//        if(!nacosParser.isOpenErp2caicai()){
//            log.info("erp to caicai mq is close,{},{}",body,tags);
//            return;
//        }


        JSONObject message = JSONObject.parseObject(body);
        String tableName = message.getString("tableName");
        AbstractErpSynHandle handle = selectHandle(tableName);
        if(handle==null){
            log.info("unsupport table MQ, body:{}",body);
            return;
        }
        handle.handle(message);

    }


    private AbstractErpSynHandle selectHandle(String tableName) {

        List<AbstractErpSynHandle> hanldes = handles.stream().filter(
                handle -> handle.match(tableName)).collect(Collectors.toList());
        if(hanldes.isEmpty() || hanldes.size()>1){
            return null;
        }
        return hanldes.get(0);

    }


    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            Map<String, String> topicMap = new HashMap<>();
            topicMap.put(topic, "*");
            listener(topicMap,
                    ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET,
                    ClientConfig.MultiCity.DISABLE,
                    groupName,
                    namesrvAddr,
                    false);
        } catch (MQClientException e) {
            log.error(topic + "消费者启动失败", e);
            throw new RuntimeException(e);
        }


    }

}
