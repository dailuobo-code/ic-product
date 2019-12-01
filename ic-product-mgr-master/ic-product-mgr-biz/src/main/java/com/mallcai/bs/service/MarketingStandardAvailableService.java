package com.mallcai.bs.service;

import com.mallcai.bs.dao.MarketingStandardAvailableDao;
import com.dailuobo.api.domain.entity.ProductAvailableChange;
import com.dailuobo.api.domain.vo.ProductAvailableChangeQueryVo;
import com.dailuobo.api.domain.vo.ProductAvailableChangeResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 标品库存管理.
 * @author zhanghao
 * @date 2019/5/13
 */
@Service
public class MarketingStandardAvailableService {

    @Autowired
    private MarketingStandardAvailableDao availableDao;

    /**
     * 新增记录.
     * @param productAvailableChange
     */
    public void addProductAvailableChange(ProductAvailableChange productAvailableChange){

        availableDao.addProductAvailableChange(productAvailableChange);
    }

    /**
     * 修改记录.
     * @param productAvailableChange
     */
    public void updateProductAvailableChange(ProductAvailableChange productAvailableChange){
        availableDao.updateProductAvailableChange(productAvailableChange);
    }

    /**
     * 根据商品Id和时间查询记录.
     * @param cityProductId
     * @param execDate
     * @return
     */
    public List<ProductAvailableChange> selectDataByProductIdAndDate(Integer cityProductId, Date execDate){
        return availableDao.selectDataByProductIdAndDate(cityProductId,execDate);
    }

    /**
     * 通过ID查询数据.
     * @param id
     * @return
     */
    public ProductAvailableChange selectDataById(Integer id){
        return availableDao.selectDataById(id);
    }

    /**
     * 查询列表.
     * @param changeQueryVo
     * @return
     */
    public List<ProductAvailableChangeResponseVo> selectProductAvailableChangeList(ProductAvailableChangeQueryVo changeQueryVo){
        return availableDao.selectProductAvailableChangeList(changeQueryVo);
    }
}
