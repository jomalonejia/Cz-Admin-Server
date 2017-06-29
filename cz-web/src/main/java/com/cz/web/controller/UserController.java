package com.cz.web.controller;

import com.cz.api.service.IUserService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jomalone_jia on 2017/6/26.
 */
@RestController
@RequestMapping("/user")
public class UserController implements ApplicationContextAware {

    private ApplicationContext context;

    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public Object list(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", userService.selectList(null));
        RedissonClient redissonClient = (RedissonClient) context.getBean("redissonClient");
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        return userService.selectList(null);
    }


    @RequestMapping("/set")
    public Object set(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().setAttribute("testsession","123");
        return userService.selectList(null);
    }

    @RequestMapping("/get")
    public Object get(ModelAndView modelAndView, HttpServletRequest request) {
        String testsession = request.getSession().getAttribute("testsession").toString();
        return testsession + "~~";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
