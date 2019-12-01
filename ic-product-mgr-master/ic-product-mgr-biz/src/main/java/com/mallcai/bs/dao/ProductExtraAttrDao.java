package com.mallcai.bs.dao;

import com.mallcai.bs.domain.ProductExtraAttr;
import com.mallcai.bs.mapper.MysqlProductExtraAttrMapper;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: lekaijun
 * @Date: 2019-10-17 12
 * @Description:
 */
@Repository
public class ProductExtraAttrDao {

    @Autowired
    MysqlProductExtraAttrMapper productExtraAttrMapper;

    public List<ProductExtraAttr> selectListByCityProductIds(List<Integer> cityProductIds, ProductExtraAttrTypeEnum attrType){
        return productExtraAttrMapper.selectListByCityProductIds(cityProductIds,attrType);
    }

    public void insertBatch(List<ProductExtraAttr> attrDOs){
        productExtraAttrMapper.insertBatch(attrDOs);
    }

    public void deleteByCityProductIdAndType(Integer cityProductId, ProductExtraAttrTypeEnum attrType){
        productExtraAttrMapper.deleteByCityProductId(cityProductId,attrType);
    }
}
