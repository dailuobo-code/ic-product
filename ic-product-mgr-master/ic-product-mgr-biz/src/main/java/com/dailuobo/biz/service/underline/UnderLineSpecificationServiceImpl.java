package com.dailuobo.biz.service.underline;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.common.PageDTO;
import com.dailuobo.api.domain.entity.UnSpec;
import com.dailuobo.api.domain.vo.UndProductVo;
import com.dailuobo.api.domain.vo.UndSpecVo;
import com.dailuobo.api.underLine.ICUnderLineSpecificationService;
import com.dailuobo.biz.service.ICResponseHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mallcai.bs.service.UnSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("underLineSpecificationService")
public class UnderLineSpecificationServiceImpl implements ICUnderLineSpecificationService {
    @Autowired
    private UnSpecService unSpecService;

    public ICResponse<UnSpec> getDefaultSpec(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            UnSpec defaultSpec = unSpecService.getDefaultSpec(param);
            return ICResponse.success(defaultSpec);
        }, "getDefaultSpec", param);

    }

    public ICResponse<List<UndSpecVo>> getUnSpecs(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            List<UndSpecVo> unSpecs = unSpecService.getUnSpecs(param);
            return ICResponse.success(unSpecs);
        }, "getUnSpecs", param);


    }

    public ICResponse<List<UndSpecVo>> getStoreUnSpecs(Map<String, Object> param) {
        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.offsetPage(offset, limit);
            }
            List<UndSpecVo> storeUnSpecs = unSpecService.getStoreUnSpecs(param);
            //int pageSize, long totalNumber, int currentPage

            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) storeUnSpecs).getTotal(), offset / limit + 1);
                return ICResponse.success(storeUnSpecs, pageDTO);
            }

            return ICResponse.success(storeUnSpecs);
        }, "getStoreUnSpecs", param);


    }

    public ICResponse<List<UndProductVo>> selectUnderProducts(Map<String, Object> param) {

        return ICResponseHandler.template(() -> {
            // TODO Auto-generated method stub
            int offset = 0;
            int limit = 0;
            if (param.get("offset") != null && param.get("limit") != null) {
                offset = (int) param.get("offset");
                limit = (int) param.get("limit");
                PageHelper.offsetPage(offset, limit);
            }
            List<UndProductVo> undProductVos = unSpecService.selectUnderProducts(param);
            //int pageSize, long totalNumber, int currentPage

            if (offset >= 0 && limit > 0) {
                PageDTO pageDTO = new PageDTO(limit, (int) ((Page) undProductVos).getTotal(), offset / limit + 1);
                return ICResponse.success(undProductVos, pageDTO);
            }
            return ICResponse.success(undProductVos);
        }, "selectUnderProducts", param);

    }
}
