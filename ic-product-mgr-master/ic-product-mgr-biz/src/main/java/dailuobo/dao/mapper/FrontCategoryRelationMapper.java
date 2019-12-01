package dailuobo.dao.mapper;

import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface FrontCategoryRelationMapper {
    Integer create(FrontCategoryRelationDO frontCategoryRelationDO);

    Integer update(FrontCategoryRelationDO frontCategoryRelationDO);

    List<FrontCategoryRelationDO> loadFCategoryRelation(@Param("frontCategoryId") BigInteger frontCategoryId, @Param("effectScope") Integer effectScope, @Param("cityId") Integer cityId);

    FrontCategoryRelationDO loadFCategoryRelationById(@Param("id") BigInteger relationId);

    List<FrontCategoryRelationDO> listFCategoryRelation(@Param("frontCategoryId") BigInteger frontCategoryId,
                                                        @Param("relationType") Integer relationType, @Param("cityId") Integer cityId);

    List<FrontCategoryRelationDO> listFrontCategoryRelationsByFrontCategoryIdList(@Param("frontCategoryIdList") List<BigInteger> frontCategoryId, @Param("cityId") Integer cityId);

    Integer deleteRelations(@Param("id") BigInteger id, @Param("frontCategoryId") BigInteger frontCategoryId, @Param("cityId") Integer cityId, @Param("operatorId") Integer operatorId);
}
