package com.mallcai.product;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 */
@ImportResource(locations = {"classpath:applicationContext.xml"})
@SpringBootApplication(scanBasePackages = {"com.mallcai"}, exclude = {DataSourceAutoConfiguration.class, PageHelperAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
