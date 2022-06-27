package shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shoppinglist.entity.Product;
import shoppinglist.entity.User;
import shoppinglist.view.ShoppingItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("SELECT new shoppinglist.view.ShoppingItem(p.id, p.name, p.price, c.name) " +
            "FROM Product p " +
            "JOIN p.category c")
    List<ShoppingItem> findAllShoppingItems();

    void deleteAllByOwner(User owner);

    void deleteProductById(long id);
}
