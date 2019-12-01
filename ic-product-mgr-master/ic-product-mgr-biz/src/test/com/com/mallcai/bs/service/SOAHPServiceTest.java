package com.mallcai.bs.service;

import com.dailuobo.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SOAHPServiceTest extends BaseTest {

    @Autowired
    private SOAHPService soahpService;

    @Test
    public void updateProductClassifyRef() {
        soahpService.updateProductClassifyRef(153, 29426);
    }
}