package com.cz.core.session.listener;

import com.cz.core.util.cache.CacheUtil;
import com.cz.core.util.constant.CacheConstant;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by jomalone_jia on 2017/6/29.
 */
public class UserNumberListener implements HttpSessionListener{

    private static Logger _log = LoggerFactory.getLogger(UserNumberListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        setUserNumber(1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        setUserNumber(-1);
    }

    public void setUserNumber(int number){
        RLock lock = CacheUtil.getRedissonUtil().getRedisson().getLock(CacheConstant.CACHE_SESSION_LOCK);
        lock.lock(2,TimeUnit.SECONDS);
        try {
            Integer userNumber = getUserNumber()+1;
            if(userNumber >= 0){
                CacheUtil.getRedissonUtil().set(CacheConstant.CACHE_USER_NUMBER,userNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Integer getUserNumber(){
        Integer number = (Integer) CacheUtil.getRedissonUtil().get(CacheConstant.CACHE_USER_NUMBER);
        if(number != null){
            return number;
        }
        return 0;
    }
}
