package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Cocktail;
import it.syncroweb.es_03_spring_swagger_database.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CocktailService {

    @Autowired
    public CocktailRepository cocktailRepository;

    //Get All Cocktails
    public ResponseEntity<List<Cocktail>> getAllCocktails(){
        try{
            List<Cocktail> cocktails = new ArrayList<Cocktail>();
            cocktails = cocktailRepository.findAll();
            return new ResponseEntity<>(cocktails, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }
}
