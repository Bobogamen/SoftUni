package com.onlineshop.repository;

import com.onlineshop.model.entity.UserRole;
import com.onlineshop.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole getByName(UserRoleEnum name);
}
