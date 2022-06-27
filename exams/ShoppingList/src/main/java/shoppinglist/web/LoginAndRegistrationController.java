package shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppinglist.entity.DTO.LoginDTO;
import shoppinglist.entity.DTO.RegistrationDTO;
import shoppinglist.entity.User;
import shoppinglist.repository.UserRepository;
import shoppinglist.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginAndRegistrationController {

    private final UserService userService;
    private final UserRepository userRepository;

    public LoginAndRegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("registrationDTO")
    public RegistrationDTO initRegistrationDTO() {
        return new RegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        Optional<User> username = userRepository.findByUsername(registrationDTO.getUsername());
        Optional<User> email = userRepository.findByEmail(registrationDTO.getEmail());

        boolean uniqueUsernameAndEmail = true;

        if (username.isPresent()) {
            uniqueUsernameAndEmail = false;
        } else if (email.isPresent()) {
            uniqueUsernameAndEmail = false;
        }

        boolean passwordMatch = registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword());

        if (bindingResult.hasErrors() || !passwordMatch || !uniqueUsernameAndEmail) {

            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        userService.register(registrationDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if(!userService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}