package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Drink;
import it.syncroweb.es_03_spring_swagger_database.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {

    @Autowired
    public DrinkRepository drinkRepository;

    //Get all drink
    public ResponseEntity<List<Drink>> getAllDrink(){
        try {
            List<Drink> drinks = new ArrayList<>();
            drinks = drinkRepository.findAll();
            return new ResponseEntity<>(drinks, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one drink
    public ResponseEntity<Drink> addDrink(Drink drink){
        try{
            Drink drink1 = drinkRepository.save(drink);
            return new ResponseEntity<>(drink1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
