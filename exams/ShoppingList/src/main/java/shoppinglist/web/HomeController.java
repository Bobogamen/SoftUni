package shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shoppinglist.service.ProductService;
import shoppinglist.session.LoggedUser;

@Controller
public class HomeController {

    private LoggedUser loggedUser;
    private ProductService productService;

    public HomeController(LoggedUser loggedUser, ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        model.addAttribute("products", this.productService.getProducts(loggedUser.getId()));


        return "/home";
    }
}
