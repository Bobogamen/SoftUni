package bg.softuni.com.shoppinglist.repository;

import bg.softuni.com.shoppinglist.entity.Product;
import bg.softuni.com.shoppinglist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findProductsByOwner_Id(long id);

}
