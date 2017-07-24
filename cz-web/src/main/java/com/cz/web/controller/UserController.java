package com.cz.web.controller;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.api.service.IUserService;
import com.cz.core.util.constant.SecurityConstant;
import com.cz.model.User;
import com.cz.security.security.JwtTokenUtil;
import com.cz.security.security.service.JwtAuthenticationResponse;
import com.cz.user.JwtAuthenticationRequest;
import com.cz.user.JwtUser;
import com.cz.user.SettingsUser;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by jomalone_jia on 2017/6/26.
 */
@RestController
@RequestMapping("/user")
public class UserController implements ApplicationContextAware {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    private ApplicationContext context;

    @Autowired
    private IUserService userService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @RequestMapping("/list")
    public Object list(ModelAndView modelAndView, HttpServletRequest request,HttpServletResponse response) {
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", userService.selectList(null));
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        /*_log.info(request.getHeader("x-auth-token").toString());*/
       /* _log.info(response.getHeader("x-auth-token").toString());*/
        return userService.selectList(null);
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
            return ResponseEntity.ok("logout success");
        }else{
            return ResponseEntity.ok("you are not login");
        }

    }


    @GetMapping("/getSettings")
    public ResponseEntity<?> getSettings(@RequestParam(value = "username",required = true) String username,HttpServletRequest request){
        User user = userService.getUserByUsername(username);
        SettingsUser settingsUser = new SettingsUser();
        settingsUser.setFirstName(user.getFirstname());
        settingsUser.setLastName(user.getLastname());
        settingsUser.setUsername(user.getUsername());
        settingsUser.setEmail(user.getEmail());
        return ResponseEntity.ok(settingsUser);
    }

    @PostMapping("/setSettings")
    public ResponseEntity<?> updateSettings(@RequestBody SettingsUser settingsUseruser) {
        Integer flag = userService.updateUserSettings(settingsUseruser);
        return ResponseEntity.ok(flag);
    }

    @PostMapping(value = "/login" )
    public ResponseEntity<?> auth(@RequestBody JwtAuthenticationRequest requestBoby, HttpServletRequest request) throws AuthenticationException {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestBoby.getUsername(),requestBoby.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser user = (JwtUser) this.userDetailsService.loadUserByUsername(requestBoby.getUsername());
        String token = this.jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token,user.getImgUrl(),user.getUsername()));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.TOKEN_NAME);
        String username = this.jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) this.userDetailsService.loadUserByUsername(username);
        _log.info(user.toString());
        if (this.jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = this.jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

}


