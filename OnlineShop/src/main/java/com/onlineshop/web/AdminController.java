package com.onlineshop.web;

import com.onlineshop.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public ModelAndView admin() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allUsers", this.adminService.getAllUsers());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable long id, RedirectAttributes redirectAttributes) {

        this.adminService.deleteUser(id);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/admin";
    }

    @PostMapping("/admin/make-admin/{id}")
    public String makeAdmin(@PathVariable long id, RedirectAttributes redirectAttributes) {

        boolean result = this.adminService.makeAdminByUserId(id);

        if (!result) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
        }

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/admin";
    }

    @PostMapping("/admin/make-moderator/{id}")
    public String makeModerator(@PathVariable long id, RedirectAttributes redirectAttributes) {

        boolean result = this.adminService.makeModeratorById(id);

        if (!result) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
        }

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/users/admin";
    }
}


