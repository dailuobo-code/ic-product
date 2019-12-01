package com.dailuobo.dao.mapper.ivy;

import com.alibaba.fastjson.JSON;
import com.dailuobo.BaseTest;
import com.mallcai.bs.mapper.ivy.IvySkuInventoryWhiteMapper;
import com.mallcai.bs.model.ivy.IvySkuInventoryWhiteDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IvySkuInventoryWhiteMapperTest extends BaseTest {

    @Autowired
    private IvySkuInventoryWhiteMapper ivySkuInventoryWhiteMapper;

    @Test
    public void  get(){
        List<IvySkuInventoryWhiteDO> datas = ivySkuInventoryWhiteMapper.getWarehouseSkuWhite(null);

        System.err.println(JSON.toJSONString(datas));

        List<IvySkuInventoryWhiteDO> datas2 = ivySkuInventoryWhiteMapper.getWarehouseSkuWhite(datas);

        System.err.println(JSON.toJSONString(datas2));
    }

}
