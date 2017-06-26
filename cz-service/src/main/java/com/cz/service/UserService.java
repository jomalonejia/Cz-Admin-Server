package com.cz.service;

import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.UserMapper;
import com.cz.model.User;
import com.cz.api.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/6/20.
 */
@Service
public class UserService extends BaseServiceImpl<UserMapper,User> implements IUserService{
}
