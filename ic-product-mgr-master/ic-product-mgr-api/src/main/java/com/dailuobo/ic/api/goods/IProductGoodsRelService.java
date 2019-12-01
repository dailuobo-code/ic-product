package com.dailuobo.ic.api.goods;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.ic.api.request.goods.*;
import com.dailuobo.ic.api.vo.ProductGoodsRelVO;

import java.util.List;


/**
 * 商品货品关联关系管理服务
 * <b>商品 1:n 货品</b>
 * <b>服务无需灰度开关</b>
 *
 * @author lgh
 * @date 2019/9/29
 */
public interface IProductGoodsRelService {

    /**
     * 新增货品关联关系
     *
     * @param addProductGoodsRelRequest
     * @return
     */
    ICResponse addProductGoodsRel(AddProductGoodsRelRequest addProductGoodsRelRequest);

    /**
     * 根据城市商品id批量查询货品关联关系
     *
     * @param queryProductGoodsRelRequest
     * @return
     */
    ICResponse<List<ProductGoodsRelVO>> queryByCityProductId(QueryProductGoodsRelRequest queryProductGoodsRelRequest);


    /**
     * 根据货品编号查询关联关系
     * <b>货品中心校验货品是否关联</b>
     *
     * @return
     */
    ICResponse<List<ProductGoodsRelVO>> queryByGoodsNo(QueryProductGoodsRelRequest queryProductGoodsRelRequest);


    /**
     * 更新关联关系
     *
     * @return
     */
    ICResponse rebuildProductGoodsRel(UpdateProductGoodsRelRequest updateProductGoodsRelRequest);


    /**
     * 根据城市商品id批量查询货品关联关系
     *
     * @param relRequest
     * @return
     */
    ICResponse<List<ProductGoodsRelVO>> batchQueryByCityProductIds(BatchQueryProductGoodsRelRequest relRequest);
}
