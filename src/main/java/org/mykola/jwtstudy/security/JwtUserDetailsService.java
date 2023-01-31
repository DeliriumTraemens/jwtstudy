package org.mykola.jwtstudy.security;

import lombok.extern.slf4j.Slf4j;
import org.mykola.jwtstudy.model.User;
import org.mykola.jwtstudy.security.jwt.JwtUser;
import org.mykola.jwtstudy.security.jwt.JwtUserFactory;
import org.mykola.jwtstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userservice) {
        this.userService = userservice;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User with username: "+ username+" not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} "+username+" is successfully created");
        return jwtUser;
    }
}
