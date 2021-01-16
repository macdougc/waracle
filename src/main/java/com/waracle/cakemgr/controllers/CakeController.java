package com.waracle.cakemgr.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.waracle.cakemgr.models.Cake;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CakeController {

    @GetMapping("/cakes")
    public Cake cakes() {
        return new Cake();
    }
}
