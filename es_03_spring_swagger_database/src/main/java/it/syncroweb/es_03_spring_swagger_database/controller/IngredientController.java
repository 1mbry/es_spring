package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import it.syncroweb.es_03_spring_swagger_database.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

    @Autowired
    public IngredientService ingredientService;

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredient(){
        return ingredientService.getAllIngredient();
    }

    @PostMapping("")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.addIngredient(ingredient);
    }
}
