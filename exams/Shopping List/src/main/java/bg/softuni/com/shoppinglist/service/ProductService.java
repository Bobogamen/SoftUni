package bg.softuni.com.shoppinglist.service;

import bg.softuni.com.shoppinglist.entity.Category;
import bg.softuni.com.shoppinglist.entity.DTO.AddProductDTO;
import bg.softuni.com.shoppinglist.entity.Product;
import bg.softuni.com.shoppinglist.entity.User;
import bg.softuni.com.shoppinglist.entity.enums.CategoryType;
import bg.softuni.com.shoppinglist.repository.CategoryRepository;
import bg.softuni.com.shoppinglist.repository.ProductRepository;
import bg.softuni.com.shoppinglist.repository.UserRepository;
import bg.softuni.com.shoppinglist.session.LoggedUser;
import bg.softuni.com.shoppinglist.view.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, LoggedUser loggedUser, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    public boolean productUniqueName(AddProductDTO addProductDTO) {

        Optional<Product> productOpt = this.productRepository.findByName(addProductDTO.getName());

        return productOpt.isPresent();
    }

    public void addProduct(AddProductDTO addProductDTO) {

        CategoryType type = CategoryType.OTHER;

        for (CategoryType value : CategoryType.values()) {
            if (addProductDTO.getCategory().equals(value.name())) {
                type = CategoryType.valueOf(addProductDTO.getCategory());
                break;
            }
        }

        Category category = this.categoryRepository.findByName(type);
        User owner = this.userRepository.findById(loggedUser.getId());


        Product product = new Product();

        product.setName(addProductDTO.getName());
        product.setDescription(addProductDTO.getDescription());
        product.setNeededBefore(addProductDTO.getNeededBefore());
        product.setPrice(addProductDTO.getPrice());
        product.setOwner(owner);
        product.setCategory(category);

        this.productRepository.save(product);

    }

    public List<Product> getProducts(long id) {
        return productRepository.findProductsByOwner_Id(id);
    }

    public ShoppingList getProducts() {
        return new ShoppingList(this.productRepository.findAllShoppingItems());
    }


    public void buyAllProducts(long id) {
        this.productRepository.deleteAllByOwner_Id(id);
    }

    public void buyProduct(long id) {
        this.productRepository.deleteProductById(id);
    }
}
