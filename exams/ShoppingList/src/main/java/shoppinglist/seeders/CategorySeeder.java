package shoppinglist.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shoppinglist.entity.Category;
import shoppinglist.entity.enums.CategoryType;
import shoppinglist.repository.CategoryRepository;

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

        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryType.values()).map(Category::new).toList();

            this.categoryRepository.saveAll(categories);
        }
    }
}
