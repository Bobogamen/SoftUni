package bg.softuni.com.shoppinglist.service;

import bg.softuni.com.shoppinglist.entity.DTO.LoginDTO;
import bg.softuni.com.shoppinglist.entity.DTO.RegistrationDTO;
import bg.softuni.com.shoppinglist.entity.Product;
import bg.softuni.com.shoppinglist.entity.User;
import bg.softuni.com.shoppinglist.repository.UserRepository;
import bg.softuni.com.shoppinglist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
