package com.dailuobo.api.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: HqProductCheckVo
 * @Description: 商家商品批量导入HqProduct核验结果返回对象
 * @Author: zhangxuefeng
 * @Date: 2019/8/29 下午4:51
 * @Version: 1.0
 **/
@Data
public class HqProductCheckVo implements Serializable {
    private List<String> duplicateHqProductNOs;
    private List<String> duplicateHqProductNames;
}
