package com.waracle.cakemgr.controllers;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.services.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefaultController {

    public static final String SAVE_SUCCESS_MESSAGE = "The cake was saved successfully.";
    public static final String SAVE_ERROR_MESSAGE = "The cake could not be saved. Please check the title is unique and the other data is correct.";

    @Autowired
    private final CakeService _cakeService;

    DefaultController(CakeService cakeService) {
        _cakeService = cakeService;
    }

    @GetMapping("/")
    public String showAll(Model model) {
        model.addAttribute("cakes", _cakeService.getCakes());
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
            _cakeService.saveCake(cake);
        }
        catch (Exception exception) {
            model.addAttribute("message", SAVE_ERROR_MESSAGE);
            model.addAttribute("cake", cake);
            return "addCake";
        }

        model.addAttribute("message", SAVE_SUCCESS_MESSAGE);
        return addCakeForm(model);
    }

}
