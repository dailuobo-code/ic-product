package com.dailuobo.biz;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"com.mallcai", "com.dailuobo.biz","com.dailuobo.ic"}, exclude = {TransactionAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, WebMvcAutoConfiguration.class, PageHelperAutoConfiguration.class})
@ImportResource(locations = {"classpath:spring/spring*.xml", "classpath:springboot/spring*.xml"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
