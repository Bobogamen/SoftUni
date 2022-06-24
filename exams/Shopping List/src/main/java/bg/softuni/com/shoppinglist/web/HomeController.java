package bg.softuni.com.shoppinglist.web;

import bg.softuni.com.shoppinglist.entity.Product;
import bg.softuni.com.shoppinglist.service.ProductService;
import bg.softuni.com.shoppinglist.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        List<Product> userProducts = this.productService.getProducts(loggedUser.getId());

        Map<String, Float> food = new LinkedHashMap<>();
        Map<String, Float> drink = new LinkedHashMap<>();
        Map<String, Float> household = new LinkedHashMap<>();
        Map<String, Float> other = new LinkedHashMap<>();

        for (Product product : userProducts) {

            String category = product.getCategory().getName().name();
            float price = product.getPrice();
            String name = product.getName();

            switch (category) {
                case "FOOD" -> food.put(name, price);
                case "DRINK" -> drink.put(name, price);
                case "HOUSEHOLD" -> household.put(name, price);
                case "OTHER" -> other.put(name, price);
            }
        }

        float totalPrice = 0.0f;

        for (Float value : food.values()) {
            totalPrice += value;
        }

        for (Float value : drink.values()) {
            totalPrice += value;
        }
        for (Float value : household.values()) {
            totalPrice += value;
        }
        for (Float value : other.values()) {
            totalPrice += value;
        }

        model.addAttribute("food", food);
        model.addAttribute("drink", drink);
        model.addAttribute("household", household);
        model.addAttribute("other", other);
        model.addAttribute("totalPrice", totalPrice);



        return "/home";
    }


}
