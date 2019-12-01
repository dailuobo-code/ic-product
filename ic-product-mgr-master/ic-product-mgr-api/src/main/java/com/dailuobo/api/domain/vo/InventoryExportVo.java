package com.dailuobo.api.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: InventoryExportVo
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/6/24 下午2:01
 * @Version: 1.0
 **/
@Data
public class InventoryExportVo implements Serializable {

    private Integer id;
    // 处理进度，取值范围 [0, 100]
    private Byte progress;
    // 处理状态  0、处理中，1、已完成
    private Byte status;
    // 触发人名称
    private Integer userId;
    // 生命文件名
    private String filename;
    // 下载的url
    private String downloadUrl;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
}
