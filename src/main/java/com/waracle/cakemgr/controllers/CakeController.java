package com.waracle.cakemgr.controllers;

import java.sql.SQLException;
import java.util.List;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/cakes")
class CakeController {

    private final CakeRepository repository;

    CakeController(CakeRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    List<Cake> all() {
        return repository.findAll();
    }

    @PostMapping()
    Cake newCake(@RequestBody Cake newCake) {
        try {
            return repository.save(newCake);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The cake could not be saved. Please check the data is correct and the cake is not already saved.", exception);
        }
    }
}
