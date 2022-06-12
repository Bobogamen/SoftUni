package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.model.dto.UserLoginDTO;
import bg.softuni.MobiLeLeLe.model.dto.UserRegisterDTO;
import bg.softuni.MobiLeLeLe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {

        boolean login = userService.login(userLoginDTO);

        if (login) {
            System.out.printf("User %s is logged in%n", userLoginDTO.getUsername());
        } else {
            System.out.printf("User %s fail to log in!%n", userLoginDTO.getUsername());
        }


        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";

    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register (UserRegisterDTO userRegisterDTO) {

        userService.registerAndLogin(userRegisterDTO);

        return "redirect:/";
    };

}
