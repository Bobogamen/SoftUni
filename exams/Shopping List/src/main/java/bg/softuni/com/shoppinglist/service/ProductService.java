package bg.softuni.com.shoppinglist.service;

import bg.softuni.com.shoppinglist.entity.DTO.AddProductDTO;
import bg.softuni.com.shoppinglist.entity.Product;
import bg.softuni.com.shoppinglist.entity.User;
import bg.softuni.com.shoppinglist.repository.ProductRepository;
import bg.softuni.com.shoppinglist.repository.UserRepository;
import bg.softuni.com.shoppinglist.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;

    public ProductService(ProductRepository productRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }



    public boolean addProduct(AddProductDTO addProductDTO) {

        Optional<Product> productOpt = this.productRepository.findByName(addProductDTO.getName());
        Optional<User> owner = this.userRepository.findById(loggedUser.getId());

        if (productOpt.isPresent()) {
            return true;
        }

        Product product = new Product();
        product.setName(addProductDTO.getName());
        product.setDescription(addProductDTO.getDescription());
        product.setNeededBefore(addProductDTO.getNeededBefore());
        product.setPrice(addProductDTO.getPrice());
        product.setOwner(owner.get());
        product.setCategory(addProductDTO.getCategory());
        

    }
}
