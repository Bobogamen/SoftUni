package bg.softuni.com.shoppinglist.web;

import bg.softuni.com.shoppinglist.entity.DTO.AddProductDTO;
import bg.softuni.com.shoppinglist.repository.ProductRepository;
import bg.softuni.com.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController{

    private ProductService productService;

    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @ModelAttribute("addProductDTO")
    public AddProductDTO initAddProductDTO() {
        return new AddProductDTO();
    }

    @GetMapping("/add-product")
    public String addProduct() {
        return "product-add";
    }

    @PostMapping("add-product")
    public String addProduct(@Valid AddProductDTO addProductDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.productService.addProduct(addProductDTO)) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/add-product";
        }

    }

}
