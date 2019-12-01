package com.mallcai.itemcenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Bootstrap
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/16 17:44<br/>
 */
@Slf4j
@SpringBootApplication
@ImportResource(locations = {"applicationContext.xml"})
public class ICBootstrap {

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(ICBootstrap.class);
            app.run(args);
        } catch (Exception ex) {
            log.error("springboot run ex", ex);
        }
    }

}
