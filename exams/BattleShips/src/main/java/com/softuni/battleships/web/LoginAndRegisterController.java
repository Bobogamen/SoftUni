package com.softuni.battleships.web;

import com.softuni.battleships.entity.DTO.LoginDTO;
import com.softuni.battleships.entity.DTO.RegistrationDTO;
import com.softuni.battleships.entity.User;
import com.softuni.battleships.repository.UserRepository;
import com.softuni.battleships.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class LoginAndRegisterController {

    private UserService userService;
    private UserRepository userRepository;

    public LoginAndRegisterController(UserService userService, UserRepository userRepository) {
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

        if (username.isEmpty()) {
            uniqueUsernameAndEmail = false;
        } else if (email.isEmpty()) {
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
}
