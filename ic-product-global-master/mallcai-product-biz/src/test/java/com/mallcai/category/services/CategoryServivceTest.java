package com.mallcai.category.services;

import com.google.common.collect.Lists;
import com.mallcai.BaseTransactionalTest;
import com.mallcai.api.category.CategoryService;
import com.mallcai.api.category.front.MgrFrontCategoryAssociatedService;
import com.mallcai.api.category.front.MgrFrontCategoryService;
import com.mallcai.biz.category.manager.HqFcatCacheManager;
import com.mallcai.common.ICResponse;
import com.mallcai.domain.category.dto.CategoryDTO;
import com.mallcai.domain.category.front.request.FrontCategoryAssociatedConfigurationQueryRequest;
import com.mallcai.domain.category.front.request.FrontCategoryAssociatedItemRequest;
import com.mallcai.domain.category.front.request.FrontCategoryAssociatedSaveRequest;
import com.mallcai.domain.category.front.request.FrontCategoryQueryRequest;
import com.mallcai.domain.category.request.CategoryEditRequest;
import com.mallcai.domain.category.request.CategoryQueryRequest;
import com.mallcai.domain.category.request.PageCategoryQeueryRequest;
import com.mallcai.domain.enums.OperationEnum;
import com.mallcai.domain.enums.ResponseCodeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class CategoryServivceTest  extends BaseTransactionalTest {

    @Autowired
    private CategoryService categoryService;

@Resource
    private MgrFrontCategoryService mgrFrontCategoryService;

    @Resource
    private HqFcatCacheManager hqFcatCacheManager;
    @Resource
    private MgrFrontCategoryAssociatedService mgrFrontCategoryAssociatedService;

    @Test
    @Transactional
    public void testEditCategory(){
        CategoryEditRequest request = new CategoryEditRequest();
        /*ICResponse<Boolean> booleanICResponse = categoryService.editCategory(request);
        Assert.assertEquals(booleanICResponse.getCode(), ResponseCodeEnum.INVALID_OPERATION.getCode());
        request.setOperationEnum(OperationEnum.EDIT);
        ICResponse<Boolean> editWithOutCategoryId = categoryService.editCategory(request);
        Assert.assertEquals(editWithOutCategoryId.getCode(),ResponseCodeEnum.INVALID_PARAM.getCode());

        request.setClassifyName("test1");
        request.setClassifyNo("998");
        request.setCreateUserId(1);
        request.setUpdateUserId(1);
        request.setFatherId(0);
        request.setLevel(1);
        request.setPickupOrder(101);
        request.setShowOrder(101);
        request.setOperationEnum(OperationEnum.ADD);
        //新增操作
        ICResponse<Boolean> editCategory = categoryService.editCategory(request);*/

        //修改ClassifyNo
        CategoryEditRequest classifyNoEditRequest= new CategoryEditRequest();
        classifyNoEditRequest.setClassifyId(request.getClassifyId());
        classifyNoEditRequest.setOperationEnum(OperationEnum.EDIT);
        classifyNoEditRequest.setClassifyId(3);
        classifyNoEditRequest.setClassifyName("小青菜test");
        classifyNoEditRequest.setUpdateUserId(1);
        ICResponse<Boolean> editClassifyNo = categoryService.editCategory(classifyNoEditRequest);
        Assert.assertEquals(editClassifyNo.getData(),true);
        ICResponse<CategoryDTO> editResult = categoryService.loadCategoryById(classifyNoEditRequest.getClassifyId());
       // Assert.assertEquals(editCategory.getData(),true);
        Assert.assertEquals(editResult.getData().getClassifyNo(),classifyNoEditRequest.getClassifyNo());

        //修改ClassifyName
        CategoryEditRequest classifyNameEditRequest= new CategoryEditRequest();
        classifyNameEditRequest.setClassifyId(request.getClassifyId());
        classifyNameEditRequest.setClassifyName("ts111");
        classifyNameEditRequest.setOperationEnum(OperationEnum.EDIT);
        editClassifyNo = categoryService.editCategory(classifyNameEditRequest);
        Assert.assertEquals(editClassifyNo.getData(),true);
        editResult = categoryService.loadCategoryById(classifyNameEditRequest.getClassifyId());
       // Assert.assertEquals(editCategory.getData(),true);
        Assert.assertEquals(editResult.getData().getClassifyName(),classifyNameEditRequest.getClassifyName());


        //修改ShowOrder
        CategoryEditRequest showOrderEditRequest= new CategoryEditRequest();
        showOrderEditRequest.setClassifyId(request.getClassifyId());
        showOrderEditRequest.setShowOrder(878);
        showOrderEditRequest.setOperationEnum(OperationEnum.EDIT);
        editClassifyNo = categoryService.editCategory(showOrderEditRequest);
        Assert.assertEquals(editClassifyNo.getData(),true);
        editResult = categoryService.loadCategoryById(showOrderEditRequest.getClassifyId());
       // Assert.assertEquals(editCategory.getData(),true);
        Assert.assertEquals(editResult.getData().getShowOrder(),showOrderEditRequest.getShowOrder());
    }


    @Test
    public void testListCategory()throws Exception{
        CategoryQueryRequest request = new CategoryQueryRequest();
       /* ICResponse<List<CategoryDTO>> nullRequestResult= categoryService.listCategory(request);
        Assert.assertEquals(nullRequestResult.getCode(),ResponseCodeEnum.INVALID_PARAM.getCode());*/
        //父Id查询
        request.setFatherId(437);
        ICResponse<List<CategoryDTO>> listByParentId = categoryService.listCategory(request);
        Assert.assertEquals(listByParentId.getCode(),ResponseCodeEnum.SUCCESS.getCode());
        Assert.assertFalse(CollectionUtils.isEmpty(listByParentId.getData()));
        Assert.assertEquals(7, listByParentId.getData().size());

        CategoryQueryRequest fuzzyQuery = new CategoryQueryRequest();
        fuzzyQuery.setClassifyName("肉质");
        ICResponse<List<CategoryDTO>> fuzzyListResult = categoryService.listCategory(fuzzyQuery);
        Assert.assertEquals(fuzzyListResult.getCode(),ResponseCodeEnum.SUCCESS.getCode());
        Assert.assertFalse(CollectionUtils.isEmpty(fuzzyListResult.getData()));
        Assert.assertEquals(1, fuzzyListResult.getData().size());
        fuzzyListResult.getData().forEach(t->{
            Assert.assertTrue(t.getClassifyName().contains("肉质"));
        });

        fuzzyQuery.setClassifyName("果类");
        fuzzyListResult = categoryService.listCategory(fuzzyQuery);
        Assert.assertEquals(fuzzyListResult.getCode(),ResponseCodeEnum.SUCCESS.getCode());
        Assert.assertFalse(CollectionUtils.isEmpty(fuzzyListResult.getData()));
        fuzzyListResult.getData().forEach(t->{
            Assert.assertTrue(t.getClassifyName().contains("果类"));
        });
    }

    @Test
    public void testPageListCategory()throws Exception{
        PageCategoryQeueryRequest request  = new PageCategoryQeueryRequest();
        request.setCurrentPage(1);
        request.setPageSize(40);
        request.setLevel(2);
        request.setParentId(556);
        ICResponse<List<CategoryDTO>> categoryPageList = categoryService.categoryPageList(request);
        Assert.assertEquals(categoryPageList.getCode(),ResponseCodeEnum.SUCCESS.getCode());
        Assert.assertFalse(CollectionUtils.isEmpty(categoryPageList.getData()));
        Assert.assertTrue(categoryPageList.getData().size()>0);
        Assert.assertEquals(5, categoryPageList.getData().size());
        Assert.assertEquals(86, categoryPageList.getPage().getTotalNumber());

        request.setLevel(2);
        categoryPageList = categoryService.categoryPageList(request);
        Assert.assertTrue(categoryPageList.getData().size()>0);
        Assert.assertEquals(5, categoryPageList.getData().size());
        Assert.assertEquals(72, categoryPageList.getPage().getTotalNumber());

        request.setLevel(2);
        request.setClassifyName("果类");
        categoryPageList = categoryService.categoryPageList(request);
        Assert.assertTrue(categoryPageList.getData().size()>0);
        Assert.assertEquals(5, categoryPageList.getData().size());
        Assert.assertEquals(8, categoryPageList.getPage().getTotalNumber());

    }

    @Test
    public void testFindByCategoryIdList()throws Exception{
        List<Integer> categoryIds = Lists.newArrayList(437, 438, 439);
        ICResponse<List<CategoryDTO>> byCategoryIdList = categoryService.findByCategoryIdList(categoryIds);
        Assert.assertTrue(byCategoryIdList.getCode().equals(200));
        Assert.assertTrue(byCategoryIdList.getData().size()==3);
        byCategoryIdList.getData().forEach(t->{
            if(t.getClassifyId()==437){
                Assert.assertTrue(t.getFatherName()==null);
            }else {
                Assert.assertTrue(t.getFatherName()!=null);
                System.out.println(t.getFatherName());
            }
        });

    }


    @Test
   // @Transactional
    public void testDelCategory(){
        List<Integer> categoryIds = Lists.newArrayList(1336, 1337, 1338);
        ICResponse<Boolean> booleanICResponse = categoryService.delCategoryByIdList(categoryIds);
        Assert.assertTrue(booleanICResponse.getData());
    }
    @Test
    public void getCategoryList(){
        FrontCategoryQueryRequest query=new FrontCategoryQueryRequest();
        query.setEffectiveStatus(2);
        //query.setAssociatedStatus(1);
        mgrFrontCategoryService.getCategoryList(query);
    }

    @Test
    public void testWriteHqFcatAssociateItemToCache(){
        FrontCategoryQueryRequest query=new FrontCategoryQueryRequest();
        query.setEffectiveStatus(2);
        hqFcatCacheManager.writeHqFcatAssociateItemToCache(4L);
    }

    @Test
    public void testSaveAssociated(){
        FrontCategoryAssociatedSaveRequest request=new FrontCategoryAssociatedSaveRequest();
        request.setCategoryId(610L);
        request.setAssociatedType(2);
        request.setOperatorId(0);
        List<FrontCategoryAssociatedItemRequest> associatedItemRequestList = new ArrayList<>();
        FrontCategoryAssociatedItemRequest itemRequest=new FrontCategoryAssociatedItemRequest();
        itemRequest.setId("21640");
        itemRequest.setSort(1);
        associatedItemRequestList.add(itemRequest);
        request.setAssociatedItemRequestList(associatedItemRequestList);
        mgrFrontCategoryAssociatedService.saveAssociated(request);
    }

    @Test
    public void testInitCategory(){
        //ICResponse icResponse=mgrFrontCategoryService.initCategory();
        FrontCategoryAssociatedConfigurationQueryRequest request=new FrontCategoryAssociatedConfigurationQueryRequest();
        request.setCategoryId(614L);
        mgrFrontCategoryAssociatedService.getAssociateConfigurationList(request);
    }

}
