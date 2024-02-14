package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponse;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequest;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.service.DrinkService;
import jakarta.validation.Valid;
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

    @Autowired
    public DrinkService drinkService;
    @GetMapping("/getall")
    public ResponseEntity<List<DrinkResponse>> getAllDrink() throws UnprocessableEntityException{
        List<DrinkResponse> drinkResponse = drinkService.getAllDrink();
        return new ResponseEntity<>(drinkResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DrinkResponse> createDrink(@Valid @RequestBody DrinkRequest drinkRequest) throws UnprocessableEntityException {
        DrinkResponse drinkResponse = drinkService.addDrink(drinkRequest);
        return new ResponseEntity<>(drinkResponse, HttpStatus.CREATED);
    }
}
