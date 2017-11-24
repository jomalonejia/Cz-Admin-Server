package com.cz.common.util.constant;

/**
 * Created by jomalone_jia on 2017/6/29.
 */
public interface CacheConstant {
    public static final int CACHE_EXPIRE = 1000 * 60;
    public static final String CACHE_NAMESPACE = "CZADMIN_CACHE:";
    public static final String CACHE_USER_NUMBER = CACHE_NAMESPACE + "USER_NUMBER";
    public static final String CACHE_SESSION_LOCK = CACHE_NAMESPACE + "SESSION_LOCK";
}
