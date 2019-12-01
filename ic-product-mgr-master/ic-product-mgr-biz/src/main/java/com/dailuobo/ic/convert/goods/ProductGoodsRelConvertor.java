package com.dailuobo.ic.convert.goods;

import com.dailuobo.ic.api.request.goods.AddProductGoodsRelRequest;
import com.dailuobo.ic.api.vo.ProductGoodsRelVO;
import com.dailuobo.ic.domain.dao.model.goods.ProductGoodsRelDO;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 货品关联关系转换
 *
 */
public class ProductGoodsRelConvertor {


    /**
     * 将request转为relDo
     *
     * @param request
     * @return
     */
    public static List<ProductGoodsRelDO> convert2DO(AddProductGoodsRelRequest request) {

        /**
         * 判空
         */
        if(null == request){
            return new ArrayList<>();
        }

        List<ProductGoodsRelDO> productGoodsRelDOS = new ArrayList<>();
        request.getGoodsRelDTOS().forEach(reldto->{
            ProductGoodsRelDO relDO = new ProductGoodsRelDO();
            relDO.setCityId(request.getCityId());
            relDO.setCityProductId(request.getCityProductId());
            relDO.setGoodsId(reldto.getGoodsId());
            relDO.setGoodsName(reldto.getGoodsName());
            relDO.setGoodsUnit(reldto.getGoodsUnit());
            relDO.setRelNum(reldto.getRelNum());
            relDO.setCreateUserId(request.getOperatorId());
            relDO.setUpdateUserId(request.getOperatorId());

            relDO.setIsStandard(reldto.getIsStandard());
            relDO.setKeepType(reldto.getKeepType());
            // 已关联
            relDO.setIsRel(1);

            productGoodsRelDOS.add(relDO);
        });

        return productGoodsRelDOS;
    }

    /**
     *
     * @param productGoodsRelDOS
     * @return
     */
    public static List<ProductGoodsRelVO> convert2VO(List<ProductGoodsRelDO> productGoodsRelDOS){

        /**
         * 判空
         */
        if(CollectionUtils.isEmpty(productGoodsRelDOS)){
            return new ArrayList<>();
        }

        List<ProductGoodsRelVO> productGoodsRelVOS = new ArrayList<>();
        productGoodsRelDOS.forEach(reldo->{
            ProductGoodsRelVO vo = new ProductGoodsRelVO();
            vo.setCityId(reldo.getCityId());
            vo.setCityProductId(reldo.getCityProductId());
            vo.setGoodsId(reldo.getGoodsId());
            vo.setGoodsName(reldo.getGoodsName());
            vo.setGoodsUnit(reldo.getGoodsUnit());
            vo.setRelNum(reldo.getRelNum());
            vo.setRelId(reldo.getId());

            vo.setKeepType(reldo.getKeepType());
            vo.setIsStandard(reldo.getIsStandard());
            productGoodsRelVOS.add(vo);
        });

        return productGoodsRelVOS;
    }

}
