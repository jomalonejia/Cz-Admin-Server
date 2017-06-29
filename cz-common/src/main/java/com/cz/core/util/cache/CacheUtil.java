package com.cz.core.util.cache;

import com.cz.core.util.cache.redis.RedisUtil;
import com.cz.core.util.cache.redisson.RedissonUtil;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by jomalone_jia on 2017/6/29.
 */

@Configuration
public class CacheUtil {

    private static RedisUtil redisUtil;
    private static RedissonUtil redissonUtil;

    @Bean
    public RedisUtil setRedisUtil() {
        redisUtil = getRedisUtil();
        return redisUtil;
    }

    @Bean
    public RedissonUtil setRedissonUtil() {
        redissonUtil = getRedissonUtil();
        return redissonUtil;
    }

    public static RedisUtil getRedisUtil(){
        if(redisUtil == null){
            synchronized (CacheUtil.class){
                if(redisUtil == null){
                    redisUtil = new RedisUtil();
                }
            }
        }
        return redisUtil;
    }

    public static RedissonUtil getRedissonUtil(){
        if(redissonUtil == null){
            synchronized (CacheUtil.class){
                if(redissonUtil ==null) {
                    redissonUtil = new RedissonUtil();
                }
            }
        }
        return redissonUtil;
    }
}
