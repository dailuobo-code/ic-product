package com.dailuobo.api.domain.soa.inventory;

/**
 * Created by Cowboy on 2016/4/6.
 */
public class InventoryConstant {
    /**
     * 营销库存锁前缀
     */
    public final static String MARKETING_INVENTORY_LOCK_PREFIX = "/lock/marketing";
    
    public final static String REVERTMARKETING_INVENTORY_LOCK_PREFIX = "/lock/revertMarketing";

    /**
     * 营销库存是否有库存Redis Hash Key
     */
    public final static String MARKETING_INVENTORY_FLAG_REDIS_KEY = "marketing_inventory_flag_redis_key";

    /**
     * 营销库存是否已经预警Redis Hash Key
     */
    public final static String MARKETING_INVENTORY_ALARMED_REDIS_KEY = "marketing_inventory_alarmed_redis_key";
    
    public final static String MARKETING_INVENTORY_ALARMED_USER_PHONE = "phone";
    public final static String MARKETING_INVENTORY_ALARMED_USER_EMAIL = "email";
    public final static String MARKETING_INVENTORY_FLAG_NEW_KEY = "marketing_inventory_flag";

    /** mgr 标品库存 开放城市Id列表 **/
    public final static String MGR_CITY_STANDARD_CITYIDS_KEY = "mgr_city_standard_cityIds";

    /** mgr 门店多仓 开放城市Id列表 **/
    public final static String MGR_CITY_MOREWAREHOUSE_CITYIDS_KEY = "mgr_city_morewarehouse_cityids";
}
