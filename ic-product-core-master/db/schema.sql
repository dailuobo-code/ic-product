-- 后台类目
CREATE TABLE IF NOT EXISTS `ic_back_category`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `pid`          bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '源id',
    `name`         varchar(64)         NOT NULL DEFAULT '' COMMENT '名称',
    `level`        int(11)             NOT NULL DEFAULT '1' COMMENT '类目层级',
    `status`       int(11)             NOT NULL DEFAULT '1' COMMENT '状态,1启用,-1禁用',
    `has_children` tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否有子类目',
    `has_spu`      tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否有spu关联',
    `update_time`  timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`  timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`   bigint(20)          NOT NULL DEFAULT '0' COMMENT '更新者',
    `created_by`   bigint(20)          NOT NULL DEFAULT '0' COMMENT '创建者',
    PRIMARY KEY (`id`),
    KEY `idx_pid` (`pid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='后台类目表';

-- 类目属性
CREATE TABLE IF NOT EXISTS `ic_category_attribute`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `category_id`     bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '类目id',
    `group`           varchar(64)         NOT NULL DEFAULT '' COMMENT '所属组名',
    `attr_key`        varchar(64)         NOT NULL DEFAULT '' COMMENT '属性名',
    `attr_metas_json` varchar(1024)       NOT NULL DEFAULT '' COMMENT 'json 格式存储的属性元信息',
    `attr_vals_json`  varchar(1024)       NOT NULL DEFAULT '' COMMENT 'json 格式存储的属性值信息',
    `extra_json`      varchar(1024)       NOT NULL DEFAULT '' COMMENT '名称',
    `index`           int(11)             NOT NULL DEFAULT '1' COMMENT '顺序编号',
    `status`          tinyint(1)          NOT NULL DEFAULT '1' COMMENT '状态,1启用,-1禁用',
    `update_time`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`      bigint(20)          NOT NULL DEFAULT '0' COMMENT '更新者',
    `created_by`      bigint(20)          NOT NULL DEFAULT '0' COMMENT '创建者',
    PRIMARY KEY (`id`),
    KEY `idx_pid` (`category_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='后台类目属性表';

-- 商品
CREATE TABLE IF NOT EXISTS `ic_item`
(
    `id`                    bigint(20) unsigned                 NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `spu_id`                bigint(20) unsigned                 NOT NULL COMMENT 'SPU id',
    `category_id`           bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '类目id',
    `item_code`             varchar(64)                         NULL     DEFAULT '' COMMENT '商品编码',
    `seller_id`             bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '店铺id',
    `seller_name`           varchar(128)                        NOT NULL DEFAULT '' COMMENT '店铺名',
    `service_fee_temp_id`   bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '服务费模板id',
    `keywords`              varchar(256) CHARACTER SET utf8mb4  NOT NULL DEFAULT '' COMMENT '运营定义关键词',
    `name`                  varchar(1024) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '商品名',
    `advertise`             text CHARACTER SET utf8mb4          NOT NULL DEFAULT '' COMMENT '广告，副标题',
    `main_image`            varchar(512)                        NOT NULL DEFAULT '' COMMENT '主图地址',
    `video_url`             varchar(512)                        NOT NULL DEFAULT '' COMMENT '视频地址',
    `status`                smallint(6)                         NOT NULL DEFAULT 0 COMMENT '商品状态：1(上架),-1(下架),-2(冻结),-3(删除)',
    `type`                  smallint(6)                         NOT NULL DEFAULT 0 COMMENT '商品类型',
    `storeType`             smallint(6)                         NOT NULL DEFAULT 0 COMMENT '存储类型',
    `pickType`              smallint(6)                         NOT NULL DEFAULT 0 COMMENT '取货类型',
    `high_price`            int(11)                             NOT NULL DEFAULT 0 COMMENT '最高价',
    `low_price`             int(11)                             NOT NULL DEFAULT 0 COMMENT '最低价',
    `sku_attributes_json`   varchar(4096) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT 'sku销售属性集合',
    `other_attributes_json` varchar(4096) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '其它属性',
    `extra_json`            text CHARACTER SET utf8mb4          NOT NULL DEFAULT '' COMMENT '其它内容',
    `hash_code`             varchar(45)                         NOT NULL DEFAULT '' comment '商品的 hash 值',
    `version`               int(10)                             NOT NULL DEFAULT 0 COMMENT '信息版本号',
    `update_time`           timestamp                           NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    `create_time`           timestamp                           NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
    `updated_by`            bigint(20)                          NOT NULL DEFAULT 0 COMMENT '更新者',
    `created_by`            bigint(20)                          NOT NULL DEFAULT 0 COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_item_code` (`item_code`) USING BTREE,
    KEY `idx_spu_id` (`spu_id`) USING BTREE,
    KEY `idx_seller_id` (`seller_id`) USING BTREE,
    KEY `idx_category_id` (`category_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商品主表';

-- 商品SKU
CREATE TABLE IF NOT EXISTS `ic_sku`
(
    `id`              bigint(20) unsigned                 NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `item_id`         bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '商品id',
    `sku_template_id` bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT 'sku模板id',
    `sku_code`        varchar(64)                         NULL     DEFAULT '' COMMENT 'SKU 编码 (标准库存单位编码)',
    `barcode`         varchar(32)                         NOT NULL DEFAULT '' COMMENT '商品条码',
    `outer_id`        varchar(64)                         NOT NULL DEFAULT 0 COMMENT '外部id',
    `seller_id`       bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '卖家 ID',
    `name`            varchar(1024) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '名称',
    `image`           varchar(1024)                       NOT NULL DEFAULT '' COMMENT '图片url',
    `status`          tinyint(1)                          NOT NULL DEFAULT 0 COMMENT 'sku状态, 1: 上架, -1:下架, -2:冻结, -3:删除',
    `type`            smallint(6)                         NOT NULL DEFAULT 0 COMMENT '商品类型',
    `price`           int(11)                             NOT NULL DEFAULT 0 COMMENT '实际售卖价格',
    `price_json`      varchar(255)                        NOT NULL DEFAULT '' COMMENT 'sku其他各种价格的json表示形式',
    `attrs_json`      varchar(1024) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT 'json存储的sku属性键值对',
    `extra_json`      text CHARACTER SET utf8mb4          NOT NULL DEFAULT '' COMMENT 'sku额外信息',
    `version`         int(10)                             NOT NULL DEFAULT 0 COMMENT '信息版本号',
    `update_time`     timestamp                           NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    `create_time`     timestamp                           NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
    `updated_by`      bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '更新者',
    `created_by`      bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sku_code` (`sku_code`) USING BTREE,
    KEY `idx_item_id` (`item_id`) USING BTREE,
    KEY `idx_seller_id` (`seller_id`) USING BTREE,
    KEY `idx_barcode` (`barcode`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商品SKU表';

-- 商品详情
CREATE TABLE IF NOT EXISTS `ic_item_detail`
(
    `id`          bigint(20) unsigned                 NOT NULL AUTO_INCREMENT,
    `item_id`     bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '商品id',
    `remark`      varchar(4000)                       NOT NULL DEFAULT '' COMMENT '描述',
    `image_json`  longtext                            NOT NULL DEFAULT '' COMMENT '图片列表, json表示',
    `detail`      longtext CHARACTER SET utf8mb4      NOT NULL DEFAULT '' COMMENT '富文本详情',
    `extra_json`  varchar(1024) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '其它信息',
    `update_time` timestamp                           NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    `create_time` timestamp                           NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
    `updated_by`  bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '更新者',
    `created_by`  bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商品详情';

-- 城市商品
CREATE TABLE IF NOT EXISTS `ic_city_item`
(
    `id`          bigint(20) unsigned                 NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `item_id`     bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '商品id',
    `city_id`     bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '城市id',
    `category_id` bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '类目id',
    `seller_id`   bigint(20) unsigned                 NOT NULL DEFAULT 0 COMMENT '卖家 ID',
    `seller_name` varchar(128)                        NOT NULL DEFAULT '' COMMENT '卖家名',
    `name`        varchar(1024) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '商品名',
    `status`      tinyint(1)                          NOT NULL DEFAULT 0 COMMENT '商品状态, 1: 上架, -1:下架, -2:冻结, -3:删除',
    `version`     int(10)                             NOT NULL DEFAULT 0 COMMENT '信息版本号',
    `update_time` timestamp                           NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    `create_time` timestamp                           NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
    `updated_by`  bigint(20)                          NOT NULL DEFAULT 0 COMMENT '更新者',
    `created_by`  bigint(20)                          NOT NULL DEFAULT 0 COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_item_id` (`item_id`) USING BTREE,
    KEY `idx_city_id` (`city_id`) USING BTREE,
    KEY `idx_category_id` (`category_id`) USING BTREE,
    KEY `idx_seller_id` (`seller_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商品主表';

-- 城市商品SKU
CREATE TABLE IF NOT EXISTS `ic_city_sku`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `city_id`     bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '城市id',
    `item_id`     bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '商品id',
    `sku_id`      bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'sku id',
    `barcode`     varchar(32)         NOT NULL DEFAULT '' COMMENT '商品条码',
    `seller_id`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '卖家 ID',
    `status`      tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'sku状态, 1: 上架, -1:下架, -2:冻结, -3:删除',
    `price`       int(11)             NOT NULL DEFAULT 0 COMMENT '实际售卖价格',
    `price_json`  varchar(255)        NOT NULL DEFAULT '' COMMENT 'sku其他各种价格的json表示形式',
    `threshold`   int(10) unsigned    NOT NULL DEFAULT 0 COMMENT '限购份数：0，表示不限购',
    `version`     int(10)             NOT NULL DEFAULT 0 COMMENT '信息版本号',
    `update_time` timestamp           NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    `create_time` timestamp           NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
    `updated_by`  bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者',
    `created_by`  bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_item_id` (`item_id`) USING BTREE,
    KEY `idx_sku_id` (`sku_id`) USING BTREE,
    KEY `idx_city_id` (`city_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商品SKU表';
