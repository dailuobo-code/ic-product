package com.dailuobo.ic.manager.goods;

import com.dailuobo.ic.api.request.goods.AddProductGoodsRelRequest;
import com.dailuobo.ic.api.request.goods.UpdateProductGoodsRelRequest;
import com.dailuobo.ic.convert.goods.ProductGoodsRelConvertor;
import com.dailuobo.ic.domain.dao.model.goods.ProductGoodsRelDO;
import dailuobo.dao.mapper.ProductGoodsRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lgh
 * @date 2019/10/15
 */
@Slf4j
@Repository
public class ProductGoodsRelManager {

    /**
     * mapper
     */
    @Autowired
    private ProductGoodsRelMapper productGoodsRelMapper;

    /**
     * 批量添加关联关系
     *
     * @param addProductGoodsRelRequest
     */
    @Transactional
    public void initGoodsRel(AddProductGoodsRelRequest addProductGoodsRelRequest){

        // 参数转换
        List<ProductGoodsRelDO> productGoodsRelDOS = ProductGoodsRelConvertor.convert2DO(addProductGoodsRelRequest);

        // 批量插入
        productGoodsRelMapper.batchSave(productGoodsRelDOS);
    }

    /**
     * 重新关联
     *
     * @param updateProductGoodsRelRequest
     */
    @Transactional
    public void rebuildGoodsRel(UpdateProductGoodsRelRequest updateProductGoodsRelRequest){

        //参数转换
        List<ProductGoodsRelDO> productGoodsRelDOS = ProductGoodsRelConvertor.convert2DO(updateProductGoodsRelRequest);

        //货品关联关系为空,直接返回
        if(CollectionUtils.isEmpty(productGoodsRelDOS)){
            log.warn("rebuildGoodsRel,货品关联关系为空,直接返回");
            return;
        }

        //删除
        ProductGoodsRelDO hisRelDO = productGoodsRelMapper.queryById(updateProductGoodsRelRequest.getOrgRelId(),updateProductGoodsRelRequest.getCityId());
        if(null != hisRelDO){
            List<ProductGoodsRelDO> hisRelDOS = new ArrayList<>();
            hisRelDOS.add(hisRelDO);
            productGoodsRelMapper.batchSaveHis(hisRelDOS);
            productGoodsRelMapper.del(updateProductGoodsRelRequest.getCityId(),updateProductGoodsRelRequest.getOrgRelId());
        }

        // 批量插入(事务)
        productGoodsRelMapper.batchSave(productGoodsRelDOS);
    }


}
