package bg.softuni.com.shoppinglist.repository;

import bg.softuni.com.shoppinglist.entity.Product;
import bg.softuni.com.shoppinglist.view.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findProductsByOwner_Id(long id);

    @Query("SELECT new bg.softuni.com.shoppinglist.view.ShoppingItem(p.id, p.name, p.price, c.name) " +
            "FROM Product p " +
            "JOIN p.category c")
    List<ShoppingItem> findAllShoppingItems();

    void deleteAllByOwner_Id(long owner_id);

    void deleteProductById(long id);
}
