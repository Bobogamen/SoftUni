package shoppinglist.entity.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AddProductDTO {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 5)
    private String description;

    @Positive
    private float price;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime neededBefore;

    @NotEmpty
    private String category = "";

    public AddProductDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
