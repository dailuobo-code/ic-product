package com.mallcai.api.product.backend;

import com.mallcai.common.ICResponse;
import com.mallcai.domain.product.dto.attr.HqProductDeliveryHomeAttr;
import com.mallcai.domain.product.dto.ProductDTO;
import com.mallcai.domain.product.request.*;

import java.util.List;
import java.util.Map;

public interface MgrProductService {

    /**
     * 添加商品
     *
     * @param addProductRequest
     */
    ICResponse<Boolean> addProduct(AddProductRequest addProductRequest);

    /**
     * 获取商品列表
     *
     * @param getProductListRequest
     */
    ICResponse<List<ProductDTO>> getProductList(GetProductListRequest getProductListRequest);

    /**
     * 判断商品是否存在
     *
     * @param existedProductRequest
     */
    ICResponse<Boolean> existedProduct(ExistedProductRequest existedProductRequest);

    /**
     * 修改商品名称
     *
     * @param modifyProductNameRequest
     */
    ICResponse<Boolean> modifyProductName(ModifyProductNameRequest modifyProductNameRequest);

    /**
     * 编辑商品
     *
     * @param modifyProductRequest
     */
    ICResponse<Boolean> modifyProduct(ModifyProductRequest modifyProductRequest);

    /**
     * 本市化标记
     * @param localizeRequest
     * @return
     */
    ICResponse<Boolean> localizeProduct(LocalizeProductRequest localizeRequest);

    /**
     * 编辑商品状态
     *
     * @param modifyProductStatusRequest
     */
    ICResponse<Boolean> modifyProductStatus(ModifyProductStatusRequest modifyProductStatusRequest);

    /**
     * 删除商品
     *
     * @param productIds
     */
    ICResponse<Boolean> delete(List<Integer> productIds);

    /**
     * 获取商品
     *
     * @param hqProductId
     * @return
     */
    ICResponse<ProductDTO> getProduct(Integer hqProductId);


    ICResponse<HqProductVo> addHqProduct(HqProductVo hqProductVo);


    /**
     * 导入拼多多商品特有属性和映射关系
     * @param pddProductAttr
     * @return
     */
    ICResponse<PddProductAttr> addPddProductAttr(PddProductAttr pddProductAttr);


    /**
     * 根据ProductNO获取拼多多商品映射关系
     * @param goodIds
     * @return
     */
    ICResponse<List<PddProductAttr>> getPddProductAttrByPddGoodIds(List<String> goodIds);

    /**
     * 根据商品编号列表查询总部商品
     * @param productNoList 商品编号
     * @return
     */
    ICResponse<List<ProductDTO>> queryProductByProductNos(List<String> productNoList);

    ICResponse<Boolean> updateServiceFeeTemplate(ModifyServiceFeeTemplateRequest request);

    /**
     * @param request keyword  productNo 或者 productName 模糊查询总部商品信息
     * @return
     */
    ICResponse<List<ProductDTO>> queryProductByKeyWord(PagingHqProductSearchQueryRequest request);

    /**
     * 返回商品属性列表
     */
    ICResponse<Map<Integer, HqProductDeliveryHomeAttr>> getHqProductDeliverExtraAttrs(List<Integer> hqProductIds);

}
