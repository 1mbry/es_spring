package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Cocktail;
import it.syncroweb.es_03_spring_swagger_database.repository.AlcoholicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlcoholicService {

    @Autowired
    public AlcoholicRepository alcoholicRepository;

    //Get all alcoholic
    public ResponseEntity<List<Alcoholic>> getAllAlcoholic(){
        try {
            List<Alcoholic> alcoholics = new ArrayList<>();
            alcoholics = alcoholicRepository.findAll();
            return new ResponseEntity<>(alcoholics, HttpStatus.OK);
            } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one alcoholic
    public ResponseEntity<Alcoholic> addAlcoholic(Alcoholic alcoholic){
        try{
            Alcoholic alcoholic1 = alcoholicRepository.save(alcoholic);
            return new ResponseEntity<>(alcoholic1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
