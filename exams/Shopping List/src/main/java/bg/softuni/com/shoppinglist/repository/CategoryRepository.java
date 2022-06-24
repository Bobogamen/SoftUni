package bg.softuni.com.shoppinglist.repository;

import bg.softuni.com.shoppinglist.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
