package com.cz.service;

import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.UserMapper;
import com.cz.model.JwtUser;
import com.cz.model.User;
import com.cz.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
public class UserService extends BaseServiceImpl<UserMapper,User> implements IUserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User loadUserByUsername(String username) {
        User user = userMapper.loadUserByUsername(username);
        return user;
    }

}
