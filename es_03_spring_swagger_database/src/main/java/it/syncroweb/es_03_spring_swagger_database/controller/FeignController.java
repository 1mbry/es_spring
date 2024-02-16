package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.CocktailApiResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.feign.CocktailClient;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.service.CocktailClientService;
import it.syncroweb.es_03_spring_swagger_database.service.CombinedService;
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

    private static final Logger logger = LoggerFactory.getLogger(CombinedService.class);

    @Autowired
    private CocktailClient cocktailClient;

    @Autowired
    public CocktailClientService cocktailClientService;

    @GetMapping("/cocktail")
    public ResponseEntity<List<Drink>> getCocktail() throws UnprocessableEntityException {
        logger.info("Inizio richiesta cocktailClient.getCocktail()");
        CocktailApiResponse cocktailApiResponse = cocktailClient.getCocktail();
        logger.info("Ritorno del body JSON");
        logger.info("Inizio richiesta di FeignController addDrinks()");
        List<Drink> drinks = cocktailClientService.addDrinks(cocktailApiResponse);
        if (drinks.isEmpty()){
            throw new UnprocessableEntityException("Errore");
        }
        logger.info("Fine richiesta con body di risposta List<Drink>");
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

}
