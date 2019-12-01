package com.dailuobo.biz.service.inventory;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.ProductAvailableChange;
import com.dailuobo.api.domain.vo.ProductAvailableChangeQueryVo;
import com.dailuobo.api.domain.vo.ProductAvailableChangeResponseVo;
import com.dailuobo.api.inventory.ICMarketingStandardAvailableService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mallcai.bs.service.MarketingStandardAvailableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("iCMarketingStandardAvailableService")
public class ICMarketingStandardAvailableServiceImpl implements ICMarketingStandardAvailableService {
    @Autowired
    private MarketingStandardAvailableService marketingStandardAvailableService;

    /**
     * 新增记录.
     *
     * @param productAvailableChange
     * @return
     */
    public ICResponse addProductAvailableChange(ProductAvailableChange productAvailableChange) {
        return ICResponseHandler.template(() -> {
            marketingStandardAvailableService.addProductAvailableChange(productAvailableChange);
            return ICResponse.success();
        }, "addProductAvailableChange", productAvailableChange);

    }

    /**
     * 修改记录.
     *
     * @param productAvailableChange
     */
    public ICResponse updateProductAvailableChange(ProductAvailableChange productAvailableChange) {
        return ICResponseHandler.template(() -> {
            marketingStandardAvailableService.updateProductAvailableChange(productAvailableChange);
            return ICResponse.success();
        }, "updateProductAvailableChange", productAvailableChange);
    }

    /**
     * 根据商品Id和时间查询记录.
     *
     * @param cityProductId
     * @param execDate
     * @return
     */
    public ICResponse<List<ProductAvailableChange>> selectDataByProductIdAndDate(Integer cityProductId, Date execDate) {
        Map param1= Maps.newHashMap();
        param1.put("cityProductId",cityProductId);
        param1.put("execDate",execDate);

        return ICResponseHandler.template(() -> {
            List<ProductAvailableChange> productAvailableChanges = marketingStandardAvailableService.selectDataByProductIdAndDate(cityProductId, execDate);
            return ICResponse.success(productAvailableChanges);
        }, "selectDataByProductIdAndDate", param1);

    }

    /**
     * 通过ID查询数据.
     *
     * @param id
     * @return
     */
    public ICResponse<ProductAvailableChange> selectDataById(Integer id) {
        return ICResponseHandler.template(() -> {
            ProductAvailableChange productAvailableChange = marketingStandardAvailableService.selectDataById(id);
            return ICResponse.success(productAvailableChange);
        }, "", id);
    }

    /**
     * 查询列表.
     *
     * @param changeQueryVo
     * @return
     */
    public ICResponse<List<ProductAvailableChangeResponseVo>> selectProductAvailableChangeList(ProductAvailableChangeQueryVo changeQueryVo) {


        try {
            if (null == changeQueryVo.getOffset()) {
                changeQueryVo.setOffset(0);
            }
            PageHelper.startPage(changeQueryVo.getOffset() / changeQueryVo.getLimit() + 1, changeQueryVo.getLimit());
            List<ProductAvailableChangeResponseVo> productAvailableChangeResponseVos = marketingStandardAvailableService.selectProductAvailableChangeList(changeQueryVo);
            if (changeQueryVo.getOffset() > 0 && changeQueryVo.getLimit() > 0) {
                PageDTO pageDTO = new PageDTO(changeQueryVo.getLimit(), (int) ((Page) productAvailableChangeResponseVos).getTotal(), changeQueryVo.getOffset() / changeQueryVo.getLimit() + 1);
                return ICResponse.success(productAvailableChangeResponseVos, pageDTO);
            }
            return ICResponse.success(productAvailableChangeResponseVos);
        } catch (Exception ex) {
            log.error(String.format("getWhMarketingInventories error,request:%s", JSON.toJSONString(changeQueryVo)), ex);
            return ICResponse.fail(ex.getMessage());
        }
    }
}
