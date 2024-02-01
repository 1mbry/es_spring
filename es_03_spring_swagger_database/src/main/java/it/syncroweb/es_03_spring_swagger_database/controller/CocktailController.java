package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.model.Cocktail;
import it.syncroweb.es_03_spring_swagger_database.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class CocktailController {

    @Autowired
    CocktailService cocktailService;

    @GetMapping("/cocktails")
    public ResponseEntity<List<Cocktail>> getAllCocktails(){
        return cocktailService.getAllCocktails();
    }
}
