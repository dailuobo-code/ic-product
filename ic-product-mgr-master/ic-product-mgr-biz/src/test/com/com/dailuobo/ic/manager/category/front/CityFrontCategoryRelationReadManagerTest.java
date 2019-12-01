package com.dailuobo.ic.manager.category.front;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.dailuobo.ic.domain.dao.model.category.front.FrontCategoryRelationDO;
import com.dailuobo.ic.dto.category.front.FCategoryRelatedBCategoryDTO;
import com.dailuobo.ic.dto.category.front.FCategoryRelationDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedCityProductDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedItemDTO;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class CityFrontCategoryRelationReadManagerTest extends BaseTest {

    List<BigInteger> relationIds = Lists.newArrayList(BigInteger.valueOf(4), BigInteger.valueOf(8));

    @Autowired
    private CityFrontCategoryRelationReadManager cityFrontCategoryRelationReadManager;

    @Test
    public void listFrontCategoryRelation() {

        List<FCategoryRelationDTO> relationDTOList = cityFrontCategoryRelationReadManager.listFrontCategoryRelation(BigInteger.valueOf(4), null, 30);
        System.out.println(relationDTOList);
    }

    @Test
    public void listFrontCategoryRelationByFrontCategoryIdList() {
        List<FCategoryRelationDTO> relationDTOList = cityFrontCategoryRelationReadManager.listFrontCategoryRelationByFrontCategoryIdList(Lists.newArrayList(BigInteger.valueOf(4), BigInteger.valueOf(8)), 30);
        System.out.println(relationDTOList);
    }

    @Test
    public void listFrontCategoryRelatedBackendCategory() {
        List<FCategoryRelatedBCategoryDTO> categoryRelatedBCategoryDTOList = cityFrontCategoryRelationReadManager.listFrontCategoryRelatedBackendCategory(BigInteger.valueOf(9));
        System.out.println(categoryRelatedBCategoryDTOList);
    }

    @Test
    public void listFrontCategoryRelatedCityProduct() {
        List<FrontCategoryRelatedCityProductDTO> frontCategoryRelatedCityProductDTOS = cityFrontCategoryRelationReadManager.listFrontCategoryRelatedCityProduct(BigInteger.valueOf(12));
        System.out.println(frontCategoryRelatedCityProductDTOS);
    }

    @Test
    public void listAllFrontCategoryRelations() {
        List<FrontCategoryRelatedItemDTO> frontCategoryRelatedItemDTOS = cityFrontCategoryRelationReadManager.listAllFrontCategoryRelations(30);
        System.out.println(frontCategoryRelatedItemDTOS);

    }

    @Test
    public void loadFrontCategoryAllRelation() {
        Integer cityId =30;
        List<FrontCategoryRelationDO> relationDOList = cityFrontCategoryRelationReadManager.loadFrontCategoryAllRelation(cityId);
        System.out.println(JSON.toJSONString(relationDOList));
    }
}