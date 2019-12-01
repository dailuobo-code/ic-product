package com.dailuobo.api.domain.util;

public class Constant {
    public static final String COOKIE_USER_KEY = "COOKIE_USER_KEY";

    public static final String COOKIE_SERVICE_CITY_ID_KEY = "ServiceCityId";

    /**
     * 定门店定时条件 失效时间
     */
    public static final String MULTI_BARGAIN_TASK_LOSE_EFFICACY_TIME = "22:00:00";

    public static final String MGR = "mgr";

    public static final String SESSION_USER = "SESSION_USER";

    public static final int REDIS_USER_TIMEOUT = 3600;

    public static final int COOKIE_USER_TIMEOUT = 3600;

    public static final int COOKIE_SERVICE_CITY_ID_TIMEOUT = -1;

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String CUSTOMER_SERVICE_TEL = "0551-62913176";
    
    public static final String SYS_BALANCE_COMPENSATE = "sys_balance_compensate";
    
    public static final String SYS_SPECIAL_TIME = "sys_special_time";
    
	public static final String SYS_VERSION_STR 	=  "sys_version_str";
	
	public static final int SYS_CITY_ID 	=  30;  //城市id
	
	public static final String BUSINESS_TYPE_C 	=  "C";  //扫码充值业务标识
	
	
	//消息类型 1系统消息，2我的资产.4取菜提醒
    public static final int PUSH_MOULD_TYPE_1 = 1;
    public static final int PUSH_MOULD_TYPE_2 = 2;
    public static final int PUSH_MOULD_MSGTYPE_4 = 4;

    //子消息类型（系统消息：1取货，2订单，3群发；我的资产：1客服充值，2优惠券 3门店充值, 10我的优惠券列表 ）
    public static final int PUSH_MOULD_MSGTYPE_1 = 1;
    public static final int PUSH_MOULD_MSGTYPE_2 = 2;
    public static final int PUSH_MOULD_MSGTYPE_3 = 3;
    public static final int PUSH_MOULD_MSGTYPE_10 = 10;
	

	public static final String SYS_AFTER_SALE_LIMIT = "sys_after_sale_limit";
	

	public static final int SMOKE_CLASSIFY_ID = 237;  //烟草分类二级id，用于区分线下库存管理过滤和采购单过滤 dev（203） 备二（237）
	
	public static final String WAIT_PASS = "1";
	public static final String NOT_PASS = "3";
	//app_user重置密码 123456
	public static final String DEFAULT_PASSWORD = "3275cc58a72b25cc41161f57463ba498a06bca4723daa96eeaff24aa1644c6bb";
    
	public static final String STORE_OT_CONFIG = "otConfig";//门店下单时间参数
	
	public static final String STORE_BH_CONFIG = "bhConfig";//门店营业时间参数
	
	public static final String GROUP_USER_ISAUDIT = "sys_group_user_isAudit";//申请团购团长通过
	public static final String GROUP_USER_DISAUDIT = "sys_group_user_disAudit";//申请团购团长不通过

    public static final String SYS_RECHARGE_POINTS = "sys_recharge_points";

    //已经拆分出去的城市Id
    public static final String SYS_PARAM_TYPE_SPLIT_CITYIDS = "sys_param_type_split_cityids";
}
