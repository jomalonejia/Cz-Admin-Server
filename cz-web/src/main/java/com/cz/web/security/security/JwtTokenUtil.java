package com.cz.web.security.security;

import com.cz.common.util.constant.JwtConstant;
import com.cz.common.util.constant.SecurityConstant;
import com.cz.web.security.utils.TimeProvider;
import com.cz.dto.user.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    @Autowired
    private TimeProvider timeProvider;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(JwtConstant.CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(JwtConstant.CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(JwtConstant.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(timeProvider.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (JwtConstant.AUDIENCE_TABLET.equals(audience) || JwtConstant.AUDIENCE_MOBILE.equals(audience));
    }

    /*public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(JwtConstant.CLAIM_KEY_USERNAME, userDetails.getUsername());

        final Date createdDate = timeProvider.now();
        claims.put(JwtConstant.CLAIM_KEY_CREATED, createdDate);

        return doGenerateToken(claims);
    }*/

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(JwtConstant.CLAIM_KEY_USERNAME, username);

        final Date createdDate = timeProvider.now();
        claims.put(JwtConstant.CLAIM_KEY_CREATED, createdDate);

        return doGenerateToken(claims);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        final Date createdDate = (Date) claims.get(JwtConstant.CLAIM_KEY_CREATED);
        final Date expirationDate = new Date(createdDate.getTime() + JwtConstant.EXPIRATION * 1000);

        System.out.println("doGenerateToken " + createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean canTokenBeRefreshed(String token) {
        final Long expireTime = getExpirationDateFromToken(token).getTime();
        final Long nowTime = timeProvider.now().getTime();
        return ((expireTime > nowTime) && ((expireTime - nowTime) / 1000 * 60) < JwtConstant.CAN_REFRESH_MINUTES);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(JwtConstant.CLAIM_KEY_CREATED, timeProvider.now());
            refreshedToken = doGenerateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String authToken;
        authToken = request.getHeader(SecurityConstant.TOKEN_NAME);
        if (authToken != null) {
            if (authToken.startsWith(SecurityConstant.TOEKN_PRIFIX)) {
                authToken = authToken.substring(SecurityConstant.TOEKN_PRIFIX.length());
            }
        }
        return authToken;
    }

    public Object test1(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JwtConstant.SECRET)
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        return expiration;
    }
}