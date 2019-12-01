package com.mallcai.biz.product.dao.mapper;

import com.mallcai.BaseTransactionalTest;
import com.mallcai.biz.product.model.AsyProductLogDO;
import com.mallcai.domain.enums.AsyStatusEnum;
import com.mallcai.domain.enums.AsyncLogTypeEnum;
import com.mallcai.domain.product.AsyncProductLogDTO;
import com.mallcai.domain.product.AsyncProductLogDetail;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Action;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductAsyLogMapperTest extends BaseTransactionalTest {

    @Autowired
    private ProductAsyLogMapper productAsyLogMapper;
    @Test
    public void insert() {
        AsyncProductLogDTO asyncProductLogDTO = new AsyncProductLogDTO();
        asyncProductLogDTO.setAfterConvertRequestParamJson("test");
        asyncProductLogDTO.setItemId(1L);
        asyncProductLogDTO.setRequestUUId("1aadf");
        asyncProductLogDTO.setRequestParamJson("asdf");

        List<AsyncProductLogDetail> list = new ArrayList<>();

        AsyncProductLogDetail detail =new AsyncProductLogDetail();
        detail.setAsyncLogTypeEnum(AsyncLogTypeEnum.CITY_PRODUCT_ASYNC);
        detail.setAsyStatusEnum(AsyStatusEnum.SUCCESS);
        detail.setRequestString("qq");
        detail.setResponseString("asdfa");

        list.add(detail);
        AsyncProductLogDetail spec =new AsyncProductLogDetail();
        spec.setAsyncLogTypeEnum(AsyncLogTypeEnum.SPEC_ASYNC);
        spec.setAsyStatusEnum(AsyStatusEnum.Fail);
        spec.setRequestString("qq");
        spec.setResponseString("asdfa");

        list.add(spec);


        asyncProductLogDTO.setAsyncProductLogDetailList(list);

        AsyProductLogDO asyProductLogDO = new AsyProductLogDO();
        BeanUtils.copyProperties(asyncProductLogDTO,asyProductLogDO);
        Integer insert = productAsyLogMapper.insert(asyProductLogDO);
    }
}