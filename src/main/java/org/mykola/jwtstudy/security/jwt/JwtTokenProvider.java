package org.mykola.jwtstudy.security.jwt;

import org.mykola.jwtstudy.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTokenProvider {
    public String createToken(String username, List<Role>roles){

        return null;
    }

    public Authentication getAuthentication(String token){
        return null;
    }

    public String getUsername(String token) {
        return null;
    }
    public boolean validateToken(String token){
        return true;
    }
    private List<String> getRoleNames(List<Role>userRoles){
        List<String>result = new ArrayList<>();
        userRoles.forEach(role -> {
            result.add(role.getName());
        });
        return result;
    }
}
