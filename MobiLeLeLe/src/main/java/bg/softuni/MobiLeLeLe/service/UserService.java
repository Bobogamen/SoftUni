package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.dto.UserRegisterDTO;
import bg.softuni.MobiLeLeLe.model.entity.UserEntity;
import bg.softuni.MobiLeLeLe.model.dto.UserLoginDTO;
import bg.softuni.MobiLeLeLe.repository.UserRepository;
import bg.softuni.MobiLeLeLe.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

        UserEntity newUser = new UserEntity();
        newUser.setActive(true);
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setFirstName(userRegisterDTO.getFirstName());
        newUser.setLastName(userRegisterDTO.getLastName());
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(newUser);

        login(newUser);
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<UserEntity> user = userRepository.findByEmail(userLoginDTO.getUsername());

        if (user.isEmpty()) {
            LOGGER.info("Account doesn't exist");
            return false;
        }

        String rawPassword = userLoginDTO.getPassword();
        String hashedPassword = user.get().getPassword();

        boolean passwordMatch = passwordEncoder.matches(rawPassword, hashedPassword);

        if (passwordMatch) {
            login(user.get());
        } else {
            logout();
        }

        return passwordMatch;
    }

    public void logout() {
        currentUser.clear();
    }

    private void login(UserEntity userEntity) {
        currentUser.setLoggedIn(true);
        currentUser.setName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }


}
