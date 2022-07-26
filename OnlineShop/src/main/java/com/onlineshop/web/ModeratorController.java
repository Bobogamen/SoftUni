package com.onlineshop.web;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.ItemService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ModeratorController {

    private final ItemService itemService;

    public ModeratorController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/users/moderator")
    public String moderator() {
        return "moderator";
    }

    @ModelAttribute("addItemDto")
    public AddItemDTO addItemDTO() {
        return new AddItemDTO();
    }

    @GetMapping("/users/moderator/add-item")
    public String addItem() {

        return "add-item";
    }

    @PostMapping("/users/moderator/add-item")
    public String addItem(@Valid AddItemDTO addItemDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addItemDTO", addItemDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDto", bindingResult);

            return "redirect:/users/moderator/add-item";
        }

        this.itemService.addItem(addItemDTO);

        return "redirect:/users/moderator";
    }
}
