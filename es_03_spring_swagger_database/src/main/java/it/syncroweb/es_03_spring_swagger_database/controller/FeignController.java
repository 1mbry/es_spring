package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.feign.CocktailClient;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.service.CocktailClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feign")
public class FeignController {

    private static final Logger log = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    private CocktailClient cocktailClient;

    @Autowired
    public CocktailClientService cocktailClientService;

    @GetMapping("/cocktail")
    public ResponseEntity<List<Drink>> getCocktail() throws UnprocessableEntityException {
        log.info("Inizio richiesta CocktailClient.getCocktail()");
        CocktailApiResponse cocktailApiResponse = cocktailClient.getCocktail();
        log.info("Ritorno del body : {}", cocktailApiResponse);
        log.info("Inizio richiesta di FeignController cocktailClientService.addDrinks()");
        List<Drink> drinks = cocktailClientService.addDrinks(cocktailApiResponse);
        if (drinks.isEmpty()){
            throw new UnprocessableEntityException("Errore");
        }
        log.info("Fine richiesta con ritorno di tutti i drinks");
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

}
