package com.onlineshop.service;

import com.onlineshop.model.entity.Role;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.AddressRepository;
import com.onlineshop.repository.RoleRepository;
import com.onlineshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;

    public AdminService(UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUser(long id) {
        UserEntity userById = userRepository.getUserById(id);

        userById.getAddress().forEach(a -> addressRepository.deleteById(a.getId()));

        this.userRepository.deleteById(id);
    }

    public boolean makeAdminByUserId(long id) {

        UserEntity userById = userRepository.getUserById(id);
        Set<Role> userRoles = userById.getUserRoles();

        if (userRoles.size() == 2) {
            return false;
        }

        userRoles.add(this.roleRepository.getByName(RoleEnum.ADMIN));

        userById.setRoles(userRoles);

        this.userRepository.save(userById);

        return true;
    }

    public boolean makeModeratorById(long id) {

        UserEntity userById = userRepository.getUserById(id);
        Set<Role> userRoles = userById.getUserRoles();

        if (userRoles.size() == 0) {

            userRoles.add(this.roleRepository.getByName(RoleEnum.MODERATOR));

            userById.setRoles(userRoles);

            this.userRepository.save(userById);

            return true;
        } else {
            return false;
        }
    }
}
