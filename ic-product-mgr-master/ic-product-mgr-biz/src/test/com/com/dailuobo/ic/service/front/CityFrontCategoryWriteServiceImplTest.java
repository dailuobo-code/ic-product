package com.dailuobo.ic.service.front;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.category.front.ICityFrontCategoryWriteService;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedBCategory;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedCityProduct;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationDeleteRequest;
import com.dailuobo.ic.api.request.category.front.FrontCategoryRelationsCreateRequest;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class CityFrontCategoryWriteServiceImplTest extends BaseTest {
    @Autowired
    private ICityFrontCategoryWriteService cityFrontCategoryWriteService;

    @Test
    public void saveFCategoryRelation() {
        FrontCategoryRelationsCreateRequest relationsCreateRequest = new FrontCategoryRelationsCreateRequest();
        relationsCreateRequest.setCityId(30);
        relationsCreateRequest.setEffectEntityIdList(Lists.newArrayList(6, 9, 3, 4, 19));
        relationsCreateRequest.setEffectScope(1);
        relationsCreateRequest.setFrontCategoryId(BigInteger.valueOf(4));
        relationsCreateRequest.setRelationType(2);
        relationsCreateRequest.setOperatorId(1);


       /* FCategoryRelatedBCategory fCategoryRelatedBCategory = new FCategoryRelatedBCategory();
        fCategoryRelatedBCategory.setCategoryId(10);
        fCategoryRelatedBCategory.setDisplayOrder(10);
        fCategoryRelatedBCategory.setOperatorId(1);

        FCategoryRelatedBCategory fCategoryRelatedBCategory1 = new FCategoryRelatedBCategory();
        fCategoryRelatedBCategory1.setCategoryId(12);
        fCategoryRelatedBCategory1.setDisplayOrder(18);
        fCategoryRelatedBCategory1.setOperatorId(1);

        List<FCategoryRelatedBCategory> relatedBCategoryList = Lists.newArrayList();
        relatedBCategoryList.add(fCategoryRelatedBCategory1);*/


       List<FCategoryRelatedCityProduct> relatedBCategoryList = Lists.newArrayList();
        FCategoryRelatedCityProduct relatedCityProduct = new FCategoryRelatedCityProduct();
        relatedCityProduct.setCityProductId(3);
        relatedCityProduct.setDisPlayOrder(88);
        relatedCityProduct.setProductNo("6");
        relatedCityProduct.setCityId(30);
        relatedBCategoryList.add(relatedCityProduct);


        FCategoryRelatedCityProduct relatedCityProduct1 = new FCategoryRelatedCityProduct();
        relatedCityProduct1.setCityProductId(6);
        relatedCityProduct1.setDisPlayOrder(85);
        relatedCityProduct1.setProductNo("999");
        relatedCityProduct.setCityId(30);
        relatedBCategoryList.add(relatedCityProduct1);

        relationsCreateRequest.setRelatedCityProductList(relatedBCategoryList);

        String str ="{\"cityId\":30,\"effectEntityIdList\":[3,8,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60,63,66,69,72,75,78,81,84,87,90,93,96,99,102,105,110,113,116,119,122,125,128,131,134,137,140,142,144,147,150],\"effectEntityIdListStr\":\"3,8,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60,63,66,69,72,75,78,81,84,87,90,93,96,99,102,105,110,113,116,119,122,125,128,131,134,137,140,142,144,147,150\",\"effectScope\":2,\"frontCategoryId\":4,\"operatorId\":1,\"relatedBCategoryList\":[{\"categoryId\":562,\"displayOrder\":1,\"operatorId\":1},{\"categoryId\":560,\"displayOrder\":1,\"operatorId\":1},{\"categoryId\":561,\"displayOrder\":1,\"operatorId\":1}],\"relationType\":1}";
        FrontCategoryRelationsCreateRequest relationsCreateRequest1 = JSON.parseObject(str, FrontCategoryRelationsCreateRequest.class);

        ICResponse<Boolean> booleanICResponse = cityFrontCategoryWriteService.saveFCategoryRelation(relationsCreateRequest1);
        Assert.assertTrue(booleanICResponse.isSuccess());
    }

    @Test
    public void delFCategoryRelation() {
        FCategoryRelationDeleteRequest relationDeleteRequest =new FCategoryRelationDeleteRequest();
        relationDeleteRequest.setRelationId(BigInteger.valueOf(14));
        relationDeleteRequest.setCityId(30);
        relationDeleteRequest.setOperatorId(1);
        ICResponse<Boolean> booleanICResponse = cityFrontCategoryWriteService.delFCategoryRelation(relationDeleteRequest);
        System.out.println(booleanICResponse);
    }
}