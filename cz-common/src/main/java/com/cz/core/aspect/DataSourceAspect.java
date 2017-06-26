package com.cz.core.aspect;

import com.cz.core.aspect.ChooseDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by admin on 2017/6/19.
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    private static Logger _log = LogManager.getLogger();

    @Pointcut("execution(* com..service..*.*(..))")
    public void aspect(){

    }


    @Before("aspect()")
    public void before(JoinPoint point){

        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        _log.info(className +"----------->" + method);
        try {
            L:for (String key : DynamicDataSource.methodType.keySet()) {
                for (String methodType : DynamicDataSource.methodType.get(key)) {
                    if(method.startsWith(methodType)){
                        ChooseDataSource.setDatSource(key);
                        break L;
                    }
                }
            }
        } catch (Exception e) {
            _log.error(e);
           ChooseDataSource.setDatSource("read");
        }
    }

    @After("aspect()")
    public void after(){
        ChooseDataSource.clearDataSource();
    }
}
