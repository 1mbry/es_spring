package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/drink")
public class DrinkController {

    @Autowired
    public DrinkService drinkService;

    @GetMapping("/all")
    public ResponseEntity<List<Drink>> getAllDrink(){
        return drinkService.getAllDrink();
    }

    @PostMapping("")
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink){
        return drinkService.addDrink(drink);
    }
}
