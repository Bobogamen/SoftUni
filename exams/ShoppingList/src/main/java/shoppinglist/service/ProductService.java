package shoppinglist.service;

import org.springframework.stereotype.Service;
import shoppinglist.entity.Category;
import shoppinglist.entity.DTO.AddProductDTO;
import shoppinglist.entity.Product;
import shoppinglist.entity.User;
import shoppinglist.entity.enums.CategoryType;
import shoppinglist.repository.CategoryRepository;
import shoppinglist.repository.ProductRepository;
import shoppinglist.repository.UserRepository;
import shoppinglist.session.LoggedUser;
import shoppinglist.view.ShoppingList;

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

    public ShoppingList getProducts(long ownerId) {

        return new ShoppingList(this.productRepository.findAllShoppingItems(), ownerId);
    }


    public void buyAllProducts(long id) {
        this.productRepository.deleteAllByOwner(this.userRepository.findById(id));
    }

    public void buyProduct(long id) {
        this.productRepository.deleteProductById(id);
    }
}
