package com.onlineshop.service;

import com.onlineshop.model.dto.AddItemDTO;
import com.onlineshop.model.entity.Category;
import com.onlineshop.model.entity.Item;
import com.onlineshop.repository.CategoryRepository;
import com.onlineshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public long addItem(AddItemDTO addItemDTO) {

        Category category = getCategory(addItemDTO);

        Item item = new Item(addItemDTO.getName(), addItemDTO.getDescription(), addItemDTO.getPrice(), category);

        this.itemRepository.save(item);

        return item.getId();
    }

    private Category getCategory(AddItemDTO addItemDTO) {
        return this.categoryRepository.getByName(addItemDTO.getCategory());
    }

    public void setPictureOfItem(long id, String picture) {
        Item itemById = this.itemRepository.getItemById(id);
        itemById.setPicture(picture);

        this.itemRepository.save(itemById);
    }

    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    public void deleteItemById(long id) {
        this.itemRepository.deleteById(id);
    }

    public Item getItemById(long id) {
        return this.itemRepository.getItemById(id);
    }

    public void editItemById(long id, AddItemDTO addItemDTO) {
        Item itemById = this.itemRepository.getItemById(id);

        itemById.setName(addItemDTO.getName());
        itemById.setDescription(addItemDTO.getDescription());
        itemById.setPrice(addItemDTO.getPrice());
        itemById.setDiscount(addItemDTO.getDiscount());
        itemById.setCategory(getCategory(addItemDTO));

        this.itemRepository.save(itemById);
    }
}
