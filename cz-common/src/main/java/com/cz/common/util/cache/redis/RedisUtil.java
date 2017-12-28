package com.cz.common.util.cache.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jomalone_jia on 2017/6/27.
 */
public class RedisUtil implements ApplicationContextAware{
    private static RedisTemplate redisTemplate;
    private static ApplicationContext context;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static   RedisTemplate<Serializable,Serializable> getRedis(){
        if(redisTemplate == null){
            synchronized (RedisUtil.class){
                if(redisTemplate == null){
                    redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
                }
            }
        }
        return redisTemplate;
    }



    public Object get(String index,String key){
        HashOperations hash = redisTemplate.opsForHash();
        lock.readLock().lock();
        try {
            return hash.get(index,key);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void set(String index,String key,Object value){
        HashOperations hash = redisTemplate.opsForHash();
        lock.writeLock().lock();
        try {
            hash.put(index,key,value);
        }finally {
            lock.writeLock().unlock();
        }
    }

   /* public synchronized  Object get(String key){
        expire(key);
        return getRedis().boundValueOps(key).get();
    }

    public synchronized Boolean expire(String key){
        return getRedis().expire(key, CacheConstant.CACHE_EXPIRE, TimeUnit.SECONDS);
    }

    public synchronized  Boolean expire(String key,int seconds){
        return getRedis().expire(key,seconds, TimeUnit.SECONDS);
    }*/


}
