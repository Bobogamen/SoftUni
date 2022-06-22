package bg.softuni.com.shoppinglist.seeders;

import bg.softuni.com.shoppinglist.entity.Category;
import bg.softuni.com.shoppinglist.entity.enums.CategoryType;
import bg.softuni.com.shoppinglist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class CategorySeeder implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryType.values()).map(Category::new).toList();


            this.categoryRepository.saveAll(categories);
        }

    }
}
