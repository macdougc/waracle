package com.waracle.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CakeRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Cake("Bilbo", "Baggins", "bilbo@lotr.com")));
            log.info("Preloading " + repository.save(new Cake("Frodo", "Baggins", "frodo@lotr.com")));
        };
    }
}
