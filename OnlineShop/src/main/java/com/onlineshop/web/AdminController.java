package com.onlineshop.web;

import com.onlineshop.model.dto.UsersInfoDTO;
import com.onlineshop.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/all-users")
    public ResponseEntity<List<UsersInfoDTO>> getAllUsers() {

        return ResponseEntity.ok(this.adminService.getAllUsers());
    }






}


