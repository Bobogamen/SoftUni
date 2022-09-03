package com.onlineshop.web;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.entity.UserEntity;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginAndRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService mockAddressService;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/register")).
                andExpect(status().isOk()).
                andExpect(view().name("/register"));
    }

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/login")).
                andExpect(status().isOk()).
                andExpect(view().name("/login"));
    }
}
