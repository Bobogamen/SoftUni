package com.onlineshop.web;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.model.entity.Item;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.ItemService;
import com.onlineshop.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users/")
public class ModeratorController {

    private final ItemService itemService;
    private final PictureService pictureService;

    public ModeratorController(ItemService itemService, PictureService pictureService) {
        this.itemService = itemService;
        this.pictureService = pictureService;
    }

    @ModelAttribute("addItemDTO")
    public AddItemDTO addItemDTO() {
        return new AddItemDTO();
    }

    @GetMapping("/moderator")
    public ModelAndView moderator() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("allItems", this.itemService.getAllItems());
        modelAndView.setViewName("moderator.html");

        return modelAndView;
    }


    @GetMapping("/moderator/add-item")
    public String addItem() {
        return "add-item";
    }

    @PostMapping("/moderator/add-item")
    public String addItem(@RequestParam("picture") MultipartFile picture, @Valid AddItemDTO addItemDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addItemDTO", addItemDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDTO", bindingResult);

            return "redirect:/users/moderator/add-item";
        }

        long addedItemId = this.itemService.addItem(addItemDTO);

        String pictureURL = this.pictureService.save(picture, addItemDTO.getName() + "_" + addedItemId);

        this.itemService.setPictureOfItem(addedItemId, pictureURL);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/moderator";
    }

    @PostMapping("/moderator/delete/{id}")
    public String deleteItem(@PathVariable long id, RedirectAttributes redirectAttributes) {

        this.itemService.deleteItemById(id);

        redirectAttributes.addFlashAttribute("delete", true);

        return "redirect:/users/moderator";
    }
}
