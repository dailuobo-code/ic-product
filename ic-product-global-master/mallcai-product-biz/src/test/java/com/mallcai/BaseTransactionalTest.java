package com.mallcai;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BaseTransactionalTest {
    public static void printJson(Object object) {
        System.out.println(JSON.toJSONString(object)) ;
    }
}
