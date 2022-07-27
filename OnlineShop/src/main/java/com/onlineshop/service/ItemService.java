package com.onlineshop.service;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.model.entity.Category;
import com.onlineshop.model.entity.Item;
import com.onlineshop.repository.CategoryRepository;
import com.onlineshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public long addItem(AddItemDTO addItemDTO) {

        Category category = this.categoryRepository.getByName(addItemDTO.getCategory());

        Item item = new Item(addItemDTO.getName(), addItemDTO.getDescription(), addItemDTO.getPrice(), category);

        this.itemRepository.save(item);

        return item.getId();
    }

    public void setPictureOfItem(long id, String picture) {
        Item itemById = this.itemRepository.getItemById(id);
        itemById.setPicture(picture);

        this.itemRepository.save(itemById);
    }
}
