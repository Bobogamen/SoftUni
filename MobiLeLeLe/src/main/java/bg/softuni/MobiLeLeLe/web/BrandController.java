package bg.softuni.MobiLeLeLe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    @GetMapping("/brands/all")
    public String allBrands() {
        return "brands";



    }
}
