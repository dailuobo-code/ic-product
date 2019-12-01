package dailuobo.dao.mapper;

import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface FrontCategoryRelatedBackendCategoryMapper {
    /**
     * 创建
     *
     * @param fCategoryRelatedBackendCategoryDO
     * @return
     */
    int create(FCategoryRelatedBackendCategoryDO fCategoryRelatedBackendCategoryDO);


    int batchCreate(List<FCategoryRelatedBackendCategoryDO> categoryRelatedBackendCategoryList);

    /**
     * 更新
     *
     * @param fCategoryRelatedBackendCategoryDO
     * @return
     */
    int update(FCategoryRelatedBackendCategoryDO fCategoryRelatedBackendCategoryDO);

    /**
     * 加载单个前台类目关联的后台类目
     *
     * @param id 主键Id
     * @return
     */
    FCategoryRelatedBackendCategoryDO loadRelatedBackendCategory(BigInteger id);


    /**
     * @param relationId 关联关系Id
     * @return
     */
    List<FCategoryRelatedBackendCategoryDO> listFCategoryRelatedBackendCategory(@Param("relationId") BigInteger relationId);


    /**
     * @param list   关联关系Id列表
     * @return
     */
    List<FCategoryRelatedBackendCategoryDO> listRelatedBackendCategoryByFrontCategoryRelatedIdList(@Param("list") List<BigInteger> list);


    Integer deleteRelationByRelationId(@Param("relationId") BigInteger relationId, @Param("cityId") Integer cityId, @Param("operatorId") Integer operatorId);


}
