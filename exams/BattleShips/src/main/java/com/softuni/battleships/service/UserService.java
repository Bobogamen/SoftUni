package com.softuni.battleships.service;

import com.softuni.battleships.entity.DTO.LoginDTO;
import com.softuni.battleships.entity.DTO.RegistrationDTO;
import com.softuni.battleships.entity.User;
import com.softuni.battleships.repository.UserRepository;
import com.softuni.battleships.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public void register(RegistrationDTO registrationDTO) {

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setFullName(registrationDTO.getFullName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(user);
    }


    public boolean login(LoginDTO loginDTO) {

        Optional<User> username = userRepository.findByUsername(loginDTO.getUsername());

        if (username.isEmpty()) {
            return false;
        }

        boolean passwordMatch = passwordEncoder.matches(loginDTO.getPassword(), username.get().getPassword());

        if (!passwordMatch) {
            return false;
        }

        this.loggedUser.login(username.get());

        return true;
    }
}
