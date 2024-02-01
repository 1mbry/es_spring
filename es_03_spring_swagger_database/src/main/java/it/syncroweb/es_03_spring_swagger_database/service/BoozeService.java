package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Booze;
import it.syncroweb.es_03_spring_swagger_database.repository.BoozeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoozeService {

    @Autowired
    public BoozeRepository boozeRepository;

    //Get all booze
    public ResponseEntity<List<Booze>> getAllBooze(){
        try {
            List<Booze> boozes = new ArrayList<>();
            boozes = boozeRepository.findAll();
            return new ResponseEntity<>(boozes, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one booze
    public ResponseEntity<Booze> addBooze(Booze booze){
        try{
            Booze booze1 = boozeRepository.save(booze);
            return new ResponseEntity<>(booze1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
