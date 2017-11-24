package com.cz.common.util.cache.redis;

import com.cz.common.util.constant.CacheConstant;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by jomalone_jia on 2017/6/27.
 */
public class RedisUtil implements ApplicationContextAware{
    private RedisTemplate redisTemplate;
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public  RedisTemplate<Serializable,Serializable> getRedis(){
        if(redisTemplate == null){
            synchronized (RedisUtil.class){
                if(redisTemplate == null){
                    redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
                }
            }
        }
        return redisTemplate;
    }

    public synchronized  Object get(String key){
        expire(key);
        return getRedis().boundValueOps(key).get();
    }

    public synchronized Boolean expire(String key){
        return getRedis().expire(key, CacheConstant.CACHE_EXPIRE, TimeUnit.SECONDS);
    }

    public synchronized  Boolean expire(String key,int seconds){
        return getRedis().expire(key,seconds, TimeUnit.SECONDS);
    }


}
