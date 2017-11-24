package com.cz.web.security.security;

import com.cz.model.personal.Role;
import com.cz.model.personal.User;
import com.cz.dto.user.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFullname(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getEnabled(),
                user.getLastPasswordResetDate(),
                user.getProfile()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
                .collect(Collectors.toList());
    }
}
