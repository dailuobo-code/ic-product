package com.mallcai.biz.product;

import com.github.pagehelper.PageInfo;
import com.mallcai.api.product.backend.PartnerProductService;
import com.mallcai.backend.common.utils.OrikaUtil;
import com.mallcai.biz.product.manager.MgrProductServiceHelper;
import com.mallcai.common.ICResponse;
import com.mallcai.common.PageDTO;
import com.mallcai.domain.dataobject.product.FrozenProductDO;
import com.mallcai.domain.product.dto.FrozenProductDTO;
import com.mallcai.domain.product.request.GetFrozenProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author tianjunwei
 * @date 2019-09-02
 */
@Slf4j
@Service("partnerProductService")
public class PartnerProductServiceImpl implements PartnerProductService {

    @Resource
    private MgrProductServiceHelper mgrProductServiceHelper;

    @Override
    public ICResponse<List<FrozenProductDTO>> getFrozenProduct(GetFrozenProductRequest request){
        try {
            if(request == null){
                return ICResponse.fail("参数为空");
            }
            // 冻品类型为3
            if(request.getKeepType() == null){
                request.setKeepType(3);
            }
            List<FrozenProductDO> list = mgrProductServiceHelper.getFrozenProduct(request);
            if (CollectionUtils.isEmpty(list)){
                return ICResponse.success(Collections.EMPTY_LIST);
            }
            List<FrozenProductDTO> frozenProductDTOS = OrikaUtil.convertList(list, FrozenProductDTO.class);
            PageInfo<FrozenProductDO> pageInfo = new PageInfo<>(list);
            return ICResponse.success(frozenProductDTOS, new PageDTO(request.getPageSize(), (int)pageInfo.getTotal(), request.getCurrentPage()));
        }catch (Exception e){
            log.error("获取冻品数据异常:{}",e);
            return ICResponse.fail(e.getMessage());
        }
    }
}
