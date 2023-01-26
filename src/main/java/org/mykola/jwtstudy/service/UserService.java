package org.mykola.jwtstudy.service;

import org.mykola.jwtstudy.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User>getAll();

    User findByUsername(String name);

    User findById(Long id);

    void delete(Long id);

}
