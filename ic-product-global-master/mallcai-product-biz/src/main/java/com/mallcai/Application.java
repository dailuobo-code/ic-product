package com.mallcai;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 */
@DubboComponentScan("com.mallcai.biz")
@SpringBootApplication(scanBasePackages = {"com.mallcai"}, exclude = PageHelperAutoConfiguration.class)
@ImportResource(locations = {"applicationContext.xml"})
public class Application {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.setAllowBeanDefinitionOverriding(true);
        app.run(args);
    }

}



