package com.cz.web.controller;

import com.cz.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jomalone_jia on 2017/6/26.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public Object index(ModelAndView modelAndView) {
        //modelAndView.setViewName("index");
        //modelAndView.addObject("userList", userService.selectList(null));

        return userService.selectList(null);
    }
}
