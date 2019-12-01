package com.mallcai.bs.model;

import lombok.Data;

/**
 * @ClassName: ExportInventoryLine
 * @Description: 库存导出记录基本信息
 * @Author: zhangxuefeng
 * @Date: 2019/6/24 下午4:50
 * @Version: 1.0
 **/
@Data
public class ExportInventoryLine {
    private Integer id;
    private String cityName;
    private Integer cityProductId;
    private String productNo;
    private String hqProductName;
    private String showName;
    private Integer isShare;
    private Integer available;
    private Integer threshold;
    private Integer availableBase;
    private Integer warehouseId;
    private Integer storeId;
    private Integer classifyId;
}
