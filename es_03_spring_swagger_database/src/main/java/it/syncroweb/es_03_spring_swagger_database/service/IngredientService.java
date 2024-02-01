package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Ingredient;
import it.syncroweb.es_03_spring_swagger_database.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    public IngredientRepository ingredientRepository;

    //Get all ingredient
    public ResponseEntity<List<Ingredient>> getAllIngredient(){
        try {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients = ingredientRepository.findAll();
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one ingredient
    public ResponseEntity<Ingredient> addIngredient(Ingredient ingredient){
        try{
            Ingredient ingredient1 = ingredientRepository.save(ingredient);
            return new ResponseEntity<>(ingredient1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
