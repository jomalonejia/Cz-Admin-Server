package com.cz.web.controller;
import com.cz.api.service.IUserService;
import com.cz.core.base.BaseController;
import com.cz.core.util.constant.SecurityConstant;
import com.cz.core.util.qiniu.PictureUtil;
import com.cz.model.User;
import com.cz.security.security.JwtTokenUtil;
import com.cz.security.security.service.JwtAuthenticationResponse;
import com.cz.user.DtoUser;
import com.cz.security.security.JwtAuthenticationRequest;
import com.cz.user.JwtUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/26.
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user",description = "User Controller")
public class UserController extends BaseController implements ApplicationContextAware {

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

    @GetMapping("/test")
    public ResponseEntity<?> test(HttpServletResponse response,@RequestParam String token){
        Object o = jwtTokenUtil.test1(token);
        return ResponseEntity.ok(o);
    }

    @PostMapping("/test1")
    public ResponseEntity<?> test1(HttpServletResponse response){
        response.addHeader("aluba1","aluba1");
        return ResponseEntity.ok("success1");
    }

    @PostMapping(value = "/login" )
    @ApiOperation(value = "user login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest requestBoby, HttpServletRequest request ,HttpServletResponse response) throws AuthenticationException {
        JwtUser user = null;
        String token = null;
        try {
            String username = requestBoby.getEmail() != null ?requestBoby.getEmail():requestBoby.getUsername();
            String password = requestBoby.getPassword();
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            user = (JwtUser) this.userDetailsService.loadUserByUsername(username);
            token = this.jwtTokenUtil.generateToken(user.getUsername());
            return new ResponseEntity(new JwtAuthenticationResponse(token,user.getProfile(),user.getUsername(),user.getFullname(),user.getId()),HttpStatus.ACCEPTED);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "user logout")
    public Object logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
            return ResponseEntity.ok("logout success");
        }else{
            return ResponseEntity.ok("you are not login");
        }
    }

    @PostMapping(value = "/register" )
    @ApiOperation(value = "user register")
    public ResponseEntity<?> register(@RequestBody DtoUser dtoUser,HttpServletResponse response) throws AuthenticationException {
        String token = null;
        User user = null;
        response.setHeader("register","register header");
        try {
            String username = dtoUser.getUsername() != null ? dtoUser.getUsername() : dtoUser.getEmail();
            if(userService.isUserExsit(username)){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            dtoUser.setUsername(username);
            token = this.jwtTokenUtil.generateToken(username);
            user = userService.registerUser(dtoUser);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token,user.getProfile(),user.getUsername(),user.getFullname(),user.getId()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getSettings")
    @ApiOperation(value = "get user settings")
    public ResponseEntity<?> getSettings(@RequestParam(value = "username",required = true) String username,HttpServletRequest request){
        User user = userService.getUserByUsername(username);
        DtoUser dtoUser = new DtoUser();
        dtoUser.setUsername(user.getUsername());
        return ResponseEntity.ok(dtoUser);
    }

    @PostMapping("/setSettings")
    @ApiOperation(value = "update user settings")
    public ResponseEntity<?> updateSettings(@RequestBody DtoUser dtoUseruser) {
        Integer flag = userService.updateUserSettings(dtoUseruser);
        return ResponseEntity.ok(flag);
    }



    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    @ApiOperation(value = "user refresh token")
    public ResponseEntity<?> refresh(HttpServletRequest request,HttpServletResponse response) {

        try {
            String authToken = jwtTokenUtil.getTokenFromRequest(request);
            _log.info(authToken);
            if (this.jwtTokenUtil.canTokenBeRefreshed(authToken)){
                String refreshedToken = this.jwtTokenUtil.refreshToken(authToken);
                return new ResponseEntity(new JwtAuthenticationResponse(refreshedToken),HttpStatus.RESET_CONTENT);
            }else{
                return ResponseEntity.ok(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/profileUpload")
    @ApiOperation(value = "user profile update")
    public ResponseEntity<?> profileUpload(@RequestParam("uploadedfile") MultipartFile file,HttpServletRequest request) {
        String username;
        String pictureResponse;
        try {
            username = request.getAttribute("username").toString();
            pictureResponse = PictureUtil.getInstance().uploadPicture(file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
        Integer flag = userService.updateUserProfile(pictureResponse, username);
        return ResponseEntity.ok(pictureResponse);
    }

    @GetMapping("/listUserWithRole")
    @ApiOperation(value = "list user with role")
    public ResponseEntity<?> listUserWithRole() {
        List<User> users = userService.listUserWithRole();
        return ResponseEntity.ok(users);
    }


    @PostMapping("/updateUser")
    @ApiOperation(value = "update user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Boolean success = userService.updateUserWithRole(user);
        if(success){
            return ResponseEntity.ok("UpdateUserWithRole Success");
        }
        else{
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "update user")
    public ResponseEntity<?> deleteUser(@RequestParam  Long id) {
        return ResponseEntity.ok(userService.deleteUserWithRole(id));
    }

    @GetMapping("/listRelatedUsers")
    @ApiOperation(value = "list related user")
    public ResponseEntity<?> listRelatedUsers(@RequestParam  Long userId) {
        _log.info(userService.listRelatedUsers(userId).toString());
        return ResponseEntity.ok(userService.listRelatedUsers(userId));
    }

}


