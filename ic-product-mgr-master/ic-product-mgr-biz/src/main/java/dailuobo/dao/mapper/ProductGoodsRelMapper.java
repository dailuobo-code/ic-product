package dailuobo.dao.mapper;

import com.dailuobo.ic.domain.dao.model.goods.ProductGoodsRelDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品货品关联mapper
 *
 * @author lgh
 * @date 2019/9/29
 */
public interface ProductGoodsRelMapper {

    /**
     * 批量插入
     *
     * @param productGoodsRelDOS
     */
    void batchSave(List<ProductGoodsRelDO> productGoodsRelDOS);


    /**
     * 批量删除
     *
     * @param id
     */
    void del(@Param("cityId")Integer cityId,@Param("id")Integer id);

    /**
     * 根据城市商品id查询
     *
     * @param id
     */
    ProductGoodsRelDO queryById(@Param("id")Integer id,@Param("cityId")Integer cityId);

    /**
     * 根据城市商品id查询
     *
     * @param cityProductId
     */
    List<ProductGoodsRelDO> queryByCityProductId(@Param("cityId")Integer cityId, @Param("cityProductId")Integer cityProductId);

    /**
     * 根据城市商品id查询
     *
     * @param cityProductIds
     */
    List<ProductGoodsRelDO> batchQueryByCityProductIds(@Param("cityId")Integer cityId, @Param("cityProductIds")List<Integer> cityProductIds);

    /**
     * 根据货品编号查询
     *
     * @param goodsNo
     */
    List<ProductGoodsRelDO> queryByGoodsNo(@Param("cityId")Integer cityId, @Param("goodsNo")String goodsNo);


    /**
     * 批量插入
     *
     * @param productGoodsRelDOS
     */
    void batchSaveHis(List<ProductGoodsRelDO> productGoodsRelDOS);

}
