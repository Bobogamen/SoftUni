package com.onlineshop.web;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.service.AddressService;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/add-address/{profile-id}")
    public String addAddress(@PathVariable("profile-id") long profileId, AddAddressDTO addAddressDTO) {

        System.out.println();

        this.addressService.addAddress(addAddressDTO, profileId);

        return "redirect:/users/profile";
    }
}
