package com.onlineshop.web;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.model.entity.Category;
import com.onlineshop.model.user.ShopUserDetails;
import com.onlineshop.service.CategoryService;
import com.onlineshop.service.ItemService;
import com.onlineshop.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/users/")
public class ModeratorController {

    private final ItemService itemService;
    private final PictureService pictureService;
    private final CategoryService categoryService;


    public ModeratorController(ItemService itemService, PictureService pictureService, CategoryService categoryService) {
        this.itemService = itemService;
        this.pictureService = pictureService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("addItemDTO")
    public AddItemDTO addItemDTO() {
        return new AddItemDTO();
    }

    @ModelAttribute("allCategories")
    public List<Category> allCategories() {
        return this.categoryService.getAllCategories();
    }



    @GetMapping("/moderator")
    public ModelAndView moderator() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("allItems", this.itemService.getAllItems());
        modelAndView.addObject("allCategories", allCategories());
        modelAndView.addObject("itemsCountByCategory", this.itemService.itemsByCategory());
        modelAndView.setViewName("moderator");

        return modelAndView;
    }

    @GetMapping("/moderator/add-item")
    public String addItem(@AuthenticationPrincipal ShopUserDetails user, Model model) {

        if (user.getAuthorities().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        model.addAttribute("categories", allCategories());

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
    public ModelAndView editItem(@PathVariable long id, @AuthenticationPrincipal ShopUserDetails user, RedirectAttributes redirectAttributes) {

        if (user.getAuthorities().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

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

    @PostMapping("/moderator/delete-item/{id}")
    public String deleteItem(@PathVariable long id, RedirectAttributes redirectAttributes) throws IOException {

        this.pictureService.deletePicture(this.itemService.getPicturePathByItemId(id));
        this.itemService.deleteItemById(id);

        redirectAttributes.addFlashAttribute("delete", true);

        return "redirect:/users/moderator";
    }

    @GetMapping("/moderator/add-category")
    public String addCategory(@AuthenticationPrincipal ShopUserDetails user) {

        if (user.getAuthorities().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return "add-category";
    }

    @PostMapping("/moderator/add-category")
    public String addItem(String name,RedirectAttributes redirectAttributes) {

        this.categoryService.addCategory(name);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/moderator";
    }

    @PostMapping("/moderator/delete-category/{id}")
    public String deleteCategory(@PathVariable long id, RedirectAttributes redirectAttributes) {

        this.categoryService.deleteCategoryById(id);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/moderator";
    }
}
