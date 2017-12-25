package com.cz.common.util.constant;

/**
 * Created by jomalone_jia on 2017/7/24.
 */
public interface JwtConstant {
     Long EXPIRATION = 60 * 60 * 12L;
     Integer CAN_REFRESH_MINUTES = 30;
     String SECRET = "jomalone";
     String CLAIM_KEY_USERNAME = "sub";
     String CLAIM_KEY_AUDIENCE = "audience";
     String CLAIM_KEY_CREATED = "created";
     String CLAIM_KEY_EXPIRED = "exp";
     String AUDIENCE_UNKNOWN = "unknown";
     String AUDIENCE_WEB = "web";
     String AUDIENCE_MOBILE = "mobile";
