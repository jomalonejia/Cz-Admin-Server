package testAop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by admin on 2017/6/23.
 */
@Aspect
public class Around2 {

    @Pointcut("execution(* *.sleep())")
    public void sleep(){
        System.out.println("sleep...");
    }

    @Before("sleep()")
    public void before(){
        System.out.println("before");
    }

    @After("sleep()")
    public void after(){
        System.out.println("after");
    }
}
