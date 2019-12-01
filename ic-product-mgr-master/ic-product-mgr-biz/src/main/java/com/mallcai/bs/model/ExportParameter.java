package com.mallcai.bs.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ExportParameter
 * @Description: 库存导出相关的基本参数
 * @Author: zhangxuefeng
 * @Date: 2019/6/24 下午4:32
 * @Version: 1.0
 **/
@Data
public class ExportParameter implements Serializable {
    private Integer exportId;
    private String filename;
    private List<Integer> cityIds;
    private List<Integer> classifyIds;

}
