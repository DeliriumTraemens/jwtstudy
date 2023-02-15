package org.mykola.jwtstudy.rest;

import liquibase.pro.packaged.E;
import lombok.extern.slf4j.Slf4j;
import org.mykola.jwtstudy.dto.AuthenticationRequestDto;
import org.mykola.jwtstudy.model.User;
import org.mykola.jwtstudy.security.jwt.JwtAuthenticationException;
import org.mykola.jwtstudy.security.jwt.JwtTokenProvider;
import org.mykola.jwtstudy.security.jwt.JwtUser;
import org.mykola.jwtstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@Slf4j
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;


    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto, HttpServletRequest request) {
        try {
            String username = requestDto.getUsername();
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            JwtUser principal = (JwtUser) authenticate.getPrincipal();

            User user = userService.findByUsername(principal.getUsername());
                if (user == null) {
                    throw new UsernameNotFoundException("User with username: " + username + " not found");
                }

            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("AUTH is BRoken"+e.getStackTrace());
            throw new BadCredentialsException("Invalid username or password");
//            throw new BadCredentialsException("Invalid username or password");
//            return ResponseEntity.ok("TUCHES");
        } finally {
            System.out.println("=======\n Hmmmmm \n =========");
        }

    }
}
