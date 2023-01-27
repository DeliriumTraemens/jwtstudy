package org.mykola.jwtstudy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mykola.jwtstudy.model.Role;
import org.mykola.jwtstudy.model.Status;
import org.mykola.jwtstudy.model.User;
import org.mykola.jwtstudy.repository.RoleRepository;
import org.mykola.jwtstudy.repository.UserRepository;
import org.mykola.jwtstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role>userRoles=new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByUsername(String name) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
