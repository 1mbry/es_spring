package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.IngredientCocktail;
import it.syncroweb.es_03_spring_swagger_database.service.IngredientCocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ingredient-cocktail")
public class IngredientCocktailController {

    @Autowired
    public IngredientCocktailService ingredientCocktailService;

    @GetMapping("/")
    public ResponseEntity<List<IngredientCocktail>> getIngredients(){
        return ingredientCocktailService.getIngredients();
    }
    @PostMapping("/all")
    public ResponseEntity<List<IngredientCocktail>> createAllBooze(@RequestBody List<IngredientCocktail> ingredients){
        return ingredientCocktailService.addAllBooze(ingredients);
    }
}
