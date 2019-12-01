package com.mallcai.itemcenter;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TestBootstrap
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 14:10<br/>
 */
@EnableCreateCacheAnnotation
@Configuration
@EnableAutoConfiguration
@ComponentScan({
        "com.mallcai.itemcenter.item.repository",
        "com.mallcai.itemcenter.sku.repository",
})
public class DaoTestConfiguration {
}
