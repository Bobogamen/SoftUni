package shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shoppinglist.entity.DTO.LoginDTO;
import shoppinglist.entity.DTO.RegistrationDTO;
import shoppinglist.entity.User;
import shoppinglist.repository.UserRepository;
import shoppinglist.session.LoggedUser;

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

    public void logout() {
        loggedUser.logout();
    }
}
