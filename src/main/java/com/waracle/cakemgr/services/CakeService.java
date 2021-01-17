package com.waracle.cakemgr.services;

import java.util.List;

import com.waracle.cakemgr.entities.Cake;

public interface CakeService {

    List<Cake> getCakes();
    Cake saveCake(Cake cake) throws Exception;
}
