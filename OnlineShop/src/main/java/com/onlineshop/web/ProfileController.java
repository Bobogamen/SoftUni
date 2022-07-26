package com.onlineshop.web;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.AddressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/users")
public class ProfileController {

    private final AddressService addressService;

    public ProfileController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/profile")
    public String profile() {

        return "/profile";
    }

    @ModelAttribute("addAddressDTO")
    public AddAddressDTO addAddressDTO() {
        return new AddAddressDTO();
    }

    @PostMapping("/add-address")
    public String addAddress(AddAddressDTO addAddressDTO,
                             @AuthenticationPrincipal ShopUserDetails user) {

        this.addressService.addAddress(addAddressDTO, user);

        return "redirect:/users/profile";
    }
}
