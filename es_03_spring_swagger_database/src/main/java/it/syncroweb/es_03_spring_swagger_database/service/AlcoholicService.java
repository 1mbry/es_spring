package it.syncroweb.es_03_spring_swagger_database.service;

import it.syncroweb.es_03_spring_swagger_database.model.Alcoholic;
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

    public ResponseEntity<List<Alcoholic>> addAllAlcoholic(List<Alcoholic> alcoholics) {
        try {
            List<Alcoholic> alcoholics1 = alcoholicRepository.saveAll(alcoholics);
            return new ResponseEntity<>(alcoholics1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
