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
        String clazzName = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();

        String key = genKey(methodName, args);
        if (_log.isDebugEnabled()) {
            _log.debug("generator key:{}", key);
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();


        Class objectType = method.getAnnotation(CzRedisCache.class).type();

        String index = PREFIX + clazzName;

        String value = (String) RedisUtil.getRedis().opsForHash().get(index, key);
        Object result = null;
        if (null == value) {
            if (_log.isDebugEnabled()) {
                _log.debug("get null redis cache");
            }

            result = point.proceed(args);

            String json = serialize(result);

            RedisUtil.getRedis().opsForHash().put(index, key, json);
        } else {
            if (_log.isDebugEnabled()) {
                _log.debug("get redis cache, value = {}", value);
            }

            Class returnType = ((MethodSignature) point.getSignature()).getReturnType();

            result = deserialize(value, returnType, objectType);
        }

        return result;
    }


    @Around(value = "pointcut2(czRedisEvict)")
    public Object evictCache(ProceedingJoinPoint point,CzRedisEvict czRedisEvict) throws Throwable {

        String clazzName = point.getTarget().getClass().getName();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();


        String[] evicts = method.getAnnotation(CzRedisEvict.class).evicts();

        String index = PREFIX + clazzName;

        if (evicts.length > 0){
            for (String evict : evicts) {
                RedisUtil.getRedis().opsForHash().delete(index,evict);
            }
        }else{
            if (_log.isDebugEnabled()) {
                _log.debug("clear redis cache:{}", index);
            }

            RedisUtil.getRedis().delete(index);
        }


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
        if (clazz.isAssignableFrom(List.class)) {
            return JSON.parseArray(jsonString, modelType);
        }

        return JSON.parseObject(jsonString, clazz);
    }
}
