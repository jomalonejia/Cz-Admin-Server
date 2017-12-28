package com.cz.service.annotation;

import java.lang.annotation.*;

/**
 * Created by jomalone_jia on 2017/12/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CzRedisCache {
    Class type();
    int expires() default 0;
}
