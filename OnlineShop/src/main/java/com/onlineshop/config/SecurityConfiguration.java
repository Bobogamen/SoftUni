package com.onlineshop.config;

import com.onlineshop.model.enums.RoleEnum;
import com.onlineshop.repository.UserRepository;
import com.onlineshop.service.ShopUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
             //which links can have access by who
             antMatchers("/", "/login", "/register").permitAll(). //by everyone
             antMatchers("/users/admin").hasRole(RoleEnum.ADMIN.name()). //only for admins
             antMatchers("/users/moderator").hasRole(RoleEnum.MODERATOR.name()). //only for moderators
             anyRequest().authenticated(). //any other links
        and().
             formLogin().loginPage("/login"). //setting login page
             usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
             passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
             defaultSuccessUrl("/users/profile"). //set login successful page
             failureForwardUrl("/login-fail"). //set login fail page
        and().
             logout(). //logout set
             logoutUrl("/logout"). //set logout link MUST BE "POST" (not a link) use form
             logoutSuccessUrl("/"). //after logout where redirect
             invalidateHttpSession(true).deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ShopUserDetailsService(userRepository);
        }
    }
