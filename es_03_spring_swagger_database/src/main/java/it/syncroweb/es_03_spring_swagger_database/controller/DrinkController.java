package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.service.DrinkService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/api/drink")
public class DrinkController {

    private static final Logger logger = LoggerFactory.getLogger(DrinkController.class);

    @Autowired
    private DrinkService drinkService;

    /*@GetMapping("/one")
    public ResponseEntity<DrinkResponse> getOne(Integer id){
        return new ResponseEntity<>(drinkService.getDrinkById(id), HttpStatus.OK);
    }*/

    @GetMapping("/getall")
    public ResponseEntity<List<DrinkResponse>> getAllDrink() throws UnprocessableEntityException{
        logger.info("Inizio richiesta Drink Controller getAllDrink");
        List<DrinkResponse> drinkResponse = drinkService.getAllDrink();
        logger.info("Fine richiesta con body di risposta List<DrinkResponse>");
        return new ResponseEntity<>(drinkResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DrinkResponse> createDrink(@Valid @RequestBody DrinkRequest drinkRequest) throws UnprocessableEntityException {
        logger.info("Inizio richiesta Drink Controller createDrink con body di richiesta DrinkRequest");
        DrinkResponse drinkResponse = drinkService.addDrink(drinkRequest);
        logger.info("Fine richiesta con body di risposta DrinkResponse");
        return new ResponseEntity<>(drinkResponse, HttpStatus.CREATED);
    }
}
