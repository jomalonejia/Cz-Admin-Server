package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.User;
import com.cz.user.SettingsUser;

/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface IUserService extends IService<User>{
    User loadUserByUsername(String username);
    User getUserByUsername(String username);
    Integer updateUserSettings(SettingsUser settingsUseruser);
}
