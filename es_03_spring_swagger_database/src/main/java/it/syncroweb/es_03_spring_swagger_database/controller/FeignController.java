package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.CocktailResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.feign.CocktailClient;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.service.CocktailClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private CocktailClient cocktailClient;

    @Autowired
    public CocktailClientService cocktailClientService;

    @GetMapping("/cocktail")
    public ResponseEntity<List<Drink>> getCocktail()throws UnprocessableEntityException {
        CocktailApiResponse cocktailApiResponse = cocktailClient.getCocktail();
        List<Drink> drinks = cocktailClientService.addDrinks(cocktailApiResponse);
        return new ResponseEntity<>(drinks, HttpStatus.CREATED);
    }

}
