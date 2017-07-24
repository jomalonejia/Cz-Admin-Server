package com.cz.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.UserMapper;
import com.cz.model.User;
import com.cz.api.service.IUserService;
import com.cz.user.SettingsUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public User getUserByUsername(String username){
        User userEntity = new User();
        userEntity.setUsername(username);
        User user = userMapper.selectOne(userEntity);
        return user;
    }

    @Override
    public Integer updateUserSettings(SettingsUser settingsUseruser) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("username={0}",settingsUseruser.getUsername());
        User user = new User();
        user.setFirstname(settingsUseruser.getFirstName());
        user.setLastname(settingsUseruser.getLastName());
        user.setEmail(settingsUseruser.getEmail());
        if(StringUtils.isNotEmpty(settingsUseruser.getPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(settingsUseruser.getPassword()));
        }
        return userMapper.update(user,ew);
    }

}
