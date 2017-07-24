package com.cz.security.security.service;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    private  String imgUrl;

    private String username;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public JwtAuthenticationResponse(String token,String imgUrl,String username) {
        this.token = token;
        this.imgUrl = imgUrl;
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public String getImgUrl() { return this.imgUrl;}

    public String getUsername() {return this.username;}

    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "token='" + token + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
