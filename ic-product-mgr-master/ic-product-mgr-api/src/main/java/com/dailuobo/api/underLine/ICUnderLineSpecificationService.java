package com.dailuobo.api.underLine;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.UnSpec;
import com.dailuobo.api.domain.vo.UndProductVo;
import com.dailuobo.api.domain.vo.UndSpecVo;

import java.util.List;
import java.util.Map;

/**
 * 线下规格管理服务 full_copy_mgr
 */
public interface ICUnderLineSpecificationService {
    ICResponse<UnSpec> getDefaultSpec(Map<String, Object> param);

    ICResponse<List<UndSpecVo>> getUnSpecs(Map<String, Object> param);

    ICResponse<List<UndSpecVo>> getStoreUnSpecs(Map<String, Object> param);

    ICResponse<List<UndProductVo>> selectUnderProducts(Map<String, Object> param);
}
