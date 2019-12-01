package com.mallcai.bs.mq;

/**
 * @author Sjn
 * @description mq Topic Tag信息
 * @date 2019-06-17
 */
public interface MqInfo {
    String GROUP_CONSUMER_CLASSIFY_UPDATE = "Management_ConsumerClassifyUpdate";
    String TOPIC_CLASSIFY_UPDATE_N = "ClassifyUpdate_N";
    String TAGS_TOPIC_CLASSIFY_UPDATE_N = "DefaultCity";


    // the mq group for this app
    String APP_GROUP="Ic_ConsumeGlobalProduct";


    // app-product MQ
    String APP_PRODUCT_GROUP="app-product-service";
    String APP_PRODUCT_TOPIC="Ic_GlobalProduct_N";
    String TAG_MODIFY_HQ_CATEGORY_NAME="tag_modify_hq_category_name";
    String TAG_MODIFY_HQ_PRODUCT_BASE="tag_modify_hq_product_base";

}
