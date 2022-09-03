package com.onlineshop.service;

import com.onlineshop.model.entity.Role;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopUserDetailServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private ShopUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new ShopUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExist() {

        //arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail("test@mail.test");
        testUser.setName("Test");
        testUser.setPassword("password");
        testUser.setRegisteredOn(LocalDateTime.now());
        testUser.addRole(new Role(RoleEnum.ADMIN));
        testUser.addRole(new Role(RoleEnum.MODERATOR));

        when(mockUserRepo.findUserByEmail(testUser.getEmail())).
                thenReturn(Optional.of(testUser));

        //act
        ShopUserDetails userDetails = (ShopUserDetails) toTest.loadUserByUsername(testUser.getEmail());

        //assert
        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getName(), userDetails.getName());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getRegisteredOn(), userDetails.getRegisteredOn());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//
//        GrantedAuthority authority1 = iterator.next();
//        GrantedAuthority authority2 = iterator.next();
//
//
//        Assertions.assertEquals("ROLE_" + RoleEnum.ADMIN.name(), authority1.getAuthority());
//        Assertions.assertEquals("ROLE_" + RoleEnum.MODERATOR.name(), authority2.getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserNotExist() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("no_mail@mail.not"));
    }

}
