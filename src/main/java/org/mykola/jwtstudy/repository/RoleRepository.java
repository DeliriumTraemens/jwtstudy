package org.mykola.jwtstudy.repository;

import org.mykola.jwtstudy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
