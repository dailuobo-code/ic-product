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
