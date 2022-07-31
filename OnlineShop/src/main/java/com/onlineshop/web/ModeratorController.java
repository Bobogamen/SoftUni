package com.onlineshop.web;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.service.ItemService;
import com.onlineshop.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;

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
        modelAndView.setViewName("moderator");

        return modelAndView;
    }

    @GetMapping("/moderator/add-item")
    public String addItem() {
        return "add-item";
    }

    @PostMapping("/moderator/add-item")
    public String addItem(@RequestParam() MultipartFile picture, @Valid AddItemDTO addItemDTO,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addItemDTO", addItemDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDTO", bindingResult);

            return "redirect:/users/moderator/add-item";
        }

        long addedItemId = this.itemService.addItem(addItemDTO);

        String pictureURL = getPictureURL(picture, addItemDTO, addedItemId);

        this.itemService.setPictureOfItem(addedItemId, pictureURL);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/moderator";
    }

    private String getPictureURL(MultipartFile picture, AddItemDTO addItemDTO, long addedItemId) throws IOException {
        return this.pictureService.save(picture, addItemDTO.getName() + "_" + addedItemId);
    }

    @GetMapping("/moderator/edit-item/{id}")
    public ModelAndView editItem(@PathVariable long id, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item", this.itemService.getItemById(id));
        modelAndView.setViewName("edit-item");

        return modelAndView;
    }

    @PostMapping("/moderator/edit-item/{id}")
    public String editItem(@PathVariable long id, @RequestParam() MultipartFile picture, AddItemDTO addItemDTO,
                           RedirectAttributes redirectAttributes) throws IOException {

        this.itemService.editItemById(id, addItemDTO);

        if (!picture.isEmpty()) {
            this.pictureService.deletePicture(Path.of(this.itemService.getItemById(id).getPicture()));
            this.itemService.setPictureOfItem(id, getPictureURL(picture, addItemDTO, id));
        }

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
