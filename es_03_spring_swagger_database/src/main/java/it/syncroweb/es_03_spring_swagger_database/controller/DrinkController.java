package it.syncroweb.es_03_spring_swagger_database.controller;

import it.syncroweb.es_03_spring_swagger_database.dto.DrinkResponseDTO;
import it.syncroweb.es_03_spring_swagger_database.dto.DrinkRequestDTO;
import it.syncroweb.es_03_spring_swagger_database.dto.IngredientCocktailRequestDTO;
import it.syncroweb.es_03_spring_swagger_database.dto.InstructionRequestDTO;
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
    public ResponseEntity<List<DrinkResponseDTO>> getAllDrink(){
        return drinkService.getAllDrink();
    }

    @PostMapping("")
    public ResponseEntity<DrinkResponseDTO> createDrink(@Valid @RequestBody DrinkRequestDTO drinkRequestDTO) throws UnprocessableEntityException {
        DrinkResponseDTO drinkResponseDTO = drinkService.addDrink(drinkRequestDTO);
        return new ResponseEntity<>(drinkResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Drink>> createAllDrink(@RequestBody List<Drink> drinks){
        return drinkService.addAllDrink(drinks);
    }
}
