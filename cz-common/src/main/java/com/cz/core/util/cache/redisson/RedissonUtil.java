package com.cz.core.util.cache.redisson;

import com.cz.core.util.cache.redis.RedisUtil;
import com.cz.core.util.constant.CacheConstant;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by jomalone_jia on 2017/6/29.
 */
public class RedissonUtil implements ApplicationContextAware {
    private RedissonClient redissonClient;
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public org.redisson.api.RedissonClient  getRedisson(){
        if(redissonClient == null){
            synchronized (RedisUtil.class){
                if(redissonClient == null){
                    redissonClient = (RedissonClient) context.getBean("redissonClient");
                }
            }
        }
        return redissonClient;
    }

    public  RBucket<Object> getBucket(String key){
        return getRedisson().getBucket(key);
    }

    public void expire(RBucket<Object> bucket){
       bucket.expire(CacheConstant.CACHE_EXPIRE, TimeUnit.SECONDS);
    }
    public void expire(RBucket<Object> bucket,int seconds){
        bucket.expire(seconds, TimeUnit.SECONDS);
    }

    public synchronized Object get(String key){
        RBucket<Object> bucket = getBucket(key);
        expire(bucket);
        return bucket.get();
    }

    public synchronized void set(String key,Serializable value){
        RBucket<Object> bucket = getBucket(key);
        bucket.set(value);
        expire(bucket);
    }

    public synchronized void set(String key,Serializable value,int seconds){
        RBucket<Object> bucket = getBucket(key);
        bucket.set(value);
        expire(bucket,seconds);
    }
}
