package com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by jomalone_jia on 2017/6/21.
 */
public class ServiceMain {


    private static final Logger _log = LoggerFactory.getLogger(ServiceMain.class);

    public static void main(String[] args) throws IOException {
        _log.info(">>>>> cz-rpc-service 正在启动 <<<<<");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/spring.xml" });
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
        //new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        _log.info(">>>>> cz-rpc-service 启动完成 <<<<<");
    }
}
