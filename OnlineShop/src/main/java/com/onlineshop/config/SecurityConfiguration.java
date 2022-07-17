package com.onlineshop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
             authorizeRequests(). // define which requests are allowed and which not
             requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll(). // everyone can download static resources (css, js, images)
             //which links can have access
             antMatchers("/", "/users/login", "/users/register").permitAll().
             anyRequest().authenticated(). //any other links
        and().
             formLogin().loginPage("/users/login"). //setting login page
             usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
             passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
             defaultSuccessUrl("/home"). //set login successful page
             failureForwardUrl("/"). //set login fail page
        and().
             logout(). //logout set
             logoutUrl("/users/logout"). //set logout link
             logoutSuccessUrl("/"). //after logout where redirect
             invalidateHttpSession(true).deleteCookies("JSESSIONID");

        return http.build();
    }


}
