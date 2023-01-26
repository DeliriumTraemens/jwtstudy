package org.mykola.jwtstudy.repository;

import org.mykola.jwtstudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
}
