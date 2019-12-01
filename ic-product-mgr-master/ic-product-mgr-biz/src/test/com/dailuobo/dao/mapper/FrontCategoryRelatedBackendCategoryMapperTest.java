package dailuobo.dao.mapper;

import com.dailuobo.BaseTest;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedBackendCategoryDO;
import com.google.common.collect.Lists;
import dailuobo.dao.mapper.FrontCategoryRelatedBackendCategoryMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class FrontCategoryRelatedBackendCategoryMapperTest extends BaseTest {

    @Autowired
    private FrontCategoryRelatedBackendCategoryMapper frontCategoryRelatedBackendCategoryMapper;

    @Test
    @Transactional
    public void create() {

        FCategoryRelatedBackendCategoryDO categoryDO = new FCategoryRelatedBackendCategoryDO();
        categoryDO.setRelationId(BigInteger.valueOf(10));
        categoryDO.setDisplayOrder(100);
        categoryDO.setBackendCategoryId(3);
        categoryDO.setCreateUserId(1);
        categoryDO.setUpdateUserId(10);
        categoryDO.setCityId(30);
        int createId = frontCategoryRelatedBackendCategoryMapper.create(categoryDO);
        Assert.assertTrue(createId > 0);
        System.out.println(categoryDO);
    }


    @Test
    @Transactional
    public void batchCreateTest() throws Exception {
        List<FCategoryRelatedBackendCategoryDO> batch = Lists.newArrayList();
        FCategoryRelatedBackendCategoryDO categoryDO = new FCategoryRelatedBackendCategoryDO();
        categoryDO.setRelationId(BigInteger.valueOf(11));
        categoryDO.setDisplayOrder(87);
        categoryDO.setBackendCategoryId(3);
        categoryDO.setCreateUserId(1);
        categoryDO.setUpdateUserId(10);
        categoryDO.setCityId(30);

        batch.add(categoryDO);
        FCategoryRelatedBackendCategoryDO categoryDO1 = new FCategoryRelatedBackendCategoryDO();
        categoryDO1.setRelationId(BigInteger.valueOf(12));
        categoryDO1.setDisplayOrder(86);
        categoryDO1.setBackendCategoryId(3);
        categoryDO1.setCreateUserId(1);
        categoryDO1.setUpdateUserId(10);
        categoryDO1.setCityId(30);
        batch.add(categoryDO1);
        int createId = frontCategoryRelatedBackendCategoryMapper.batchCreate(batch);
        Assert.assertTrue(createId > 0);
        System.out.println(categoryDO);
    }


    @Test
    @Transactional
    public void update() {
        FCategoryRelatedBackendCategoryDO categoryDO = new FCategoryRelatedBackendCategoryDO();
        categoryDO.setCityId(30);
        categoryDO.setId(BigInteger.valueOf(1));
        categoryDO.setRelationId(BigInteger.valueOf(10));
        categoryDO.setDisplayOrder(99);
        int update = frontCategoryRelatedBackendCategoryMapper.update(categoryDO);
        Assert.assertTrue(update > 0);
    }

    @Test
    @Transactional
    public void loadRelatedBackendCategory() {

        FCategoryRelatedBackendCategoryDO categoryDO = frontCategoryRelatedBackendCategoryMapper.loadRelatedBackendCategory(BigInteger.valueOf(1));
        Assert.assertNotNull(categoryDO);
    }

    @Test
    @Transactional
    public void listFCategoryRelatedBackendCategory() {
        BigInteger relationId = BigInteger.valueOf(10);
        Integer cityId = 30;
        List<FCategoryRelatedBackendCategoryDO> fCategoryRelatedBackendCategoryDOS = frontCategoryRelatedBackendCategoryMapper.listFCategoryRelatedBackendCategory(relationId);

        Assert.assertNotNull(fCategoryRelatedBackendCategoryDOS);
    }

    @Test
    @Transactional
    public void listRelatedBackendCategoryByFrontCategoryRelatedIdList() {
        List<BigInteger> values = Lists.newArrayList(BigInteger.valueOf(10), BigInteger.valueOf(100));
        Integer cityId = 30;
        List<FCategoryRelatedBackendCategoryDO> fCategoryRelatedBackendCategoryDOS = frontCategoryRelatedBackendCategoryMapper.listRelatedBackendCategoryByFrontCategoryRelatedIdList(values);
        Assert.assertNotNull(fCategoryRelatedBackendCategoryDOS);
        System.out.println(fCategoryRelatedBackendCategoryDOS);
    }
}