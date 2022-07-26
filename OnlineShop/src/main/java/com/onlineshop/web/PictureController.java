package com.onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PictureController {

    @GetMapping("/add-file")
    public String addFile() {
        return "add-file";
    }

    @PostMapping("/add-file")
    public String handleFileUpload(@RequestParam("file")MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

//        storageService.store(file);

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded file");

        return "redirect:/add-file";

    }
}
