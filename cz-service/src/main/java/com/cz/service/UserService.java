package com.cz.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cz.core.base.BaseServiceImpl;
import com.cz.core.util.constant.CacheConstant;
import com.cz.mapper.UserMapper;
import com.cz.mapper.UserRoleMapper;
import com.cz.model.Role;
import com.cz.model.User;
import com.cz.api.service.IUserService;
import com.cz.model.UserRole;
import com.cz.user.SettingsUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.cache.annotation.CacheRemoveAll;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "usercache")
public class UserService extends BaseServiceImpl<UserMapper,User> implements IUserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE+"loadUserByUsername")
    public User loadUserByUsername(String username) {
        User user = userMapper.loadUserByUsername(username);
        return user;
    }

    @Cacheable(value = CacheConstant.CACHE_NAMESPACE+"getUserByUsername")
    public User getUserByUsername(String username){
        User userEntity = new User();
        userEntity.setUsername(username);
        User user = userMapper.selectOne(userEntity);
        return user;
    }

    @Override
    @CacheEvict(value = {
            CacheConstant.CACHE_NAMESPACE+"loadUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"getUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"listUserWithRole"})
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

    @Override
    @CacheEvict(value = {
            CacheConstant.CACHE_NAMESPACE+"loadUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"getUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"listUserWithRole"})
    public Integer updateUserProfile(String profileName, String username){
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("username={0}",username);
        User user = new User();
        user.setImgUrl(profileName);
        return userMapper.update(user,ew);
    }

    @Override
    @Transactional
    @CacheEvict(value = {
            CacheConstant.CACHE_NAMESPACE+"loadUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"getUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"listUserWithRole"})
    public Boolean updateUserWithRole(User user) {

        try {
            EntityWrapper<User> ew = new EntityWrapper<User>();
            ew.where("id={0}",user.getId());
            User updateUser = new User();
            updateUser.setFirstname(user.getFirstname());
            updateUser.setLastname(user.getLastname());
            updateUser.setEmail(user.getEmail());
            userMapper.update(user, ew);
            EntityWrapper<UserRole> ew2 = new EntityWrapper<UserRole>();
            ew2.where("user_id={0}",user.getId());
            userRoleMapper.delete(ew2);
            userRoleMapper.deleteById(user.getId());
            for (Role role : user.getRoles()) {
                userRoleMapper.insert(new UserRole(user.getId(),role.getId()));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object updateuserrole(UserRole userRole){
        return userRoleMapper.insert(userRole);
    }

    @Override
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE+"listUserWithRole")
    public List<User> listUserWithRole() {
        List<User> users = userMapper.listAllUser();
        return users;
    }

    @Override
    public Page listUserWithRole(Page<User> page) {
        page.setRecords(userMapper.listAllUser(page));
        return page;
    }

    @Override
    @Transactional
    @CacheEvict(value = {
            CacheConstant.CACHE_NAMESPACE+"loadUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"getUserByUsername",
            CacheConstant.CACHE_NAMESPACE+"listUserWithRole"})
    public Integer deleteUserWithRole(Long id){
        Integer flag = userMapper.deleteById(id);
        return flag;
    }
}
