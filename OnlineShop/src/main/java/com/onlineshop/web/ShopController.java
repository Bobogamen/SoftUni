package com.onlineshop.web;

import com.onlineshop.model.entity.Item;
import com.onlineshop.service.CategoryService;
import com.onlineshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final List<Item> cart;

    public ShopController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.cart = new ArrayList<>();
    }

    @GetMapping
    public ModelAndView shop() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allItem", this.itemService.getAllItems());
        modelAndView.addObject("allCategories", this.categoryService.getAllCategories());

        modelAndView.setViewName("shop");

        return modelAndView;
    }

    @GetMapping("/item/{id}")
    public ModelAndView item(@PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView("item");
        modelAndView.addObject("item", this.itemService.getItemById(id));

        return modelAndView;
    }

    @PostMapping("/buy/{id}")
    public String buy(@PathVariable long id, RedirectAttributes redirectAttributes) {

        this.cart.add(this.itemService.getItemById(id));

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/shop/item/{id}";
    }



    @GetMapping("/cart")
    public ModelAndView cart() {

        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cartItems", this.cart);

        return modelAndView;
    }
}
