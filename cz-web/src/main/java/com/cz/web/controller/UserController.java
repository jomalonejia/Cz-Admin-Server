package com.cz.web.controller;
import com.cz.api.service.IUserService;
import com.cz.core.util.constant.SecurityConstant;
import com.cz.model.JwtUser;
import com.cz.security3.security.JwtTokenUtil;
import com.cz.security3.security.service.JwtAuthenticationResponse;
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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public Object login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    @GetMapping("/logout")
    public Object logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
            return "logout success";
        }else{
            return "you are not login ";
        }

    }


    @RequestMapping("/set")
    public Object set(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().setAttribute("testsession","123");
        return userService.selectList(null);
    }

    @RequestMapping("/get")
    public Object get(ModelAndView modelAndView, HttpServletRequest request) {

        return "~~";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


    @RequestMapping(value = "/auth" , method = RequestMethod.POST)
    public ResponseEntity<?> auth(HttpServletRequest request) throws AuthenticationException {

        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getParameter("username"),request.getParameter("password")));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getParameter("username"));
        String token = this.jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
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
