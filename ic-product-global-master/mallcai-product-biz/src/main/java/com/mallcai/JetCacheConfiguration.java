package com.mallcai;

import com.alicp.jetcache.CacheBuilder;
import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.KryoValueDecoder;
import com.alicp.jetcache.support.KryoValueEncoder;
import com.mallcai.backend.common.redis.config.RedisConfig;
import com.mallcai.backend.common.utils.BaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 全局方法缓存配置
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-07-17 16:50<br/>
 */
@Slf4j
@Order
@Configuration
@EnableMethodCache(basePackages = "com.mallcai.biz.cacher")
@EnableCreateCacheAnnotation
public class JetCacheConfiguration {

    @Value("${configPath.path}")
    private String configPath;

    @Bean
    @ConditionalOnMissingBean
    public RedisConfig redisConfig(BaseConfig baseConfig) {
        // 显示依赖 config
        return RedisConfig.getDefault();
    }

    @Bean
    public Pool<Jedis> pool(RedisConfig redisConfig) {
        GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
        pc.setMinIdle(2);
        pc.setMaxIdle(10);
        pc.setMaxTotal(10);
        pc.setNumTestsPerEvictionRun(10);
        pc.setMaxWaitMillis(5000L);
        pc.setTestOnBorrow(true);
        pc.setTestOnReturn(true);

        log.info("host: {}, port: {}, index: {}", redisConfig.getRedisHost(), redisConfig.getRedisPort(), redisConfig.getDbIndex());
        return new JedisPool(pc, redisConfig.getRedisHost(), redisConfig.getRedisPort(), redisConfig.getRedisMaxWaitMillis(),
                redisConfig.getRedisPassWord(), redisConfig.getDbIndex());
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool) {
        // 本地缓存最大容量 1 万，1 分钟后失效
        Map<String, CacheBuilder> localBuilder = new HashMap<>();
        CaffeineCacheBuilder caffeineCacheBuilder = CaffeineCacheBuilder.createCaffeineCacheBuilder()
                .limit(10_000)
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .expireAfterWrite(1, TimeUnit.MINUTES);
        localBuilder.put(CacheConsts.DEFAULT_AREA, caffeineCacheBuilder);
        // 中心缓存10 分钟后失效
        Map<String, CacheBuilder> remoteBuilders = new HashMap<>();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(KryoValueEncoder.INSTANCE)
                .valueDecoder(KryoValueDecoder.INSTANCE)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilder);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setStatIntervalMinutes(15);
        globalCacheConfig.setAreaInCacheName(false);

        return globalCacheConfig;
    }
}
