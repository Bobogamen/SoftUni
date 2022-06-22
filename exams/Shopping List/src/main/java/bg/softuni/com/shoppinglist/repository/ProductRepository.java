package bg.softuni.com.shoppinglist.repository;

import bg.softuni.com.shoppinglist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
