package com.waracle.cakemgr.services;

import java.util.List;

import com.waracle.cakemgr.entities.Cake;
import com.waracle.cakemgr.entities.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebCakeService implements CakeService {

    @Autowired
    private final CakeRepository _cakeRepository;

    public WebCakeService(CakeRepository cakeRepository){
        _cakeRepository = cakeRepository;
    }

    @Override
    public List<Cake> getCakes() {
        return _cakeRepository.findAll();
    }

    @Override
    public Cake saveCake(Cake cake) {
        return _cakeRepository.save(cake);
    }
}
