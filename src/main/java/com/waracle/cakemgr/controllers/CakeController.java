package com.waracle.cakemgr.controllers;

import java.util.List;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CakeController {

    private final CakeRepository repository;

    CakeController(CakeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/cakes")
    List<Cake> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/cakes")
    Cake newCake(@RequestBody Cake newCake) {
        return repository.save(newCake);
    }
}
