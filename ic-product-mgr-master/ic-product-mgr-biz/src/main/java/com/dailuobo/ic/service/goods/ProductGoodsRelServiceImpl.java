package com.dailuobo.ic.service.goods;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.ResponseCodeEnum;
import com.dailuobo.biz.exception.BusinessException;
import com.dailuobo.biz.service.ICResponseHandler;
import com.dailuobo.ic.api.goods.IProductGoodsRelService;
import com.dailuobo.ic.api.request.goods.*;
import com.dailuobo.ic.api.vo.ProductGoodsRelVO;
import com.dailuobo.ic.convert.goods.ProductGoodsRelConvertor;
import com.dailuobo.ic.domain.dao.model.goods.ProductGoodsRelDO;
import com.dailuobo.ic.manager.goods.ProductGoodsRelManager;
import dailuobo.dao.mapper.ProductGoodsRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现
 *
 * @author lgh
 * @date 2019/9/29
 */
@Service("productGoodsRelService")
public class ProductGoodsRelServiceImpl implements IProductGoodsRelService {

    /**
     * manager
     */
    @Autowired
    private ProductGoodsRelManager productGoodsRelManager;

    /**
     * mapper
     */
    @Autowired
    private ProductGoodsRelMapper productGoodsRelMapper;

    /**
     * 新增货品关联关系
     *
     * @param addProductGoodsRelRequest
     * @return
     */
    @Override
    public ICResponse addProductGoodsRel(AddProductGoodsRelRequest addProductGoodsRelRequest) {

        return ICResponseHandler.template(()->{
            //参数校验
            if(!addProductGoodsRelRequest.checkParams()){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"addProductGoodsRel 参数校验异常");
            }

            productGoodsRelManager.initGoodsRel(addProductGoodsRelRequest);

            // 更新总部商品属性

            return ICResponse.success();

        },"IProductGoodsRelService.addProductGoodsRel", addProductGoodsRelRequest);

    }

    /**
     * 根据城市商品id批量查询货品关联关系
     *
     * @param queryProductGoodsRelRequest
     * @return
     */
    @Override
    public ICResponse<List<ProductGoodsRelVO>> queryByCityProductId(QueryProductGoodsRelRequest queryProductGoodsRelRequest) {

        return ICResponseHandler.template(()->{
            //参数校验
            if(!queryProductGoodsRelRequest.checkParams()){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"queryByCityProductId 参数校验异常");
            }

            if(null == queryProductGoodsRelRequest.getCityProductId()){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"queryByCityProductId 参数校验异常,cityProductId为null");
            }

            // 查询
            List<ProductGoodsRelDO> productGoodsRelDOS = productGoodsRelMapper.queryByCityProductId(queryProductGoodsRelRequest.getCityId(),queryProductGoodsRelRequest.getCityProductId());
            List<ProductGoodsRelVO> productGoodsRelVOS =  ProductGoodsRelConvertor.convert2VO(productGoodsRelDOS);

            return ICResponse.success(productGoodsRelVOS);

        },"IProductGoodsRelService.queryByCityProductId", queryProductGoodsRelRequest);

    }

    /**
     * 根据货品编号查询关联关系
     *
     * @param queryProductGoodsRelRequest
     * @return
     */
    @Override
    public ICResponse<List<ProductGoodsRelVO>> queryByGoodsNo(QueryProductGoodsRelRequest queryProductGoodsRelRequest) {

        return ICResponseHandler.template(()->{
            //参数校验
            if(!queryProductGoodsRelRequest.checkParams()){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"queryByGoodsNo 参数校验异常");
            }

            if(StringUtil.isBlank(queryProductGoodsRelRequest.getGoodsNo())){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"queryByGoodsNo 参数校验异常,goodsNo为null");
            }

            // 查询
            List<ProductGoodsRelDO> productGoodsRelDOS = productGoodsRelMapper.queryByGoodsNo(queryProductGoodsRelRequest.getCityId(),queryProductGoodsRelRequest.getGoodsNo());
            List<ProductGoodsRelVO> productGoodsRelVOS =  ProductGoodsRelConvertor.convert2VO(productGoodsRelDOS);

            return ICResponse.success(productGoodsRelVOS);

        },"IProductGoodsRelService.queryByGoodsNo", queryProductGoodsRelRequest);

    }

    /**
     * 重建关联关系
     *
     * @param updateProductGoodsRelRequest
     * @return
     */
    @Override
    public ICResponse rebuildProductGoodsRel(UpdateProductGoodsRelRequest updateProductGoodsRelRequest) {

        return ICResponseHandler.template(()->{
            //参数校验
            if(!updateProductGoodsRelRequest.checkParams()){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"updateProductGoodsRel 参数校验异常");
            }
            productGoodsRelManager.rebuildGoodsRel(updateProductGoodsRelRequest);
            return ICResponse.success();

        },"IProductGoodsRelService.updateProductGoodsRel", updateProductGoodsRelRequest);

    }

    /**
     * 根据城市商品id批量查询货品关联关系
     *
     * @param relRequest
     * @return
     */
    @Override
    public ICResponse<List<ProductGoodsRelVO>> batchQueryByCityProductIds(BatchQueryProductGoodsRelRequest relRequest) {

        return ICResponseHandler.template(()->{

            if(!relRequest.checkParams()){
                throw new BusinessException(ResponseCodeEnum.INVALID_PARAM,"batchQueryByCityProductIds参数校验异常,请求列表为空");
            }

            // 查询
            List<ProductGoodsRelDO> productGoodsRelDOS = productGoodsRelMapper.batchQueryByCityProductIds(relRequest.getCityId(),relRequest.getCityProductIds());
            List<ProductGoodsRelVO> productGoodsRelVOS =  ProductGoodsRelConvertor.convert2VO(productGoodsRelDOS);

            return ICResponse.success(productGoodsRelVOS);

        },"IProductGoodsRelService.batchQueryByCityProductIds", relRequest);

    }
}
