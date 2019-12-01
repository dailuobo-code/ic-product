package com.dailuobo.api.inventory.ivy;


import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYWarehouseSkuWhiteDTO;
import com.dailuobo.api.domain.ivy.SearchWarehouseSkuWhiteDTO;

import java.util.List;

/**
 * 仓库商品白名单
 * @author tianjunwei
 * @date 2019-09-18
 */
public interface IVYWarehouseSkuWhiteService {

    /**
     * 删除白名单商品
     * @param id
     * @return
     */
    ICResponse<Boolean> delWarehouseSkuWhite(Integer id);

    /**
     * 新增白名单商品
     * @param ivyWarehouseSkuWhiteDTOS
     * @return
     */
    ICResponse<Boolean> addWarehouseSkuWhite(List<IVYWarehouseSkuWhiteDTO> ivyWarehouseSkuWhiteDTOS);

    /**
     * 更新白名单
     * @param ivyWarehouseSkuWhite
     * @return
     */
    ICResponse<Boolean> updateWarehouseSkuWhite(IVYWarehouseSkuWhiteDTO ivyWarehouseSkuWhite);

    /**
     * 分页查询
     * @return
     */
    ICResponse<List<IVYWarehouseSkuWhiteDTO>> getWarehouseSkuWhite(SearchWarehouseSkuWhiteDTO searchWarehouseSkuWhiteDTO, PageDTO pageDTO);

}
