package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.interfaz.CargoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/cargos")
@RestController
public class CargoController {
    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("")
    public List<String> getAllCargos() throws Exception {
        try {
            return cargoService.listCargos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
