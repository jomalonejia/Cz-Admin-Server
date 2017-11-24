package com.cz.web.security.security;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/9/30.
 */
public class TokenObject implements Serializable{

    private static final long serialVersionUID = 3313257984323818121L;

    private String token;

    public TokenObject(){}

    public TokenObject(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
