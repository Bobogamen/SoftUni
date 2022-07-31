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
    @ModelAttribute("addAddressDTO")
    public AddAddressDTO addAddressDTO()  {
        return new AddAddressDTO();
    }

    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", this.userService.getNameByUserEntityId(user.getId()));
        modelAndView.addObject("addresses", getUserAddresses(user));
        modelAndView.setViewName("profile");

        return modelAndView;
    }

    private List<Address> getUserAddresses(ShopUserDetails user) {
        return this.addressService.getAddressesByUserEntityId(user.getId());
    }

    @GetMapping("/profile/edit-name")
    public ModelAndView editProfile(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("addresses", getUserAddresses(user));
        modelAndView.addObject("userName", this.userService.getNameByUserEntityId(user.getId()));
        modelAndView.setViewName("edit-name");

        return modelAndView;
    }

    @PostMapping("/profile/edit-name")
    public String editName(@AuthenticationPrincipal ShopUserDetails user,
                           String name, RedirectAttributes redirectAttributes) {

        this.userService.editNameByUserId(user.getId(), name);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }

    @GetMapping("/profile/edit-address/{id}")
    public ModelAndView editAddress(@PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("addressById", this.addressService.getAddressesById(id));
        modelAndView.setViewName("edit-address");

        return modelAndView;
    }

    @PostMapping("/profile/edit-address/{id}")
    public String editAddress(@PathVariable long id, AddAddressDTO addAddressDTO,
                              RedirectAttributes redirectAttributes) {
        this.addressService.editAddressById(id, addAddressDTO);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }

    @PostMapping("/add-address")
    public String addAddress(AddAddressDTO addAddressDTO,
                             @AuthenticationPrincipal ShopUserDetails user,
                             RedirectAttributes redirectAttributes) {

        this.addressService.addAddress(addAddressDTO, user);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }

    @PostMapping("/profile/delete/{id}")
    public String deleteAddress(@PathVariable long id, RedirectAttributes redirectAttributes) {
        this.addressService.deleteAddressById(id);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }
}
