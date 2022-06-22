package com.softuni.battleships.service;

import com.softuni.battleships.entity.Category;
import com.softuni.battleships.entity.DTO.CreateShipDTO;
import com.softuni.battleships.entity.Ship;
import com.softuni.battleships.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public boolean create(CreateShipDTO createShipDTO) {

        Optional<Ship> shipOpt = this.shipRepository.findByName(createShipDTO.getName());

        if (shipOpt.isPresent()) {
            return false;
        }

        int category = createShipDTO.getCategory();

        Category category1 = new Category()

        Ship ship = new Ship();

        ship.setName(createShipDTO.getName());
        ship.setHealth(createShipDTO.getHealth());
        ship.setPower(createShipDTO.getPower());
        ship.setCreated(createShipDTO.getCreated());
        ship.setCategory();




        return true;
    }
}
