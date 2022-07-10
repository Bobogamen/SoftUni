package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.LoginDTO;
import com.example.spotifyplaylistapp.model.dto.RegistrationDTO;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;



    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public void register(RegistrationDTO registrationDTO) {

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(user);

    }

    public boolean login(LoginDTO loginDTO) {

        if (userRepository.findByUsername(loginDTO.getUsername()).isEmpty()) {
            return false;
        }

        User user = userRepository.getByUsername(loginDTO.getUsername());


        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return false;
        }

        loggedUser.setId(user.getId());
        loggedUser.setUsername(user.getUsername());

        System.out.println();

        return true;
    }

    public void logout() {
        loggedUser.clear();
    }

    public User getUser(long id) {
        return this.userRepository.findUserById(id);
    }
}

