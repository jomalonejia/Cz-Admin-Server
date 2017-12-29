package com.cz.service.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cz.common.base.BaseServiceImpl;
import com.cz.common.util.constant.CacheConstant;
import com.cz.api.service.IUserService;
import com.cz.mapper.UserMapper;
import com.cz.mapper.UserRelationshipMapper;
import com.cz.mapper.UserRoleMapper;
import com.cz.model.personal.Role;
import com.cz.model.personal.User;
import com.cz.model.personal.UserRole;
import com.cz.dto.user.DtoUser;
import com.cz.service.annotation.CzRedisCache;
import com.cz.service.annotation.CzRedisEvict;
import com.cz.service.util.CastUtil;
import com.cz.service.util.UserConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "user_cache")
public class UserService extends BaseServiceImpl<UserMapper,User> implements IUserService {

    private static Logger _log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserRelationshipMapper userRelationshipMapper;


    @Override
    @CzRedisCache(type = User.class)
    public User loadUserByUsername(String username) {
        try {
            User user = userMapper.loadUserByUsername(username);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CzRedisCache(type = User.class)
    public User getUserByUsername(String username) {
        try {
            User userEntity = new User();
            userEntity.setUsername(username);
            User user = userMapper.selectOne(userEntity);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CzRedisEvict
    public Integer updateUserSettings(DtoUser dtoUser) {
        try {
            EntityWrapper<User> ew = new EntityWrapper<User>();
            ew.where("username={0}", dtoUser.getUsername());
            User user = CastUtil.castDtoUserToUser(dtoUser);
            user.setUsername("");
            return userMapper.update(user, ew);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CzRedisEvict
    public Integer updateUserProfile(String profileName, String username) {
        try {
            EntityWrapper<User> ew = new EntityWrapper<User>();
            ew.where("username={0}", username);
            User user = new User();
            user.setProfile(profileName);
            user.setLastPasswordResetDate(new Date());
            return userMapper.update(user, ew);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CzRedisEvict
    @Transactional
    public Boolean updateUserWithRole(User user) {

        try {
            EntityWrapper<User> ew = new EntityWrapper<User>();
            ew.where("id={0}", user.getId());
            User updateUser = new User();
            updateUser.setLastPasswordResetDate(new Date());
            userMapper.update(user, ew);
            EntityWrapper<UserRole> ew2 = new EntityWrapper<UserRole>();
            ew2.where("user_id={0}", user.getId());
            userRoleMapper.delete(ew2);
            userRoleMapper.deleteById(user.getId());
            for (Role role : user.getRoles()) {
                userRoleMapper.insert(new UserRole(user.getId(), role.getId()));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object updateuserrole(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    @Override
    @CzRedisCache(type = User.class)
    public List<User> listUserWithRole() {
        try {
            List<User> users = userMapper.listAllUser();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    @CzRedisCache(type = Page.class)
    public Page listUserWithRole(Page<User> page) {
        try {
            page.setRecords(userMapper.listAllUser(page));
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CzRedisEvict
    @Transactional
    public Integer deleteUserWithRole(Long id) {
        try {
            Integer flag = userMapper.deleteById(id);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CzRedisEvict
    @Transactional
    public User registerUser(DtoUser dtoUser) {
        try {
            User user = CastUtil.castDtoUserToUser(dtoUser);
            user.setProfile(UserConstants.DEFAULT_IMAGE_URL);
            user.setEnabled(true);
            userMapper.insert(user);
            UserRole userRole = new UserRole(user.getId(), UserConstants.DEFAULT_ROLE_ID);
            userRoleMapper.insert(userRole);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CzRedisCache(type = Boolean.class)
    public Boolean isUserExsit(String username) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("username={0}", username);
        return userMapper.selectCount(ew) > 0 ? true : false;
    }

    @Override
    @CzRedisCache(type = User.class)
    public List<User> listRelatedUsers(Long userId) {
        try {
            List<Long> ids = userRelationshipMapper.listRelatedId(userId);
            List<User> users = userMapper.selectBatchIds(ids);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}