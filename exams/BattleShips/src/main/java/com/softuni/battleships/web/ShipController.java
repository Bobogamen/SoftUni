package com.softuni.battleships.web;

import com.softuni.battleships.entity.DTO.CreateShipDTO;
import com.softuni.battleships.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }


    @GetMapping("/ships/add")
    public String ships() {
        return "ship-add";
    }


    @ModelAttribute("createShipDTO")
    public CreateShipDTO createShipDTO(){
        return new CreateShipDTO();
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO createShipDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors() || !this.shipService.create(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);

            return "redirect:/ship/add";
        }

        return "redirect:/home";


    }
}
