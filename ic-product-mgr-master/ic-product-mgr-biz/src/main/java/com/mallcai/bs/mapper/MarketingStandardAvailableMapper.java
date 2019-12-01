package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.ProductAvailableChange;
import com.dailuobo.api.domain.vo.ProductAvailableChangeQueryVo;
import com.dailuobo.api.domain.vo.ProductAvailableChangeResponseVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 标品库存管理.
 * @author zhanghao
 * @date 2019/5/13
 */
public interface MarketingStandardAvailableMapper {

    /**
     * 新增记录.
     * @param productAvailableChange
     */
    void addProductAvailableChange(ProductAvailableChange productAvailableChange);

    /**
     * 修改记录.
     * @param productAvailableChange
     */
    void updateProductAvailableChange(ProductAvailableChange productAvailableChange);

    /**
     * 根据商品Id和时间查询记录.
     * @param cityProductId
     * @param execDate
     * @return
     */
    List<ProductAvailableChange> selectDataByProductIdAndDate(@Param("cityProductId") Integer cityProductId,@Param("execDate") Date execDate);

    /**
     * 通过ID查询数据.
     * @param id
     * @return
     */
    ProductAvailableChange selectDataById(@Param("id") Integer id);

    /**
     * 查询列表.
     * @param changeQueryVo
     * @return
     */
    List<ProductAvailableChangeResponseVo> selectProductAvailableChangeList(ProductAvailableChangeQueryVo changeQueryVo);
}
