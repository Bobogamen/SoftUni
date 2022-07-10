package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StyleSeeder implements CommandLineRunner {

    private StyleRepository styleRepository;

    @Autowired
    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.styleRepository.count() == 0) {

            List<Style> styles = Arrays.stream(StyleEnum.values()).map(Style::new).toList();

            this.styleRepository.saveAll(styles);
        }
    }
}
