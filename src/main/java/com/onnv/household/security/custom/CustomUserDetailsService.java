package com.onnv.household.security.custom;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(ErrorConstant.USER_NOT_FOUND);
        }
        List<String> roleNames = user.getUserRoleList()
                .stream()
                .map(it -> it.getRole().getRoleName().toString())
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .userId(user.getId())
                .username(user.getEmail())
                .authorities(authorities)
                .password(user.getPassword())
                .build();
    }
}
