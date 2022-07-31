package com.onlineshop.model.dto;

import com.onlineshop.model.validation.PasswordMatcher;
import com.onlineshop.model.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordMatcher(password = "password",
                 confirmPassword = "confirmPassword")
public class RegistrationDTO {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Enter valid email address.")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 character")
    private String name;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 symbols")
    private String password;

    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
