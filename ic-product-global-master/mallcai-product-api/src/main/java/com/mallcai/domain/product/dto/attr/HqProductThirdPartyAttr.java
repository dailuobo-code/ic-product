package com.mallcai.domain.product.dto.attr;

import com.mallcai.domain.product.dto.HqProductExtraAttrDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 第三方商家属性
 * @author wangshifeng
 * @date 2019-07-20 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HqProductThirdPartyAttr extends HqProductExtraAttrDTO {

  /**
   * 开始时间
   */
  private Integer startHour;

  /**
   * 结束时间
   */
  private Integer endHour;

  /**
   * 商家名称解释
   */
  private String productDesc;

}
