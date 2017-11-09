package com.cz.util;

import com.cz.model.personal.User;
import com.cz.dto.user.DtoUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * Created by jomalone_jia on 2017/8/1.
 */
public class CastUtil {
    public static User castDtoUserToUser(DtoUser dtoUser){
        User user = new User();
        user.setUsername(dtoUser.getUsername());
        user.setLastPasswordResetDate(new Date());
        if(StringUtils.isNotEmpty(dtoUser.getPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(dtoUser.getPassword()));
        }
        return user;
    }
}
