package bg.softuni.com.shoppinglist.web;

import bg.softuni.com.shoppinglist.service.ProductService;
import bg.softuni.com.shoppinglist.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


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

        model.addAttribute("products", this.productService.getProducts());


        return "/home";
    }
}
