package com.waracle.cakemgr.controllers;

import java.util.List;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.services.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/cakes")
public class CakeController {

    public static final String SAVE_FAILURE_MESSAGE = "The cake could not be saved. Please check the data is correct and the cake is not already saved.";

    @Autowired
    private final CakeService _cakeService;

    CakeController(CakeService cakeService) {
        this._cakeService = cakeService;
    }

    @GetMapping()
    List<Cake> all() {
        return _cakeService.getCakes();
    }

    @PostMapping()
    Cake newCake(@RequestBody Cake newCake) {
        try {
            return _cakeService.saveCake(newCake);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SAVE_FAILURE_MESSAGE, exception);
        }
    }
}
