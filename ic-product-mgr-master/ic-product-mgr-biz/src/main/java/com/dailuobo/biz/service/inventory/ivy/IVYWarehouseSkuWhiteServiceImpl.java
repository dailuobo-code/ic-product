package com.dailuobo.biz.service.inventory.ivy;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.ivy.IVYWarehouseSkuWhiteDTO;
import com.dailuobo.api.domain.ivy.SearchWarehouseSkuWhiteDTO;
import com.dailuobo.api.inventory.ivy.IVYWarehouseSkuWhiteService;
import com.github.pagehelper.Page;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.bs.manager.ivy.IVYWarehouseSkuWhiteManager;
import com.mallcai.bs.model.ivy.IvySkuInventoryWhiteDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("iVYWarehouseSkuWhiteService")
public class IVYWarehouseSkuWhiteServiceImpl implements IVYWarehouseSkuWhiteService {

    @Resource
    private IVYWarehouseSkuWhiteManager ivyWarehouseSkuWhiteManager;

    /**
     * 新增白名单商品
     *
     * @param ivyWarehouseSkuWhiteDTOS
     * @return
     */
    @Override
    public ICResponse<Boolean> addWarehouseSkuWhite(List<IVYWarehouseSkuWhiteDTO> ivyWarehouseSkuWhiteDTOS) {
        try {

            if(CollectionUtils.isEmpty(ivyWarehouseSkuWhiteDTOS)){
                return ICResponse.fail("数据为空");
            }
            for (IVYWarehouseSkuWhiteDTO ivyWarehouseSkuWhite : ivyWarehouseSkuWhiteDTOS) {
                if (ivyWarehouseSkuWhite.getWarehouseId() == null) {
                    return ICResponse.fail("仓库id为空");
                }
                if (ivyWarehouseSkuWhite.getCityProductId() == null) {
                    return ICResponse.fail("城市商品id为空");
                }
            }
            List<IvySkuInventoryWhiteDO> ivySkuInventoryWhiteDOS = OrikaUtil.convertList(ivyWarehouseSkuWhiteDTOS, IvySkuInventoryWhiteDO.class);
            List<IvySkuInventoryWhiteDO> exist = ivyWarehouseSkuWhiteManager.getIvySkuInventoyWhite(ivySkuInventoryWhiteDOS);
            if(CollectionUtils.isNotEmpty(exist)){
                return ICResponse.fail("商品已存在白名单中"+exist.get(0).getCityProductId());
            }
            ivyWarehouseSkuWhiteManager.addWarehouseSkuWhite(ivySkuInventoryWhiteDOS);
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("addWarehouseSkuWhite error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 更新白名单
     *
     * @param ivyWarehouseSkuWhite
     * @return
     */
    @Override
    public ICResponse<Boolean> updateWarehouseSkuWhite(IVYWarehouseSkuWhiteDTO ivyWarehouseSkuWhite) {
        try {
            if(ivyWarehouseSkuWhite.getWarehouseId() == null){
                return ICResponse.fail("仓库id为空");
            }
            if(ivyWarehouseSkuWhite.getCityProductId() == null){
                return ICResponse.fail("城市商品id为空");
            }
            IvySkuInventoryWhiteDO ivySkuInventoryWhiteDO = OrikaUtil.convert(ivyWarehouseSkuWhite, IvySkuInventoryWhiteDO.class);
            ivyWarehouseSkuWhiteManager.updateWarehouseSkuWhite(ivySkuInventoryWhiteDO);
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("updateWarehouseSkuWhite error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 分页查询
     *
     * @param pageDTO
     * @return
     */
    @Override
    public ICResponse<List<IVYWarehouseSkuWhiteDTO>> getWarehouseSkuWhite(SearchWarehouseSkuWhiteDTO searchWarehouseSkuWhiteDTO, PageDTO pageDTO) {
        try {

            if(pageDTO == null){
                pageDTO = new PageDTO();
                pageDTO.setPageSize(10);
                pageDTO.setCurrentPage(1);
            }
            if(pageDTO.getPageSize() < 1){
                pageDTO.setPageSize(10);
            }
            if(pageDTO.getCurrentPage() < 1){
                pageDTO.setCurrentPage(1);
            }

            Page<IvySkuInventoryWhiteDO> page = ivyWarehouseSkuWhiteManager.getWarehouseSkuWhite(pageDTO);
            if(page == null){
                pageDTO.setTotalNumber(0);
                return ICResponse.success(new ArrayList<>(),pageDTO);
            }else {
                List<IVYWarehouseSkuWhiteDTO> ivyWarehouseSkuWhiteDTOS = OrikaUtil.convertList(page.getResult(), IVYWarehouseSkuWhiteDTO.class);
                pageDTO.setTotalNumber(page.getTotal());
                return ICResponse.success(ivyWarehouseSkuWhiteDTOS,pageDTO);
            }
        }catch (Exception e){
            log.error("getWarehouseSkuWhite error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }

    /**
     * 删除白名单商品
     *
     * @param id
     * @return
     */
    @Override
    public ICResponse<Boolean> delWarehouseSkuWhite(Integer id) {
        try {
            if(id == null){
                return ICResponse.fail("参数为空");
            }
            ivyWarehouseSkuWhiteManager.delWarehouseSkuWhite(Arrays.asList(id));
            return ICResponse.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("delWarehouseSkuWhite error :{}",e.toString());
            return ICResponse.fail(e.toString());
        }
    }


}
