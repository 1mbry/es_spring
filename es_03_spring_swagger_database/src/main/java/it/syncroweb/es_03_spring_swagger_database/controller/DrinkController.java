package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.service.CombinedService;
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

    private static final Logger log = LoggerFactory.getLogger(DrinkController.class);

    @Autowired
    private DrinkService drinkService;

    @GetMapping("/getall")
    public ResponseEntity<List<DrinkResponse>> getAllDrink() throws UnprocessableEntityException{
        log.info("Inizio richiesta DrinkController.getAllDrink");
        List<DrinkResponse> drinkResponse = drinkService.getAllDrink();
        log.info("Fine richiesta con body di risposta List<DrinkResponse>");
        return new ResponseEntity<>(drinkResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DrinkResponse> createDrink(@Valid @RequestBody DrinkRequest drinkRequest) throws UnprocessableEntityException {
        log.info("Inizio richiesta DrinkController.createDrink()");
        DrinkResponse drinkResponse = drinkService.addDrink(drinkRequest);
        log.info("Fine richiesta con body di risposta DrinkResponse : {}", drinkResponse);
        return new ResponseEntity<>(drinkResponse, HttpStatus.CREATED);
    }
}
