package com.mallcai.biz.erp.manager;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author chengxi
 * @version v_1.0
 * @date 2019-08-05 17:39
 */
@Slf4j
public abstract class AbstractErpSynHandle {

    public void handle(JSONObject message) {

        StopWatch stopWatch = new StopWatch();
        String eventType=message.getString("eventType");
        String tableName=message.getString("tableName");
        stopWatch.start(tableName+"_"+eventType);

        doHandle(message);

        stopWatch.stop();
        log.info("handle erp sync table success . {}",stopWatch.prettyPrint());


    }


    public abstract Boolean match(String tableName);

    protected abstract void doHandle(JSONObject message);

}
