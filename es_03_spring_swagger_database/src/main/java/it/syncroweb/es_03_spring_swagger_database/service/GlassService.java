package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
import it.syncroweb.es_03_spring_swagger_database.model.Glass;
import it.syncroweb.es_03_spring_swagger_database.repository.GlassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlassService {

    @Autowired
    public GlassRepository glassRepository;

    //Get all glass
    public ResponseEntity<List<Glass>> getAllGlass(){
        try {
            List<Glass> glasses = new ArrayList<>();
            glasses = glassRepository.findAll();
            return new ResponseEntity<>(glasses, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //Post create one glass
    public ResponseEntity<Glass> addGlass(Glass glass){
        try{
            Glass glass1 = glassRepository.save(glass);
            return new ResponseEntity<>(glass1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
