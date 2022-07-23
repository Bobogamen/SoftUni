package com.onlineshop.repository;


import com.onlineshop.model.dto.UsersInfoDTO;
import com.onlineshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);

    UserEntity getUserById(long id);

    @Query("SELECT new com.onlineshop.model.dto.UsersInfoDTO(u.id, u.email, u.name, u.address.size, 0, 0.0) FROM UserEntity AS u")
    List<UsersInfoDTO> getAllUsersInfo();

}
