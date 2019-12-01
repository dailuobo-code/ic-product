package dailuobo.dao.mapper;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.ic.domain.dao.model.category.front.FCategoryRelatedCityProductDO;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FrontCategoryRelatedCityProductMapperTest extends BaseTest {

    @Autowired
    private FrontCategoryRelatedCityProductMapper frontCategoryRelatedCityProductMapper;

    @Test
    public void create() {
        FCategoryRelatedCityProductDO fCategoryRelatedCityProductDO = new FCategoryRelatedCityProductDO();
        fCategoryRelatedCityProductDO.setCityId(30);
        fCategoryRelatedCityProductDO.setCityProductId(3);
        fCategoryRelatedCityProductDO.setDisplayOrder(108);
        fCategoryRelatedCityProductDO.setCreateUserId(888);
        fCategoryRelatedCityProductDO.setProductNo("445864");
        fCategoryRelatedCityProductDO.setRelationId(BigInteger.valueOf(1));
        fCategoryRelatedCityProductDO.setUpdateUserId(888);
        int i = frontCategoryRelatedCityProductMapper.create(fCategoryRelatedCityProductDO);
        Assert.assertTrue(i > 0);
    }


    @Test
    public void batchCreate() {
        List<FCategoryRelatedCityProductDO> list = new ArrayList<>();
        FCategoryRelatedCityProductDO fCategoryRelatedCityProductDO = new FCategoryRelatedCityProductDO();
        fCategoryRelatedCityProductDO.setCityId(30);
        fCategoryRelatedCityProductDO.setCityProductId(3);
        fCategoryRelatedCityProductDO.setCreateUserId(888);
        fCategoryRelatedCityProductDO.setDisplayOrder(109);
        fCategoryRelatedCityProductDO.setProductNo("445864");
        fCategoryRelatedCityProductDO.setRelationId(BigInteger.valueOf(1));
        fCategoryRelatedCityProductDO.setUpdateUserId(888);


        FCategoryRelatedCityProductDO fCategoryRelatedCityProductDO1 = new FCategoryRelatedCityProductDO();
        fCategoryRelatedCityProductDO1.setCityId(30);
        fCategoryRelatedCityProductDO1.setCityProductId(3);
        fCategoryRelatedCityProductDO1.setCreateUserId(888);
        fCategoryRelatedCityProductDO1.setDisplayOrder(111);
        fCategoryRelatedCityProductDO1.setProductNo("445864");
        fCategoryRelatedCityProductDO1.setRelationId(BigInteger.valueOf(1));
        fCategoryRelatedCityProductDO1.setUpdateUserId(888);

        list.add(fCategoryRelatedCityProductDO);
        list.add(fCategoryRelatedCityProductDO1);
        int i = frontCategoryRelatedCityProductMapper.batchCreate(list);

        Assert.assertTrue(i > 0);
    }

    @Test
    public void update() {
        FCategoryRelatedCityProductDO fCategoryRelatedCityProductDO = new FCategoryRelatedCityProductDO();
        fCategoryRelatedCityProductDO.setId(BigInteger.valueOf(1));
        fCategoryRelatedCityProductDO.setCityProductId(3);
        fCategoryRelatedCityProductDO.setCreateUserId(666);
        fCategoryRelatedCityProductDO.setProductNo("4586544");
        fCategoryRelatedCityProductDO.setRelationId(BigInteger.valueOf(1));
        fCategoryRelatedCityProductDO.setUpdateUserId(777);

        int update = frontCategoryRelatedCityProductMapper.update(fCategoryRelatedCityProductDO);
        assertTrue(update > 0);

    }

    @Test
    public void load() {
        FCategoryRelatedCityProductDO load = frontCategoryRelatedCityProductMapper.load(BigInteger.valueOf(1));
        Assert.assertNotNull(load);
        System.out.println(load);
    }

    @Test
    public void listFCategoryRelatedCityProductList() {
        BigInteger relationId = BigInteger.valueOf(1);
        Integer cityId = 30;
        List<FCategoryRelatedCityProductDO> fCategoryRelatedCityProductDOS = frontCategoryRelatedCityProductMapper.listFCategoryRelatedCityProduct(relationId);
        Assert.assertNotNull(fCategoryRelatedCityProductDOS);
    }

    @Test
    public void listRelatedCityProductListByRelationIdList() {

        List<BigInteger> values = Lists.newArrayList(BigInteger.valueOf(10), BigInteger.valueOf(100));
        Integer cityId = 30;
        List<FCategoryRelatedCityProductDO> fCategoryRelatedCityProductDOS = frontCategoryRelatedCityProductMapper.listRelatedCityProductListByRelationIdList(values, cityId);
        Assert.assertNotNull(fCategoryRelatedCityProductDOS);
        System.out.println(JSON.toJSONString(fCategoryRelatedCityProductDOS));

    }


    @Test
    public void testDelete(){
        Integer integer = frontCategoryRelatedCityProductMapper.deleteRelationByRelationId(BigInteger.valueOf(14), 30, 1);
        System.out.println(integer);
    }
}