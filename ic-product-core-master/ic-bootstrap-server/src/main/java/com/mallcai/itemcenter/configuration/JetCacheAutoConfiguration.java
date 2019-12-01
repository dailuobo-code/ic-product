package com.mallcai.itemcenter.configuration;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.KryoValueDecoder;
import com.alicp.jetcache.support.KryoValueEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * JetcacheAutoConfiguration
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/18 15:22<br/>
 */
@Slf4j
@Configuration
@EnableMethodCache(basePackages = {"com.mallcai.itemcenter.category","com.mallcai.itemcenter.item"})
@EnableCreateCacheAnnotation
public class JetCacheAutoConfiguration {
    @Value("${local.redis.host}")
    private String redisHost;
    @Value("${local.redis.port}")
    private int redisPort;
    @Value("${local.redis.maxWaitMillis}")
    private int redisTimeout;
    @Value("${local.redis.passWord}")
    private String redisPasswd;

    @Value("${redis.poolConfig.minIdle}")
    private int redisPoolConfigMinIdle;
    @Value("${redis.poolConfig.maxIdle}")
    private int redisPoolConfigMaxIdle;
    @Value("${redis.poolConfig.maxTotal}")
    private int redisPoolConfigMaxTotal;
    @Value("${redis.poolConfig.maxWaitMillis}")
    private long redisPoolConfigMaxWaitMillis;
    @Value("${redis.poolConfig.numTestsPerEvictionRun}")
    private int redisPoolConfigNumTestsPerEvictionRun;
    @Value("${jetCache.local.limit}")
    private int jetCacheLocalLimit;
    @Value("${jetCache.local.expireAfterWrite}")
    private long jetCacheLocalExpireAfterWrite;
    @Value("${jetCache.remote.statIntervalMinutes}")
    private int jetCacheRemoteStatIntervalMinutes;

    @Bean
    public Pool<Jedis> pool(){
        GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
        pc.setMinIdle(redisPoolConfigMinIdle);
        pc.setMaxIdle(redisPoolConfigMaxIdle);
        pc.setMaxTotal(redisPoolConfigMaxTotal);
        pc.setMaxWaitMillis(redisPoolConfigMaxWaitMillis);
        pc.setNumTestsPerEvictionRun(redisPoolConfigNumTestsPerEvictionRun);
        return new JedisPool(pc, redisHost, redisPort,redisTimeout,redisPasswd);
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool){
        Map localBuilders = new HashMap();
        EmbeddedCacheBuilder localBuilder = CaffeineCacheBuilder.createCaffeineCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE).limit(jetCacheLocalLimit).expireAfterWrite(jetCacheLocalExpireAfterWrite, TimeUnit.MILLISECONDS);
        localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

        Map remoteBuilders = new HashMap();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(KryoValueEncoder.INSTANCE)
                .valueDecoder(KryoValueDecoder.INSTANCE)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setStatIntervalMinutes(jetCacheRemoteStatIntervalMinutes);
        globalCacheConfig.setAreaInCacheName(false);
        globalCacheConfig.setHiddenPackages(new String[]{"com.alibaba"});

        return globalCacheConfig;
    }
}
