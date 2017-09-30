package com.cz.security.security;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private  String token;

    private  String profile;

    private String username;

    private Long userId;

    private String fullname;

    public JwtAuthenticationResponse() {}

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public JwtAuthenticationResponse(String token,String profile,String username,String fullname,Long userId) {
        this.token = token;
        this.profile = profile;
        this.username = username;
        this.fullname = fullname;
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public String getProfile() { return this.profile;}

    public String getUsername() {return this.username;}

    public Long getUserId() {return this.userId;}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "token='" + token + '\'' +
                ", profile='" + profile + '\'' +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
