package com.cz.service.aop;

import com.alibaba.fastjson.JSON;
import com.cz.common.util.cache.redis.RedisUtil;
import com.cz.service.annotation.CzRedisCache;
import com.cz.service.annotation.CzRedisEvict;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/12/28.
 */
@Aspect
@Component
@Order(0)
public class RedisCacheAspect {
    private static final Logger _log = LoggerFactory.getLogger(RedisCacheAspect.class);

    private static final String PREFIX = "cz-admin-";
    private static final String DELIMITER = "-";


    @Pointcut(value = "@annotation(czRedisCache)",argNames = "czRedisCache")
    public void pointcut(CzRedisCache czRedisCache){}

    @Pointcut(value = "@annotation(czRedisEvict)",argNames = "czRedisEvict")
    public void pointcut2(CzRedisEvict czRedisEvict){}


    @Around(value = "pointcut(czRedisCache)")
    public Object readCache(ProceedingJoinPoint point,CzRedisCache czRedisCache) throws Throwable{
        // 得到类名、方法名和参数
        String clazzName = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();

        String key = genKey(methodName, args);
        if (_log.isDebugEnabled()) {
            _log.debug("生成key:{}", key);
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        _log.info(method.getName());
        // 得到被代理的方法上的注解

        Class objectType = method.getAnnotation(CzRedisCache.class).type();

        String index = PREFIX + clazzName;

        String value = (String) RedisUtil.getRedis().opsForHash().get(index, key);
        // result是方法的最终返回结果
        Object result = null;
        if (null == value) {
            // 缓存未命中
            if (_log.isDebugEnabled()) {
                _log.debug("缓存未命中");
            }

            // 调用数据库查询方法
            result = point.proceed(args);

            // 序列化查询结果
            String json = serialize(result);

            // 序列化结果放入缓存
            RedisUtil.getRedis().opsForHash().put(index, key, json);
        } else {
            // 缓存命中
            if (_log.isDebugEnabled()) {
                _log.debug("缓存命中, value = {}", value);
            }

            // 得到被代理方法的返回值类型
            Class returnType = ((MethodSignature) point.getSignature()).getReturnType();

            // 反序列化从缓存中拿到的json
            result = deserialize(value, returnType, objectType);

            if (_log.isDebugEnabled()) {
                _log.debug("反序列化结果 = {}", result);
            }
        }

        return result;
    }


    @Around(value = "pointcut2(czRedisEvict)")
    public Object evictCache(ProceedingJoinPoint point,CzRedisEvict czRedisEvict) throws Throwable {

        String clazzName = point.getTarget().getClass().getName();
        // 得到被代理的方法

        String index = PREFIX + clazzName;

        if (_log.isDebugEnabled()) {
            _log.debug("清空缓存:{}", index);
        }

        // 清除对应缓存
        RedisUtil.getRedis().delete(index);

        return point.proceed(point.getArgs());
    }



    private String genKey(String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(methodName);

        for (Object obj : args) {
            sb.append(DELIMITER);
            sb.append(obj.toString());
        }

        return sb.toString();
    }

    protected String serialize(Object target) {
        return JSON.toJSONString(target);
    }

    protected Object deserialize(String jsonString, Class clazz, Class modelType) {
        // 序列化结果应该是List对象
        if (clazz.isAssignableFrom(List.class)) {
            return JSON.parseArray(jsonString, modelType);
        }

        // 序列化结果是普通对象
        return JSON.parseObject(jsonString, clazz);
    }
}
