package shoppinglist.web;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppinglist.entity.DTO.AddProductDTO;
import shoppinglist.repository.ProductRepository;
import shoppinglist.service.ProductService;
import shoppinglist.session.LoggedUser;

import javax.validation.Valid;

@Controller
public class ProductController{
    private ProductService productService;
    private LoggedUser loggedUser;
    private ProductRepository productRepository;

    public ProductController(ProductService productService, LoggedUser loggedUser, ProductRepository productRepository) {
        this.productService = productService;
        this.loggedUser = loggedUser;
        this.productRepository = productRepository;
    }
    @ModelAttribute("addProductDTO")
    public AddProductDTO initAddProductDTO() {
        return new AddProductDTO();
    }

    @GetMapping("/add-product")
    public String addProduct() {

        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        return "product-add";
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid AddProductDTO addProductDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || this.productService.productUniqueName(addProductDTO)) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/add-product";
        }

        productService.addProduct(addProductDTO);

        return "redirect:/home";
    }
    @Transactional
    @GetMapping("/buy-all")
    public String buyAll() {
        if (this.loggedUser.getId() == 0) {
            return "redirect:/";
        }

        this.productService.buyAllProducts(loggedUser.getId());

        return "redirect:/home";
    }

    @Transactional
    @GetMapping("/buy/{id}")
    public String buy(@PathVariable long id) {
        if (this.loggedUser.getId() == 0) {
            return "redirect:/";
        }

        this.productService.buyProduct(id);

        return "redirect:/home";


    }


}
