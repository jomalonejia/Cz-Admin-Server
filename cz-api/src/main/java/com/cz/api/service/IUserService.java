package com.cz.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cz.model.User;
import com.cz.model.UserRole;
import com.cz.user.SettingsUser;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface IUserService extends IService<User>{
    User loadUserByUsername(String username);
    User getUserByUsername(String username);
    Integer updateUserSettings(SettingsUser settingsUseruser);
    Integer updateUserProfile(String profileName,String username);
    Boolean updateUserWithRole(User user);
    Integer deleteUserWithRole(Long id);
    Page listUserWithRole(Page<User> page);
    List<User> listUserWithRole();
}
