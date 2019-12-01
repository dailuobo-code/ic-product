package dailuobo.dao.mapper;

import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedCityProductDO;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 *
 */
public interface FrontCategoryRelatedCityProductMapper {

    /**
     * 创建前台类目关联城市商品
     *
     * @param fCategoryRelatedCityProductDO
     * @return
     */
    int create(FCategoryRelatedCityProductDO fCategoryRelatedCityProductDO);

    int batchCreate(@Param("list") List<FCategoryRelatedCityProductDO> list);

    /**
     * 更新前台类目关联城市商品
     *
     * @param fCategoryRelatedCityProductDO
     * @return
     */
    int update(FCategoryRelatedCityProductDO fCategoryRelatedCityProductDO);

    /**
     * 根据主键id查询关联数据
     *
     * @param id 主键Id
     * @return
     */
    FCategoryRelatedCityProductDO load(BigInteger id);

    /**
     * 前台类目下关联的所有城市商品
     *
     * @param relationId 关联Id
     * @return
     */
    List<FCategoryRelatedCityProductDO> listFCategoryRelatedCityProduct(@Param("relationId") BigInteger relationId);

    List<FCategoryRelatedCityProductDO> listRelatedCityProductListByRelationIdList(@Param("list") List<BigInteger> relationIdList, @Param("cityId")Integer cityId);

    Integer deleteRelationByRelationId(@Param("relationId") BigInteger relationId, @Param("cityId") Integer cityId, @Param("operatorId") Integer operatorId);
}
