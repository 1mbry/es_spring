package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.request.CocktailRequest;
import it.syncroweb.es_03_spring_swagger_database.model.Cocktail;
import it.syncroweb.es_03_spring_swagger_database.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CocktailController {

    @Autowired
    CocktailService cocktailService;

    @GetMapping("/cocktails")
    public ResponseEntity<List<Cocktail>> getAllCocktails(){
        return cocktailService.getAllCocktails();
    }

    @PostMapping("/cocktails")
    public ResponseEntity<Cocktail> createCocktail(@RequestBody CocktailRequest cocktailRequest){
        return cocktailService.createCocktail(cocktailRequest);
    }

    @PostMapping("/cocktails/all")
    public ResponseEntity<List<Cocktail>> createCocktails(@RequestBody List<CocktailRequest> cocktailRequest){
        return cocktailService.createAllCocktails(cocktailRequest);
    }
    @PutMapping("/cocktails/{id}")
    public ResponseEntity<Cocktail> updateCocktail(@PathVariable("id") Integer id, @RequestBody Cocktail cocktail){
        return cocktailService.updateCocktail(id, cocktail);
    }
    //@DeleteMapping
}
