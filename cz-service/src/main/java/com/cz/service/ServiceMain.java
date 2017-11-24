package com.cz.service;

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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/spring.xml" });
        context.start();
        System.out.println("----------------------------------");
        System.out.println(">>>>> cz--service is running <<<<<");
        System.out.println("----------------------------------");
        System.in.read();
    }
}
