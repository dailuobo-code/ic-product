package com.mallcai.bs.manager.ivy;

import com.dailuobo.api.common.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.mapper.ivy.IvySkuInventoryWhiteMapper;
import com.mallcai.bs.model.ivy.IvySkuInventoryWhiteDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品库存白名单
 * @author tianjunwei
 * @date 2019-09-19
 */

@Service
public class IVYWarehouseSkuWhiteManager {

    @Resource
    private IvySkuInventoryWhiteMapper ivySkuInventoryWhiteMapper;

    /**
     * 新增白名单商品
     *
     * @param ivySkuInventoryWhiteDOS
     * @return
     */
    public Integer addWarehouseSkuWhite(List<IvySkuInventoryWhiteDO> ivySkuInventoryWhiteDOS) {
        return ivySkuInventoryWhiteMapper.addWarehouseSkuWhite(ivySkuInventoryWhiteDOS);
    }

    public List<IvySkuInventoryWhiteDO> getIvySkuInventoyWhite(List<IvySkuInventoryWhiteDO> ivySkuInventoryWhiteDOS){
        return ivySkuInventoryWhiteMapper.getWarehouseSkuWhite(ivySkuInventoryWhiteDOS);
    }

    /**
     * 更新白名单
     *
     * @param ivyWarehouseSkuWhite
     * @return
     */
    public Integer updateWarehouseSkuWhite(IvySkuInventoryWhiteDO ivyWarehouseSkuWhite) {
        return ivySkuInventoryWhiteMapper.updateWarehouseSkuWhite(ivyWarehouseSkuWhite);
    }

    /**
     * 分页查询
     *
     * @param pageDTO
     * @return
     */
    public Page<IvySkuInventoryWhiteDO> getWarehouseSkuWhite(PageDTO pageDTO) {

        Page<IvySkuInventoryWhiteDO> page = PageHelper.startPage(pageDTO.getCurrentPage(),pageDTO.getPageSize());
        ivySkuInventoryWhiteMapper.getWarehouseSkuWhite(null);
        return page;
    }

    /**
     * 删除白名单商品
     *
     * @param id
     * @return
     */
    public Integer delWarehouseSkuWhite(List<Integer> id) {
        return ivySkuInventoryWhiteMapper.delWarehouseSkuWhite(id);
    }


}
