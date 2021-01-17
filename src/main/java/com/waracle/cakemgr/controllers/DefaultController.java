package com.waracle.cakemgr.controllers;

import java.util.List;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
class DefaultController {

    private final CakeRepository repository;

    DefaultController(CakeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String showAll(Model model) {
        model.addAttribute("cakes", repository.findAll());
        return "cakeList";
    }

    @GetMapping("/addCake")
    public String addCakeForm(Model model) {
        model.addAttribute("cake", new Cake());
        return "addCake";
    }

    @PostMapping("/addCake")
    public String addCakeSubmit(@ModelAttribute Cake cake, Model model) {
        try {
            repository.save(cake);
        }
        catch (Exception exception) {
            model.addAttribute("message", "The cake could not be saved. Please check the title is unique and the other data is correct.");
            model.addAttribute("cake", cake);
            return "addCake";
        }

        model.addAttribute("message", "The cake was saved successfully");
        return addCakeForm(model);
    }

}
