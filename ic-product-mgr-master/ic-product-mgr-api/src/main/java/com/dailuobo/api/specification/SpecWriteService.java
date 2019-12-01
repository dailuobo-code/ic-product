package com.dailuobo.api.specification;

import com.dailuobo.ic.api.spec.SpecUpdateAsyncQueryRequest;
import com.mallcai.backend.common.api.PlainResult;

public interface SpecWriteService {
    PlainResult<Boolean> specUpdateAsy(SpecUpdateAsyncQueryRequest request);
}
