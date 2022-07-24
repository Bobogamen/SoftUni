package com.onlineshop.web;

import com.onlineshop.model.dto.UsersInfoDTO;
import com.onlineshop.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin.html");
        return modelAndView;
    }

    @GetMapping("/admin/all-users")
    public ResponseEntity<List<UsersInfoDTO>> getAllUsers() {

        return ResponseEntity.ok(this.adminService.getAllUsers());
    }

    @GetMapping("/admin/delete/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {

        this.adminService.deleteUser(id);

        return admin();
    }






}


