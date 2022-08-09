package com.onlineshop.web;

import com.onlineshop.model.dto.OrderDTO;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.AddressService;
import com.onlineshop.service.CategoryService;
import com.onlineshop.service.ItemService;
import com.onlineshop.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final AddressService addressService;
    private final OrderService orderService;

    public ShopController(ItemService itemService,
                          CategoryService categoryService,
                          AddressService addressService,
                          OrderService orderService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView shop() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop");
        modelAndView.addObject("allItem", this.itemService.getAllItems());
        modelAndView.addObject("allCategories", this.categoryService.getAllCategories());


        return modelAndView;
    }

    @GetMapping("/discount")
    public ModelAndView shopDiscount() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop");
        modelAndView.addObject("allItem", this.itemService.getAllItemWithDiscount());
        modelAndView.addObject("allCategories", this.categoryService.getAllCategories());

        return modelAndView;
    }

    @GetMapping("/item/{id}")
    public ModelAndView item(@PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("item");
        modelAndView.addObject("item", this.itemService.getItemById(id));

        return modelAndView;
    }

    @PostMapping("/buy/{id}")
    public String buy(@PathVariable long id,
                      RedirectAttributes redirectAttributes,
                      @AuthenticationPrincipal ShopUserDetails user) {

        user.addItem(this.itemService.getItemById(id));

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/shop/item/{id}";
    }


    @GetMapping("/cart")
    public ModelAndView cart(@AuthenticationPrincipal ShopUserDetails user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        modelAndView.addObject("cartItems", user.getCart());
        modelAndView.addObject("user", user);
        modelAndView.addObject("addresses", this.addressService.getAddressesByUserEntityId(user.getId()));

        return modelAndView;
    }

    @ModelAttribute("orderDTO")
    public OrderDTO orderDTO() {
        return new OrderDTO();
    }

    @PostMapping("/order")
    public String order(OrderDTO orderDTO,
                        @AuthenticationPrincipal ShopUserDetails user,
                        RedirectAttributes redirectAttributes) {

        this.orderService.newOrder(orderDTO, user);
        user.getCart().clear();

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/profile";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable long id,
                      RedirectAttributes redirectAttributes,
                      @AuthenticationPrincipal ShopUserDetails user) {

        user.removeCartItem(id);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/shop/cart";
    }
}
