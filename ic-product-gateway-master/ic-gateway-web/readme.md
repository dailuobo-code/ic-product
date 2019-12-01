### 政采云 web 模板应用使用说明

+ 默认自动接入 dubbo RPC远程调用功能, 提供 dubbo 服务注册和消费能力
+ 默认自动接入 actuator 监控功能, 提供监控应用性能和运行状态的能力
+ 集成其他功能,如: zeye 调用链追踪, zk, 公共工具等

#### dubbo 使用
+ 配置 dubbo 服务提供者
> 在 src/main/resources 目录下创建 spring/dubbo-provider.xml 文件
> 并填写如下内容, interface 和 class 填写实际需要提供的 dubbo 服务名和实现类.

``` xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans            http://www.springframework.org/schema/beans/spring-beans.xsd            http://code.alibabatech.com/schema/dubbo            http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <dubbo:service interface="cn.zcy.gov.TestDubboService" class="cn.zcy.gov.TestDubboServiceImpl" version="1.0.0"/>
</beans>
```

+ 配置 dubbo 服务消费者
> 在 src/main/resources 目录下创建 spring/dubbo-provider.xml文件
> 并填写如下内容, interface 和 id 填写实际需要消费的 dubbo 服务名.

``` xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans            http://www.springframework.org/schema/beans/spring-beans.xsd            http://code.alibabatech.com/schema/dubbo            http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <dubbo:reference id="documentCenterPlugService" interface="cn.gov.zcy.dc.plug.service.DocumentCenterPlugService" version="1.0.0"/>
</beans>
```

+ 配置 web-auth 接入登录权限功能
> 在 src/main/resources/spring/dubbo-consumer.xml 文件中增加消费以下两个 dubbo 服务
> 详细请查看 [web应用接入权限、登录](http://confluence.cai-inc.com/pages/viewpage.action?pageId=9013173)
``` xml
<dubbo:reference retries="0" version="1.0.0" interface="com.dtdream.vanyar.user.service.UserReadService" id="userReadService" check="false" timeout="10000" />
<dubbo:reference retries="0" version="1.0.0" interface="com.dtdream.vanyar.privilege.service.ResourcePrivilegeReadService" id="resourcePrivilegeReadService" check="false" timeout="10000"/>

```

> 配置完dubbo 服务之后,还需要在配置中心配置redis-session相关内容, 保证spring redis session工作正常
``` yml

spring:
  redis:
    sentinel:
      nodes: ${zcy.redis.sentinel}
      master: session
```

#### 其他组件使用
+ 每个组件使用都应该有相应接入使用文档,且能够实现,自助查阅文档,即可自助接入.
+ 本 archetype 应用内所集成的组件, 在父pom dependencyManagement 中均附有接入文档, 若有不明白之处, 可先通过查阅接入文档自行调试.
+ 如发现本 archetype 所依赖 jar 包版本滞后, 请联系骨架管理员进行调整更新.


