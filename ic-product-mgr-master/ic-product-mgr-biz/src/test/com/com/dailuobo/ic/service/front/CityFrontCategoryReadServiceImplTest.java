package com.dailuobo.ic.service.front;

import com.dailuobo.BaseTest;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.category.front.ICityFrontCategoryReadService;
import com.dailuobo.ic.api.request.category.front.FCategoryRelatedContentQueryRequest;
import com.dailuobo.ic.api.request.category.front.FCategoryRelationQueryRequest;
import com.dailuobo.ic.dto.category.front.FCategoryRelatedBCategoryDTO;
import com.dailuobo.ic.dto.category.front.FCategoryRelationDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedCityProductDTO;
import com.dailuobo.ic.dto.category.front.FrontCategoryRelatedItemDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class CityFrontCategoryReadServiceImplTest extends BaseTest {

    @Autowired
    private ICityFrontCategoryReadService cityFrontCategoryReadService;

    @Test
    public void listFrontCategory() {

    }

    @Test
    public void listCityFrontCategoryRelations() {

        FCategoryRelationQueryRequest relationQueryRequest = new FCategoryRelationQueryRequest();
        relationQueryRequest.setLv1frontCategoryId(BigInteger.valueOf(4));
        relationQueryRequest.setCityId(30);
        ICResponse<List<FCategoryRelationDTO>> listICResponse = cityFrontCategoryReadService.listCityFrontCategoryRelations(relationQueryRequest);
        System.out.println(listICResponse);
        relationQueryRequest.setRelationType(1);
        ICResponse<List<FCategoryRelationDTO>> listICResponse1 = cityFrontCategoryReadService.listCityFrontCategoryRelations(relationQueryRequest);
        System.out.println(listICResponse1);
    }

    @Test
    public void listCityFrontCategoryRelatedBackendCategory() {
        FCategoryRelatedContentQueryRequest relatedContentQueryRequest = new FCategoryRelatedContentQueryRequest();
        relatedContentQueryRequest.setFrontCategoryId(BigInteger.valueOf(1));
        relatedContentQueryRequest.setRelationId(BigInteger.valueOf(4));
        relatedContentQueryRequest.setRelationType(1);
        relatedContentQueryRequest.setCityId(30);
        ICResponse<List<FCategoryRelatedBCategoryDTO>> listICResponse = cityFrontCategoryReadService.listCityFrontCategoryRelatedBackendCategory(relatedContentQueryRequest);
        System.out.println(listICResponse);
    }

    @Test
    public void listCityFrontCategoryRelatedCityProduct() {
        FCategoryRelatedContentQueryRequest relatedContentQueryRequest = new FCategoryRelatedContentQueryRequest();
        relatedContentQueryRequest.setRelationType(1);
        relatedContentQueryRequest.setRelationId(BigInteger.valueOf(1));
        relatedContentQueryRequest.setFrontCategoryId(BigInteger.valueOf(1));
        ICResponse<List<FrontCategoryRelatedCityProductDTO>> listICResponse = cityFrontCategoryReadService.listCityFrontCategoryRelatedCityProduct(relatedContentQueryRequest);
        System.out.println(listICResponse);
    }

    @Test
    public void listCityFrontCategoryRelatedItems() {
        FCategoryRelatedContentQueryRequest relatedContentQueryRequest = new FCategoryRelatedContentQueryRequest();
        //relatedContentQueryRequest.setFrontCategoryId(BigInteger.valueOf(1));
        relatedContentQueryRequest.setRelationId(BigInteger.valueOf(35));
        relatedContentQueryRequest.setRelationType(1);
        ICResponse<FrontCategoryRelatedItemDTO> frontCategoryRelatedItemDTOICResponse = cityFrontCategoryReadService.listCityFrontCategoryRelatedItems(relatedContentQueryRequest);
        System.out.println(frontCategoryRelatedItemDTOICResponse);
    }

    @Test
    public void listAllCityFrontCategoryRelatedItems() {
        ICResponse<List<FrontCategoryRelatedItemDTO>> listICResponse = cityFrontCategoryReadService.listAllCityFrontCategoryRelatedItems(30);
        System.out.println(listICResponse);
    }
}