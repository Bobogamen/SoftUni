package com.onlineshop.web;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.ItemService;
import com.onlineshop.service.PictureService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/users/")
public class ModeratorController {

    private final ItemService itemService;
    private final PictureService pictureService;

    public ModeratorController(ItemService itemService, PictureService pictureService) {
        this.itemService = itemService;
        this.pictureService = pictureService;
    }

    @GetMapping("/moderator")
    public String moderator() {
        return "moderator";
    }

    @ModelAttribute("addItemDTO")
    public AddItemDTO addItemDTO() {
        return new AddItemDTO();
    }

    @PostMapping("/moderator/add-item")
    public String addItem(@RequestParam("picture") MultipartFile picture, @Valid AddItemDTO addItemDTO,
                          @AuthenticationPrincipal ShopUserDetails user,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addItemDTO", addItemDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDTO", bindingResult);

            return "redirect:/users/moderator";
        }

        long addedItemId = this.itemService.addItem(addItemDTO);

        String pictureURL = this.pictureService.save(picture, addItemDTO.getName() + "_" + addedItemId);

        this.itemService.setPictureOfItem(addedItemId, pictureURL);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/moderator";
    }
}
