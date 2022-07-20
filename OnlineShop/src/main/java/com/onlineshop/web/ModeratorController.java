package com.onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModeratorController {

    @GetMapping("/moderator")
    public String moderator() {
        return "moderator";
    }
}