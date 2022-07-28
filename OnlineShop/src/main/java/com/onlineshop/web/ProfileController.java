package com.onlineshop.web;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.AddressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final AddressService addressService;

    public ProfileController(AddressService addressService) {
        this.addressService = addressService;

    }

    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("addresses", user.getAddresses());
        modelAndView.setViewName("profile.html");


        return modelAndView;
    }

    @ModelAttribute("addAddressDTO")
    public AddAddressDTO addAddressDTO() {
        return new AddAddressDTO();
    }

    @PostMapping("/add-address")
    public String addAddress(AddAddressDTO addAddressDTO,
                             @AuthenticationPrincipal ShopUserDetails user,
                             RedirectAttributes redirectAttributes) {

        this.addressService.addAddress(addAddressDTO, user);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }
}
