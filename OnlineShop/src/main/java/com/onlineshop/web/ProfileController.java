package com.onlineshop.web;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.AddressService;
import com.onlineshop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final AddressService addressService;
    private final UserService userService;

    public ProfileController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("addresses", getUserAddresses(user));
        modelAndView.setViewName("profile.html");


        return modelAndView;
    }

    private List<Address> getUserAddresses(ShopUserDetails user) {
        return this.addressService.getAddressesById(user.getId());
    }

    @GetMapping("/profile/edit")
    public ModelAndView editAddress(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", user.getName());
        modelAndView.addObject("addresses", getUserAddresses(user));
        modelAndView.setViewName("edit-profile.html");


        return modelAndView;
    }

    @ModelAttribute("addAddressDTO")
    public AddAddressDTO addAddressDTO()  {
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

    @PostMapping("/profile/edit")
    public ModelAndView editProfile(@AuthenticationPrincipal ShopUserDetails user, String name) {

        this.userService.editNameByUserId(user.getId(), String name);




        return profile(user);
    }
}
