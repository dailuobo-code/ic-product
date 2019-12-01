package com.mallcai.itemcenter.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstrap
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 17:44<br/>
 */
@SpringBootApplication
public class CategoryBootstrap {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CategoryBootstrap.class);
        application.run(args);
    }
}
