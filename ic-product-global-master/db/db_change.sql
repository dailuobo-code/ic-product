-- 2019-08-22 首页推荐主题
create table if not exists `ic_product_recommend_topic`
(
    `id`          bigint(20) unsigned primary key auto_increment comment 'ID',
    `name`        varchar(100) not null comment '推荐名称',
    `classifies`  varchar(100) not null default '' comment '绑定二级类目，逗号分隔的列表',
    `order`       int(10)      not null default 0 comment '排序',
    `operator_id` int unsigned not null comment '最后操作人 ID，必填',
    `operator`    varchar(128) not null comment '最后操作人名字，必填',
    `status`      varchar(32)  null     default '' comment '状态, ENABLE-启用，DISABLE-禁用',
    `deleted`     tinyint(1)   null     default 0 comment '软删除,0-正常,1-删除',
    `update_time` timestamp             default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `create_time` timestamp             default CURRENT_TIMESTAMP not null comment '创建时间'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 comment '首页推荐主题';

-- 2019-08-27 商品审批单
CREATE TABLE `ic_audit_bill`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type`        varchar(32)         NOT NULL DEFAULT '' COMMENT '商品类型，默认为总部商品创建审批',
    `status`      varchar(32)         NOT NULL DEFAULT '' COMMENT '状态, AUDITING-审批中，APPROVAL-审批通过, REJECT-审批拒绝',
    `comment`     varchar(100)        NOT NULL DEFAULT '' COMMENT '审批备注',
    `reply`       varchar(100)        NOT NULL DEFAULT '' COMMENT '审批意见',
    `flow_id`     varchar(64)         NOT NULL DEFAULT '' COMMENT '审批单编号',
    `operator_id` int(10) unsigned    NOT NULL COMMENT '最后操作人 ID，必填',
    `operator`    varchar(128)        NOT NULL COMMENT '最后操作人名字，必填',
    `is_deleted`  tinyint(1)          NOT NULL DEFAULT '0' COMMENT '软删除,0-正常,1-删除',
    `update_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1 COMMENT ='商品审批单';

CREATE TABLE `ic_audit_item`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `bill_id`     bigint(20) unsigned NOT NULL COMMENT '审批单 ID',
    `item_id`     bigint(20) unsigned NOT NULL COMMENT '关联关系清单',
    `reply`       varchar(100)        NOT NULL DEFAULT '' COMMENT '冗余审批意见',
    `type`        varchar(32)         NOT NULL DEFAULT '' COMMENT '商品类型，默认为总部商品创建审批',
    `status`      varchar(32)         NOT NULL DEFAULT '' COMMENT '状态, NORMAL-创建未审批, AUDITING-审批中，APPROVAL-审批通过, REJECT-审批拒绝',
    `is_deleted`  tinyint(1)          NOT NULL DEFAULT '0' COMMENT '软删除,0-正常,1-删除',
    `update_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_bill_id` (`bill_id`),
    KEY `idx_item_id` (`item_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商品审批单关联清单';
-- 总部商品新增审状态
alter table `tbl_hq_product`
    add `audit_status` varchar(32) not null default '' comment '状态, NORMAL-创建未审批, AUDITING-审批中，APPROVAL-审批通过, REJECT-审批拒绝';
-- 总部商品新增审单号
-- 历史审批单留空
alter table `tbl_hq_product`
    add `audit_bill_id` bigint(20) unsigned not null default 0 comment '审批单ID';
-- 更新原有商品为审批通过
update `tbl_hq_product`
set `audit_status`='APPROVAL'
where 1;

-- 2019-09-03 商品服务费模板
CREATE TABLE `ic_service_fee_template`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `city_id`       int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '城市ID',
    `name`          varchar(100)        NOT NULL DEFAULT '' COMMENT '模板名称',
    `is_default`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否默认模板',
    `status`        varchar(16)         NOT NULL DEFAULT '' COMMENT '状态, ENABLE-启用，DISABLE-禁用',
    `charge_method` varchar(16)         NOT NULL DEFAULT '' COMMENT '计价方式: 固定-FIXED, 按量计费-AMOUNT',
    `fee`           decimal(8, 2)       NOT NULL DEFAULT '0' COMMENT '运费,当计价方式为固定运费时使用,单位-分',
    `init_amount`   int(11)             NOT NULL DEFAULT '0' COMMENT '首费数量',
    `init_fee`      decimal(8, 2)       NOT NULL DEFAULT '0' COMMENT '首费金额,单位-分',
    `incr_amount`   int(11)             NOT NULL DEFAULT '0' COMMENT '增费数量',
    `incr_fee`      decimal(8, 2)       NOT NULL DEFAULT '0' COMMENT '增费金额,单位-分',
    `is_deleted`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '软删除,0-正常,1-删除',
    `operator_id`   int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '最后操作人 ID，必填',
    `operator`      varchar(128)        NOT NULL DEFAULT '' COMMENT '最后操作人名字，必填',
    `update_time`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_city_id` (`city_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1 COMMENT ='商品服务费模板';

CREATE TABLE `ic_product_service_fee`
(
    `id`              bigint(20) unsigned             NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `template_id`     bigint(20) unsigned DEFAULT '0' NOT NULL COMMENT '商品服务费模板ID',
    `city_id`         int(10) unsigned    DEFAULT '0' NOT NULL COMMENT '城市ID',
    `city_product_id` int(10) unsigned    DEFAULT '0' NOT NULL COMMENT '城市商品ID',
    `operator_id`     int(10) unsigned    DEFAULT '0' NOT NULL COMMENT '最后操作人 ID，必填',
    `operator`        varchar(128)        DEFAULT ''  NOT NULL COMMENT '最后操作人名字，必填',
    `update_time`     timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`     timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_template_id` (`template_id`),
    KEY `idx_city_product_id` (`city_product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1 COMMENT ='商品服务费模板关联关系';

-- 初始化服务费模板
INSERT INTO `ic_service_fee_template`
(`id`, `city_id`, `name`, `is_default`, `status`, `charge_method`, `fee`, `init_amount`, `init_fee`, `incr_amount`,
 `incr_fee`, `is_deleted`, `operator_id`, `operator`, `update_time`, `create_time`)
VALUES (1, 30, '小规格服务费', 1, 'ENABLE', 'AMOUNT', 0.00, 1, 2.00, 1, 2.00, 0, 0, 'INIT', now(), now());

-- 初始化商品服务费关联关系
INSERT INTO `ic_product_service_fee` (`template_id`, `city_id`, `city_product_id`, `operator_id`, `operator`,
                                      `update_time`, `create_time`)
VALUES (1, 32, 320064, 0, 'INIT', now(), now()),
       (1, 32, 320066, 0, 'INIT', now(), now()),
       (1, 32, 320069, 0, 'INIT', now(), now()),
       (1, 32, 320070, 0, 'INIT', now(), now());


--商品同步记录日志表
create table `ic_product_create_asy_log`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `request_id` varchar(100) NOT NULL DEFAULT '' COMMENT '请求Id',
  `item_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '城市商品id',
  `request_param_json` text NOT NULL DEFAULT '' COMMENT '同步商品请求参数',
  `after_convert_request_param_json` text NOT NULL DEFAULT '' COMMENT '同步商品请求参数',
  `asy_status` int(10) not null default '-1' COMMENT '同步响应状态',
  `async_detail_string` text not null default '' COMMENT '同步结果',
  `create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_city_item_id` (`request_id`,`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='新商品创建同步老商品表';

