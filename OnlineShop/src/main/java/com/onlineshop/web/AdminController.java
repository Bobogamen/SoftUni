package com.onlineshop.web;

import com.onlineshop.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.setViewName("admin.html");
        return modelAndView;
    }

    @PostMapping("/admin/delete/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {

        this.adminService.deleteUser(id);

        return admin();
    }






}


