package com.cz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public interface UserMapper extends BaseMapper<User>{
    User loadUserByUsername(String username);
}
