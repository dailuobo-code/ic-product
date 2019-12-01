package com.mallcai.itemcenter.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ItemBootstrap
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/22 10:59<br/>
 */
@SpringBootApplication
public class ItemBootstrap {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ItemBootstrap.class);
        application.run(args);
    }
}
