package com.onlineshop.repository;

import com.onlineshop.model.entity.Role;
import com.onlineshop.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Role getByName(RoleEnum name);
}
