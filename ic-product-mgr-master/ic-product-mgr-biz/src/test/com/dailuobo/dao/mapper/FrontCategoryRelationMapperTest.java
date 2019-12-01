package dailuobo.dao.mapper;

import com.dailuobo.BaseTest;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class FrontCategoryRelationMapperTest extends BaseTest {

    @Autowired
    private FrontCategoryRelationMapper frontCategoryRelationMapper;

    @Test
    public void create() {
        FrontCategoryRelationDO relationDO = new FrontCategoryRelationDO();
        relationDO.setCreateUserId(188);
        relationDO.setCityId(30);
        relationDO.setEffectScopeIds("1,23,45,67");
        relationDO.setEffectScope(1);
        relationDO.setRelationType(123123);
        relationDO.setUpdateUserId(126);
        relationDO.setFrontCategoryId(BigInteger.valueOf(8));
        Integer integer = frontCategoryRelationMapper.create(relationDO);
        Assert.assertTrue(integer > 0);

    }

    @Test
    public void update() {
        FrontCategoryRelationDO relationDO = new FrontCategoryRelationDO();
        relationDO.setId(BigInteger.valueOf(1));
        relationDO.setCreateUserId(188);
        relationDO.setEffectScopeIds("1,23,45,67");
        relationDO.setEffectScope(1);
        relationDO.setRelationType(123123);
        relationDO.setUpdateUserId(126);
        relationDO.setFrontCategoryId(BigInteger.valueOf(8));
        Integer update = frontCategoryRelationMapper.update(relationDO);
        Assert.assertTrue(update > 0);
    }

    @Test
    public void loadFCategoryRelation() {
        BigInteger frontCategoryId = BigInteger.valueOf(8);
        Integer cityId = 30;
        Integer relatedType = 1;
        frontCategoryRelationMapper.loadFCategoryRelation(frontCategoryId,  1, cityId);
    }
}