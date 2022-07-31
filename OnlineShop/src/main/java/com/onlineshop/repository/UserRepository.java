package com.onlineshop.repository;

import com.onlineshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);

    UserEntity getUserById(long id);

    @Query("SELECT name FROM UserEntity WHERE id=:id")
    String getNameByUserEntityId(@Param("id") long id);
}
