package com.dailuobo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dailuobo.service.annotation.DubboParameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangshifeng
 * @date 2019-08-06 11:04
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BaseRpcTest {

    @Value("${dubbo.city.registry.address}")
    private String zookeeperUrl;

    static {
        MDC.put("level", "INFO");
    }
    private GenericService genericService;

    @Before
    public void init() {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();

        RunWith runWith = this.getClass().getAnnotation(RunWith.class);
        DubboParameter dubboParameter = this.getClass().getAnnotation(DubboParameter.class);

        if (runWith != null &&
                (runWith.value() == SpringRunner.class || runWith.value() == SpringJUnit4ClassRunner.class)) {
        } else {
            ApplicationConfig application = new ApplicationConfig();
            application.setName("dubbo-test-city-consumer");

            // 连接注册中心配置
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress("zookeeper://" + zookeeperUrl);

            String zookeeper = dubboParameter.zookeeper();
            if (StringUtils.isBlank(zookeeper)) {
                zookeeper = zookeeperUrl;
            }

            registry.setAddress("zookeeper://" + zookeeper);
            reference.setApplication(application);
            reference.setRegistry(registry);
        }


        reference.setInterface(dubboParameter.interfaceClass());
        reference.setGeneric(true);

        String version = dubboParameter.version();
        if (StringUtils.isNotBlank(version)) {
            reference.setVersion(version);
        }

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        genericService = cache.get(reference);
    }

    public <Response> Response invoke(String methodName, Class<?>[] parameterTypes, Object[] parameters, TypeReference<Response> typeReference) {

        List<String> types = new ArrayList<>();
        for (Class<?> parameterType : parameterTypes) {
            types.add(parameterType.getName());
        }

        Object obj = genericService.$invoke(methodName, types.toArray(new String[parameterTypes.length]), parameters);
        return map2bean(obj, typeReference);
    }

    <Response> Response map2bean(Object map, TypeReference<Response> type) {
        return JSON.parseObject(JSON.toJSONString(map), type);
    }
}
