package com.onlineshop.web;

import com.onlineshop.model.dto.AddAddressDTO;
import com.onlineshop.model.entity.Address;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.AddressService;
import com.onlineshop.service.OrderService;
import com.onlineshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final AddressService addressService;
    private final UserService userService;
    private final OrderService ordersService;

    public ProfileController(AddressService addressService, UserService userService, OrderService ordersService) {
        this.addressService = addressService;
        this.userService = userService;
        this.ordersService = ordersService;
    }
    @ModelAttribute("addAddressDTO")
    public AddAddressDTO addAddressDTO()  {
        return new AddAddressDTO();
    }

    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", this.userService.getNameByUserEntityId(user.getId()));
        modelAndView.addObject("addresses", this.addressService.getAddressesByUserEntityId(user.getId()));
        modelAndView.addObject("orders", this.ordersService.getAllOrdersByUserEntityId(user.getId()));
        modelAndView.setViewName("profile");

        return modelAndView;
    }

    @GetMapping("/view-order/{id}")
    public ModelAndView viewOrder(@PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", this.ordersService.getOrderById(id));
        modelAndView.setViewName("view-order");

        return modelAndView;
    }

    @GetMapping("/profile/edit-name")
    public ModelAndView editProfile(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", this.userService.getNameByUserEntityId(user.getId()));
        modelAndView.setViewName("edit-name");

        return modelAndView;
    }

    @PostMapping("/profile/edit-name")
    public String editName(@AuthenticationPrincipal ShopUserDetails user, String name, RedirectAttributes redirectAttributes) {

        this.userService.editNameByUserId(user.getId(), name);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }

    @GetMapping("/profile/edit-address/{id}")
    public ModelAndView editAddress(@PathVariable long id, @AuthenticationPrincipal ShopUserDetails user) {

        long userId = user.getId();
        Address addressesById = this.addressService.getAddressesById(id);

        if (userId == addressesById.getUserEntity().getId()) {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("addressById", addressesById);
            modelAndView.setViewName("edit-address");

            return modelAndView;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
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
